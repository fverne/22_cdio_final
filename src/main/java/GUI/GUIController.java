package GUI;

import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;
import model.Language;

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

    public String yesOrNo(String text) {
        return gui.getUserSelection(text, "ja", "nej");
    }

    //Ændrer border på feltet hvis det er ejet
    public void addHouseToGUI(int fieldNumber, int amountToAdd, int housesAlreadyOnField) {

        if (gameBoard.getGUIField(fieldNumber) instanceof GUI_Street) {
            GUI_Street street = (GUI_Street) gameBoard.getGUIField(fieldNumber);
            street.setHouses((amountToAdd + housesAlreadyOnField));
        }
    }

    public void addHotelToGUI(int fieldNumber) {
        if (gameBoard.getGUIField(fieldNumber) instanceof GUI_Street) {
            GUI_Street street = (GUI_Street) gameBoard.getGUIField(fieldNumber);
            street.setHotel(true);
        }
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void initGUIPlayers() {
        numberOfPlayers = setNumberOfPlayersGUI();
        gui_player = new GUI_Player[numberOfPlayers];
        setPlayerNameGUI();
        addPlayersGUI();
    }

    public void displayGUIMsg(String message) {
        if (message == null) {
            //ja det er meningen det her er tomt #bugfixing
        } else
            gui.showMessage(message);
    }
    public String getName(int index){
        return gui_player[index].getName();
    }

    public void rollButtonGUI() {
        gui.getUserButtonPressed("Kast terningerne!", "Ok");
    }

    public void displayRollGUI(int faceValue1, int faceValue2) {
        gui.setDice(faceValue1, faceValue2);
    }

    public void movePlayerGUI(int playerNumber, int newLocation, int lastLocation) {

        for (int i = 0; i < 40; i++) {
            gui.getFields()[i].setCar(gui_player[playerNumber], false);
        }
        gui.getFields()[newLocation].setCar(gui_player[playerNumber], true);
    }

    public void teleportPlayerGUI(int playerNumber, int newLocation) {
        gui.getFields()[newLocation].setCar(gui_player[playerNumber], true);
    }

    public void removeCarGUI(int playerNumber, int newLocation) {
        gui.getFields()[newLocation].setCar(gui_player[playerNumber], false);
    }

    public void setFieldBorderGUI(int fieldNumber, int playerNumber) {
        if (gameBoard.getGUIField(fieldNumber) instanceof GUI_Street) {
            GUI_Street street = (GUI_Street) gameBoard.getGUIField(fieldNumber);
            street.setBorder(Color.BLACK, Color.red);
            street.setSubText("Ejes af: " + gui_player[playerNumber].getName());
        }
    }

    public void displayChancecardGUI(String text) {
        gui.displayChanceCard(text);
    }

    //Opdaterer balance i GUI'en
    public void updatePlayerBalanceGUI(int playerNumber, int newBalance) {
        gui_player[playerNumber].setBalance(newBalance);
    }

    public int getUserIntGUI() {
        return gui.getUserInteger("Indtast pris/bud");
    }
    public int getUserIntWithString (String text ){
        int price = gui.getUserInteger(text);
        return price;
    }

    public int amountOfHousesToBuyGUI() {
        return gui.getUserInteger("Indtast ønsket antal huse: ");
    }

    //Antal spillere
    public int setNumberOfPlayersGUI() {
        int numberOfPlayers = gui.getUserInteger(Language.inputPlayerAmount() + ": ");
        /*
        while (numberOfPlayers < 3 || numberOfPlayers > 6) {
            gui.showMessage("Indtast mellem 3-6 spillere");
            numberOfPlayers = gui.getUserInteger("Indtast antal spillere: ");
        }

         */
        return numberOfPlayers;
    }

    //Spiller navne
    public void setPlayerNameGUI() {
    	String[] prevnames = new String[numberOfPlayers]; // midlertidigt array laves, og tidligere spillernavne gemmes i denne
        boolean nametaken;
        for (int i = 0; i < numberOfPlayers; i++) {
            String name;
            do {                                                 // do while checker om spillerens navn eksisterer i forvejen
                name = gui.getUserString("Indtast navn: ");
                nametaken = false;

                if (name.length() > 25 || name.length() < 1) {
                    gui.showMessage("Det indtastede navn er af ugyldig længde.");
                }

                for (String prevname : prevnames) {
                    if (name.equals(prevname)) {
                        nametaken = true;
                        gui.showMessage("Navnet er taget af en anden spiller.");
                    }
                }
            } while ((name.length() > 25 || name.length() < 1) || nametaken);       // spilleren skal indtaste et nyt navn indtil det er korrekt

            gui_player[i] = new GUI_Player(name);       // spilleren oprettes med det tilladte navn
            prevnames[i] = name;
        }
    }

    //Adder spillerne til GUI
    public void addPlayersGUI() {
        for (int i = 0; i < numberOfPlayers; i++) {
            gui_player[i].setBalance(30000);
            gui_player[i].getName();
            gui_player[i] = new GUI_Player(gui_player[i].getName(), gui_player[i].getBalance());
            gui_player[i].getCar().setPrimaryColor(colors[i]);
            gui.addPlayer(gui_player[i]);
        }
    }

    public int getNumberOfGUIPlayers(){
        return numberOfPlayers;
    }
}