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

    public int getLatestPosition(Player player, int roll) {
        if (player.getPosition() - roll < 0) {
            return 40 + (player.getPosition() - roll);
        } else {
            return (player.getPosition() - roll);
        }
    }

    public Player makeMove(int player) {
        //hvis spilleren er i fængsel
        if (players[player].getInJail()) {
            //hvis man ruller to ens
            if (dice.rollDice()[0].getFaceValue() == getLatestRoll()[1].getFaceValue()) {
                setPosition(players[player], getLatestRoll()[0].getFaceValue() + getLatestRoll()[1].getFaceValue());
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
                setPosition(players[player], dice.rollDice()[0].getFaceValue() + getLatestRoll()[1].getFaceValue());
                if (getLatestRoll()[0].getFaceValue() != getLatestRoll()[1].getFaceValue()){
                    players[player].setTurnsInARow(0);
                }
            }
        }
            return players[player];
        }


    public void setPosition(Player player, int moveLength) {
        int position = player.getPosition();
        int newPosition = (position + moveLength)%40;

        if (moveLength + position > 39) {
            player.setPosition(newPosition);
            player.deposit(4000);
        } else {
            player.setPosition(newPosition);
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
    public void teleportPosition(Player player, int position){
        player.setPosition(position);
    }
}
