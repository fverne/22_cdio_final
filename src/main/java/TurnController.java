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

    public void startGame() {
        guiController = new GUIController();
        guiController.initGUIPlayers();
        movementController = new MovementController(guiController.getNumberOfPlayers());
        calculator = new Calculator();
    }

    public void playGame() {
        int winCondition = 0;
        for (int turnTimer = 0; winCondition == 0; turnTimer++) {
            guiController.rollButtonGUI();
            guiController.displayRollGUI(movementController.getLatestRoll()[0].getFaceValue(), movementController.getLatestRoll()[1].getFaceValue());
            model.Player player = movementController.makeMove(turnTimer);

            guiController.displayGUIMsg(movementController.passedStart(movementController.getLatestPosition(player), player.getPosition()));
            guiController.movePlayerGUI(turnTimer, player.getPosition(), movementController.getLatestPosition(player));

            int fieldNumber = player.getPosition();
            Fields.Field plField = calculator.getField(fieldNumber);

            //hvis købbart felt
            if (plField instanceof Fields.Ownerable) {

                //Hvis feltet ikke er ejet, købes det
                if ((((Fields.Ownerable) plField).getOwnedBy() == null)) {
                    if (calculator.getCredibilityBuy(player, fieldNumber) && guiController.yesOrNo("Vil du købe grunden? prisen er: " + ((Ownerable) plField).getCost() + " kr.").equals("ja")) {
                    }
                    calculator.buyField(player, fieldNumber);

                    buyField(turnTimer, player, fieldNumber, plField);

                    //auction(player, plField);

                    //Hvis feltet ejes og du er ejer, kan du byg hus
                } else if (plField instanceof Fields.Ownable.Property && player.equals(((Fields.Ownerable) plField).getOwnedBy())
                        && ((Fields.Ownable.Property) plField).isCanBuild()) {

                    if (calculator.isBuildable(fieldNumber)) {
                        build(turnTimer, player, fieldNumber, (Property) plField);
                    }

                    //ellers betal husleje
                } else {
                    this.calculator.payRent(player, fieldNumber, this.movementController.getLatestRoll());
                }
            }

            //chancekort
            if (plField instanceof Fields.NotOwnable.ChanceField) {
                landOnChancecard(player, plField);
            }

            //tax
            if (plField instanceof Fields.NotOwnable.Tax) {
                calculator.payTax(player, fieldNumber);
            }

            //opdaterer spiller balance i GUI
            guiController.updatePlayerBalanceGUI(turnTimer, player.getBalance());

            //giver ekstratur hvis der er slået dobbeltslag
            turnTimer = evalTurnTimer(turnTimer);
        }
    }

    private int evalTurnTimer(int turnTimer) {
        if (movementController.getLatestRoll()[0].getFaceValue() == movementController.getLatestRoll()[1].getFaceValue()) {
            turnTimer = -1;
        }
        if (turnTimer == guiController.getNumberOfPlayers() - 1) {
            turnTimer = -1;
        }
        return turnTimer;
    }

    private void auction(Player player, Field plField) {
        int highestBid = ((Ownerable) plField).getCost();
        Player winner = null;

        while (true) {
            Player[] in = movementController.getPlayers();
            for (Player pl : in) {
                //System.out.println("akgslakn");
                if (pl != null) {
                    if (!pl.equals(player) && !pl.equals(winner)) {
                        if (guiController.yesOrNo(pl + " Vil du byde på " + plField.getName() + " for " + highestBid).equals("ja")) {
                            int bid = guiController.getUserIntGUI();
                            if (bid >= highestBid && bid <= pl.getBalance()) {
                                winner = pl;
                                highestBid = bid;
                            }
                        } else {
                            System.out.println("der blev skrevet nej");
                            for (int i = 0; i < in.length; i++) {
                                if (pl.equals(in[i]))
                                    in[i] = null;
                            }
                        }
                    }
                }
            }
            int x = 0;
            for (Player pl : in) {
                if (pl != null)
                    x++;
            }
            //System.out.println("x is " + x);
            if ((x == 0 || x == 1) && winner != null) {
                calculator.buyWithPrice(winner, player.getPosition(), highestBid);
                System.out.println("fundet en køber");

                break;
            }
            if (winner == null) {
                System.out.println("ikke fundet en køber");

                break;
            }
        }
    }

    private void landOnChancecard(Player player, Field plField) {
        if (plField instanceof ChanceField) {
            ChanceCard card = ((ChanceField) plField).getRandomCard();

            //System.out.println("Besked" +card.getMessage() + " reward: " + card.getReward());
            guiController.displayChancecardGUI(card.getMessage());
            player.deposit(card.getReward());
        }
    }

    private void buyField(int turnTimer, Player player, int fieldNumber, Field plField) {
        if (guiController.yesOrNo("Vil du købe grunden? prisen er: " + ((Ownerable) plField).getCost() + " kr.").equals("ja")) {
            calculator.buyField(player, fieldNumber);

            if (plField instanceof Property) {
                ((Property) plField).setCanBuild(true);
            }
            guiController.setFieldBorderGUI(fieldNumber, turnTimer);
        }
    }

    private void build(int turnTimer, Player player, int fieldNumber, Property plField) {
        int amount;
        int numberOfHousesAlreadyPlaced = plField.getHouseAmount();

        //IMPLEMENTER MAKS 4 HUSE
        if (plField.getHouseAmount() == 4) {
            if (guiController.yesOrNo("Vil du bygge et hotel?, prisen pr. stk er: " + " kr.").equals("ja"))
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
}


