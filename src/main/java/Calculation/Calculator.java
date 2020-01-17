package Calculation;

import Fields.Field;
import Fields.Ownable.Building;
import Fields.Ownable.Property;
import model.Die;
import model.Player;

public class Calculator {

    private FieldProperty fieldProperty;

    public Calculator(){
        fieldProperty = new FieldProperty();
    }

    public void payTax10(model.Player player){
        int tax = getPlayerTotalValues(player) / 10;
        player.withdraw(tax);
    }

    public void payTax(Player player, int fieldNumber){
        player.withdraw(fieldProperty.getTax(fieldNumber));
    }

    public boolean getCredibilityBuy(model.Player player, int fieldNumber){
        if (player.getBalance() < fieldProperty.getPrice(fieldNumber)){
            return false;
        } else {
            return  true;
        }
    }

    public int getPlayerTotalValues(Player player){
        int totalValues = player.getBalance();
        int[] playerOwnedFields = player.getOwnedFields();
        for (int i = 0; playerOwnedFields.length > i; i++){
            Fields.Ownerable field = (Fields.Ownerable) fieldProperty.getField(playerOwnedFields[i]);
            totalValues += field.getCost();
            if (field instanceof Fields.Ownable.Property){
                totalValues += ((Property) field).getHouseAmount() * ((Property) field).getHouseCost();
            }
        }
        return totalValues;
    }

    public boolean getCredibilityRent(model.Player player, int fieldNumber){
        if (player.getBalance() < fieldProperty.getRent(fieldNumber)){
            return false;
        } else {
            return true;
        }
    }

    public boolean getCredibilityTax(Player player, int fieldNumber){
        if (player.getBalance() < fieldProperty.getTax(fieldNumber)){
            return false;
        } else {
            return true;
        }
    }

    public boolean getCredibilityTax10(Player player){
        if (player.getBalance() < getPlayerTotalValues(player) / 10){
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

    public Player payRent(model.Player player, int fieldNumber, Die[] die){
        int rent = fieldProperty.getRent(fieldNumber);
        model.Player owner = fieldProperty.getOwner(fieldNumber);

        int[] fields = fieldProperty.getFieldCategory(fieldNumber);
        int temp = 0;

        for (int i = 0; i < fields.length; i++){
            try {
                if (owner.equals(fieldProperty.getOwner(fields[i]))) {
                    temp++;
                }
            } catch (NullPointerException e){
                System.out.println(player.getPosition() + " " + fieldNumber);
            }
        }
        if (fieldProperty.getField(fieldNumber) instanceof Property && ((Property) fieldProperty.getField(fieldNumber)).getHouseAmount() == 0 &&
        fields.length == temp){
            rent = rent *2;
        }
        if (fieldProperty.getField(fieldNumber) instanceof Fields.Ownable.Buildings.Ferry){
            rent = fieldProperty.getRent(fieldNumber, temp);
        }
        if (fieldProperty.getField(fieldNumber) instanceof  Fields.Ownable.Buildings.Brewery){
            rent = fieldProperty.getRent(fieldNumber, temp) * (die[0].getFaceValue() + die[1].getFaceValue());
        }

        player.withdraw(rent);
        owner.deposit(rent);
        return owner;
    }

    public Field getField(int fieldNumber){
        return fieldProperty.getField(fieldNumber);
    }

    public void buyHouse(Player player, int fieldNumber, int amount){
        int price = (fieldProperty.getHousePrice(fieldNumber)*amount);
        player.withdraw(price);
        //fieldProperty.changeHouseAmount(fieldNumber, player, amount);
    }

    public boolean isBuildable(int fieldNumber){
        boolean buildable = false;
        int[] fields = fieldProperty.getFieldCategory(fieldNumber);
        model.Player owner = fieldProperty.getOwner(fieldNumber);

        int temp = 0;
        for (int i = 0; i < fields.length; i++){
            if (owner.equals(fieldProperty.getOwner(fields[i]))){
                temp++;
            }
        }

        if (fieldProperty.getField(fieldNumber) instanceof Property && fields.length==temp){
            buildable = true;
        }
        return buildable;
    }

    public void payBail(Player player){
        player.withdraw(1000);
        player.setInJail(false);
    }

    public int[] valuesTransfer(Player player, int fieldNumber){
        int[] fields = player.getOwnedFields();
        Player newOwner = ( (Fields.Ownerable) fieldProperty.getField(fieldNumber)).getOwnedBy();
        for (int field : fields) {
            ((Fields.Ownerable) fieldProperty.getField(field)).setOwnedBy(newOwner);
            newOwner.setOwnership(field);
        }
        int transfer = player.getBalance();
        player.withdraw(transfer);
        newOwner.deposit(transfer);
        return fields;
    }

    public int[] valuesTransfer(Player player){
        int[] fields = player.getOwnedFields();
        for (int field : fields) {
            ((Fields.Ownerable) fieldProperty.getField(field)).setOwnedBy(null);
        }
        int transfer = player.getBalance();
        player.withdraw(transfer);
        return fields;
    }
}
