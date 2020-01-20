package controllers;

import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;
import model.language.Language;
import view.GUIGameBoard;

import java.awt.*;

public class GUIController {
    private GUIGameBoard guiGameBoard = new GUIGameBoard();
    private GUI gui;
    private GUI_Player[] gui_player;
    private Color[] colors = new Color[]{Color.red, Color.BLACK, Color.blue, Color.GREEN, Color.CYAN, Color.DARK_GRAY};
    private int numberOfPlayers;

    public GUIController() {
        gui = guiGameBoard.initGUI();
    }

    //knap i gui
    public void getUserResponse(int player) {
        gui.getUserButtonPressed(Language.getUserResponse(gui_player[player].getName()), Language.ok());
    }

    //vinder besked i GUI
    public void playerWins() {
        gui.getUserButtonPressed(Language.winnerMessage(gui_player[0].getName()), Language.ok());
        gui_player[0].setBalance(1000000000);
    }

    //giver brugeren valget mellem 2 muligheder
    public Boolean getUserDecision(String message, String option1, String option2) {
        return gui.getUserLeftButtonPressed(message, option1, option2);
    }

    //ok knap i gui
    public void getUserResponse(String message) {
        gui.getUserButtonPressed(message, Language.ok());
    }

    //ja/nej knap i GUI
    public Boolean yesNoButton(String msg) {
        return gui.getUserLeftButtonPressed(msg, Language.yes(), Language.no());
    }

    //Tilføjer et hus til GUI
    public void addHouseToGUI(int fieldNumber, int amountToAdd, int housesAlreadyOnField) {

        if (guiGameBoard.getGUIField(fieldNumber) instanceof GUI_Street) {
            GUI_Street street = (GUI_Street) guiGameBoard.getGUIField(fieldNumber);
            street.setHouses((amountToAdd + housesAlreadyOnField));
        }
    }

