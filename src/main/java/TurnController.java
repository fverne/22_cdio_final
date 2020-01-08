import java.awt.*;

public class TurnController {

    public void Start() {
        int minPlayers = 2;
        int maxPlayers =6;
        int startMoney = 20;

        board = new Board();
        int MAX_PLAYERS = gui.getUserString("Type the amount of players");
        while (MAX_PLAYERS < minPlayers || MAX_PLAYERS > maxPlayers) {
            try {
                MAX_PLAYERS = Integer.parseInt(gui.getUserString("How many players?"));
            } catch (Exception e) {
                gui.showMessage("Error, type in a number between 2-4");
            }

        }

        players = new Player[MAX_PLAYERS];
        guiPlayers = new GUI_Player[MAX_PLAYERS];


        for (int i = 1; MAX_PLAYERS >= i; i++) {
            int s = i - 1;
            String playerName = gui.getUserString("Player " + i + ": What is your name?");
            playerName += i;
            players[s] = new Player(playerName, startMoney, i);

            gui.addPlayer(guiPlayers[s] = new GUI_Player(playerName, startMoney));


        }

        gui.showMessage("Alright, let's get started " + players[Math.random() * MAX_PLAYERS].getName() + " will start");



    }

    public void turn(){

    }


}


