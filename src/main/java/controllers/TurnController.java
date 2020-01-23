package controllers;

import model.fields.Field;
import model.fields.notOwnable.ChanceField;
import model.fields.ownable.Property;
import model.fields.ownable.Ownable;
import model.chancecard.ChanceCard;
import model.language.Language;
import model.Player;

public class TurnController {

    private GUIController guiController;
    private MovementController movementController;
    private Calculator calculator;
    private int playerIndex;
    private boolean playerBankrupt = false;

    public void startGame() {
        guiController = new GUIController();
        guiController.initGUIPlayers();
        movementController = new MovementController(guiController.getNumberOfPlayers());
        calculator = new Calculator();

        for (int i = 0; i < guiController.getNumberOfPlayers(); i++) {
            movementController.getPlayers()[i].setName(guiController.getName(i));
            guiController.teleportPlayerGUI(i, 0);
        }
    }

    //hvorfor står der "throws InterruptedException"
    //fra javadocs: Causes the currently executing thread to sleep (temporarily cease execution) for the specified number of milliseconds. The thread does not lose ownership of any monitors.
    public void playGame() throws InterruptedException {
        for (playerIndex = 0; guiController.getNumberOfPlayers() > 1; playerIndex++) {
            guiController.getUserResponse(playerIndex);

            //hvis man er i fængsel
            if (movementController.getPlayers()[playerIndex].getInJail()) {
                jailBailOut(movementController.getPlayers()[playerIndex]);
            }

            guiController.rollButtonGUI();
            model.Player player = movementController.makeMove(playerIndex);
            int die1 = movementController.getLatestRoll()[0];
            int die2 = movementController.getLatestRoll()[1];
            int diesum = die1 + die2;

            guiController.displayRollGUI(die1,die2);

            if (!(player.getTurnsInJail() > 0)) {
                //guiController.movePlayerGUI(playerIndex, movementController.getLatestPosition(player, diesum), diesum);
                int oldLocation = player.getPosition() - diesum;
                if(oldLocation < 0){
                    oldLocation = 40 + player.getPosition() - diesum;
                }
                guiController.removeCarGUI(playerIndex, oldLocation);
                guiController.teleportPlayerGUI(playerIndex, player.getPosition());
            }

            boolean playerInJail = player.getInJail();
            if (!playerInJail) {
                guiController.displayGUIMsg(movementController.passedStart(movementController.getLatestPosition(player,diesum)
                        , player.getPosition()));
                guiController.updatePlayerBalanceGUI(playerIndex, player.getBalance());

                int fieldNumber = player.getPosition();
                model.fields.Field plField = calculator.getField(fieldNumber);

                //hvis chancefelt
                if(plField instanceof ChanceField){
                    landOnChancecard(player, plField);
                }

                //hvis købbart felt
                if (plField instanceof Ownable) {

                    //Hvis feltet ikke er ejet, købes det
                    if ((((Ownable) plField).getOwnedBy() == null)) {
                        if (calculator.getCredibilityBuy(player, fieldNumber) &&
                                guiController.yesNoButton(Language.queryFieldBuy(((Ownable) plField).getCost()))) {
                            buyField(playerIndex, player, fieldNumber, plField);

                            //hvis spilleren ikke kan, eller vil købe feltet går det til auktion
                        } else {
                            auction(player, plField);
                        }
                    } else {
                        //Hvis feltet ejes og du er ejer, kan du byg hus
                        if (plField instanceof model.fields.ownable.Property && player.equals(((Ownable) plField).getOwnedBy()) &&
                        calculator.getCredibilityHouse(player, fieldNumber, 1)) {
                            if (calculator.isBuildable(fieldNumber)) {
                                build(playerIndex, player, fieldNumber, (Property) plField);
                            }
                        }
                        //ellers betal husleje
                        if (((Ownable) plField).getOwnedBy() != null && ((Ownable) plField).getOwnedBy() != player) {
                            if (calculator.getCredibilityRent(player, fieldNumber)) {
                                if (calculator.getOwnerAll(fieldNumber)) {
                                    guiController.getUserResponse(Language.payRentToPlayer(((Ownable) plField).getOwnedBy().getName(), ((Ownable) plField).getRent() * 2));
                                } else {
                                    guiController.getUserResponse(Language.payRentToPlayer(((Ownable) plField).getOwnedBy().getName(), ((Ownable) plField).getRent()));
                                }
                                Player owner = calculator.payRent(player, fieldNumber/*, movementController.getLatestRoll()*/);
                                for (int i = 0; i < guiController.getNumberOfPlayers(); i++) {
                                    if (movementController.getPlayers()[i].equals(owner)) {
                                        guiController.updatePlayerBalanceGUI(i, owner.getBalance());
                                    }
                                }
                            } else {
                                playerBankrupt(player, fieldNumber);
                            }
                        }
                    }
                }

                //Gå i fængsel-felt
                if (plField instanceof model.fields.notOwnable.GoToJail) {
                    movementController.landOnJailField(playerIndex);
                    guiController.removeCarGUI(playerIndex, 30);
                    guiController.teleportPlayerGUI(playerIndex, 10);
                }

                //tax
                if (plField instanceof model.fields.notOwnable.Tax) {
                    if (fieldNumber == 4) {
                        if (guiController.getUserDecision(Language.queryPayTax(),
                                Language.pay() + " 10%", Language.pay() + " 4000 " + Language.currency())) {
                            if (calculator.getCredibilityTax10(player)) {
                                calculator.payTax10(player);
                            } else {
                                playerBankrupt(player, fieldNumber);
                            }
                        } else {
                            if (calculator.getCredibilityTax(player, fieldNumber)) {
                                calculator.payTax(player, fieldNumber);
                            } else {
                                playerBankrupt(player, fieldNumber);
                            }
                        }
                    } else {
                        if (calculator.getCredibilityTax(player, fieldNumber)) {
                            calculator.payTax(player, fieldNumber);
                        } else {
                            playerBankrupt(player, fieldNumber);
                        }
                    }
                }

            }

            if (!playerBankrupt) {
                //opdaterer spiller balance i GUI
                guiController.updatePlayerBalanceGUI(playerIndex, player.getBalance());
            }

            //giver ekstratur hvis der er slået dobbeltslag
            playerIndex = evalTurnTimer(playerIndex, player,die1,die2);
        }
    }

