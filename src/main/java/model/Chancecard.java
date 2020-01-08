package main.java.model;

import model.Wallet;

import java.io.*;
import java.util.Random;
import java.util.Arrays;
import java.lang.*;




public class Chancecard  {

private static model.Wallet trans = new Wallet;
private static Player move = new Player;


    private static Object ArrayUtils;

    public static int generateChancecard() {
        int[] chanceArray = new int[31];
            int rndChanceCardValue = new Random().nextInt(chanceArray.length);
        //chanceArray = ArrayUtils.remove(chanceArray, rndChanceCardValue);
            return chanceArray[rndChanceCardValue];

            }
    public static int giveChanceCard(int rndChanceCardValue){
        String fileName = "TekstChanceKort/ChanceCardText.txt";
        File file = new File(fileName);
        String line;
        String[] text = new String[32];
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for (int i = 0; (line = bufferedReader.readLine()) != null; i++){
                text[i] = line;
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex){
            System.out.println("error reading file '" + fileName + "'");
        }
        switch(rndChanceCardValue){

            //betal penge
            case 1:
                trans.withdraw(1000);
            break;
            case 2:
                trans.withdraw();

                break;
            case 3:
                trans.withdraw(200);
                break;
            case 4
                trans.withdraw(3000);
                break;
            case 5:
                trans.withdraw(3000);
                break;

            case 6:
                trans.withdraw(1000);
                break;
            case 7:
                trans.withdraw(2000);
                break;
            case 8:
                trans.withdraw(200);
                break;
            case 9:
                break;
                //Modtag penge katagori
            // case 10 Matador legat
            case 10:
                if(trans.balance <= 15000){
                trans.deposit(20000);
                    break;
            }
                if(trans.balance > 15000){
                    break;
                }

            case 11:
                trans.deposit(1000);
                break;
            case 12:
                trans.deposit(1000);
                break;
            case 13:
                trans.deposit(1000);
                break;
            case 14:
                trans.deposit(200);
                break;
            case 15:
                trans.deposit(1000);
                break;

            case 16:
                trans.deposit(1000);
                break;
            case 17:// modtag 200 for alle andre spillere

                break;
            case 18:
                trans.deposit(1000);
                break;
            case 19:
                trans.deposit(3000);
                break;
            case 20:
                trans.deposit(500);
                break;
                //Ryk til specifikt felt Chancekort
            case 21:
                move.setSpecificPosition(39);
                break;
            case 22:
                move.setSpecificPosition(24);
                break;
            case 23:
                move.setSpecificPosition(24);
                break;
            case 24:
                move.setSpecificPosition(5);
                break;
            case 25:
                move.setSpecificPosition(0);
                break;
            case 26:
                move.setSpecificPosition(11);
                break;
            case 27:
                break;
            case 28:
                break;
            case 29:
                break;
            case 30:
                break;
            case 31:
                break;
            case 32:
                break;










        }

    }

    }




