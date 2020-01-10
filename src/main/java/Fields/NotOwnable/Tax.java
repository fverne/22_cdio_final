package Fields.NotOwnable;

import Fields.NotOwnerable;

public class Tax extends NotOwnerable {
    private int rent;
    public Tax(String name, String text, int rent) {
        super(name, text);
        this.rent = rent;
    }
}
