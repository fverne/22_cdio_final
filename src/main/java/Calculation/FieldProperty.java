package Calculation;

import Fields.Field;
import Fields.NotOwnable.ChanceField;
import Fields.NotOwnable.Tax;
import Fields.NotOwnable.Useless;
import Fields.Ownable.Property;
import Fields.Ownerable;
import model.Player;


public class FieldProperty {
    String name, message, owner;
    int price, rent;
    boolean isOwned,jail;
    Field[] fieldProperty = new Field[40];

    public FieldProperty() {

        int[] rentList = {1000, 2000, 3500, 7000, 10000, 20000};
        fieldProperty[0] = new Useless("Start", "Start");
        fieldProperty[1] = new Property("Rødovrevej","vej",50,rentList,1,4000);
        fieldProperty[2] = new ChanceField("Chance", "Prøv lykken");
        fieldProperty[3] = new Property("Hvidovrevej","vej",50,rentList,1,4000);
        fieldProperty[4] = new Tax("Indkomstskat","Betal 200kr.",200);
        fieldProperty[5] = new Property("Øresund","vej",50,rentList,1,4000);
        fieldProperty[6] = new Property("Valbylanggade","vej",50,rentList,1,4000);
        fieldProperty[7] = new ChanceField("Chance", "prøv lykken");
        fieldProperty[8] = new Property("Allégade","vej",50,rentList,1,4000);
        fieldProperty[9] = new Property("Fængsel","vej",50,rentList,1,4000);
        fieldProperty[10] = new Property("Frederiksberg Allé","vej",50,rentList,1,4000);
        fieldProperty[11] = new Property("Tuborg","vej",50,rentList,1,4000);
        fieldProperty[12] = new Property("Bülowsvej","vej",50,rentList,1,4000);
        fieldProperty[13] = new Property("Gl. Kongevej","vej",50,rentList,1,4000);
        fieldProperty[14] = new Property("Hellerupvej","vej",50,rentList,1,4000);
        fieldProperty[15] = new Property("Bernstorffsvej","vej",50,rentList,1,4000);
        fieldProperty[16] = new Property("Hellerupvej","vej",50,rentList,1,4000);
        fieldProperty[17] = new ChanceField("chance", "Prøv lykken");
        fieldProperty[18] = new Property("Strandvej","vej",50,rentList,1,4000);
        fieldProperty[19] = new Property("Helle","vej",50,rentList,1,4000);
        fieldProperty[20] = new Property("Trianglen","vej",50,rentList,1,4000);
        fieldProperty[21] = new Property("Østerbrogade","vej",50,rentList,1,4000);
        fieldProperty[22] = new ChanceField("chance", "prøv lykken");
        fieldProperty[23] = new Property("","vej",50,rentList,1,4000);
        fieldProperty[24] = new Property("Grønningen","vej",50,rentList,1,4000);
        fieldProperty[25] = new Property("Maersk","vej",50,rentList,1,4000);
        fieldProperty[26] = new Property("Bredgade","vej",50,rentList,1,4000);
        fieldProperty[27] = new Property("Kgs. Nytorv","vej",50,rentList,1,4000);
        fieldProperty[27] = new Property("Carlsberg","vej",50,rentList,1,4000);
        fieldProperty[29] = new Property("Østergade","vej",50,rentList,1,4000);
        fieldProperty[30] = new Useless("I Fængsel","Jail");
        fieldProperty[31] = new Property("Amagertorv","vej",50,rentList,1,4000);
        fieldProperty[32] = new Property("Vimmelskaftet","vej",50,rentList,1,4000);
        fieldProperty[33] = new ChanceField("chance", "Prøv lykken");
        fieldProperty[34] = new Property("Nygade","vej",50,rentList,1,4000);
        fieldProperty[35] = new Property("D/S Bornholm 1866","vej",50,rentList,1,4000);
        fieldProperty[36] = new ChanceField("chance", "Prøv lykken");
        fieldProperty[37] = new Property("Frederiksberggade","vej",50,rentList,1,4000);
        fieldProperty[38] = new Property("","vej",50,rentList,1,4000);
        fieldProperty[39] = new Property("Rådhuspladsen","vej",50,rentList,1,4000);
    }

    public Field getField(int fieldNumber) {
        return fieldProperty[fieldNumber];
    }

    public int getPrice(int fieldNumber){
        Ownerable field = (Ownerable) fieldProperty[fieldNumber];
        return field.getCost();
    }

    public void setOwned(model.Player player, int fieldNumber){
        Ownerable field = (Ownerable) fieldProperty[fieldNumber];
        field.setOwnedBy(player);
    }

    public int getRent(int fieldNumber){
        Ownerable field = (Ownerable) fieldProperty[fieldNumber];
        return field.getRent();
    }

    public model.Player getOwner(int fieldNumber){
        Ownerable field = (Ownerable) fieldProperty[fieldNumber];
        return field.getOwnedBy();
    }

    public int getHousePrice(int fieldNumber){
        Fields.Ownable.Property field = (Fields.Ownable.Property) fieldProperty[fieldNumber];
        return field.getHouseCost();
    }

    public void changeHouseAmount(int fieldNumber, model.Player player){
        Fields.Ownable.Property field = (Fields.Ownable.Property) fieldProperty[fieldNumber];
        field.changeHouseAmount(1, player);
    }

    private boolean doubleRent(Player pl, int pairType){
        for(Field field : fieldProperty){
                if(field instanceof Property && ((Property) field).getPairType() == pairType && !((Property) field).getOwnedBy().equals(pl)){
                    return false;
                }
        }
        return true;
    }

}
