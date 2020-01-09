package GUI;

import model.Chancecard;
import model.Player;

import java.util.Random;

public class Chancecards extends Fields {
    private String message;
    private int reward;
    private int position;

    private static Chancecards[] cards = new Chancecards[]{
            new Chancecards("Glem det", -20, 0, "Chancekort", false),

            new Chancecards("tillykke med fødselsdag", 5, 0, "chancekort", false),

            new Chancecards("Et eller andet",2,0,"Chancekort",false),
    };

    public Chancecards(String message, int reward, int position, String name, boolean isJail) {
        super(name, isJail);
        this.message = message;
        this.reward = reward;
        this.position = position;
    }
    public Chancecards() {
        super();
    }
    public static Chancecards getRandomCard() {
        int i = new Random().nextInt(cards.length);
        return cards[i];
    }
    public int getReward() {
        return reward;
    }
    public String getMessage() {
        return message;
    }
    @Override
    public Fields landOnField(Player player) {
        Chancecards card = Chancecards.getRandomCard();
        player.addToBalance(card.getReward());
        return this;
    }
}

