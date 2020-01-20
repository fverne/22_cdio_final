package model;

import gui_fields.*;
import gui_main.GUI;
import model.Language;

import java.awt.*;

public class GameBoard {
    private GUI_Field[] fields = new GUI_Field[40];

    public GameBoard() { // her bygges et GUI Board med felter af forskellige typer.
        fields[0] = new GUI_Start(Language.fieldTitles()[0], "", Language.startDesc() + Language.currency(), Color.red, Color.BLACK);
        fields[1] = new GUI_Street(Language.fieldTitles()[1], priceCreator(1200), descCreator(new int[]{250, 750, 2250, 4000, 6000, 600}), "", Color.WHITE, Color.BLACK);
        fields[2] = new GUI_Chance(Language.fieldTitles()[2], Language.tryChance(), "", Color.WHITE, Color.BLACK);
        fields[3] = new GUI_Street(Language.fieldTitles()[3], priceCreator(1200), descCreator(new int[]{250, 750, 2250, 4000, 6000, 600}), "", Color.WHITE, Color.BLACK);
        fields[4] = new GUI_Tax(Language.fieldTitles()[4], "", Language.taxDesc(), Color.WHITE, Color.BLACK);
        fields[5] = new GUI_Shipping("default", Language.fieldTitles()[5], priceCreator(4000), shippingDescCreator(new int[]{500, 1000, 2000, 4000}), "", Color.WHITE, Color.BLACK);
        fields[6] = new GUI_Street(Language.fieldTitles()[6], priceCreator(2000), descCreator(new int[]{600, 1800, 5400, 8000, 11000, 1000}), "", Color.RED, Color.BLACK);
        fields[7] = new GUI_Chance(Language.fieldTitles()[7], Language.tryChance(), "", Color.WHITE, Color.BLACK);
        fields[8] = new GUI_Street(Language.fieldTitles()[8], priceCreator(2000), descCreator(new int[]{600, 1800, 5400, 8000, 6000, 1000}), "", Color.RED, Color.BLACK);
        fields[9] = new GUI_Street(Language.fieldTitles()[9], priceCreator(2400), descCreator(new int[]{800, 2000, 6000, 9000, 12000, 1200}), "", Color.RED, Color.BLACK);
        fields[10] = new GUI_Jail("default", Language.fieldTitles()[10], Language.prison(), "", Color.LIGHT_GRAY, Color.BLACK);
        fields[11] = new GUI_Street(Language.fieldTitles()[11], priceCreator(2800), descCreator(new int[]{1000, 3000, 9000, 12500, 15000, 1400}), "", Color.GREEN, Color.BLACK);
        fields[12] = new GUI_Brewery("default", Language.fieldTitles()[12], priceCreator(3000), Language.breweryDesc(), "", Color.WHITE, Color.BLACK);
        fields[13] = new GUI_Street(Language.fieldTitles()[13], priceCreator(2800), descCreator(new int[]{1000, 3000, 9000, 12500, 15000, 1400}), "", Color.GREEN, Color.BLACK);
        fields[14] = new GUI_Street(Language.fieldTitles()[14], priceCreator(3200), descCreator(new int[]{1250, 3750, 10000, 14000, 18000, 1600}), "", Color.GREEN, Color.BLACK);
        fields[15] = new GUI_Shipping("default", Language.fieldTitles()[15], priceCreator(4000), shippingDescCreator(new int[]{500, 1000, 2000, 4000}), "", Color.WHITE, Color.BLACK);
        fields[16] = new GUI_Street(Language.fieldTitles()[16], priceCreator(3600), descCreator(new int[]{1400, 4000, 11000, 15000, 19000, 1800}), "", Color.LIGHT_GRAY, Color.BLACK);
        fields[17] = new GUI_Chance(Language.fieldTitles()[17], Language.tryChance(), "", Color.WHITE, Color.BLACK);
        fields[18] = new GUI_Street(Language.fieldTitles()[18], priceCreator(3600), descCreator(new int[]{1400, 4000, 11000, 15000, 19000, 1800}), "", Color.LIGHT_GRAY, Color.BLACK);
        fields[19] = new GUI_Street(Language.fieldTitles()[19], priceCreator(4000), descCreator(new int[]{1600, 4400, 12000, 16000, 20000, 2000}), "", Color.LIGHT_GRAY, Color.BLACK);
        fields[20] = new GUI_Refuge("default", Language.fieldTitles()[20], Language.refuge(), Language.refugeDesc(), Color.WHITE, Color.BLACK);
        fields[21] = new GUI_Street(Language.fieldTitles()[21], priceCreator(4400), descCreator(new int[]{1800, 5000, 14000, 17500, 21000, 2200}), "", Color.ORANGE, Color.BLACK);
        fields[22] = new GUI_Chance(Language.fieldTitles()[22], Language.tryChance(), "", Color.WHITE, Color.BLACK);
        fields[23] = new GUI_Street(Language.fieldTitles()[23], priceCreator(4400), descCreator(new int[]{1800, 5000, 14000, 17500, 21000, 2000}), "", Color.ORANGE, Color.BLACK);
        fields[24] = new GUI_Street(Language.fieldTitles()[24], priceCreator(4800), descCreator(new int[]{2000, 6000, 15000, 18500, 22000, 4400}), "", Color.ORANGE, Color.BLACK);
        fields[25] = new GUI_Shipping("default", Language.fieldTitles()[25], priceCreator(4000), shippingDescCreator(new int[]{500, 1000, 2000, 4000}), "", Color.WHITE, Color.BLACK);
        fields[26] = new GUI_Street(Language.fieldTitles()[26], priceCreator(5200), descCreator(new int[]{2200, 6600, 16000, 19500, 23000, 2600}), "", Color.YELLOW, Color.BLACK);
        fields[27] = new GUI_Street(Language.fieldTitles()[27], priceCreator(5200), descCreator(new int[]{2200, 6600, 16000, 19500, 23000, 2600}), "", Color.YELLOW, Color.BLACK);
        fields[28] = new GUI_Brewery("default", Language.fieldTitles()[28], priceCreator(3000), Language.breweryDesc(), "", Color.WHITE, Color.BLACK);
        fields[29] = new GUI_Street(Language.fieldTitles()[29], priceCreator(5600), descCreator(new int[]{2400, 7200, 17000, 20500, 24000, 2800}), "", Color.YELLOW, Color.BLACK);
        fields[30] = new GUI_Jail("default", Language.fieldTitles()[30], Language.inPrison(), Language.prisonDesc() + ": ", Color.LIGHT_GRAY, Color.BLACK);
        fields[31] = new GUI_Street(Language.fieldTitles()[31], priceCreator(6000), descCreator(new int[]{2600, 7800, 18000, 22000, 25000, 3000}), "", Color.BLUE, Color.BLACK);
        fields[32] = new GUI_Street(Language.fieldTitles()[32], priceCreator(6000), descCreator(new int[]{2600, 7800, 18000, 22000, 25000, 3000}), "", Color.BLUE, Color.BLACK);
        fields[33] = new GUI_Chance(Language.fieldTitles()[33], Language.tryChance(), "", Color.WHITE, Color.BLACK);
        fields[34] = new GUI_Street(Language.fieldTitles()[34], priceCreator(6400), descCreator(new int[]{3000, 9000, 20000, 24000, 28000, 3200}), "", Color.BLUE, Color.BLACK);
        fields[35] = new GUI_Shipping("default", Language.fieldTitles()[35], priceCreator(4000), shippingDescCreator(new int[]{500, 1000, 2000, 4000}), "", Color.WHITE, Color.BLACK);
        fields[36] = new GUI_Chance(Language.fieldTitles()[36], Language.tryChance(), "", Color.WHITE, Color.BLACK);
        fields[37] = new GUI_Street(Language.fieldTitles()[37], priceCreator(7000), descCreator(new int[]{3500, 10000, 22000, 26000, 30000, 3500}), "", Color.PINK, Color.BLACK);
        fields[38] = new GUI_Tax(Language.fieldTitles()[38], "", Language.taxDesc(), Color.WHITE, Color.BLACK);
        fields[39] = new GUI_Street(Language.fieldTitles()[39], priceCreator(8000), descCreator(new int[]{4000, 12000, 28000, 34000, 40000, 4000}), "", Color.PINK, Color.BLACK);
    }

