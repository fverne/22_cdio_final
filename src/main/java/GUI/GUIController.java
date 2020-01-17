package GUI;

import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;
import model.Language;

import java.awt.*;

public class GUIController {
    private Board gameBoard = new Board();
    private GUI gui;
    private GUI_Player[] gui_player;
    private Color[] colors = new Color[]{Color.red, Color.BLACK, Color.blue, Color.GREEN, Color.CYAN, Color.DARK_GRAY};
    private int numberOfPlayers;

    public GUIController() {
        gui = gameBoard.initGUI();
    }

    public void getUserResponse(int player){
        gui.getUserButtonPressed("Det er " + gui_player[player].getName() + "'s tur", "OK");
    }

    public void playerWins(){
        gui.getUserButtonPressed("Tillykke " + gui_player[0].getName() + " du er spillets vinder", "OK");
        gui_player[0].setBalance(1000000000);
    }

    public String getUserDecision(String message, String option1, String option2){
        return gui.getUserButtonPressed(message, option1, option2);
    }

    public void getUserResponse(String message){
        gui.getUserButtonPressed(message, "OK");
    }

    public String yesNoButton(String msg) {
        return gui.getUserButtonPressed(msg, "ja", "nej");
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

    public String getName(int index) {
        return gui_player[index].getName();
    }

    public void rollButtonGUI() {
        gui.getUserButtonPressed("Kast terningerne!", "Ok");
    }

    public void displayRollGUI(int faceValue1, int faceValue2) {
        gui.setDice(faceValue1, 2, 3, faceValue2, 3, 3);
    }

    public void movePlayerGUI(int playerNumber, int latestlocation, int roll) throws InterruptedException {

        for (int i = roll; i > 0; i--) {
            gui.getFields()[latestlocation].setCar(gui_player[playerNumber], false);
            latestlocation++;
            latestlocation = latestlocation%40;
            gui.getFields()[latestlocation].setCar(gui_player[playerNumber], true);

            Thread.sleep(200);
        }
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
    private void removeFieldBorderGUI(int fieldNumber){
        if (gameBoard.getGUIField(fieldNumber) instanceof GUI_Street) {
            GUI_Street street = (GUI_Street) gameBoard.getGUIField(fieldNumber);
            street.setBorder(Color.BLACK, Color.WHITE);
            street.setSubText("Ikke ejet");
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

    public int getUserIntWithString(String text) {
        int price = gui.getUserInteger(text);
        return price;
    }

    public int amountOfHousesToBuyGUI() {
        return gui.getUserInteger("Indtast ønsket antal huse: ");
    }

    //Antal spillere
    private int setNumberOfPlayersGUI() {
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
    private void setPlayerNameGUI() {
        String[] prevnames = new String[numberOfPlayers]; // midlertidigt array laves, og tidligere spillernavne gemmes i denne
        boolean nametaken;
        for (int i = 0; i < numberOfPlayers; i++) {
            String name;
            do
            {                                                 // do while checker om spillerens navn eksisterer i forvejen
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
    private void addPlayersGUI() {
        for (int i = 0; i < numberOfPlayers; i++) {
            gui_player[i].setBalance(30000);
            gui_player[i].getName();
            gui_player[i] = new GUI_Player(gui_player[i].getName(), gui_player[i].getBalance());
            gui_player[i].getCar().setPrimaryColor(colors[i]);
            gui.addPlayer(gui_player[i]);
        }
    }
    public void deleteCar(int playerNr, int pos){ // Fjern spillern fra gui player arrayet

        removeCarGUI(playerNr, pos);
        GUI_Player[] newGuiPl = new GUI_Player[gui_player.length - 1];
        int x = 0;
        for(int i =0; i< gui_player.length; i++ ){
            if(i != playerNr){
                newGuiPl[x] = gui_player[i];
                x++;
            }
        }
        gui_player = newGuiPl;
        System.out.println("Updater number of player = " + gui_player.length);
        System.out.println("new player aarya er = " + newGuiPl.length);
        this.numberOfPlayers = gui_player.length;

    }

    public void remmovePlayerOwned(int[] owned){
        for (int x : owned){
            removeFieldBorderGUI(x);
        }

    }

}