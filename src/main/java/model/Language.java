package model;

public class Language {

    // To use this language class, simply replace the string returned with a translation to the language you want.

    public static String yes(){
        return "Ja";
    }

    public static String no(){
        return "Nej";
    }

    public static String ok(){
        return "OK";
    }

    public static String inputPlayerAmount(){
        return "Indtast Antal Spillere";
    }

    public static String getUserResponse(String playername) {
        return String.format("Det er %s's tur", playername);
    }
}
