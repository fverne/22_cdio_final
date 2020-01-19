package field.NotOwnable;

public class Tax extends NotOwnable {
    private int rent;

    public Tax(String name, String text, int rent) {
        super(name, text);
        this.rent = rent;
    }

    public int getRent() {
        return rent;
    }
}
