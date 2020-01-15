package GUI;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class Board {
    private GUI_Field[] fields = new GUI_Field[40];

    public Board() {
        fields[0] = new GUI_Start("Start","","Hver gang start passeres modtages 4000 kr.",Color.red,Color.BLACK);
        fields[1] = new GUI_Street("Rødovrevej","Pris: 1200", descCreator(new int[]{250, 750, 2250, 4000, 6000, 600}),"50",Color.WHITE,Color.BLACK);
        fields[2] = new GUI_Chance("?","Prøv lykken","",Color.WHITE,Color.BLACK);
        fields[3] = new GUI_Street("Hvidovrevej","Pris: 1200 kr.", descCreator(new int[]{250, 750, 2250, 4000, 6000, 600}),"600",Color.WHITE,Color.BLACK);
        fields[4] = new GUI_Tax("Betal inkomstskat","","4000 kr. eller 10% af formue",Color.WHITE,Color.BLACK);
        fields[5] = new GUI_Shipping("default","Øresund","","Pris 4000 - Leje m. hhv. 1,2,3,4 rederier: 500,1000,2000,4000 kr. Pantsætningsværdig: 2000","500",Color.WHITE,Color.BLACK);
        fields[6] = new GUI_Street("Roskildevej","Pris: 2000", descCreator(new int[]{600, 1800, 5400, 8000, 11000, 1000}),"PRIS",Color.RED,Color.BLACK);
        fields[7] = new GUI_Chance("?","Prøv lykken","",Color.WHITE,Color.BLACK);
        fields[8] = new GUI_Street("Valby langgade","Pris: 2000",descCreator(new int[]{600, 1800, 5400, 8000, 6000, 1000}),"PRIS",Color.RED,Color.BLACK);
        fields[9] = new GUI_Street("Allégade","Pris: 2400 kr.",descCreator(new int[]{800, 2000, 6000, 9000, 12000, 1200}) ,"PRIS",Color.RED,Color.BLACK);
        fields[10] = new GUI_Jail("default","I Fængsel","Fængsel","Du slipper med en advarsel denne gang!",Color.LIGHT_GRAY,Color.BLACK);
        fields[11] = new GUI_Street("Frederiksberg Allé","Pris: 2800",descCreator(new int[]{1000, 3000, 9000, 12500, 15000, 1400}),"PRIS",Color.GREEN,Color.BLACK);
        fields[12] = new GUI_Brewery("default","Tuborg","Pris: 3000 kr.","Hvis en virksomhed ejes, betales 100 gange så meget som øjnene viser, hvis to jes betales 200 gange øjnene","leje",Color.WHITE,Color.BLACK);
        fields[13] = new GUI_Street("Bülowsvej","Pris: 2800",descCreator(new int[]{1000, 3000, 9000, 12500, 15000, 1400}) ,"PRIS",Color.GREEN,Color.BLACK);
        fields[14] = new GUI_Street("Gl. Kongevej","Pris: 3200 kr.",descCreator(new int[]{1250, 3750, 10000, 14000, 18000, 1600}),"",Color.GREEN,Color.BLACK);
        fields[15] = new GUI_Shipping("default","Skandinavisk Linietrafik A/S","","Pris: 4000 - Leje m. hhv. 1,2,3,4 rederier: 500,1000,2000,4000 kr. Pantsætningsværdig: 2000","",Color.WHITE,Color.BLACK);
        fields[16] = new GUI_Street("Bernstoffsvej","3600 kr.",descCreator(new int[]{1400, 4000, 11000, 15000, 19000, 1800}),"PRIS",Color.LIGHT_GRAY,Color.BLACK);
        fields[17] = new GUI_Chance("?","Prøv lykken","",Color.WHITE,Color.BLACK);
        fields[18] = new GUI_Street("Hellerupvej","Pris: 3600",descCreator(new int[]{1400, 4000, 11000, 15000, 19000, 1800}) ,"300",Color.LIGHT_GRAY,Color.BLACK);
        fields[19] = new GUI_Street("Strandvej","Pris: 4000",descCreator(new int[]{1600, 4400, 12000, 16000, 20000, 2000}),"350",Color.LIGHT_GRAY,Color.BLACK);
        fields[20] = new GUI_Refuge("default","Helle","Helle","Hold en pause",Color.WHITE,Color.BLACK);
        fields[21] = new GUI_Street("Trianglen","Pris: 4400", descCreator(new int[]{1800, 5000, 14000, 17500, 21000, 2200}) ,"350 kr.",Color.ORANGE,Color.BLACK);
        fields[22] = new GUI_Chance("?","Prøv lykken","Beskrivelse",Color.WHITE,Color.BLACK);
        fields[23] = new GUI_Street("Østerbrogade","Pris: 4400", descCreator(new int[]{1800, 5000, 14000, 17500, 21000, 2000}),"350 kr.",Color.ORANGE,Color.BLACK);
        fields[24] = new GUI_Street("Grønningen","Pris 4800 kr.", descCreator(new int[]{2000, 6000, 15000, 18500, 22000, 4400}),"400",Color.ORANGE,Color.BLACK);
        fields[25] = new GUI_Shipping("default","MOLS-LINJEN A/S","","Pris 2000 kr. - Leje for hhv 1,2,3 eller 4 rederier: 500, 1000, 2000, 4000 kr. Pantsætningsværdi: 1.200 kr.","leje",Color.WHITE,Color.BLACK);
        fields[26] = new GUI_Street("Bredgade","Pris: 5200", descCreator(new int[]{2200, 6600, 16000, 19500, 23000, 2600}) ,"PRIS",Color.YELLOW,Color.BLACK);
        fields[27] = new GUI_Street("Kgs. Nytorv","Pris: 5200", descCreator(new int[]{2200, 6600, 16000, 19500, 23000, 2600}),"PRIS",Color.YELLOW,Color.BLACK);
        fields[28] = new GUI_Brewery("default","Carlsberg","Pris: 3000 kr.","Hvis en virksomhed ejes, betales 100 gange så meget som øjnene viser, hvis to jes betales 200 gange øjnene","leje",Color.WHITE,Color.BLACK);
        fields[29] = new GUI_Street("Østergade","Pris: 5600", descCreator(new int[]{2400, 7200, 17000, 20500, 24000, 2800}),"PRIS",Color.YELLOW,Color.BLACK);
        fields[30] = new GUI_Jail("default","I Fængsel","I Fængsel","Du er endt i fængslet, for at komme ud skal du: ",Color.LIGHT_GRAY,Color.BLACK);
        fields[31] = new GUI_Street("Amagertorv","Pris: 6000",descCreator(new int[]{2600, 7800, 18000, 22000, 25000, 3000}),"PRIS",Color.BLUE,Color.BLACK);
        fields[32] = new GUI_Street("Vimmelskaftet","Pris: 6000",descCreator(new int[]{2600, 7800, 18000, 22000, 25000, 3000}),"PRIS",Color.BLUE,Color.BLACK);
        fields[33] = new GUI_Chance("?","Prøv lykken","",Color.WHITE,Color.BLACK);
        fields[34] = new GUI_Street("Nygade","Pris: 6400",descCreator(new int[]{3000, 9000, 20000, 24000, 28000, 3200}),"PRIS",Color.BLUE,Color.BLACK);
        fields[35] = new GUI_Shipping("default","D/S Bornholm ","","Pris 4000 kr. - Leje m. hhv. 1,2,3,4 rederier: 500,1000,2000,4000 kr. Pantsætningsværdig: 2000","",Color.WHITE,Color.BLACK);
        fields[36] = new GUI_Chance("?","Prøv lykken","",Color.WHITE,Color.BLACK);
        fields[37] = new GUI_Street("Frederiksberggade","Pris: 7000",descCreator(new int[]{3500, 10000, 22000, 26000, 30000, 3500}),"PRIS",Color.PINK,Color.BLACK);
        fields[38] = new GUI_Tax("Ekstraordinær statsskat","","Betal 2000kr",Color.WHITE,Color.BLACK);
        fields[39] = new GUI_Street("Rådhuspladsen","Pris: 8000",descCreator(new int[]{4000, 12000, 28000, 34000, 40000, 4000}),"PRIS",Color.PINK,Color.BLACK);
    }

    public String descCreator(int[] desc) {     // denne metode bygger descriptions på gui felterene ud fra et givent array
        String text = "Leje ved bygninger:";

        for (int i = 0; i < desc.length-2; i++) {
            text = text + "\n Hus " + (i+1) + ": " + desc[i] + "kr.";
        }

        if (desc.length > 5) {
            text = text + "\n Hotel: " + desc[desc.length-2] + "kr.";
        }

        text = text + "\n Pantsætningsværdi: " + desc[desc.length-1] + "kr.";

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
