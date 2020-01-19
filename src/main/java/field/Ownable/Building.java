package field.Ownable;

public abstract class Building extends Ownable {
    int[] rentList;

    public Building(String name, String text, int cost, int rent, int[] category, int[] rentList) {
        super(name, text, cost, rent, category);
        this.rentList = rentList;
    }

    public int getRent(int fieldsOwned) {
        return rentList[fieldsOwned];
    }


}


