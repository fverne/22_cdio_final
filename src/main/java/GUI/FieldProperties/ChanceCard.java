package GUI.FieldProperties;

public class ChanceCard {
    private String message;
    private int reward;
    private int position;

    public ChanceCard(String message, int reward, int position, String name, boolean isJail) {

        this.message = message;
        this.reward = reward;
        this.position = position;
    }

    public int getReward() {
        return reward;
    }
    public String getMessage() {
        return message;
    }

}

