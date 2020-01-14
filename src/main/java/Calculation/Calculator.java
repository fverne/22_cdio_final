package Calculation;

import Fields.Field;
import model.Player;

public class Calculator {

    private FieldProperty fieldProperty;

    public Calculator(){
        fieldProperty = new FieldProperty();
    }

    public boolean getCredibilityBuy(model.Player player, int fieldNumber){
        if (player.getBalance() < fieldProperty.getPrice(fieldNumber)){
            return false;
        } else {
            return  true;
        }
    }

    public boolean getCredibilityRent(model.Player player, int fieldNumber){
        if (player.getBalance() < fieldProperty.getRent(fieldNumber)){
            return false;
        } else {
            return true;
        }
    }

    public boolean getCredibilityHouse(model.Player player, int fieldNumber, int amount){
        if (player.getBalance() < fieldProperty.getHousePrice(fieldNumber) * amount){
            return false;
        } else {
            return true;
        }
    }

    public void buyWithPrice(model.Player player, int fieldNumber, int price){
        player.withdraw(price);
        fieldProperty.setOwned(player, fieldNumber);
        player.setOwnership(fieldNumber);
    }

    public void buyField(model.Player player, int fieldNumber){
        player.withdraw(fieldProperty.getPrice(fieldNumber));
        fieldProperty.setOwned(player, fieldNumber);
        player.setOwnership(fieldNumber);
    }

    public void payRent(model.Player player, int fieldNumber){
        int rent = fieldProperty.getRent(fieldNumber);

        if (!fieldProperty.getOwner(fieldNumber).getInJail()) {
            player.withdraw(rent);

            model.Player owner = fieldProperty.getOwner(fieldNumber);
            owner.deposit(rent);
        }
    }

    public Field getField(int fieldNumber){
        return fieldProperty.getField(fieldNumber);
    }

    public void buyHouse(Player player, int fieldNumber, int amount){
        int price = (fieldProperty.getHousePrice(fieldNumber)*amount);
        player.withdraw(price);
        fieldProperty.changeHouseAmount(fieldNumber, player);
    }

    public void payBail(Player player){
        player.withdraw(1000);
        player.setInJail(false);
    }
}
