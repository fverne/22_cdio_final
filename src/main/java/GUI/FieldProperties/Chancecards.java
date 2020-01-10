package GUI.FieldProperties;

import model.Player;

import java.util.Random;

public class Chancecards extends Fields {
    private String message;
    private int reward;
    private int position;

    private static Chancecards[] cards = new Chancecards[]{
            new Chancecards("Betal 3.000 kr. for reperation af deres vogn", -3000, 0, "Bil til reperation", false),
            new Chancecards("Betal 2000 kr. for din tandlægeregning", -2000, 0, "Hos tandlejen", false),
            new Chancecards("De har kørt overfor Fuld Stop, betal 1000 kr. i bøde",-1000,0,"Kør ordenligt!",false),
            new Chancecards("Betal 3.000 kr. for reperation af deres vogn", -3000, 0, "Bil til reperation", false),
            new Chancecards("De har været i udlandet og har for mange cigaretter med hjem, betal 200 kr. i told",-200,0,"For mange smøger med hjem",false),
            new Chancecards("Ejendomsskatten er steget, ekstraudgifterne er: 800 kr. pr. hus / 2300 kr. pr. hotel",-800,0,"Ejendomsskat",false),
            new Chancecards("Betal 1000 kr. for bilforsikringen",-1000,0,"Bilforsikring",false),
            new Chancecards("Du har fået en parkeringsbøde, betal 200 kr.",-200,0,"Parkeringsbøde",false),
            new Chancecards("Du har vundet i Klasselotteriet, modtag 500 kr.",500,0,"Klasselotteriet",false),
            new Chancecards("Kommunen har eftergivet et kvartals skat. Hæv 3000kr. i banken",3000,0,"Kvartalsskat",false),
            new Chancecards("Lønforhøjsen",1000,0,"Lønforhøjelse! modtog 1000 kr.",false),
            new Chancecards("Deres præmieobligation er kommet ud. Du modtager 1000 kr. fra banken",1000,0,"Præmieobligation",false),
            new Chancecards("De har modtaget deres aktieudbytte. Modtag 1000 kr. fra banken",1000,0,"Aktieudbytte",false),
            new Chancecards("Værdien af egen avl fra nyttehaven udgør 200, som du modtager fra banken",200,0,"Nyttehave udbytte",false),
            new Chancecards("Modtag udbytte af Deres aktier, modtag 1000 kr.",1000,0,"Aktieudbytte",false),
            new Chancecards("De havde en række med elleve rigtige, modtag 1000 kr. banken",1000,0,"",false),
            new Chancecards("Modtag udbytte af Deres aktier, modtag 1000 kr.",1000,0,"Aktieudbytte",false),
            new Chancecards("Gå i fængsel. Ryk direkte i fængslet. Selv om start passeres, modtager de ikke 4000 kr.",0,0,"Gå i Fængsel",true),

            //Ikke implementeret
            //new Chancecards("Ryk frem til frederiksberg alle, hvis start passeres, inkassér 4000 kr.", 0,0,"Ryk til frb. alle",false),
            //new Chancecards("Ryk frem til grønningen, hvis start passeres, inkassér 4000 kr.", 0,0,"Ryk til frb. alle",false),
            //new Chancecards("Ryk frem til start",0,0,"START",false),
            //new Chancecards("Tag med øresundsbåden - Flyt brikken frem, hvis start passeres, modtag 4000 kr.", 0,0,"Øresundsbåden",false),
            //new Chancecards("Besøg rådhuspladsen",0,0,"",false),
            //new Chancecards("Ryk brikken frem til det nærmeste rederi og betal ejen to gange den leje, han ellers er berettiget til. Hvis selvskabet ikke ejes af nogen kan du købe det af banken",0,0,"",false),
            //new Chancecards("Ryk tre felter tilbage",0,0,"",false),
            //new Chancecards("Ryk brikken frem til det nærmeste rederi og betal ejen to gange den leje, han ellers er berettiget til. Hvis selvskabet ikke ejes af nogen kan du købe det af banken",0,0,"",false),
            //new Chancecards("Det er deres fødselsdag, modtag 200 kr fra hver spiller!",200,0,"Fødselsdag",false),
            //new Chancecards("De modtager \"Matadorlegatet\" for værdigt trængende på 40000kr, en værdigt trængende er en person hvis formue, dvs penge + skøder + bygninger, ikke overstiger 15000 kr.", 0,0,"Matadorlegatet",false),
            //new Chancecards("I anledningen af Dronningensfødselsdag benådes De herved for fængsel, dette kort kan opbevares, indtil der er brug for det, ellers kan det sælges",0,0,"Benådet",false),
            //new Chancecards("I anledningen af Dronningensfødselsdag benådes De herved for fængsel, dette kort kan opbevares, indtil der er brug for det, ellers kan det sælges",0,0,"Benådet",false),
            //new Chancecards("Oliepriserne er steget, og De skal betale: 500 kr. pr. hus/2000 kr. pr. hotel",0,0,"Stigende oliepriser",false),
    };

    public Chancecards(String message, int reward, int position, String name, boolean isJail) {
        super(name, isJail);
        this.message = message;
        this.reward = reward;
        this.position = position;
    }
    public Chancecards() {
        super();
    }
    public static Chancecards getRandomCard() {
        int i = new Random().nextInt(cards.length);
        return cards[i];
    }
    public int getReward() {
        return reward;
    }
    public String getMessage() {
        return message;
    }

    @Override
    public Fields landOnField(Player player) {
        Chancecards card = Chancecards.getRandomCard();
        player.addToBalance(card.getReward());
        return this;
    }
}

