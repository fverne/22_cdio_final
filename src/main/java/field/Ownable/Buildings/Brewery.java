package field.Ownable.Buildings;

public class Brewery extends Building {

    public Brewery(String name, String text, int cost, int[] category, int[] rentList) {
        super(name, text, cost, rentList[0], category, rentList);
    }
}