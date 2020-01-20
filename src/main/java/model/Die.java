package model;

public class Die {
    private int faceValue;

    public void rollDie() {
        faceValue = (int) (Math.random() * 6 + 1);
    }

    public int getFaceValue() {
        return faceValue;
    }
}
