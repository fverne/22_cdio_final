package GUI;

import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class Board {
    private GUI_Field[] fields = new GUI_Field[40];

    public Board() {
        fields[0] = new GUI_Start("Start","SUBTEXT","Hver gang start passeres modtages 4000 kr.",Color.red,Color.BLACK);
        fields[1] = new GUI_Street("Rødovrevej","Pris: ","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[2] = new GUI_Chance("Title","SubText","Beskrivelse",Color.WHITE,Color.BLACK);
        fields[3] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[4] = new GUI_Tax("Title","SubText","Beskrivelse",Color.WHITE,Color.BLACK);
        fields[5] = new GUI_Shipping("Billede","title","subtext","beskrivelse","leje",Color.BLACK,Color.BLACK);
        fields[6] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[7] = new GUI_Chance("Title","SubText","Beskrivelse",Color.WHITE,Color.BLACK);
        fields[8] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[9] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[10] = new GUI_Jail("Billede","title","subtext","beskrivelse",Color.BLACK,Color.BLACK);
        fields[11] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[12] = new GUI_Brewery("Billede","title","subtext","beskrivelse","leje",Color.BLACK,Color.BLACK);
        fields[13] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[14] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[15] = new GUI_Shipping("Billede","title","subtext","beskrivelse","leje",Color.BLACK,Color.BLACK);
        fields[16] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[17] = new GUI_Chance("Title","SubText","Beskrivelse",Color.WHITE,Color.BLACK);
        fields[18] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[19] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[20] = new GUI_Refuge("Billede","title","subtext","beskrivelse",Color.BLACK,Color.BLACK);
        fields[21] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[22] = new GUI_Chance("Title","SubText","Beskrivelse",Color.WHITE,Color.BLACK);
        fields[23] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[24] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[25] = new GUI_Shipping("Billede","title","subtext","beskrivelse","leje",Color.BLACK,Color.BLACK);
        fields[26] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[27] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[28] = new GUI_Brewery("Billede","title","subtext","beskrivelse","leje",Color.BLACK,Color.BLACK);
        fields[29] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[30] = new GUI_Jail("Billede","title","subtext","beskrivelse",Color.BLACK,Color.BLACK);
        fields[31] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[32] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[33] = new GUI_Chance("Title","SubText","Beskrivelse",Color.WHITE,Color.BLACK);
        fields[34] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[35] = new GUI_Shipping("Billede","title","subtext","beskrivelse","leje",Color.BLACK,Color.BLACK);
        fields[36] = new GUI_Chance("Title","SubText","Beskrivelse",Color.WHITE,Color.BLACK);
        fields[37] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);
        fields[38] = new GUI_Tax("Title","SubText","Beskrivelse",Color.WHITE,Color.BLACK);
        fields[39] = new GUI_Street("Title","SubText","Beskrivelse","PRIS",Color.WHITE,Color.BLACK);


    }

    //returnrere gui objektet så spilbrættet kan initialiseres
    public GUI initGUI() {
        return new GUI(fields, Color.white);
    }
}
