package model;

public class DiceHolder {

    private Die[] die;

    public DiceHolder() {
        die = new Die[2];
        die[0] = new Die();
        die[1] = new Die();
    }

    public Die[] rollDice() {
        die[0].rollDie();
        die[1].rollDie();
        return die;
    }

    public Die[] getFaceValues() {
        return die;
    }
}
