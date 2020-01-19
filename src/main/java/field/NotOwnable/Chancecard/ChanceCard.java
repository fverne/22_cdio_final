package field.NotOwnable.Chancecard;

public class ChanceCard {
    private String message;
    private int reward;
    private int position;
    private boolean isJail;

    public ChanceCard(String message, int reward, int position, String name, boolean isJail) {

        this.message = message;
        this.reward = reward;
        this.position = position;
        this.isJail = isJail;
    }

    public int getReward() {
        return reward;
    }
    public String getMessage() {
        return message;
    }

    public int getPosition(){return position;}

    public boolean getIsJail(){return isJail;}
}

