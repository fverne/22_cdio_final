import GUI.GUIController;

public class TurnController {

    private GUIController guiController;
    private MovementController movementController;

    public void startGame(){
       guiController = new GUIController();
       guiController.initPlayers();
       movementController = new MovementController(guiController.getNumberOfPlayers());
    }

    public void playGame(){
        int winCondition = 0;
        for (int turnTimer = 0; winCondition == 0; turnTimer++ ){
            guiController.displayDie();
            model.Player player = movementController.makeMove(turnTimer);

            model.Die[] dice = movementController.getLatestRoll();
            guiController.displayRollGUI(dice[0].getFaceValue(), dice[1].getFaceValue());

            guiController.movePlayer(turnTimer, player.getPosition(),movementController.getLatestPosition(player));
            guiController.updatePlayerBalance(turnTimer, player.getBalance());

            if (turnTimer == guiController.getNumberOfPlayers() -1){
                turnTimer = -1;
            }
        }

    }
}


