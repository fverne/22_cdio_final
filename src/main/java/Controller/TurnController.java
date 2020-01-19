package Controller;

import Fields.Field;
import Fields.NotOwnable.ChanceField;
import Fields.Ownable.Property;
import Fields.Ownerable;
import GUI.FieldProperties.ChanceCard;
import GUI.GUIController;
import Calculation.Calculator;
import model.Language;
import model.Player;

public class TurnController {

    private GUIController guiController;
    private MovementController movementController;
    private Calculator calculator;
    private int turnTimer;
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

    public void playGame() throws InterruptedException {
        for (turnTimer = 0; guiController.getNumberOfPlayers() > 1; turnTimer++) {
            guiController.getUserResponse(turnTimer);

            //hvis man er i fængsel
            if (movementController.getPlayers()[turnTimer].getInJail()) {
                jailBailOuts(movementController.getPlayers()[turnTimer]);
            }

            //spilleren slår med terningerne og bliver rykket
            guiController.rollButtonGUI();
            model.Player player = movementController.makeMove(turnTimer);
            guiController.displayRollGUI(movementController.getLatestRoll()[0].getFaceValue(), movementController.getLatestRoll()[1].getFaceValue());
            int diesum = movementController.getLatestRoll()[0].getFaceValue() + movementController.getLatestRoll()[1].getFaceValue();

            //bevæger spilleren i gui, hvis de har rykket sig
            if (!(player.getTurnsInJail() > 0)) {
                guiController.movePlayerGUI(turnTimer, movementController.getLatestPosition(player, diesum), diesum);
            }

            //spillerens tur bliver udført efter hvilket felt de er landet på
            if (!movementController.getPlayers()[turnTimer].getInJail()) {
                guiController.displayGUIMsg(movementController.passedStart(movementController.getLatestPosition(player,diesum), player.getPosition()));
                guiController.updatePlayerBalanceGUI(turnTimer, player.getBalance());

                int fieldNumber = player.getPosition();
                Fields.Field plField = calculator.getField(fieldNumber);

                //hvis købbart felt
                if (plField instanceof Fields.Ownerable) {

                    //Hvis feltet ikke er ejet, købes det
                    if ((((Fields.Ownerable) plField).getOwnedBy() == null)) {
                        if (calculator.getCredibilityBuy(player, fieldNumber) &&
                                guiController.yesNoButton(Language.queryFieldBuy(((Ownerable) plField).getCost()))) {
                            buyField(turnTimer, player, fieldNumber, plField);

                            //hvis spilleren ikke kan, eller vil købe feltet går det til auktion
                        } else {
                            auction(player, plField);
                        }
                    } else {
                        //Hvis feltet ejes og du er ejer, kan du byg hus
                        if (plField instanceof Fields.Ownable.Property && player.equals(((Fields.Ownerable) plField).getOwnedBy()) &&
                        calculator.getCredibilityHouse(player, fieldNumber, 1)) {

                            if (calculator.isBuildable(fieldNumber)) {
                                build(turnTimer, player, fieldNumber, (Property) plField);
                            }
                        }
                        //ellers betal husleje
                        if (((Ownerable) plField).getOwnedBy() != null && ((Ownerable) plField).getOwnedBy() != player) {
                            if (calculator.getCredibilityRent(player, fieldNumber)) {
                                guiController.getUserResponse(Language.payRentToPlayer(((Ownerable) plField).getOwnedBy().getName(), ((Ownerable) plField).getRent()));
                                Player owner = calculator.payRent(player, fieldNumber, movementController.getLatestRoll());
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

                //chancekort
                if (plField instanceof Fields.NotOwnable.ChanceField) {
                    landOnChancecard(player, plField);
                }
                //Gå i fængsel-felt
                if (plField instanceof Fields.NotOwnable.GoToJail) {
                    movementController.landOnJailField(turnTimer);
                    guiController.removeCarGUI(turnTimer, 30);
                    guiController.teleportPlayerGUI(turnTimer, 10);
                }

                //tax
                if (plField instanceof Fields.NotOwnable.Tax) {
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
                guiController.updatePlayerBalanceGUI(turnTimer, player.getBalance());
            }

            //giver ekstratur hvis der er slået dobbeltslag
            turnTimer = evalTurnTimer(turnTimer, player);
        }
    }

    public void showWinner(){
        guiController.playerWins();
    }

    //vurderer hvilken spiller der skal have den næste tur
    private int evalTurnTimer(int turnTimer, Player player) {
        //giver spilleren ekstra tur hvis de har slået to ens
        if (movementController.getLatestRoll()[0].getFaceValue() == movementController.getLatestRoll()[1].getFaceValue() && !playerBankrupt) {
            turnTimer += -1;
            player.setTurnsInARow();
        }
        //sætter turen til første spiller når sidste har haft sin tur
        if (turnTimer >= guiController.getNumberOfPlayers() - 1) {
            turnTimer = -1;
        }
        playerBankrupt = false;
        return turnTimer;
    }

    private void auction(Player player, Field plField) {
        int highestBid = ((Ownerable) plField).getCost();
        Player winner = null;
        boolean n = true;
        while (n) {

            player.setInAuction(false);
            for (Player pl : movementController.getPlayers()) {
                //System.out.println("akgslakn");
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
                            System.out.println("der blev skrevet nej");
                            for (int i = 0; i < movementController.getPlayers().length; i++) {
                                if (pl.equals(movementController.getPlayers()[i]))
                                    movementController.getPlayers()[i].setInAuction(false);
                            }
                        }
                    }
                }
            }
            int x = 0;
            for (Player pl : movementController.getPlayers()) {
                if (pl.isInAuction())
                    x++;
            }
            //System.out.println("x is " + x);
            if ((x == 1) && winner != null) { //x er 2 fordi spillern der landede på feltet er med i in
                int a = 0;
                for (int i = 0; i < movementController.getPlayers().length; i++) {
                    if (movementController.getPlayers()[i].equals(winner)) {
                        a = i;
                    }
                }
                calculator.buyWithPrice(winner, player.getPosition(), highestBid);
                guiController.updatePlayerBalanceGUI(a, winner.getBalance());
                guiController.setFieldBorderGUI(player.getPosition(), a);

                System.out.println("fundet en køber");
                n = false;

            } else if (winner == null) {
                //System.out.println("ikke fundet en køber");
                n = false;

            }
            if (!n) {
                for (Player pl : movementController.getPlayers()) {
                    pl.setInAuction(true);
                }
                break;
            }
        }
    }
//når en spiller lander på et chanchefelt
    private void landOnChancecard(Player player, Field plField) {
        if (plField instanceof ChanceField) {
            ChanceCard card = ((ChanceField) plField).draw();
            //hvis chancekortet rykker dig til et bestemt felt.
            if(card.getPosition() != 1){
                int tempLatestPosition = player.getPosition();
                guiController.displayChancecardGUI(card.getMessage());
                movementController.teleportPosition(player, card.getPosition());
                guiController.removeCarGUI(turnTimer,tempLatestPosition);
                guiController.teleportPlayerGUI(turnTimer,card.getPosition());
                if(card.getIsJail()){
                    player.setInJail(true);
                }
                if(tempLatestPosition > player.getPosition() && !player.getInJail()){
                    player.deposit(4000);
                }
            }
            else {
                //System.out.println("Besked" +card.getMessage() + " reward: " + card.getReward());
                guiController.displayChancecardGUI(card.getMessage());
                player.deposit(card.getReward());
            }
        }
    }
    //når en spiller købr et felt
    private void buyField(int turnTimer, Player player, int fieldNumber, Field plField) {
        calculator.buyField(player, fieldNumber);

        if (plField instanceof Property) {
            ((Property) plField).setCanBuild(true);
        }
        guiController.setFieldBorderGUI(fieldNumber, turnTimer);
    }

    private void build(int turnTimer, Player player, int fieldNumber, Property plField) {
        int amount;
        int numberOfHousesAlreadyPlaced = plField.getHouseAmount();

        //IMPLEMENTER MAKS 4 HUSE
        if (plField.getHouseAmount() == 4) {
            if (guiController.yesNoButton(Language.queryHotelBuy(plField.getHotelCost())))
                plField.setHotelAmount(1);
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

    //når spilleren er i fængsel fra starten af sin tur
    private void jailBailOuts(Player player) {
        //hvis man har et gratis ud af fængselkort
        if (movementController.getPlayers()[turnTimer].getFreeOfJail() &&
                guiController.yesNoButton(Language.queryUseJailCard() + "?")) {
            movementController.getPlayers()[turnTimer].setInJail(false);
            movementController.getPlayers()[turnTimer].setTurnsInJail(0);
        }
        //hvis det er en tredje tur i fængsel eller man vil betale sig ud af fængslet
        if (movementController.getPlayers()[turnTimer].getTurnsInJail() == 3 ||
                guiController.yesNoButton(Language.queryPayBail() + "?")) {
            calculator.payBail(player);
            movementController.getPlayers()[turnTimer].setTurnsInJail(0);
        }
    }

    //bruges når en spiller ikke kan betale til banken eller en spiller
    private void playerBankrupt(Player player, int fieldNumber){
        guiController.getUserResponse(Language.lostGame());
        Field field = calculator.getField(fieldNumber);

        //hvis man skal betale til en anden spiller
        if (field instanceof Fields.Ownerable){
            int[] fields = calculator.valuesTransfer(player, fieldNumber);
            int newOwnerNumber = 0;
            for (int i = 0; movementController.getPlayers().length > i; i++){
                if (movementController.getPlayers()[i].equals(((Ownerable) field).getOwnedBy())){
                    newOwnerNumber = i;
                }
            }
            for (int i = 0; fields.length > i; i++){
                guiController.setFieldBorderGUI(fields[i], newOwnerNumber);
            }
            guiController.updatePlayerBalanceGUI(newOwnerNumber, movementController.getPlayers()[newOwnerNumber].getBalance());

            //hvis man skal betale sine værdier til banken
        } else {
            int[] fields = calculator.valuesTransfer(player);
            for (int i = 0; fields.length > i; i++){
                guiController.remmovePlayerOwned(fields);
            }
            guiController.remmovePlayerOwned(fields);
        }
        guiController.updatePlayerBalanceGUI(turnTimer, -1);
        removePlayer();
        turnTimer--;
        playerBankrupt = true;
    }

    //fjerner spilleren fra gui og model
    private void removePlayer(){
        // GUI remove
        Player pl = movementController.getPlayers()[turnTimer];
        guiController.deleteCar(turnTimer, pl.getPosition());

        //PLayer og board
        movementController.deletePlayer(turnTimer);
    }
}


