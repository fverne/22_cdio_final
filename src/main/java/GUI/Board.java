package GUI;

import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class Board {
    private GUI_Field[] fields = new GUI_Field[40];

    public Board() {
        fields[0] = new GUI_Start();
        fields[1] = new GUI_Street();
        fields[2] = new GUI_Chance();
        fields[3] = new GUI_Street();
        fields[4] = new GUI_Tax();
        fields[5] = new GUI_Shipping();
        fields[6] = new GUI_Jail();
        fields[7] = new GUI_Street();
        fields[8] = new GUI_Chance();
        fields[9] = new GUI_Street();
        fields[10] = new GUI_Street();
        fields[11] = new GUI_Jail();
        fields[12] = new GUI_Street();
        fields[13] = new GUI_Brewery();
        fields[14] = new GUI_Street();
        fields[15] = new GUI_Street();
        fields[16] = new GUI_Shipping();
        fields[17] = new GUI_Street();
        fields[18] = new GUI_Chance();
        fields[19] = new GUI_Street();
        fields[20] = new GUI_Street();
        fields[21] = new GUI_Refuge();
        fields[22] = new GUI_Street();
        fields[23] = new GUI_Chance();
        fields[24] = new GUI_Start();
        fields[25] = new GUI_Shipping();
        fields[26] = new GUI_Street();
        fields[27] = new GUI_Street();
        fields[28] = new GUI_Brewery();
        fields[29] = new GUI_Street();
        fields[30] = new GUI_Jail();
        fields[31] = new GUI_Street();
        fields[32] = new GUI_Street();
        fields[33] = new GUI_Chance();
        fields[34] = new GUI_Street();
        fields[35] = new GUI_Shipping();
        fields[36] = new GUI_Chance();
        fields[37] = new GUI_Street();
        fields[38] = new GUI_Tax();
        fields[39] = new GUI_Street();
    }

    //returnrere gui objektet så spilbrættet kan initialiseres
    public GUI initGUI() {
        GUI gui = new GUI(fields, Color.white);
        return gui;
    }
}
