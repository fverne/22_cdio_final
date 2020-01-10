package GUI.FieldProperties;

import model.Player;

public class LandOnField extends Fields {
    public int price;
    public boolean owned;
    public Player ownedBy;

    public LandOnField(String name, int price, boolean owned, Player ownedBy, boolean goToJail) {
        super(name, goToJail);
        this.price = price;
        this.owned = owned;
        this.ownedBy = ownedBy;
    }

    @Override
    public Fields landOnField(Player player) {
        if (!owned) {
            setFieldOwner(player);
            payForProperty(player);
        } else {
            payRent(player);
        }
        return this;
    }

    public void payRent(Player player) {
        //player.account.setBalance(player.account.getBalance() - price);
        //ownedBy.account.setBalance(ownedBy.account.getBalance() + price);
    }

    public void setFieldOwner(Player player) {
        ownedBy = player;
        owned = true;
    }

    public void payForProperty(Player player) {
        //player.account.setBalance(player.account.getBalance() - price);
    }

    public void sellField(Player player) {
    }

    public boolean isOwned() {
        return owned;
    }

    public int getPrice() {
        return price;
    }
}
