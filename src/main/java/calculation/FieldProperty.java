package calculation;

import field.Field;
import field.NotOwnable.Chancecard.ChanceField;
import field.NotOwnable.GoToJail;
import field.NotOwnable.Tax;
import field.NotOwnable.Refuge;
import field.Ownable.Building;
import field.Ownable.Buildings.Brewery;
import field.Ownable.Buildings.Ferry;
import field.Ownable.Property;
import field.Ownable.Ownable;


public class FieldProperty {
    private Field[] fieldProperty = new Field[40];

    public FieldProperty() {
        int[] rentListFerry = {500, 1000, 2000, 4000};
        int[] rentlistBrewery = {100, 200};

        fieldProperty[0] = new Refuge("Start", "Start");
        fieldProperty[1] = new Property("Rødovrevej", "vej", 1200, new int[]{50, 250, 750, 2250, 4000, 6000}, new int[]{3}, 1000, 1000);
        fieldProperty[2] = new ChanceField("Chance", "Prøv lykken");
        fieldProperty[3] = new Property("Hvidovrevej", "vej", 1200, new int[]{50, 250, 750, 2250, 4000, 6000}, new int[]{1}, 1000, 1000);
        fieldProperty[4] = new Tax("Indkomstskat", "Betal 4000kr.", 4000);
        fieldProperty[5] = new Ferry("Øresund", "vej", 4000, new int[]{15, 25, 35}, rentListFerry);
        fieldProperty[6] = new Property("Roskildevej", "vej", 2000, new int[]{100, 600, 1800, 5400, 8000, 11000}, new int[]{8, 9}, 1000, 1000);
        fieldProperty[7] = new ChanceField("Chance", "prøv lykken");
        fieldProperty[8] = new Property("Valby Langgade", "vej", 2000, new int[]{100, 600, 1800, 5400, 8000, 11000}, new int[]{6, 9}, 1000, 1000);
        fieldProperty[9] = new Property("Allégade", "vej", 2400, new int[]{150, 800, 2000, 6000, 9000, 12000}, new int[]{6, 8}, 1000, 1000);
        fieldProperty[10] = new Refuge("Fængsel", "Du besøger fængsel");
        fieldProperty[11] = new Property("Frederiksberg Allé", "vej", 2800, new int[]{200, 1000, 3000, 9000, 12500, 15000}, new int[]{13, 14}, 2000, 2000);
        fieldProperty[12] = new Brewery("Tuborg", "vej", 3000, new int[]{27}, rentlistBrewery);
        fieldProperty[13] = new Property("Bülowsvej", "vej", 2800, new int[]{200, 1000, 3000, 9000, 12500, 15000}, new int[]{11, 14}, 2000, 2000);
        fieldProperty[14] = new Property("Gl. Kongevej", "vej", 3200, new int[]{250, 1250, 3750, 10000, 14000, 18000}, new int[]{11, 13}, 2000, 2000);
        fieldProperty[15] = new Ferry("Skandinavisk Linietrafik A/S", "vej", 4000, new int[]{5, 25, 35}, rentListFerry);
        fieldProperty[16] = new Property("Bernstoffsvej", "vej", 3600, new int[]{300, 1400, 4000, 11000, 15000, 19000}, new int[]{18, 19}, 2000, 2000);
        fieldProperty[17] = new ChanceField("chance", "Prøv lykken");
        fieldProperty[18] = new Property("Hellerupvej", "vej", 3600, new int[]{300, 1400, 4000, 11000, 15000, 19000}, new int[]{16, 19}, 2000, 2000);
        fieldProperty[19] = new Property("Strandvej", "vej", 4000, new int[]{350, 1600, 4400, 12000, 16000, 20000}, new int[]{16, 18}, 2000, 2000);
        fieldProperty[20] = new Refuge("Helle", "vej");
        fieldProperty[21] = new Property("Trianglen", "vej", 4400, new int[]{350, 1800, 5000, 14000, 17500, 21000}, new int[]{23, 24}, 3000, 3000);
        fieldProperty[22] = new ChanceField("chance", "prøv lykken");
        fieldProperty[23] = new Property("Østerbrogade", "vej", 4400, new int[]{350, 1800, 5000, 14000, 17500, 21000}, new int[]{21, 24}, 3000, 3000);
        fieldProperty[24] = new Property("Grønningen", "vej", 4800, new int[]{400, 2000, 6000, 15000, 18500, 22000}, new int[]{21, 23}, 3000, 3000);
        fieldProperty[25] = new Ferry("Mols-Linien", "vej", 4000, new int[]{5, 15, 35}, rentListFerry);
        fieldProperty[26] = new Property("Bredgade", "vej", 5200, new int[]{450, 2200, 6600, 16000, 19500, 23000}, new int[]{27, 29}, 3000, 3000);
        fieldProperty[27] = new Property("Kgs. Nytorv", "vej", 5200, new int[]{450, 2200, 6600, 16000, 19500, 23000}, new int[]{26, 29}, 3000, 3000);
        fieldProperty[28] = new Brewery("Carlsberg", "vej", 3000, new int[]{12}, rentlistBrewery);
        fieldProperty[29] = new Property("Østergade", "vej", 5600, new int[]{500, 2400, 7200, 17000, 20500, 24000}, new int[]{25, 27}, 3000, 3000);
        fieldProperty[30] = new GoToJail("I Fængsel", "Jail");
        fieldProperty[31] = new Property("Amagertorv", "vej", 6000, new int[]{550, 2600, 7800, 18000, 22000, 25000}, new int[]{32, 34}, 4000, 4000);
        fieldProperty[32] = new Property("Vimmelskaftet", "vej", 6000, new int[]{550, 2600, 7800, 18000, 22000, 25000}, new int[]{31, 34}, 4000, 4000);
        fieldProperty[33] = new ChanceField("chance", "Prøv lykken");
        fieldProperty[34] = new Property("Nygade", "vej", 6400, new int[]{600, 3000, 9000, 20000, 24000, 28000}, new int[]{31, 32}, 4000, 4000);
        fieldProperty[35] = new Ferry("D/S Bornholm 1866", "vej", 4000, new int[]{5, 15, 25}, rentListFerry);
        fieldProperty[36] = new ChanceField("chance", "Prøv lykken");
        fieldProperty[37] = new Property("Frederiksberggade", "vej", 7000, new int[]{700, 3500, 10000, 22000, 26000, 30000}, new int[]{39}, 4000, 4000);
        fieldProperty[38] = new Tax("Ekstraordinær Statsskat", "Betal 2000kr.", 2000);
        fieldProperty[39] = new Property("Rådhuspladsen", "vej", 8000, new int[]{1000, 4000, 12000, 28000, 34000, 40000}, new int[]{37}, 4000, 4000);
    }

