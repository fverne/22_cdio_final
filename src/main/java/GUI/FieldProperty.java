package GUI;

import model.Player;


public class FieldProperty extends Fields {
    String name, message, owner;
    int price, rent;
    boolean isOwned,jail;
    Fields[] fieldProperty = new Fields[40];

    public FieldProperty(String name, int price, int rent, boolean isOwned, boolean jail, String owner) {
        this.name = name;
        //this.message = message;
        this.price = price;
        this.rent = rent;
        this.isOwned = isOwned;
        this.owner = owner;
        this.jail = jail;
    }

    public void initBoardProperty(Player player) {
        fieldProperty[0] = new FieldProperty("Start",0,0,false,false,"");
        fieldProperty[1] = new FieldProperty("Rødovrevej",100,50,false,false,"");
        fieldProperty[2] = new Chancecards();
        fieldProperty[3] = new FieldProperty("Hvidovrevej",100,50,false,false,"");
        fieldProperty[4] = new Tax("Indkomstskat","Betal 200kr.",200);
        fieldProperty[5] = new FieldProperty("Øresund",100,50,false,false,"");
        fieldProperty[6] = new FieldProperty("Valbylanggade",100,50,false,false,"");
        fieldProperty[7] = new Chancecards();
        fieldProperty[8] = new FieldProperty("Allégade",100,50,false,false,"");
        fieldProperty[9] = new FieldProperty("Fængsel",100,50,false,false,"");
        fieldProperty[10] = new FieldProperty("Frederiksberg Allé",100,50,false,true,"");
        fieldProperty[11] = new FieldProperty("Tuborg",100,50,false,false,"");
        fieldProperty[12] = new FieldProperty("Bülowsvej",100,50,false,false,"");
        fieldProperty[13] = new FieldProperty("Gl. Kongevej",100,50,false,false,"");
        fieldProperty[14] = new FieldProperty("Hellerupvej",100,50,false,false,"");
        fieldProperty[15] = new FieldProperty("Bernstorffsvej",100,50,false,false,"");
        fieldProperty[16] = new FieldProperty("Hellerupvej",100,50,false,false,"");
        fieldProperty[17] = new Chancecards();
        fieldProperty[18] = new FieldProperty("Strandvej",100,50,false,false,"");
        fieldProperty[19] = new FieldProperty("Helle",0,0,false,false,"");
        fieldProperty[20] = new FieldProperty("Trianglen",100,50,false,false,"");
        fieldProperty[21] = new FieldProperty("Østerbrogade",100,50,false,false,"");
        fieldProperty[22] = new Chancecards();
        fieldProperty[23] = new FieldProperty("",100,50,false,false,"");
        fieldProperty[24] = new FieldProperty("Grønningen",100,50,false,false,"");
        fieldProperty[25] = new FieldProperty("Maersk",100,50,false,false,"");
        fieldProperty[26] = new FieldProperty("Bredgade",100,50,false,false,"");
        fieldProperty[27] = new FieldProperty("Kgs. Nytorv",100,50,false,false,"");
        fieldProperty[27] = new FieldProperty("Carlsberg",100,50,false,false,"");
        fieldProperty[29] = new FieldProperty("Østergade",100,50,false,false,"");
        fieldProperty[30] = new FieldProperty("I Fængsel",0,0,false,true,"");
        fieldProperty[31] = new FieldProperty("Amagertorv",100,50,false,false,"");
        fieldProperty[32] = new FieldProperty("Vimmelskaftet",100,50,false,false,"");
        fieldProperty[33] = new Chancecards();
        fieldProperty[34] = new FieldProperty("Nygade",100,50,false,false,"");
        fieldProperty[35] = new FieldProperty("D/S Bornholm 1866",100,50,false,false,"");
        fieldProperty[36] = new Chancecards();
        fieldProperty[37] = new FieldProperty("Frederiksberggade",100,50,false,false,"");
        fieldProperty[38] = new FieldProperty("",100,50,false,false,"");
        fieldProperty[39] = new FieldProperty("Rådhuspladsen",100,50,false,false,"");
    }

    public Fields getField(int fieldNumber) {
        return fieldProperty[fieldNumber];
    }

    @Override
    public Fields landOnField(Player player) {
        return null;
    }
}
