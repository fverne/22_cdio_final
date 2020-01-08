package GUI;

import gui_fields.GUI_Player;
import gui_main.GUI;
import model.Player;

import java.awt.*;

public class GUIController {
    private Board gameBoard = new Board();
    private GUI gui;
    public GUI_Player[] gui_player;

    private Color[] colors = new Color[]{Color.red, Color.BLACK, Color.blue, Color.GREEN, Color.CYAN, Color.DARK_GRAY};
    int numberOfPlayers;

    public GUIController() {
        gui = gameBoard.initGUI();
    }

    public void initPlayers() {
        numberOfPlayers = setNumberOfPlayers();
        gui_player = new GUI_Player[numberOfPlayers];
        setPlayerName();
        addGUIPlayers();
    }

    public void displayRollGUI(int faceValue1, int faceValue2){
        gui.setDice(faceValue1,faceValue2);
    }

    public void movePlayer(int playerNumber, int newLocation){

        int deleteCarFromField = newLocation-1;
        for (int i = 0; i <= deleteCarFromField; i++) {
            gui.getFields()[i].setCar(gui_player[playerNumber], false);
        }
        gui.getFields()[newLocation].setCar(gui_player[playerNumber],true);
    }

    //Opdaterer balance i GUI'en
    public void updatePlayerBalance(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
        gui_player[i].setBalance(gui_player[i].getBalance());
        }
    }

    //Antal spillere
    public int setNumberOfPlayers() {
        int numberOfPlayers = gui.getUserInteger("Indtast antal spillere: ");
        while (numberOfPlayers < 3 || numberOfPlayers > 6) {
            gui.showMessage("Indtast mellem 3-6 spillere");
            numberOfPlayers = gui.getUserInteger("Indtast antal spillere: ");
        }
        return numberOfPlayers;
    }

    //Spiller navne
    public void setPlayerName() {
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = gui.getUserString("Indtast navn: ");
            gui_player[i] = new GUI_Player(name);
            while (gui_player[i].getName().length() > 25) {
                gui.showMessage("Indtast venligst et navne på færre end 25 karakterer");
                gui_player[i].setName(name);
            }
        }
    }

    //Adder spillerne til GUI
    public void addGUIPlayers() {
        for (int i = 0; i < numberOfPlayers; i++) {
            gui_player[i].getName();
            gui_player[i] = new GUI_Player(gui_player[i].getName(), gui_player[i].getBalance());
            gui_player[i].getCar().setPrimaryColor(colors[i]);
            gui.addPlayer(gui_player[i]);
        }
    }
}