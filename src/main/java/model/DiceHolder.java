package model;

public class DiceHolder {

    private Die die1;
    private Die die2;

    public DiceHolder(){
        die1 = new Die();
        die2 = new Die();
    }

    public int rollDice(){
        die1.rollDie();
        die2.rollDie();
        return die1.getFaceValue() + die2.getFaceValue();
    }
}
