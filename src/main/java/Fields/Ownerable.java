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
    public void buy(Player pl){
        if (pl.getBalance() - this.getCost() >= 0 && this.getOwnedBy() == null) {
            pl.addToBalance(- this.getCost());
            setOwnedBy(pl);
        }

    }
    public void landOn(Player pl) {
        if(getOwnedBy() != null){
            pl.addToBalance(-this.getRent());
            getOwnedBy().addToBalance(this.getRent());
        }
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
}
