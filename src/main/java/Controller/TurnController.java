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

    public void playGame() {
        int winCondition = 0;
        for (turnTimer = 0; winCondition == 0; turnTimer++) {
            //hvis man er i fængsel
            if (movementController.getPlayers()[turnTimer].getInJail()) {
               jailBailOuts();
            }

            guiController.rollButtonGUI();
            model.Player player = movementController.makeMove(turnTimer);
            guiController.displayRollGUI(movementController.getLatestRoll()[0].getFaceValue(), movementController.getLatestRoll()[1].getFaceValue());

            guiController.movePlayerGUI(turnTimer, player.getPosition(), movementController.getLatestPosition(player));

            if (!movementController.getPlayers()[turnTimer].getInJail()) {
                guiController.displayGUIMsg(movementController.passedStart(movementController.getLatestPosition(player), player.getPosition()));

                int fieldNumber = player.getPosition();
                Fields.Field plField = calculator.getField(fieldNumber);

                //hvis købbart felt
                if (plField instanceof Fields.Ownerable) {

                    //Hvis feltet ikke er ejet, købes det
                    if ((((Fields.Ownerable) plField).getOwnedBy() == null)) {
                        if ((calculator.getCredibilityBuy(player, fieldNumber) &&
                                guiController.yesOrNo("Vil du købe grunden? prisen er: " + ((Ownerable) plField).getCost() + " kr.").equals("ja"))) {
                            buyField(turnTimer, player, fieldNumber, plField);

                            //hvis spilleren ikke kan, eller vil købe feltet går det til auktion
                        } else {
                            auction(player, plField);
                        }
                    } else {
                        //Hvis feltet ejes og du er ejer, kan du byg hus
                        if (plField instanceof Fields.Ownable.Property && player.equals(((Fields.Ownerable) plField).getOwnedBy())
                                && ((Fields.Ownable.Property) plField).isCanBuild()) {

                            if (calculator.isBuildable(fieldNumber)) {
                                build(turnTimer, player, fieldNumber, (Property) plField);
                            }
                        }
                        //ellers betal huslej
                        if (((Ownerable) plField).getOwnedBy() != null && ((Ownerable) plField).getOwnedBy() != player) {
                            calculator.payRent(player, fieldNumber, movementController.getLatestRoll());
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
                    guiController.movePlayerGUI(turnTimer, player.getPosition(), 30);
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

    private int evalTurnTimer(int turnTimer, Player player) {
        if (movementController.getLatestRoll()[0].getFaceValue() == movementController.getLatestRoll()[1].getFaceValue() && !player.getInJail()) {
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
                    if ( !pl.equals(winner)) {
                        if (guiController.yesOrNo(pl.getName() + " Vil du byde på " + plField.getName() + " for " + highestBid).equals("ja")) {
                            int bid = guiController.getUserIntGUI();
                            if (bid >= highestBid && bid <= pl.getBalance()) {
                                winner = pl;
                                highestBid = bid;
                            }
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
            System.out.println("x is " + x);
            if ((x == 1) && winner != null) { //x er 2 fordi spillern der landede på feltet er med i in
                int a = 0;
                for(int i =0; i < movementController.getPlayers().length; i++){
                    if(movementController.getPlayers()[i].equals(winner)){
                        a = i;
                    }
                }
                calculator.buyWithPrice(winner, player.getPosition(), highestBid);
                guiController.updatePlayerBalanceGUI(a, winner.getBalance());
                guiController.setFieldBorderGUI(player.getPosition(), a);

                System.out.println("fundet en køber");
                n = false;

            }
            else if (winner == null) {
                System.out.println("ikke fundet en køber");
                n = false;

            }
            if (!n) {
                for(Player pl : movementController.getPlayers()){
                    pl.setInAuction(true);
                }
                break;
            }
        }
    }

    private void landOnChancecard(Player player, Field plField) {
        if (plField instanceof ChanceField) {
            ChanceCard card = ((ChanceField) plField).draw();

            //System.out.println("Besked" +card.getMessage() + " reward: " + card.getReward());
            guiController.displayChancecardGUI(card.getMessage());
            player.deposit(card.getReward());
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
            if (guiController.yesOrNo("Vil du bygge et hotel?, prisen pr. stk er: " + plField.getHotelCost() + " kr.").equals("ja"))
                plField.setHotelAmount(1);
            guiController.addHotelToGUI(fieldNumber);
        }

        if (guiController.yesOrNo("Vil du bygge?, prisen pr. hus er: " + plField.getHouseCost() + " kr.").equals("ja")) {
            do {
                amount = guiController.amountOfHousesToBuyGUI();

                if ((amount + numberOfHousesAlreadyPlaced) > 4) {
                    guiController.displayGUIMsg("Du kan højst bygge 4 huse pr. felt.");
                } else if (plField.getHouseAmount() < 4) {
                    calculator.buyHouse(player, fieldNumber, amount);
                    guiController.addHouseToGUI(fieldNumber, amount, numberOfHousesAlreadyPlaced);
                    guiController.setFieldBorderGUI(fieldNumber, turnTimer);
                    plField.setHouseAmount(amount);
                }
            }
            while ((amount + numberOfHousesAlreadyPlaced) > 4);
        }
    }

    private void jailBailOuts(){
        //hvis man har et gratis ud af fængselkort
        if (movementController.getPlayers()[turnTimer].getFreeOfJail() &&
                guiController.yesOrNo("Vil du bruge et gratis ud af fængselkort?").equals("ja")){
            movementController.getPlayers()[turnTimer].setInJail(false);
            movementController.getPlayers()[turnTimer].setTurnsInJail(0);
        }
        //hvis det er en trdje tur i fængsel eller man vil betale sig ud af fængslet
        if (movementController.getPlayers()[turnTimer].getTurnsInJail() == 3 ||
                guiController.yesOrNo("Vil du betale 1000 kr. for at komme ud af fængsel?").equals("ja")) {
            movementController.getPlayers()[turnTimer].setInJail(false);
            movementController.getPlayers()[turnTimer].setTurnsInJail(0);
        }
    }
}


