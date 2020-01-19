import controllers.TurnController;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TurnController turnController = new TurnController();
        turnController.startGame();
        turnController.playGame();
        turnController.showWinner();
    }
}
