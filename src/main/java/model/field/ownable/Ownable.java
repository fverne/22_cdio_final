package model.field.ownable;

import model.field.Field;
import model.*;


public abstract class Ownable extends Field {
    private Player ownedBy = null;
    private int cost;
    private int rent;
    private int[] category;

    public Ownable(String name, String text, int cost, int rent, int[] category) {
        super(name, text);
        this.cost = cost;
        this.rent = rent;
        this.category = category;
    }

    public int getCost() {
        return cost;
    }

    public int getRent() {
        return rent;
    }

    public Player getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(Player ownedBy) {
        this.ownedBy = ownedBy;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int[] getCategory() {
        return category;
    }
}
