import Fields.Field;
import GUI.FieldProperty;
import GUI.GUIController;

public class TurnController {

    private GUIController guiController;
    private MovementController movementController;

    public void startGame(){
       guiController = new GUIController();
       guiController.initPlayers();
       movementController = new MovementController(guiController.getNumberOfPlayers());
       FieldProperty FieldBoard = new FieldProperty();
    }

    public void playGame(){
        int winCondition = 0;
        for (int turnTimer = 0; winCondition == 0; turnTimer++ ){
            guiController.displayDie();
            model.Player player = movementController.makeMove(turnTimer);

<<<<<<< HEAD
=======
            model.Die[] dice = movementController.getLatestRoll();
            guiController.displayRollGUI(dice[0].getFaceValue(), dice[1].getFaceValue());

>>>>>>> 089eb10fe5340395c44469bae727af3c3533d417
            guiController.movePlayer(turnTimer, player.getPosition(),movementController.getLatestPosition(player));

            if(FieldBoard)

            guiController.updatePlayerBalance(turnTimer, player.getBalance());

            if (turnTimer == guiController.getNumberOfPlayers() -1){
                turnTimer = -1;
            }
        }

    }
}


