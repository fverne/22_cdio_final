package model;

public class DiceHolder {

    private Die[] die;
    private int[][] slag = {{1,0}, {1,2}, {1,2},
            {1,2}, {2,2}, {1,1},
            {1,1}, {1,2}, {1,3},
            {1,1}, {1,2}, {1,3},
            {1,1}, {1,2}, {1,3},
            {1,1}, {1,2}, {1,3},
            {1,1}, {1,2}, {1,3},
            {1,1}, {1,2}, {1,3}};

    private int slagInt = 0;
    public DiceHolder() {
        die = new Die[2];
        die[0] = new Die();
        die[1] = new Die();
    }

    public Die[] rollDice() {
        die[0].rollDie(slag[slagInt][0]);
        die[1].rollDie(slag[slagInt][1]);
        slagInt++;
        return die;
    }

    public Die[] getFaceValues() {
        return die;
    }
}
