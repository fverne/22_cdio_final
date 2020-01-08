package model;


import gui_fields.GUI_Player;

public class Player {
    public Wallet wallet;
    private String name;
    public Player[] players;

    public Player(String playerName) {
        name = playerName;
        wallet = new Wallet(30000);
    }

    //public void setPlayerName(){
    //}

    public String getPlayerName() {
        return name;
    }


}
