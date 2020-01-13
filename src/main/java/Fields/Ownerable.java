package Fields;

import model.*;


public abstract class Ownerable extends Field {
    private Player ownedBy = null;
    private int cost;
    private int rent;
    private int[] category;

    public Ownerable(String name, String text, int cost, int rent, int[] category){
        super(name,text);
        this.cost = cost;
        this.rent = rent;
        this.category = category;
    }
    public void buy(Player pl){
        if (pl.getBalance() - this.getCost() >= 0 && this.getOwnedBy() == null) {
            pl.deposit(- this.getCost());
            setOwnedBy(pl);
        }

    }
    public void landOn(Player pl) {
        if(getOwnedBy() != null){
            pl.deposit(-this.getRent());
            getOwnedBy().deposit(this.getRent());
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

    public int[] getCategory(){
        return  category;
    }
}
