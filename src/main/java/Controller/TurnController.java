package Controller;

import Fields.Field;
import Fields.NotOwnable.ChanceField;
import Fields.Ownable.Property;
import Fields.Ownerable;
import GUI.FieldProperties.ChanceCard;
import GUI.GUIController;
import Calculation.Calculator;
import model.Player;

public class TurnController {

    private GUIController guiController;
    private MovementController movementController;
    private Calculator calculator;
    private int turnTimer;

    public void startGame() {
        guiController = new GUIController();
        guiController.initGUIPlayers();
        movementController = new MovementController(guiController.getNumberOfPlayers());
        calculator = new Calculator();

        for (int i = 0; i < guiController.getNumberOfPlayers(); i++) {
            movementController.getPlayers()[i].setName(guiController.getName(i));
        }
    }

    public void playGame() throws InterruptedException {
        int winCondition = 0;
        for (turnTimer = 0; winCondition == 0; turnTimer++) {
            guiController.getUserResponse(turnTimer);

            //hvis man er i fængsel
            if (movementController.getPlayers()[turnTimer].getInJail()) {
                jailBailOuts();
            }

            guiController.rollButtonGUI();
            model.Player player = movementController.makeMove(turnTimer);
            guiController.displayRollGUI(movementController.getLatestRoll()[0].getFaceValue(), movementController.getLatestRoll()[1].getFaceValue());
            int diesum = movementController.getLatestRoll()[0].getFaceValue() + movementController.getLatestRoll()[1].getFaceValue();

            if (!(player.getTurnsInJail() > 0)) {
                guiController.movePlayerGUI(turnTimer, movementController.getLatestPosition(player, diesum), diesum);
            }

            if (!movementController.getPlayers()[turnTimer].getInJail()) {
                guiController.displayGUIMsg(movementController.passedStart(movementController.getLatestPosition(player,diesum), player.getPosition()));

                int fieldNumber = player.getPosition();
                Fields.Field plField = calculator.getField(fieldNumber);

                //hvis købbart felt
                if (plField instanceof Fields.Ownerable) {

                    //Hvis feltet ikke er ejet, købes det
                    if ((((Fields.Ownerable) plField).getOwnedBy() == null)) {
                        if ((calculator.getCredibilityBuy(player, fieldNumber) &&
                                guiController.yesNoButton("Vil du købe grunden? prisen er: " + ((Ownerable) plField).getCost() + " kr.").equals("ja"))) {
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
                        //ellers betal huslej
                        if (((Ownerable) plField).getOwnedBy() != null && ((Ownerable) plField).getOwnedBy() != player) {
                            guiController.getUserResponse("Du skal betale husleje til " + ((Ownerable) plField).getOwnedBy().getName() + " på " +
                                    ((Ownerable) plField).getRent() + "kr.");
                            Player owner = calculator.payRent(player, fieldNumber, movementController.getLatestRoll());
                            for (int i = 0; i < guiController.getNumberOfPlayers(); i++){
                                if (movementController.getPlayers()[i].equals(owner)){
                                    guiController.updatePlayerBalanceGUI(i, owner.getBalance());
                                }
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
                    calculator.payTax(player, fieldNumber);
                }

            }
            //opdaterer spiller balance i GUI
            guiController.updatePlayerBalanceGUI(turnTimer, player.getBalance());

            //giver ekstratur hvis der er slået dobbeltslag
            turnTimer = evalTurnTimer(turnTimer, player);
        }
    }

    public void showWinner(){

    }

    private int evalTurnTimer(int turnTimer, Player player) {
        if (movementController.getLatestRoll()[0].getFaceValue() == movementController.getLatestRoll()[1].getFaceValue()) {
            turnTimer += -1;
            player.setTurnsInARow();
        }
        if (turnTimer == guiController.getNumberOfPlayers() - 1) {
            turnTimer = -1;
        }
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
                        if (guiController.yesNoButton(pl.getName() + " Vil du byde på " + plField.getName() + " for " + highestBid).equals("ja")) {
                            int bid = 0;
                            do {
                                if (highestBid <= pl.getBalance()){
                                    bid = guiController.getUserIntGUI();
                                    if (bid >= highestBid && bid <= pl.getBalance()) {
                                        winner = pl;
                                        highestBid = bid;
                                    }
                                    if (bid < highestBid){
                                        guiController.getUserResponse("Dit bud er lavere end det højeste bud, indtast et nyt bud");
                                    }
                                    if (bid > pl.getBalance()){
                                        guiController.getUserResponse("Dit bud er højere end din balance, indtast et nyt bud");
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
                if(card.getIsJail() == true){
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
            if (guiController.yesNoButton("Vil du bygge et hotel?, prisen pr. stk er: " + " kr.").equals("ja"))
                plField.setHotelAmount(1);
            guiController.addHotelToGUI(fieldNumber);
        }

        if (plField.getHouseAmount() < 4) {
            if (guiController.yesNoButton("Vil du bygge?, prisen pr. hus er: " + plField.getHouseCost() + " kr.").equals("ja")) {
                boolean purchaseMade = true;
                do {
                    amount = guiController.amountOfHousesToBuyGUI();

                    if ((amount + numberOfHousesAlreadyPlaced) > 4) {
                        guiController.displayGUIMsg("Du kan højst bygge 4 huse pr. felt.");
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
                            guiController.displayGUIMsg("Du har ikke penge nok til " + amount + " huse");
                        }
                    }
                }
                while (purchaseMade && ((amount + numberOfHousesAlreadyPlaced) > 4 || !calculator.getCredibilityHouse(player, fieldNumber, amount)));
            }
        }
    }

    private void jailBailOuts() {
        //hvis man har et gratis ud af fængselkort
        if (movementController.getPlayers()[turnTimer].getFreeOfJail() &&
                guiController.yesNoButton("Vil du bruge et gratis ud af fængselkort?").equals("ja")) {
            movementController.getPlayers()[turnTimer].setInJail(false);
            movementController.getPlayers()[turnTimer].setTurnsInJail(0);
        }
        //hvis det er en trdje tur i fængsel eller man vil betale sig ud af fængslet
        if (movementController.getPlayers()[turnTimer].getTurnsInJail() == 3 ||
                guiController.yesNoButton("Vil du betale 1000 kr. for at komme ud af fængsel?").equals("ja")) {
            movementController.getPlayers()[turnTimer].setInJail(false);
            movementController.getPlayers()[turnTimer].setTurnsInJail(0);
        }
    }
}


