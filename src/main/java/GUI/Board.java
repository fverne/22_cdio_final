package GUI;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class Board {
    private GUI_Field[] fields = new GUI_Field[40];

    public Board() {
        fields[0] = new GUI_Start("Start","","Hver gang start passeres modtages 4000 kr.",Color.red,Color.BLACK);
        fields[1] = new GUI_Street("Rødovrevej","Pris: 1200","Leje m. hhv. 1,2,3,4 huse: 250,750,2250,4000 kr. 1 hotel: 6000 kr. Pantsætningsværdig: 1200","50",Color.WHITE,Color.BLACK);
        fields[2] = new GUI_Chance("?","Prøv lykken","",Color.WHITE,Color.BLACK);
        fields[3] = new GUI_Street("Hvidovrevej","Pris: 1200 kr.","Leje m. hhv. 1,2,3,4 huse: 250,750,2250,4000 kr. 1 hotel: 6000 kr. Pantsætningsværdig: 1800","600",Color.WHITE,Color.BLACK);
        fields[4] = new GUI_Tax("Betal inkomstskat","10% eller 200 kr.","Beskrivelse",Color.WHITE,Color.BLACK);
        fields[5] = new GUI_Shipping("default","Øresund","","Pris 4000 - Leje m. hhv. 1,2,3,4 rederier: 500,1000,2000,4000 kr. Pantsætningsværdig: 2000","500",Color.WHITE,Color.BLACK);
        fields[6] = new GUI_Street("Roskildevej","Pris: 2000","Leje m. hhv. 1,2,3,4 huse: 600,1800,5400,8000 kr. 1 hotel: 11000 kr. Pantsætning: kr.","PRIS",Color.RED,Color.BLACK);
        fields[7] = new GUI_Chance("?","Prøv lykken","",Color.WHITE,Color.BLACK);
        fields[8] = new GUI_Street("Valby langgade","Pris: 2000","Leje m. hhv. 1,2,3,4 huse: 600,1800,5400,8000 kr. 1 hotel: 11000 kr.. Pantsætning: kr.","PRIS",Color.RED,Color.BLACK);
        fields[9] = new GUI_Street("Allégade","2400 kr.","Leje m. hhv. 1,2,3,4 huse: 800,2000,6000,9000 kr. 1 hotel: 12000 kr. Pantsætning: kr.","PRIS",Color.RED,Color.BLACK);
        fields[10] = new GUI_Jail("default","I Fængsel","Fængsel","Du slipper med en advarsel denne gang!",Color.LIGHT_GRAY,Color.BLACK);
        fields[11] = new GUI_Street("Frederiksberg Allé","Pris 2800","Leje m. hhv. 1,2,3,4 huse: 1000,3000,9000,12500. 1 hotel: 15000. Pantsætning: 1400","PRIS",Color.GREEN,Color.BLACK);
        fields[12] = new GUI_Brewery("default","Tuborg","Pris: 3000 kr.","Hvis en virksomhed ejes, betales 100 gange så meget som øjnene viser, hvis to jes betales 200 gange øjnene","leje",Color.WHITE,Color.BLACK);
        fields[13] = new GUI_Street("Bülowsvej","Pris: 2800","Leje m. hhv. 1,2,3,4 huse: 1000,3000,9000,12500 kr. 1 hotel: 15000. Pantsætning: 1400 kr.","PRIS",Color.GREEN,Color.BLACK);
        fields[14] = new GUI_Street("Gl. Kongevej","Pris: 3200 kr.","Leje m. hhv. 1,2,3,4 huse: 1250,3750,10000,14000. 1 hotel: 18000. Pantsætning: 1600","",Color.GREEN,Color.BLACK);
        fields[15] = new GUI_Shipping("default","Skandinavisk Linietrafik A/S","","Pris: 4000 - Leje m. hhv. 1,2,3,4 rederier: 500,1000,2000,4000 kr. Pantsætningsværdig: 2000","",Color.WHITE,Color.BLACK);
        fields[16] = new GUI_Street("Bernstoffsvej","3600 kr.","Leje m. hhv. 1,2,3,4 huse: 1400,4000,11000,15000 kr. 1 hotel: 19000 kr. Pantsætningsværdig: 1800","PRIS",Color.LIGHT_GRAY,Color.BLACK);
        fields[17] = new GUI_Chance("?","Prøv lykken","",Color.WHITE,Color.BLACK);
        fields[18] = new GUI_Street("Hellerupvej","Pris: 3600","Leje m. hhv. 1,2,3,4 huse: 1400,4000,11000,15000 kr. 1 hotel: 19000 kr. Pantsætningsværdig: 1800","300",Color.LIGHT_GRAY,Color.BLACK);
        fields[19] = new GUI_Street("Strandvej","Pris: 4000","Leje m. hhv. 1,2,3,4 huse: 1600,4400,12000,16000 kr. 1 hotel: 20000 kr. Pantsætningsværdig: 1800","350",Color.LIGHT_GRAY,Color.BLACK);
        fields[20] = new GUI_Refuge("default","Helle","Helle","Hold en pause",Color.WHITE,Color.BLACK);
        fields[21] = new GUI_Street("Trianglen","Pris: 4400","Leje m. hhv. 1,2,3,4 huse: 1800,5000,14000,17500,21000 kr. 1 hotel: 21000 kr. Pantsætningsværdig: 2200 kr. ","350 kr.",Color.ORANGE,Color.BLACK);
        fields[22] = new GUI_Chance("?","Prøv lykken","Beskrivelse",Color.WHITE,Color.BLACK);
        fields[23] = new GUI_Street("Østerbrogade","Pris: 4400","Leje m. hhv. 1,2,3,4 huse: 1800,5000,14000,17500,21000 kr. 1 hotel: 21000 kr. Pantsætningsværdig: 2200 kr.","PRIS",Color.ORANGE,Color.BLACK);
        fields[24] = new GUI_Street("Grønningen","Pris 4800 kr.","Leje m. hhv. 1,2,3,4 huse: 2000,6000,15000,18500kr. 1 hotel: 22000kr. ","400",Color.ORANGE,Color.BLACK);
        fields[25] = new GUI_Shipping("default","MOLS-LINJEN A/S","","Pris 2000 kr. - Leje for hhv 1,2,3 eller 4 rederier: 500, 1000, 2000, 4000 kr. Pantsætningsværdi: 1.200 kr.","leje",Color.WHITE,Color.BLACK);
        fields[26] = new GUI_Street("Bredgade","Pris: 5200","Leje m. hhv. 1,2,3,4 huse: 2200,6600,16000,19500 kr. 1 hotel: 23000 kr. Pantsætning: 2600kr.","PRIS",Color.YELLOW,Color.BLACK);
        fields[27] = new GUI_Street("Kgs. Nytorv","Pris: 5200","Leje m. hhv. 1,2,3,4 huse: 2200,6600,16000,19500 kr. 1 hotel: 23000 kr.. Pantsætning: 2600 kr.","PRIS",Color.YELLOW,Color.BLACK);
        fields[28] = new GUI_Brewery("default","Carlsberg","Pris: 3000 kr.","Hvis en virksomhed ejes, betales 100 gange så meget som øjnene viser, hvis to jes betales 200 gange øjnene","leje",Color.WHITE,Color.BLACK);
        fields[29] = new GUI_Street("Østergade","Pris: 5600","Leje m. hhv. 1,2,3,4 huse: 2400,7200,17000,20500 kr. 1 hotel: 24000 kr. Pantsætning: 2800kr.","PRIS",Color.YELLOW,Color.BLACK);
        fields[30] = new GUI_Jail("default","I Fængsel","I Fængsel","Du er endt i fængslet, for at komme ud skal du: ",Color.LIGHT_GRAY,Color.BLACK);
        fields[31] = new GUI_Street("Amagertorv","Pris: 6000","Leje m. hhv. 1,2,3,4 huse: 2600,7800,18000, kr. 1 hotel: 25000 kr. Pantsætning: 3000kr.","PRIS",Color.BLUE,Color.BLACK);
        fields[32] = new GUI_Street("Vimmelskaftet","Pris: 6000","Leje m. hhv. 1,2,3,4 huse: 2600,7800,18000,22000 kr. 1 hotel: 25000. Pantsætning: 3000 kr.","PRIS",Color.BLUE,Color.BLACK);
        fields[33] = new GUI_Chance("?","Prøv lykken","",Color.WHITE,Color.BLACK);
        fields[34] = new GUI_Street("Nygade","Pris: 6400","Leje m. hhv. 1,2,3,4 huse: 3000,9000,20000,24000 kr. 1 hotel: 28000. Pantsætning: 3200 kr.","PRIS",Color.BLUE,Color.BLACK);
        fields[35] = new GUI_Shipping("default","D/S Bornholm ","","Pris 4000 kr. - Leje m. hhv. 1,2,3,4 rederier: 500,1000,2000,4000 kr. Pantsætningsværdig: 2000","",Color.WHITE,Color.BLACK);
        fields[36] = new GUI_Chance("?","Prøv lykken","",Color.WHITE,Color.BLACK);
        fields[37] = new GUI_Street("Frederiksberggade","Pris: 7000","Leje m. hhv. 1,2,3,4 huse: 3500,10000,22000,26000 kr. 1 hotel: 30000. Pantsætning: 3500 kr.","PRIS",Color.PINK,Color.BLACK);
        fields[38] = new GUI_Tax("Ekstraordinær statsskat","","Betal 200 kr eller 10% af formue",Color.WHITE,Color.BLACK);
        fields[39] = new GUI_Street("Rådhuspladsen","Pris: 8000","Leje m. hhv. 1,2,3,4 huse: 4000,12000,28000,34000 kr. 1 hotel: 40000. Pantsætning: 4000 kr.","PRIS",Color.PINK,Color.BLACK);
    }

    //returnrere gui objektet så spilbrættet kan initialiseres
    public GUI initGUI() {
        return new GUI(fields, Color.white);
    }
    public GUI_Field getGUIField(int fieldNumber) {
        return fields[fieldNumber];
    }
}
