import model.DiceHolder;
import model.Player;

public class MovementController {

    private DiceHolder dice = new DiceHolder();
    private Player[] players;

    public MovementController(int numberOfPlayers){
        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++){
            players[i] = new Player();
        }
    }

    public int getLatestRoll(){
        return dice.getFaceValues();
    }

    public Player makeMove(int player){
        /*if (Player.getInJail){

        } */
        players[player].setPosition(dice.rollDice());

        return players[player];
    }
}
