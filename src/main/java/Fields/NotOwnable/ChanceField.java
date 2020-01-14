package Fields.NotOwnable;

import Fields.NotOwnerable;
import GUI.FieldProperties.ChanceCard;


public class ChanceField extends NotOwnerable {
    private Deck deck = Deck.getInstance();

    public ChanceField(String name, String text){

        super(name, text);
    }
    public ChanceCard draw (){
        return deck.draw();
    }


}
