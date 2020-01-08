package Fields;

import model.*;


public abstract class Ownerable extends Field {
    private Player ownedBy = null;
    private int cost;
    private int rent;

    public Ownerable(String name, String text, int cost, int rent){
        super(name,text);
        this.cost = cost;
        this.rent = rent;

    }
    public void buy(){

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
}
