package model.language;

public class Language {

    // To use this model.language class, simply replace the string returned with a translation to the model.language you want.


    // -------
    // General
    // -------
    public static String currency() {
        return "Kr.";
    }

    // -------------
    // GUIController
    // -------------
    public static String yes() {
        return "Ja";
    }

    public static String no() {
        return "Nej";
    }

    public static String ok() {
        return "Ok";
    }

    public static String pay() {
        return "Betal";
    }

    public static String enterPlayerAmount() {
        return "Indtast Antal Spillere";
    }

    public static String enterCorrectPlayerAmount() {
        return "Indtast mellem 3-6 spillere";
    }

    public static String throwDies() {
        return "Kast terningerne";
    }

    public static String ownedBy() {
        return "Ejet af";
    }

    public static String enterPrice() {
        return "Indtast pris/bud";
    }

    public static String enterName() {
        return "Indtast dit navn";
    }

    public static String enterHouses() {
        return "Indtast ønsket antal huse";
    }

    public static String illegalLength() {
        return "Det indtastede navn er af ugyldig længde";
    }

    public static String illegalName() {
        return "Navnet er taget af en anden spiller";
    }

    public static String getUserResponse(String playername) {
        return String.format("Det er %s's tur", playername);
    }

    public static String notOwned() {
        return "Ikke ejet";
    }

    public static String winnerMessage(String playername) {
        return String.format("Tillykke %s, du er spillets vinder!", playername);
    }

    //------------------
    //MovementController
    //------------------

    public static String passedStart() {
        return "Du har passeret start, modtag 4000 kr";
    }

    //------------------
    //Board
    //------------------

    public static String buildingRents() {
        return "Leje ved bygninger";
    }

    public static String shippingRents() {
        return "Leje ved flere rederier";
    }

    public static String breweryDesc() {
        return "Hvis en virksomhed ejes, betales 100 gange så meget som øjnene viser, hvis to jes betales 200 gange øjnene";
    }

    public static String startDesc() {
        return "Hver gang du passerer start modtager du 4000";
    }

    public static String prisonDesc() {
        return "Du er endt i fængslet, for at komme ud skal du";
    }

    public static String ship() {
        return "Skib";
    }

    public static String prison() {
        return "Fængsel";
    }

    public static String inPrison() {
        return "I Fængsel";
    }

    public static String refuge() {
        return "Helle";
    }

    public static String refugeDesc() {
        return "Tag et Hvil";
    }

    public static String house() {
        return "Hus";
    }

    public static String hotel() {
        return "Hotel";
    }

    public static String price() {
        return "Pris";
    }

    public static String tryChance() {
        return "Prøv Lykken";
    }

    public static String taxDesc() {
        return "Betal enten:\n - 10% af din formue\n - 4000";
    }

    public static String[] fieldTitles() {
        String[] titles = new String[40];

        titles[0] = "Start";
        titles[1] = "Rødovrevej";
        titles[2] = "?";
        titles[3] = "Hvidovrevej";
        titles[4] = "Indkomstskat";
        titles[5] = "Øresund";
        titles[6] = "Roskildevej";
        titles[7] = "?";
        titles[8] = "Valby Langgade";
        titles[9] = "Allégade";
        titles[10] = "I Fængsel";
        titles[11] = "Frederiksberg Allé";
        titles[12] = "Tuborg";
        titles[13] = "Bülowsvej";
        titles[14] = "Gl. Kongevej";
        titles[15] = "Linietrafik";
        titles[16] = "Bernstoffsvej";
        titles[17] = "?";
        titles[18] = "Hellerupvej";
        titles[19] = "Strandvej";
        titles[20] = "Helle";
        titles[21] = "Trianglen";
        titles[22] = "?";
        titles[23] = "Østerbrogade";
        titles[24] = "Grønningen";
        titles[25] = "Molslinjen";
        titles[26] = "Bredgade";
        titles[27] = "Kgs. Nytorv";
        titles[28] = "Carlsberg";
        titles[29] = "Østergade";
        titles[30] = "I Fængsel";
        titles[31] = "Amagertorv";
        titles[32] = "Vimmelskaftet";
        titles[33] = "?";
        titles[34] = "Nygade";
        titles[35] = "Bornholm";
        titles[36] = "?";
        titles[37] = "Frederiks-\nberggade";
        titles[38] = "Ekstra-\noridinær Statsskat";
        titles[39] = "Rådhus-\npladsen";

        return titles;
    }

    // --------------
    // TurnController
    // --------------

    public static String queryFieldBuy(int fieldcost) {
        return String.format("Vil du købe grunden? Prisen er: %d kr.", fieldcost);
    }

    public static String queryPayTax() {
        return "Hvordan vil du betale indkomstskatten?";
    }

    public static String payRentToPlayer(String playername, int fieldrent) {
        return String.format("Du skal betale husleje til %s, på %d kr.", playername, fieldrent);
    }

    public static String bidForField(String playername, String fieldname, int highestbid) {
        return String.format("%s, vil du byde på %s for %d", playername, fieldname, highestbid);
    }

    public static String bidLowerThanCompetition() {
        return "Dit bud er lavere end det højeste bud, indtast et nyt bud";
    }

    public static String lostGame() {
        return "Du kan ikke betale din udgift og er gået falit, du er nu ude af spillet";
    }

    public static String bidHigherThanBalance() {
        return "Dit bud er højere end din balance, indtast et nyt bud";
    }

    public static String queryHotelBuy(int hotelcost) {
        return String.format("Vil du bygge et hotel? Prisen er: %d kr.", hotelcost);
    }

    public static String queryHouseBuy(int housecost) {
        return String.format("Vil du bygge huse? Prisen er: %d kr. pr. hus.", housecost);
    }

    public static String maxHouses() {
        return "Du kan højst bygge 4 huse pr. felt.";
    }

    public static String noMoneyForHouses(int housecost) {
        return String.format("Du har ikke penge nok til %d huse", housecost);
    }

    public static String queryUseJailCard() {
        return "Vil du bruge et gratis \"Ud af fængslet\"-kort";
    }

    public static String queryPayBail() {
        return "Vil du betale 1000 kr. for at komme ud af fængslet";
    }

}
