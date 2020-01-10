import Fields.Field;
import Fields.Ownable.Property;
import Fields.Ownerable;
import GUI.FieldProperties.FieldProperty;
import GUI.GUIController;

public class TurnController {

    private GUIController guiController;
    private MovementController movementController;
    private FieldProperty FieldBoard;

    public void startGame() {
        guiController = new GUIController();
        guiController.initPlayers();
        movementController = new MovementController(guiController.getNumberOfPlayers());
        FieldBoard = new FieldProperty();
    }

    public void playGame() {
        int winCondition = 0;
        for (int turnTimer = 0; winCondition == 0; turnTimer++) {
            guiController.displayDie();
            model.Player player = movementController.makeMove(turnTimer);

            guiController.movePlayer(turnTimer, player.getPosition(), movementController.getLatestPosition(player));

            Field plField = FieldBoard.getField(player.getPosition());
            if (plField instanceof Ownerable) {
                if (((Ownerable) plField).getOwnedBy() == null) {
                    if (guiController.yesOrNo("Vil du købe " + plField.getText()).equals("ja")) {
                        Calculater.buyField(player, plField);
                    }
                } else if (plField instanceof Property && player.equals(((Ownerable) plField).getOwnedBy()) && ((Property) plField).isCanBuild()) {
                    if (guiController.yesOrNo("Vil du bygge hus").equals("ja"))
                        Calculater.buyHouse(player, plField);
                } else {
                    Calculater.rent(player, plField);
                }
            }

            guiController.updatePlayerBalance(turnTimer, player.getBalance());

            if (turnTimer == guiController.getNumberOfPlayers() - 1) {
                turnTimer = -1;
            }
        }

    }
}


