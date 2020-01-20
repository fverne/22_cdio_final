package controllers;

import model.field.Field;
import model.field.ownable.Ownable;
import model.field.ownable.Property;
import model.Die;
import model.GameBoard;
import model.Player;

public class Calculator {

    private GameBoard gameBoard;

    public Calculator() {
        gameBoard = new GameBoard();
    }

    //betal 10% indkomstskat
    public void payTax10(model.Player player) {
        int tax = getPlayerTotalValues(player) / 10;
        player.withdraw(tax);
    }

    //betal fast beløb i tax
    public void payTax(Player player, int fieldNumber) {
        player.withdraw(gameBoard.getTax(fieldNumber));
    }

    //har en spiller nok penge til at købe ejendom
    public boolean getCredibilityBuy(model.Player player, int fieldNumber) {
        return player.getBalance() >= gameBoard.getPrice(fieldNumber);
    }

    //få spillerens totalt "networth"
    private int getPlayerTotalValues(Player player) {
        int totalValues = player.getBalance();
        int[] playerOwnedFields = player.getOwnedFields();

        for (int playerOwnedField : playerOwnedFields) {
            Ownable field = (Ownable) gameBoard.getField(playerOwnedField);
            totalValues += field.getCost();
            if (field instanceof Property) {
                totalValues += ((Property) field).getHouseAmount() * ((Property) field).getHouseCost();
            }
        }

        return totalValues;
    }

    //har en spiller nok penge til at betale leje
    public boolean getCredibilityRent(model.Player player, int fieldNumber) {
        return player.getBalance() >= gameBoard.getRent(fieldNumber);
    }

    //har en spiller nok penge til at betale tax
    public boolean getCredibilityTax(Player player, int fieldNumber) {
        return player.getBalance() >= gameBoard.getTax(fieldNumber);
    }

    //har en spiller nok penge til at betale 10% tax
    public boolean getCredibilityTax10(Player player) {
        return player.getBalance() >= getPlayerTotalValues(player) / 10;
    }

    //har en spiller nok penge til at betale hus
    public boolean getCredibilityHouse(model.Player player, int fieldNumber, int amount) {
        return player.getBalance() >= gameBoard.getHousePrice(fieldNumber) * amount;
    }

    //betal for ejendom med bestemt pris, sætter owner og ownership
    public void buyWithPrice(model.Player player, int fieldNumber, int price) {
        player.withdraw(price);
        gameBoard.setOwned(player, fieldNumber);
        player.setOwnership(fieldNumber);
    }

    //betal for ejendom for feltets pris, sætter owner og ownership
    public void buyField(model.Player player, int fieldNumber) {
        player.withdraw(gameBoard.getPrice(fieldNumber));
        gameBoard.setOwned(player, fieldNumber);
        player.setOwnership(fieldNumber);
    }

    //betal husleje, beregner forskellig leje, alt efter hvor mange huse man har/om grunden er et rederi eller et bryggeri
    public Player payRent(model.Player player, int fieldNumber, Die[] die) {
        int rent = gameBoard.getRent(fieldNumber);
        model.Player owner = gameBoard.getOwner(fieldNumber);

        int[] fields = gameBoard.getFieldCategory(fieldNumber);
        int temp = 0;

        for (int field : fields) {
            try {
                if (owner.equals(gameBoard.getOwner(field))) {
                    temp++;
                }
            } catch (NullPointerException e) {
                System.out.println(player.getPosition() + " " + fieldNumber);
            }
        }

        if (gameBoard.getField(fieldNumber) instanceof Property && ((Property) gameBoard.getField(fieldNumber)).getHouseAmount() == 0 &&
                fields.length == temp) {
            rent = rent * 2;
        }

        if (gameBoard.getField(fieldNumber) instanceof model.field.ownable.buildings.Ferry) {
            rent = gameBoard.getRent(fieldNumber, temp);
        }

        if (gameBoard.getField(fieldNumber) instanceof model.field.ownable.buildings.Brewery) {
            rent = gameBoard.getRent(fieldNumber, temp) * (die[0].getFaceValue() + die[1].getFaceValue());
        }

        player.withdraw(rent);
        owner.deposit(rent);

        return owner;
    }

    public Field getField(int fieldNumber) {
        return gameBoard.getField(fieldNumber);
    }

    //køber hus
    public void buyHouse(Player player, int fieldNumber, int amount) {
        int price = (gameBoard.getHousePrice(fieldNumber) * amount);
        player.withdraw(price);
        gameBoard.changeHouseAmount(fieldNumber, player, amount);
    }

    //kan der bygges på felt? er feltet ejet, så nej, er feltet frit, så ja
    public boolean isBuildable(int fieldNumber) {
        boolean buildable = false;
        int[] fields = gameBoard.getFieldCategory(fieldNumber);
        model.Player owner = gameBoard.getOwner(fieldNumber);

        int temp = 0;
        for (int field : fields) {
            if (owner.equals(gameBoard.getOwner(field))) {
                temp++;
            }
        }

        if (gameBoard.getField(fieldNumber) instanceof Property && fields.length == temp) {
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
        Player newOwner = ((Ownable) gameBoard.getField(fieldNumber)).getOwnedBy();
        for (int field : fields) {
            ((Ownable) gameBoard.getField(field)).setOwnedBy(newOwner);
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
            ((Ownable) gameBoard.getField(field)).setOwnedBy(null);
            if (gameBoard.getField(field) instanceof Property) {
                ((Property) gameBoard.getField(field)).setHouseAmount(0);
            }
        }
        int transfer = player.getBalance();
        player.withdraw(transfer);

        return fields;
    }
}
