package model.fields.ownable.buildings;

public class Ferry extends Building {

    public Ferry(String name, String text, int cost, int[] category, int[] rentList) {
        super(name, text, cost, rentList[0], category, rentList);
    }
}