    //tilføjer et hotel til GUI
    public void addHotelToGUI(int fieldNumber) {
        if (guiGameBoard.getGUIField(fieldNumber) instanceof GUI_Street) {
            GUI_Street street = (GUI_Street) guiGameBoard.getGUIField(fieldNumber);
            street.setHotel(true);
        }
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    //tilføjer spillerne og deres informationer til GUI
    public void initGUIPlayers() {
        numberOfPlayers = setNumberOfPlayersGUI();
        gui_player = new GUI_Player[numberOfPlayers];
        setPlayerNameGUI();
        addPlayersGUI();
    }

    //Udskriver besked i gui
    public void displayGUIMsg(String message) {
        if (message == null) {
            //ja det er meningen det her er tomt #bugfixing
        } else
            gui.showMessage(message);
    }

    public String getName(int index) {
        return gui_player[index].getName();
    }

    //knap der muliggøre terninge kast
    public void rollButtonGUI() {
        gui.getUserButtonPressed(Language.throwDies() + "!", Language.ok());
    }

    //knap der viser terningernes øjne
    public void displayRollGUI(int faceValue1, int faceValue2) {
        gui.setDice(faceValue1, 2, 3, faceValue2, 3, 3);
    }

    //metode der rykker spillerne rundt på spilpladen
    public void movePlayerGUI(int playerNumber, int latestlocation, int roll) throws InterruptedException {

        for (int i = roll; i > 0; i--) {
            gui.getFields()[latestlocation].setCar(gui_player[playerNumber], false);
            latestlocation++;
            latestlocation = latestlocation % 40;
            gui.getFields()[latestlocation].setCar(gui_player[playerNumber], true);

            Thread.sleep(200);
        }
    }

    //teleportere spiller rundt på pladen(kan bruges når en spiller skal ud af fængsel eller trækker et kort der rykker ham til en bestemt grund
    public void teleportPlayerGUI(int playerNumber, int newLocation) {
        gui.getFields()[newLocation].setCar(gui_player[playerNumber], true);
    }

    //fjerner bil fra gui
    public void removeCarGUI(int playerNumber, int newLocation) {
        gui.getFields()[newLocation].setCar(gui_player[playerNumber], false);
    }

    //ændrer feltets ramme når feltet er ejet, samt ændrer feltets substring til ejerens navn
    public void setFieldBorderGUI(int fieldNumber, int playerNumber) {
        if (guiGameBoard.getGUIField(fieldNumber) instanceof GUI_Street) {
            GUI_Street street = (GUI_Street) guiGameBoard.getGUIField(fieldNumber);
            street.setBorder(Color.BLACK, Color.red);
            street.setSubText(Language.ownedBy() + ": " + gui_player[playerNumber].getName());
        }
    }

    //fjerner border og substring fra felt
    private void removeFieldBorderGUI(int fieldNumber) {
        if (guiGameBoard.getGUIField(fieldNumber) instanceof GUI_Street) {
            GUI_Street street = (GUI_Street) guiGameBoard.getGUIField(fieldNumber);
            street.setBorder(Color.BLACK, Color.WHITE);
            street.setSubText(Language.notOwned());
        }
    }

    //viser informationer om et chancekort i gui
    public void displayChancecardGUI(String text) {
        gui.displayChanceCard(text);
    }

    //Opdaterer balance i GUI'en
    public void updatePlayerBalanceGUI(int playerNumber, int newBalance) {
        gui_player[playerNumber].setBalance(newBalance);
    }

    //tager imod integer fra bruger
    public int getUserIntGUI() {
        return gui.getUserInteger(Language.enterPrice());
    }

    public int getUserIntWithString(String text) {
        int price = gui.getUserInteger(text);

        return price;
    }

    //tager imod en int fra bruger
    public int amountOfHousesToBuyGUI() {
        return gui.getUserInteger(Language.enterHouses() + ": ");
    }

    //metode der tager imod integer fra spiller og sætter antallet af spillere
    private int setNumberOfPlayersGUI() {
        int numberOfPlayers = gui.getUserInteger(Language.enterPlayerAmount() + ": ");

        while (numberOfPlayers < 3 || numberOfPlayers > 6) {
            gui.showMessage(Language.enterCorrectPlayerAmount() + ": ");
            numberOfPlayers = gui.getUserInteger(Language.enterPlayerAmount() + ": ");
        }

        return numberOfPlayers;
    }

    //tager imod spillernes navne, sørger for ingen spiller hedder det samme
    private void setPlayerNameGUI() {
        String[] prevnames = new String[numberOfPlayers]; // midlertidigt array laves, og tidligere spillernavne gemmes i denne
        boolean nametaken;
        for (int i = 0; i < numberOfPlayers; i++) {
            String name;
            do {                                                 // do while checker om spillerens navn eksisterer i forvejen
                name = gui.getUserString(Language.enterName() + ": ");
                nametaken = false;

                if (name.length() > 25 || name.length() < 1) {
                    gui.showMessage(Language.illegalLength() + ".");
                }

                for (String prevname : prevnames) {
                    if (name.equals(prevname)) {
                        nametaken = true;
                        gui.showMessage(Language.illegalName() + ".");
                    }
                }
            } while ((name.length() > 25 || name.length() < 1) || nametaken);       // spilleren skal indtaste et nyt navn indtil det er korrekt

            gui_player[i] = new GUI_Player(name);       // spilleren oprettes med det tilladte navn
            prevnames[i] = name;
        }
    }

    //tilføjer spillerne til GUI
    private void addPlayersGUI() {
        for (int i = 0; i < numberOfPlayers; i++) {
            gui_player[i].setBalance(30000);
            gui_player[i].getName();
            gui_player[i] = new GUI_Player(gui_player[i].getName(), gui_player[i].getBalance());
            gui_player[i].getCar().setPrimaryColor(colors[i]);
            gui.addPlayer(gui_player[i]);
        }
    }

    // Fjern spillern fra gui player arrayet
    public void deleteCar(int playerNr, int pos) {
        removeCarGUI(playerNr, pos);
        GUI_Player[] newGuiPl = new GUI_Player[gui_player.length - 1];
        int x = 0;
        for (int i = 0; i < gui_player.length; i++) {
            if (i != playerNr) {
                newGuiPl[x] = gui_player[i];
                x++;
            }
        }
        gui_player = newGuiPl;

        this.numberOfPlayers = gui_player.length;
    }

    //fjerner spillers owned border
    public void remmovePlayerOwned(int[] owned) {
        for (int x : owned) {
            removeFieldBorderGUI(x);
        }
    }
}