import model.DiceHolder;
import model.Die;
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

    public Die[] getLatestRoll(){
        return dice.getFaceValues();
    }

    public int getLatestPosition(Player player){
        if (player.getPosition() - getLatestRoll()[0].getFaceValue() - getLatestRoll()[1].getFaceValue() < 0){
            return 40 + player.getPosition() - getLatestRoll()[0].getFaceValue() - getLatestRoll()[1].getFaceValue();
        } else {

            return player.getPosition() - getLatestRoll()[0].getFaceValue() - getLatestRoll()[1].getFaceValue();
        }
    }

    public int lastPos(Player player, int faceValue){
        int lastFieldIndex =+ faceValue;
        return lastFieldIndex;
    }

    public Player makeMove(int player){
        /*if (Player.getInJail){

        } */
        players[player].setPosition(dice.rollDice()[0].getFaceValue() + getLatestRoll()[1].getFaceValue());

        return players[player];
    }
}
