package model;

public class DiceHolder {

    private Die[] die = new Die[2];

    public DiceHolder(){
        die[0] = new Die();
        die[1] = new Die();
    }

    public Die[] rollDice(){
        die[0].rollDie();
        die[1].rollDie();
        return die;
    }

    public  Die[] getFaceValues(){
        return  die;
    }
}
