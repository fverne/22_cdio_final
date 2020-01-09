package model;
import model.*;

public class Die {

    private static final int MAX = 6;
    private int faceValue;
    private int SameDieCounter = 0;

    public Die() {
        roll();
    }

    public void roll() {


        int Die1 = (int) ((Math.random() * MAX) + 1);
        int Die2 = (int) ((Math.random() * MAX) + 1);
        faceValue = Die1 + Die2;

        if (Die1 == Die2) {
            SameDieCounter = +1;
        }
        if (SameDieCounter == 2) {

        }
    }

    public int getFaceValue() {
        return faceValue;
    }
}




