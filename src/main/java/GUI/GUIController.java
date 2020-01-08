package GUI;
import model.*;

import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;

public class GUIController {
    private Board gameBoard = new Board();
    private GUI gui;
    public GUI_Player[] gui_player;
    private String playerName;
    private Color[] colors = new Color[]{Color.red, Color.BLACK, Color.blue, Color.GREEN};
    int numberOfPlayers;

    public GUIController() {
        gui = gameBoard.initGUI();
    }

    public void initGUIPlayers(){
        numberOfPlayers = setNumberOfPlayers();
        setPlayerName(numberOfPlayers);
        createGUIPlayers(numberOfPlayers);
    }

    public void updatePlayerBalance(int playerNum) {
        gui_player[playerNum].setBalance(gui_player[playerNum].getBalance());
    }

    //beder brugeren om et antal spillere, udskriver fejl meddelelse hvis der indtastes for få eller for mange spilelre
    public int setNumberOfPlayers() {
        int numberOfPlayers = gui.getUserInteger("Indtast antal spillere: ");
        while (numberOfPlayers < 3 || numberOfPlayers > 6) {
            gui.showMessage("Indtast mellem 3-6 spillere");
            numberOfPlayers = gui.getUserInteger("Indtast antal spillere: ");
        }
        return numberOfPlayers;
    }

    public void setPlayerName(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            playerName = gui.getUserString("Indtast navn: ");
            while (playerName.length() > 25) {
                gui.showMessage("Indtast venligst et navne på færre end 25 karakterer");
                playerName = gui.getUserString("Indtast navn: ");
            }
        }
    }

    public String getPlayerName() {
        return playerName;
    }


    //initialiserer alle spillerne og dertilhørende informationer
    public void createGUIPlayers(int numberOfPlayers) {

        gui_player = new GUI_Player[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            gui_player[i].getName();
            gui_player[i] = new GUI_Player(gui_player[i].getName(), gui_player[i].getBalance());
            gui_player[i].getCar().setPrimaryColor(colors[i]);
            gui.addPlayer(gui_player[i]);
        }
    }


}


