package model.field.notOwnable;


import model.chancecard.ChanceCard;
import model.chancecard.Deck;

public class ChanceField extends NotOwnable {
    private Deck deck = Deck.getInstance();

    public ChanceField(String name, String text){
        super(name, text);
    }

    public ChanceCard draw (){
        return deck.draw();
    }
}
