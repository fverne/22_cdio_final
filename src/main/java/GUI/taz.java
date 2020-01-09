package GUI;

import model.Player;

public class taz extends Fields {
    String name, message;
    int taxPayment;
    boolean isOwned;

    @Override
    public Fields landOnField(Player player) {
        return null;
    }

    public taz(String name, String message, int taxPayment) {
        this.name = name;
        this.message = message;
        this.taxPayment = taxPayment;
    }
}
