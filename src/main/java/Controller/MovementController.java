package Controller;

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
        //hvis spilleren er i fængsel
        if (players[player].getInJail()) {
            //hvis man ruller to ens
            if (dice.rollDice()[0].getFaceValue() == getLatestRoll()[1].getFaceValue()) {
                setPosition(players[player], dice.rollDice()[0].getFaceValue() + getLatestRoll()[1].getFaceValue());
                players[player].setInJail(false);
                players[player].setTurnsInJail(0);
                //hvis man ikke ruller to ens
            } else {
                players[player].setTurnsInJail();
            }
            //hvis man ikke er i fængsel
        } else {
            //hvis det er ens tredje tur
            if (players[player].getTurnsInARow() == 2) {
                //hvis man ruller to ens
                if (dice.rollDice()[0].getFaceValue() == getLatestRoll()[1].getFaceValue()) {
                    players[player].setInJail(true);
                    players[player].setPosition(10);
                    players[player].setTurnsInARow(0);
                    //hvis man ikke ruller to ens
                } else {
                    players[player].setTurnsInARow(0);
                    setPosition(players[player], getLatestRoll()[0].getFaceValue() + getLatestRoll()[1].getFaceValue());
                }
                //hvis det ikke er ens tredje tur
            } else {
                players[player].setTurnsInARow(0);
                setPosition(players[player], dice.rollDice()[0].getFaceValue() + getLatestRoll()[1].getFaceValue());
            }
        }
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
    public Player landOnJailField(int player){
        players[player].setPosition(10);
        players[player].setInJail(true);
        return players[player];
    }
}
