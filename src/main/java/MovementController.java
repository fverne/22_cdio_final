import model.DiceHolder;
import model.Die;
import model.Player;

public class MovementController {

    private DiceHolder dice;
    private Player[] players;


    public MovementController(int numberOfPlayers) {
        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player();
        }
        dice = new DiceHolder();
    }

    public Player[] getPlayers() {
        return players;
    }

    public Die[] getLatestRoll() {
        return dice.getFaceValues();
    }

    public int getLatestPosition(Player player) {
        if (player.getPosition() - getLatestRoll()[0].getFaceValue() - getLatestRoll()[1].getFaceValue() < 0) {
            return 40 + player.getPosition() - getLatestRoll()[0].getFaceValue() - getLatestRoll()[1].getFaceValue();
        } else {

            return player.getPosition() - getLatestRoll()[0].getFaceValue() - getLatestRoll()[1].getFaceValue();
        }
    }

    public Player makeMove(int player) {
        /*if (Player.getInJail){

        } */
        setPosition(players[player], dice.rollDice()[0].getFaceValue() + getLatestRoll()[1].getFaceValue());

        return players[player];
    }

    public void setPosition(Player player, int moveLength) {
        int position = player.getPosition();

        if (moveLength + position > 39) {
            player.setPosition((position + moveLength) - 40);
            player.deposit(4000);
        } else {
            player.setPosition(player.getPosition() + moveLength);
        }
    }

    public String passedStart(int lastPos, int newPos) {
        String msg;
        if (lastPos > newPos) {
            msg = "Du har passeret start, modtag 4000 kr.";
            return msg;
        }
        return null;
    }
}
