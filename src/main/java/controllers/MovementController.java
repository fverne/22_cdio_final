package controllers;

import model.DiceHolder;
import model.Die;
import model.language.Language;
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

    //giver spillerens forrige placering
    public int getLatestPosition(Player player, int roll) {
        if (player.getPosition() - roll < 0) {
            return 40 + (player.getPosition() - roll);
        } else {
            return (player.getPosition() - roll);
        }
    }

    //logikken bag spillerens bevægelser på spillepladen
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
                if (getLatestRoll()[0].getFaceValue() != getLatestRoll()[1].getFaceValue()) {
                    players[player].setTurnsInARow(0);
                }
            }
        }

        return players[player];
    }

    //sætter spillerens position
    public void setPosition(Player player, int moveLength) {
        int position = player.getPosition();
        int newPosition = (position + moveLength) % 40;

        if (moveLength + position > 39) {
            player.setPosition(newPosition);
            player.deposit(4000);
        } else {
            player.setPosition(newPosition);
        }
    }
    //udskriver besked når spilleren passerer start og modtager 4000
    public String passedStart(int lastPos, int newPos) {
        String msg;
        if (lastPos > newPos) {
            msg = Language.passedStart() + ".";
            return msg;
        }

        return null;
    }

    //spiller lander på fængselsfelt, metode sørger for spilleren bliver sat i fængsel
    public Player landOnJailField(int player) {
        players[player].setPosition(10);
        players[player].setInJail(true);

        return players[player];
    }

    public void teleportPosition(Player player, int position) {
        player.setPosition(position);
    }

    //fjerner en spiller der er udgået fra spillet
    public void deletePlayer(int playerNumber) {
        int x = 0;
        Player[] newPlayers = new Player[players.length - 1];
        for (int i = 0; i < this.players.length; i++) {
            if (i != playerNumber) {
                newPlayers[x] = players[i];
                x++;
            }
        }
        players = newPlayers;
    }
}
