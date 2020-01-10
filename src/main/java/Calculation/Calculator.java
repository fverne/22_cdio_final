package Calculation;

import Fields.Field;
import model.Player;

public class Calculator {

    FieldProperty fieldProperty;

    public Calculator(){
        fieldProperty = new FieldProperty();
    }

    public boolean getCredibility(model.Player player, int field){

        return true;
    }

    public void buyField(model.Player player, int fieldNumber){
        player.addToBalance(fieldProperty.getPrice(fieldNumber));
        fieldProperty.setOwned(player, fieldNumber);
        player.setOwnership(fieldNumber);
    }

    public void payRent(model.Player player, int fieldNumber){
        int rent = fieldProperty.getRent(fieldNumber);

        player.addToBalance(- rent);

        model.Player owner = fieldProperty.getOwner(fieldNumber);
        owner.addToBalance(rent);
    }

    public Field getField(int fieldNumber){
        return fieldProperty.getField(fieldNumber);
    }

    public void buyHouse(Player player, int fieldNumber){
        int price = fieldProperty.getHousePrice(fieldNumber);
        player.addToBalance(-price);
        fieldProperty.changeHouseAmount(fieldNumber, player);
    }
}
