package model;

public class DemoDie {
    private int[] die;
    private int counter = 0;
    public DemoDie(){
        die = new int[] {3, 2, 1, 1, 28, 2, 37, 31, 17, 40, 40,1,1};
    }

    public int[] getFaceValues(){
        int[] faces = new int[] {die[counter], 0};
        return faces;
    }

    public void setCounter(){
        counter++;
    }
}
