import GUI.GUIController;
import Calculation.Calculator;

public class TurnController {

    private GUIController guiController;
    private MovementController movementController;
    private Calculator calculator;

    public void startGame(){
       guiController = new GUIController();
       guiController.initPlayers();
       movementController = new MovementController(guiController.getNumberOfPlayers());
       calculator = new Calculator();
    }

    public void playGame(){
        int winCondition = 0;
        for (int turnTimer = 0; winCondition == 0; turnTimer++ ){
            guiController.displayDie();
            model.Player player = movementController.makeMove(turnTimer);

            guiController.movePlayer(turnTimer, player.getPosition(),movementController.getLatestPosition(player));

            int fieldNumber = player.getPosition();
            Fields.Field plField = calculator.getField(fieldNumber);

            if(plField instanceof Fields.Ownerable){
                 if((((Fields.Ownerable) plField).getOwnedBy() == null)){
                     if(guiController.yesOrNo("Vil du købe "+ plField.getText()).equals("ja")){
                         calculator.buyField(player, fieldNumber);
                     }
                 }else {
                     if(plField instanceof Fields.Ownable.Property && player.equals(((Fields.Ownerable) plField).getOwnedBy())
                             &&  ((Fields.Ownable.Property) plField).isCanBuild()) {
                         if (guiController.yesOrNo("Vil du bygge hus").equals("ja")) {
                             calculator.buyHouse(player, fieldNumber);
                         }
                     }else {
                         calculator.payRent(player, fieldNumber);
                     }
                 }
            }

            guiController.updatePlayerBalance(turnTimer, player.getBalance());

            if (turnTimer == guiController.getNumberOfPlayers() -1){
                turnTimer = -1;
            }
        }

    }
}


