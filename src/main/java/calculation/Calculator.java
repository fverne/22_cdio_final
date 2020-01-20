package calculation;

import field.Field;
import field.Ownable.Ownable;
import field.Ownable.Property;
import model.Die;
import field.FieldProperty;
import model.Player;

public class Calculator {
    private FieldProperty fieldProperty;

    public Calculator() {
        fieldProperty = new FieldProperty();
    }

    //betal 10% indkomstskat
    public void payTax10(model.Player player) {
        int tax = getPlayerTotalValues(player) / 10;
        player.withdraw(tax);
    }

    //betal fast beløb i tax
    public void payTax(Player player, int fieldNumber) {
        player.withdraw(fieldProperty.getTax(fieldNumber));
    }

    //har en spiller nok penge til at købe ejendom
    public boolean getCredibilityBuy(model.Player player, int fieldNumber) {
        return player.getBalance() >= fieldProperty.getPrice(fieldNumber);
    }

    //få spillerens totalt "networth"
    private int getPlayerTotalValues(Player player) {
        int totalValues = player.getBalance();
        int[] playerOwnedFields = player.getOwnedFields();

        for (int playerOwnedField : playerOwnedFields) {
            Ownable field = (Ownable) fieldProperty.getField(playerOwnedField);
            totalValues += field.getCost();
            if (field instanceof Property) {
                totalValues += ((Property) field).getHouseAmount() * ((Property) field).getHouseCost();
            }
        }

        return totalValues;
    }

    //har en spiller nok penge til at betale leje
    public boolean getCredibilityRent(model.Player player, int fieldNumber) {
        return player.getBalance() >= fieldProperty.getRent(fieldNumber);
    }

    //har en spiller nok penge til at betale tax
    public boolean getCredibilityTax(Player player, int fieldNumber) {
        return player.getBalance() >= fieldProperty.getTax(fieldNumber);
    }

    //har en spiller nok penge til at betale 10% tax
    public boolean getCredibilityTax10(Player player) {
        return player.getBalance() >= getPlayerTotalValues(player) / 10;
    }

    //har en spiller nok penge til at betale hus
    public boolean getCredibilityHouse(model.Player player, int fieldNumber, int amount) {
        return player.getBalance() >= fieldProperty.getHousePrice(fieldNumber) * amount;
    }

    //betal for ejendom med bestemt pris, sætter owner og ownership
    public void buyWithPrice(model.Player player, int fieldNumber, int price) {
        player.withdraw(price);
        fieldProperty.setOwned(player, fieldNumber);
        player.setOwnership(fieldNumber);
    }

    //betal for ejendom for feltets pris, sætter owner og ownership
    public void buyField(model.Player player, int fieldNumber) {
        player.withdraw(fieldProperty.getPrice(fieldNumber));
        fieldProperty.setOwned(player, fieldNumber);
        player.setOwnership(fieldNumber);
    }

    //betal husleje, beregner forskellig leje, alt efter hvor mange huse man har/om grunden er et rederi eller et bryggeri
    public Player payRent(model.Player player, int fieldNumber, Die[] die) {
        int rent = fieldProperty.getRent(fieldNumber);
        model.Player owner = fieldProperty.getOwner(fieldNumber);
        int[] fields = fieldProperty.getFieldCategory(fieldNumber);
        int temp = 0;

        for (int field : fields) {
            try {
                if (owner.equals(fieldProperty.getOwner(field))) {
                    temp++;
                }
            } catch (NullPointerException e) {
                System.out.println(player.getPosition() + " " + fieldNumber);
            }
        }

        if (fieldProperty.getField(fieldNumber) instanceof Property && ((Property) fieldProperty.getField(fieldNumber)).getHouseAmount() == 0 &&
                fields.length == temp) {
            rent = rent * 2;
        }

        if (fieldProperty.getField(fieldNumber) instanceof field.Ownable.Buildings.Ferry) {
            rent = fieldProperty.getRent(fieldNumber, temp);
        }

        if (fieldProperty.getField(fieldNumber) instanceof field.Ownable.Buildings.Brewery) {
            rent = fieldProperty.getRent(fieldNumber, temp) * (die[0].getFaceValue() + die[1].getFaceValue());
        }

        player.withdraw(rent);
        owner.deposit(rent);

        return owner;
    }

    public Field getField(int fieldNumber) {
        return fieldProperty.getField(fieldNumber);
    }

    //køber hus
    public void buyHouse(Player player, int fieldNumber, int amount) {
        int price = (fieldProperty.getHousePrice(fieldNumber) * amount);
        player.withdraw(price);
    }

    //kan der bygges på felt? er feltet ejet, så nej, er feltet frit, så ja
    public boolean isBuildable(int fieldNumber) {
        boolean buildable = false;
        int[] fields = fieldProperty.getFieldCategory(fieldNumber);
        model.Player owner = fieldProperty.getOwner(fieldNumber);

        int temp = 0;
        for (int field : fields) {
            if (owner.equals(fieldProperty.getOwner(field))) {
                temp++;
            }
        }

        if (fieldProperty.getField(fieldNumber) instanceof Property && fields.length == temp) {
            buildable = true;
        }

        return buildable;
    }

    //betal dig ud af fængsel
    public void payBail(Player player) {
        player.withdraw(1000);
        player.setInJail(false);
    }

    //sælges et felt på auktion skal ejerskab mm overføres til ny spiller og væk fra gammel spiller
    public int[] valuesTransfer(Player player, int fieldNumber) {
        int[] fields = player.getOwnedFields();
        Player newOwner = ((Ownable) fieldProperty.getField(fieldNumber)).getOwnedBy();
        for (int field : fields) {
            ((Ownable) fieldProperty.getField(field)).setOwnedBy(newOwner);
            newOwner.setOwnership(field);
        }
        int transfer = player.getBalance();
        player.withdraw(transfer);
        newOwner.deposit(transfer);

        return fields;
    }

    public int[] valuesTransfer(Player player) {
        int[] fields = player.getOwnedFields();
        for (int field : fields) {
            ((Ownable) fieldProperty.getField(field)).setOwnedBy(null);
            if (fieldProperty.getField(field) instanceof Property) {
                ((Property) fieldProperty.getField(field)).setHouseAmount(0);
                ((Property) fieldProperty.getField(field)).setHotelAmount(0);
            }
        }
        int transfer = player.getBalance();
        player.withdraw(transfer);

        return fields;
    }
}
