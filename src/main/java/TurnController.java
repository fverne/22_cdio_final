import Fields.NotOwnable.ChanceField;
import Fields.Ownable.Property;
import Fields.Ownerable;
import GUI.FieldProperties.Chancecards;
import GUI.GUIController;
import Calculation.Calculator;

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
            guiController.displayRollGUI(movementController.getLatestRoll()[0].getFaceValue(), movementController.getLatestRoll()[1].getFaceValue());
            model.Player player = movementController.makeMove(turnTimer);

            guiController.displayGUIMsg(movementController.passedStart(movementController.getLatestPosition(player), player.getPosition()));
            guiController.movePlayer(turnTimer, player.getPosition(), movementController.getLatestPosition(player));

            int fieldNumber = player.getPosition();
            Fields.Field plField = calculator.getField(fieldNumber);

            if (plField instanceof Fields.Ownerable) {
                if ((((Fields.Ownerable) plField).getOwnedBy() == null)) {
                    if (guiController.yesOrNo("Vil du købe grunden? prisen er: "+((Ownerable) plField).getCost()+" kr.").equals("ja")) {
                        calculator.buyField(player, fieldNumber);
                        ((Fields.Ownable.Property) plField).setCanBuild(true);
                        guiController.setFieldBorder(fieldNumber, turnTimer);
                    }
                } else {
                    if (plField instanceof Fields.Ownable.Property && player.equals(((Fields.Ownerable) plField).getOwnedBy())
                            && ((Fields.Ownable.Property) plField).isCanBuild()) {
                        if (guiController.yesOrNo("Vil du bygge et hus? prisen er: "+((Property) plField).getHouseCost()+" kr.").equals("ja")) {
                            int amount = guiController.getAmountOfHouses();

                                calculator.buyHouse(player, fieldNumber, amount);
                                guiController.addHouse(fieldNumber, amount);
                                guiController.setFieldBorder(fieldNumber, turnTimer);
                        }
                    } else {
                        calculator.payRent(player, fieldNumber);
                    }
                }
            }
            if (plField instanceof Fields.NotOwnable.ChanceField) {
                Chancecards card = ChanceField.getRandomCard();
                //System.out.println("Besked" +card.getMessage() + " reward: " + card.getReward());
                guiController.displayChancecard(card.getMessage());
                player.deposit(card.getReward());
            }

            guiController.updatePlayerBalance(turnTimer, player.getBalance());

            if (turnTimer == guiController.getNumberOfPlayers() - 1) {
                turnTimer = -1;
            }
        }
    }
}


