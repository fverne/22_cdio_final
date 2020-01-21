package model;

public class Die {
    private int faceValue;

    public void rollDie(int nr) {
        //faceValue = (int) (Math.random() * 6 + 1);
        faceValue = nr;
    }

    public int getFaceValue() {
        return faceValue;
    }
}