    public String descCreator(int[] desc) {     // denne metode bygger descriptions på gui felterene ud fra et givent array
        String text = Language.buildingRents() + ":";

        for (int i = 0; i < desc.length - 2; i++) {
            text = text + "\n " + Language.house() + " " + (i + 1) + ": " + desc[i] + Language.currency();
        }

        if (desc.length > 5) {
            text = text + "\n " + Language.hotel() + ": " + desc[desc.length - 2] + Language.currency();
        }

        // denne del er udkommenteret da vi ikke fik indført pantsætning af ejendomme
        //text = text + "\n Pantsætningsværdi: " + desc[desc.length-1] + "kr.";

        return text;
    }

    public String priceCreator(int price) {     // denne metode bygger pris-stringen på gui felterene ud fra en given int

        return Language.price() + ": " + price + " " + Language.currency();
    }

    public String shippingDescCreator(int[] desc) {     // denne metode bygger pris-stringen på gui felterene ud fra en given int
        String text = Language.shippingRents() + ": ";

        for (int i = 0; i < desc.length; i++) {
            text = text + "\n " + Language.ship() + " " + (i + 1) + ": " + desc[i] + Language.currency();
        }

        return text;
    }

    //returnrere gui objektet så spilbrættet kan initialiseres
    public GUI initGUI() {
        return new GUI(fields, Color.white);
    }

    public GUI_Field getGUIField(int fieldNumber) {
        return fields[fieldNumber];
    }
}
