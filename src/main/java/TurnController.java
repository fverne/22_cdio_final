import Calculation.Calculator;
import Fields.NotOwnable.ChanceField;
import Fields.Ownable.Property;
import Fields.Ownerable;
import GUI.FieldProperties.ChanceCard;
import GUI.GUIController;

public class TurnController {

    private GUIController guiController;
    private MovementController movementController;
    private Calculator calculator;

    public void startGame() {
        guiController = new GUIController();
        guiController.initPlayers();
        movementController = new MovementController(guiController.getNumberOfPlayers());
        calculator = new Calculator();
    }

    public void playGame() {
        int winCondition = 0;
        for (int turnTimer = 0; winCondition == 0; turnTimer++) {
            guiController.rollButton();
            model.Player player = movementController.makeMove(turnTimer);
            guiController.displayRollGUI(movementController.getLatestRoll()[0].getFaceValue(), movementController.getLatestRoll()[1].getFaceValue());

            guiController.displayGUIMsg(movementController.passedStart(movementController.getLatestPosition(player), player.getPosition()));
            guiController.movePlayer(turnTimer, player.getPosition(), movementController.getLatestPosition(player));

            int fieldNumber = player.getPosition();
            Fields.Field plField = calculator.getField(fieldNumber);

            if (plField instanceof Fields.Ownerable) {
                if ((((Fields.Ownerable) plField).getOwnedBy() == null)) {
                    if (calculator.getCredibilityBuy(player, fieldNumber) && guiController.yesOrNo("Vil du købe grunden? prisen er: " + ((Ownerable) plField).getCost() + " kr.").equals("ja")) {
                        calculator.buyField(player, fieldNumber);

                        if (plField instanceof Fields.Ownable.Property) {
                            ((Fields.Ownable.Property) plField).setCanBuild(true);
                        }

                        guiController.setFieldBorder(fieldNumber, turnTimer);
                    } else {
                        int highestBid = ((Ownerable) plField).getCost();
                        model.Player winner = null;

                        while (true) {
                            model.Player[] in = movementController.getPlayers();
                            for (model.Player pl : in) {
                                //System.out.println("akgslakn");
                                if (pl != null) {
                                    if (!pl.equals(player) && !pl.equals(winner )) {
                                        if (guiController.yesOrNo(pl + " Vil du byde på " + plField.getName() + " for " + highestBid).equals("ja")) {
                                            int bid = guiController.getUserInt();
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
                            int x=0;
                            for (model.Player pl : in){
                                if(pl != null)
                                    x++;
                            }
                           //System.out.println("x is " + x);
                            if((x == 0 || x == 1) && winner != null){
                                calculator.buyWithPrice(winner, player.getPosition(), highestBid);
                                System.out.println("fundet en køber");

                                break;
                            }
                            if( winner == null){
                                System.out.println("ikke fundet en køber");

                                break;
                            }
                        }
                    }

                } else {
                    if (plField instanceof Fields.Ownable.Property && player.equals(((Fields.Ownerable) plField).getOwnedBy())
                            && ((Fields.Ownable.Property) plField).isCanBuild()) {
                        if (calculator.getCredibilityHouse(player, fieldNumber, 1) && guiController.yesOrNo("Vil du bygge et hus? prisen er: " + ((Property) plField).getHouseCost() + " kr.").equals("ja")) {
                            int amount = guiController.getAmountOfHouses();

                            calculator.buyHouse(player, fieldNumber, amount);
                            guiController.addHouse(fieldNumber, amount);
                            guiController.setFieldBorder(fieldNumber, turnTimer);
                        }
                    } else {
                        calculator.payRent(player, fieldNumber, movementController.getLatestRoll());
                        }
                }
            }
            if (plField instanceof Fields.NotOwnable.ChanceField) {
                ChanceCard card = ((ChanceField) plField).getRandomCard();

            //System.out.println("Besked" +card.getMessage() + " reward: " + card.getReward());
                guiController.displayChancecard(card.getMessage());
                player.deposit(card.getReward());
            }
            if (plField instanceof Fields.NotOwnable.Tax){
                calculator.payTax(player, fieldNumber);
            }

            guiController.updatePlayerBalance(turnTimer, player.getBalance());

            if (movementController.getLatestRoll()[0].getFaceValue() == movementController.getLatestRoll()[1].getFaceValue()) {
                turnTimer =- 1;
            }
            if (turnTimer == guiController.getNumberOfPlayers() - 1) {
                turnTimer = -1;
            }
        }
    }
}