    public Field getField(int fieldNumber) {
        return fieldProperty[fieldNumber];
    }

    public int getPrice(int fieldNumber) {
        Ownable field = (Ownable) fieldProperty[fieldNumber];
        return field.getCost();
    }

    public void setOwned(model.Player player, int fieldNumber) {
        Ownable field = (Ownable) fieldProperty[fieldNumber];
        field.setOwnedBy(player);
    }

    public int getRent(int fieldNumber) {
        Ownable field = (Ownable) fieldProperty[fieldNumber];
        return field.getRent();
    }

    public int getRent(int fieldNumber, int amount) {
        Building field = (Building) fieldProperty[fieldNumber];
        return field.getRent(amount);
    }

    public int getTax(int fieldNumber) {
        Tax field = (Tax) fieldProperty[fieldNumber];
        return field.getRent();
    }

    public model.Player getOwner(int fieldNumber) {
        Ownable field = (Ownable) fieldProperty[fieldNumber];
        return field.getOwnedBy();
    }

    public int getHousePrice(int fieldNumber) {
        field.Ownable.Property field = (field.Ownable.Property) fieldProperty[fieldNumber];
        return field.getHouseCost();
    }

    public void changeHouseAmount(int fieldNumber, model.Player player, int amount) {
        field.Ownable.Property field = (field.Ownable.Property) fieldProperty[fieldNumber];
        field.changeHouseAmount(amount, player);
    }

    public int[] getFieldCategory(int fieldNumber) {
        Ownable field = (Ownable) fieldProperty[fieldNumber];
        return field.getCategory();
    }
}
