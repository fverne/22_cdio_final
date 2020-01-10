package model;

import java.util.Arrays;

public class Player {

    private static GUI.Board fieldID = new GUI.Board();

    //information holding variables
    private int balance;
    private int position;
    private int[] ownedFields = new int[0];

    public Player(){
        position = 0;
        balance = 30000;
    }

    public int getBalance() {
        return balance;
    }

    public void addToBalance(int modifier) {
        if (balance + modifier < 0){
            balance = 0;
        } else{
          balance += modifier;
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int moveLength) {
        if (moveLength + position > 39){
            position = (position + moveLength) - 40;
            addToBalance(4000);
        } else {
            position += moveLength;
        }

    }

    public void setOwnership(int field){
        if (ownedFields.length == 0){
            ownedFields = new int[1];
            ownedFields[0] = field;
        } else{
            int temp = ownedFields.length;

            ownedFields = Arrays.copyOf(ownedFields, ownedFields.length + 1);
            ownedFields[temp] = field;
        }
    }

    public boolean getOwnership(int field){
        int temp = 0;
        for (int i = 0; ownedFields.length > i; i++){
            if (ownedFields[i] == field){
                temp++;
                break;
            }
        }

        if (temp > 0){
            return true;
        } else{
            return false;
        }
    }
    public void setSpecificPosition(int fieldID) {
        position = fieldID;
    }

}