    public void showWinner(){
        guiController.playerWins();
    }

    //sørger for ekstra tur hvis en spiller slår dobbeltslag, men ikke er bankrupt
    private int evalTurnTimer(int turnTimer, Player player, int die1, int die2) {
        if (die1 == die2 && !playerBankrupt) {
            turnTimer += -1;
            player.setTurnsInARow();
        }
        if (turnTimer >= guiController.getNumberOfPlayers() - 1) {
            turnTimer = -1;
        }
        movementController.setCounter();
        return turnTimer;
    }

    //metode der kører auktion af grunde/ejendom
    private void auction(Player player, Field plField) {
        int highestBid = ((Ownable) plField).getCost();
        Player winner = null;
        boolean runAuction = true;
        while (runAuction) {
            player.setInAuction(false);
            for (Player pl : movementController.getPlayers()) {
                if (pl.isInAuction()) {
                    if (!pl.equals(winner)) {
                        if (guiController.yesNoButton(Language.bidForField(pl.getName(), plField.getName(), highestBid) + "?")) {
                            int bid = 0;
                            do {
                                if (highestBid <= pl.getBalance()){
                                    bid = guiController.getUserIntGUI();
                                    if (bid >= highestBid && bid <= pl.getBalance()) {
                                        winner = pl;
                                        highestBid = bid;
                                    }
                                    if (bid < highestBid){
                                        guiController.getUserResponse(Language.bidLowerThanCompetition());
                                    }
                                    if (bid > pl.getBalance()){
                                        guiController.getUserResponse(Language.bidHigherThanBalance());
                                    }
                                }
                            } while (highestBid <= pl.getBalance() && (bid > pl.getBalance() || bid < highestBid));
                        } else {
                            for (int i = 0; i < movementController.getPlayers().length; i++) {
                                if (pl.equals(movementController.getPlayers()[i]))
                                    movementController.getPlayers()[i].setInAuction(false);
                            }
                        }
                    }
                }
            }
            int playerInAuction = 0;
            for (Player pl : movementController.getPlayers()) {
                if (pl.isInAuction())
                    playerInAuction++;
            }

            if ((playerInAuction == 1) && winner != null) {
                int a = 0;
                for (int i = 0; i < movementController.getPlayers().length; i++) {
                    if (movementController.getPlayers()[i].equals(winner)) {
                        a = i;
                    }
                }
                calculator.buyWithPrice(winner, player.getPosition(), highestBid);
                guiController.updatePlayerBalanceGUI(a, winner.getBalance());
                guiController.setFieldBorderGUI(player.getPosition(), a);

                runAuction = false;

            } else if (winner == null) {
                runAuction = false;

            }
            if (!runAuction) {
                for (Player pl : movementController.getPlayers()) {
                    pl.setInAuction(true);
                }
                break;
            }
        }
    }

