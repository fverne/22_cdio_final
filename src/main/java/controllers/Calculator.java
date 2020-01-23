package controllers;

import model.fields.Field;
import model.fields.ownable.Ownable;
import model.fields.ownable.Property;
import model.Die;
import model.GameBoard;
import model.Player;

public class Calculator {

    private GameBoard gameBoard;

    public Calculator() {
        gameBoard = new GameBoard();
    }

    /**
     *betal 10% indkomstskat
     * @param player - tager imod spiller objekt
     */
    public void payTax10(model.Player player) {
        int tax = getPlayerTotalValues(player) / 10;
        player.withdraw(tax);
    }

    /**
     * betaler tax
     * @param player - player objekt
     * @param fieldNumber - felt index
     */
    public void payTax(Player player, int fieldNumber) {
        player.withdraw(gameBoard.getTax(fieldNumber));
    }

    /**
     * har en spiller nok penge til at købe ejendom
     * @param player - player objekt
     * @param fieldNumber - felt index
     * @return true/false
     */
    public boolean getCredibilityBuy(Player player, int fieldNumber) {
        return player.getBalance() >= gameBoard.getPrice(fieldNumber);
    }

    /**
     * beregner spillerens totale værdi
     * @param player - player objekt
     * @return - total værdi
     */
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

    /**
     * har en spiller nok penge til at betale leje
     * @param player - player objekt
     * @param fieldNumber - felt index
     * @return true/false
     */
    public boolean getCredibilityRent(Player player, int fieldNumber) {
        return player.getBalance() >= gameBoard.getRent(fieldNumber);
    }

    /**
     *har en spiller nok penge til at betale tax
     * @param player - player objekt
     * @param fieldNumber - felt index
     * @return true/false
     */
    public boolean getCredibilityTax(Player player, int fieldNumber) {
        return player.getBalance() >= gameBoard.getTax(fieldNumber);
    }

    /**
     * har en spiller nok penge til at betale 10% tax
     * @param player - player objekt
     * @return - true/false
     */
    public boolean getCredibilityTax10(Player player) {
        return player.getBalance() >= getPlayerTotalValues(player) / 10;
    }

    /**
     * har en spiller nok penge til at betale hus
     * @param player - player objekt
     * @param fieldNumber - felt index
     * @param amount - antal huse
     * @return - true/false
     */
    public boolean getCredibilityHouse(Player player, int fieldNumber, int amount) {
        return player.getBalance() >= gameBoard.getHousePrice(fieldNumber) * amount;
    }

    /**
     * betal for ejendom med bestemt pris, sætter owner og ownership
     * @param player - player objekt
     * @param fieldNumber - felt index
     * @param price - pris
     */
    public void buyWithPrice(Player player, int fieldNumber, int price) {
        player.withdraw(price);
        gameBoard.setOwned(player, fieldNumber);
        player.setOwnership(fieldNumber);
    }

    /**
     * betal for ejendom for feltets pris, sætter owner og ownership
     * @param player - player objekt
     * @param fieldNumber - felt index
     */
    public void buyField(Player player, int fieldNumber) {
        player.withdraw(gameBoard.getPrice(fieldNumber));
        gameBoard.setOwned(player, fieldNumber);
        player.setOwnership(fieldNumber);
    }

    /**
     * betal husleje, beregner forskellig leje, alt efter hvor mange huse man har/om grunden er et rederi eller et bryggeri
     * @param player - player objekt
     * @param fieldNumber - felt index
     * @param die - terning array
     * @return - feltets ejer
     */
    public Player payRent(Player player, int fieldNumber/*, Die[] die*/) {
        int rent = gameBoard.getRent(fieldNumber);
        Player owner = gameBoard.getOwner(fieldNumber);

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

        if (gameBoard.getField(fieldNumber) instanceof model.fields.ownable.buildings.Ferry) {
            rent = gameBoard.getRent(fieldNumber, temp);
        }

        //if (gameBoard.getField(fieldNumber) instanceof model.fields.ownable.buildings.Brewery) {
          //  rent = gameBoard.getRent(fieldNumber, temp) * (die[0].getFaceValue() + die[1].getFaceValue());
        //}

        player.withdraw(rent);
        owner.deposit(rent);

        return owner;
    }

    public boolean getOwnerAll(int fieldNumber){
        boolean temp1 = false;
        Player owner = gameBoard.getOwner(fieldNumber);

        int[] fields = gameBoard.getFieldCategory(fieldNumber);
        int temp = 0;

        for (int field : fields) {
            if (owner.equals(gameBoard.getOwner(field))) {
                    temp++;
            }
        }

        if (gameBoard.getField(fieldNumber) instanceof Property && ((Property) gameBoard.getField(fieldNumber)).getHouseAmount() == 0 &&
                fields.length == temp) {
            temp1 = true;
        }

        return temp1;
    }


    public Field getField(int fieldNumber) {
        return gameBoard.getField(fieldNumber);
    }

    /**
     * køb hus
     * @param player - spiller objekt
     * @param fieldNumber - felt index
     * @param amount - antal ønskede huse
     */
    public void buyHouse(Player player, int fieldNumber, int amount) {
            int price = (gameBoard.getHousePrice(fieldNumber) * amount);
            player.withdraw(price);
        gameBoard.changeHouseAmount(fieldNumber, player, amount);
    }

    /**
     *kan der bygges på felt? er feltet ejet, så nej, er feltet frit, så ja
     * @param fieldNumber - felt index
     * @return true/false
     */
    public boolean isBuildable(int fieldNumber) {
        boolean buildable = false;
        int[] fields = gameBoard.getFieldCategory(fieldNumber);
        Player owner = gameBoard.getOwner(fieldNumber);

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

    /**
     * betal dig ud af fængsel
     * @param player - player objekt
     */
    public void payBail(Player player) {
        player.withdraw(1000);
        player.setInJail(false);
    }

    /**
     * sælges et felt på auktion skal ejerskab mm overføres til ny spiller og væk fra gammel spiller
     * @param player - player objekt
     * @param fieldNumber - felt index
     * @return - et array af felter spiller objektet ejer
     */

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
