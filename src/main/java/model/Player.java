package model;

import java.util.Arrays;

public class Player {

    //information holding variables

    private int balance;
    private int position;
    private String name;

    private int[] ownedFields = new int[0];
    private boolean inJail;
    private boolean freeOfJail = false;
    private int turnsInJail;
    private int turnsInARow;
    private boolean inAuction = true;

    public Player() {
        position = 0;
        balance = 30000;
    }

    public boolean isInAuction() {
        return inAuction;
    }

    public void setInAuction(boolean inAuction) {
        this.inAuction = inAuction;
    }

    public void setName(String playerName) {
        name = playerName;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int modifier) {
        balance += modifier;
    }

    public void withdraw(int modifier) {
        if (balance - modifier < 0) {
            balance = 0;
        } else {
            balance -= modifier;
        }
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

/*
    public void setPosition(int moveLength) {
        if (moveLength + position > 39){
            position = (position + moveLength) - 40;
            deposit(4000);
        } else {
            position += moveLength;
        }
    }

 */

    public void setOwnership(int field) {
        if (ownedFields.length == 0) {
            ownedFields = new int[1];
            ownedFields[0] = field;
        } else {
            int temp = ownedFields.length;

            ownedFields = Arrays.copyOf(ownedFields, ownedFields.length + 1);
            ownedFields[temp] = field;
        }
    }

    public boolean getOwnership(int field) {
        int temp = 0;
        for (int ownedField : ownedFields) {
            if (ownedField == field) {
                temp++;
                break;
            }
        }

        return temp > 0;
    }

    public int[] getOwnedFields() {
        return ownedFields;
    }

    public void setSpecificPosition(int fieldID) {
        position = fieldID;
    }

    public boolean getInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public boolean getFreeOfJail() {
        return freeOfJail;
    }

    public void setFreeOfJail() {
        this.freeOfJail = true;
    }

    public void setTurnsInARow() {
        turnsInARow++;
    }

    public void setTurnsInARow(int turnsInARow) {
        this.turnsInARow = turnsInARow;
    }

    public void setTurnsInJail() {
        turnsInJail++;
    }

    public void setTurnsInJail(int turnsInJail) {
        this.turnsInJail = turnsInJail;
    }

    public int getTurnsInJail() {
        return turnsInJail;
    }

    public int getTurnsInARow() {
        return turnsInARow;
    }
}