    //afgører udfaldet af at lande på et chancekort
    private void landOnChancecard(Player player, Field plField) {
        if (plField instanceof ChanceField) {
            ChanceCard card = ((ChanceField) plField).draw();
            //hvis chancekortet rykker dig til et bestemt felt.
            if(card.getPosition() != 1){
                int tempLatestPosition = player.getPosition();
                guiController.displayChancecardGUI(card.getMessage());
                movementController.teleportPosition(player, card.getPosition());
                guiController.removeCarGUI(playerIndex,tempLatestPosition);
                guiController.teleportPlayerGUI(playerIndex,card.getPosition());
                if(card.getIsJail()){
                    player.setInJail(true);
                }
                if(tempLatestPosition > player.getPosition() && !player.getInJail()){
                    player.deposit(4000);
                }
            }
            else {
                guiController.displayChancecardGUI(card.getMessage());
                player.deposit(card.getReward());
            }
        }
    }

    //når et felt købes, sættes border, sørger for at spiller kan bygge og betaler for felt
    private void buyField(int turnTimer, Player player, int fieldNumber, Field plField) {
        calculator.buyField(player, fieldNumber);
        guiController.setFieldBorderGUI(fieldNumber, turnTimer);
    }

    //bygger huse/hotel på felt, tager højde for spillerens balance
    private void build(int turnTimer, Player player, int fieldNumber, Property plField) {
        int amount;
        int numberOfHousesAlreadyPlaced = plField.getHouseAmount();

        if (plField.getHouseAmount() == 4) {
            if (guiController.yesNoButton(Language.queryHotelBuy(plField.getHotelCost())))
                calculator.buyHouse(player, fieldNumber, 1);
            guiController.addHotelToGUI(fieldNumber);
        }

        if (plField.getHouseAmount() < 4) {
            if (guiController.yesNoButton(Language.queryHouseBuy(plField.getHouseCost()))) {
                boolean purchaseMade = true;
                do {
                    amount = guiController.amountOfHousesToBuyGUI();

                    if ((amount + numberOfHousesAlreadyPlaced) > 4) {
                        guiController.displayGUIMsg(Language.maxHouses());
                    } else {
                        if (calculator.getCredibilityHouse(player, fieldNumber, amount)) {
                            if (plField.getHouseAmount() < 4) {
                                calculator.buyHouse(player, fieldNumber, amount);
                                guiController.addHouseToGUI(fieldNumber, amount, numberOfHousesAlreadyPlaced);
                                guiController.setFieldBorderGUI(fieldNumber, turnTimer);
                                plField.setHouseAmount(amount);
                                purchaseMade = false;
                            }
                        } else {
                            guiController.displayGUIMsg(Language.noMoneyForHouses(amount));
                        }
                    }
                }
                while (purchaseMade && ((amount + numberOfHousesAlreadyPlaced) > 4 || !calculator.getCredibilityHouse(player, fieldNumber, amount)));
            }
        }
    }

    private void jailBailOut(Player player) {
        //hvis man har et gratis ud af fængselkort
        if (player.getFreeOfJail() &&
                guiController.yesNoButton(Language.queryUseJailCard() + "?")) {
            player.setInJail(false);
            player.setTurnsInJail(0);
        }
        //hvis det er en tredje tur i fængsel eller man vil betale sig ud af fængslet
        if (player.getTurnsInJail() == 3 ||
                guiController.yesNoButton(Language.queryPayBail() + "?")) {
            calculator.payBail(player);
            movementController.getPlayers()[playerIndex].setTurnsInJail(0);
        }
    }


    private void playerBankrupt(Player player, int fieldNumber){
        guiController.getUserResponse(Language.lostGame());
        Field field = calculator.getField(fieldNumber);

        if (field instanceof Ownable){
            int[] fields = calculator.valuesTransfer(player, fieldNumber);
            int newOwnerNumber = 0;
            for (int i = 0; movementController.getPlayers().length > i; i++){
                if (movementController.getPlayers()[i].equals(((Ownable) field).getOwnedBy())){
                    newOwnerNumber = i;
                }
            }
            for (int value : fields) {
                guiController.setFieldBorderGUI(value, newOwnerNumber);
            }
            guiController.updatePlayerBalanceGUI(newOwnerNumber, movementController.getPlayers()[newOwnerNumber].getBalance());
        } else {
            int[] fields = calculator.valuesTransfer(player);
            for (int i = 0; fields.length > i; i++){
                guiController.remmovePlayerOwned(fields);
            }
            guiController.remmovePlayerOwned(fields);
        }

        guiController.updatePlayerBalanceGUI(playerIndex, -1);
        removePlayer();
        playerIndex--;
        playerBankrupt = true;
    }

    private void removePlayer(){
        // GUI remove
        Player pl = movementController.getPlayers()[playerIndex];
        guiController.deleteCar(playerIndex, pl.getPosition());

        //PLayer og board
        movementController.deletePlayer(playerIndex);
    }
}


