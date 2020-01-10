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
        player.withdraw(fieldProperty.getPrice(fieldNumber));
        fieldProperty.setOwned(player, fieldNumber);
        player.setOwnership(fieldNumber);
    }

    public void payRent(model.Player player, int fieldNumber){
        int rent = fieldProperty.getRent(fieldNumber);

        player.withdraw(rent);

        model.Player owner = fieldProperty.getOwner(fieldNumber);
        owner.deposit(rent);
    }

    public Field getField(int fieldNumber){
        return fieldProperty.getField(fieldNumber);
    }

    public void buyHouse(Player player, int fieldNumber, int amount){
        int price = (fieldProperty.getHousePrice(fieldNumber)*amount);
        player.withdraw(price);
        fieldProperty.changeHouseAmount(fieldNumber, player);
    }
}
