package field.NotOwnable.Chancecard;//https://www.geeksforgeeks.org/singleton-class-java/ singleton kode hentet her fra

public class Deck {
    private static Deck single_instance = null;
    private int drawCounter = 0;

    public static Deck getInstance() {
        if (single_instance == null)
            single_instance = new Deck();

        return single_instance;
    }

    private ChanceCard[] cards = new ChanceCard[]{
            //new ChanceCard("Betal 3.000 kr. for reperation af deres vogn", -3000, 1, "Bil til reperation", false),
            //new ChanceCard("Betal 2000 kr. for din tandlægeregning", -2000, 1, "Hos tandlejen", false),
            //new ChanceCard("De har kørt overfor Fuld Stop, betal 1000 kr. i bøde", -1000, 1, "Kør ordenligt!", false),
            //new ChanceCard("Betal 3.000 kr. for reperation af deres vogn", -3000, 1, "Bil til reperation", false),
            //new ChanceCard("De har været i udlandet og har for mange cigaretter med hjem, betal 200 kr. i told", -200, 1, "For mange smøger med hjem", false),
            //new ChanceCard("Ejendomsskatten er steget, ekstraudgifterne er: 800 kr. pr. hus / 2300 kr. pr. hotel", -800, 1, "Ejendomsskat", false),
            //new ChanceCard("Betal 1000 kr. for bilforsikringen", -1000, 1, "Bilforsikring", false),
            //new ChanceCard("Du har fået en parkeringsbøde, betal 200 kr.", -200, 1, "Parkeringsbøde", false),
            //new ChanceCard("Du har vundet i Klasselotteriet, modtag 500 kr.", 500, 1, "Klasselotteriet", false),
            //new ChanceCard("Kommunen har eftergivet et kvartals skat. Hæv 3000kr. i banken", 3000, 1, "Kvartalsskat", false),
            //new ChanceCard("Lønforhøjsen", 1000, 0, "Lønforhøjelse! modtog 1000 kr.", false),
            //new ChanceCard("Deres præmieobligation er kommet ud. Du modtager 1000 kr. fra banken", 1000, 1, "Præmieobligation", false),
            //new ChanceCard("De har modtaget deres aktieudbytte. Modtag 1000 kr. fra banken", 1000, 1, "Aktieudbytte", false),
            //new ChanceCard("Værdien af egen avl fra nyttehaven udgør 200, som du modtager fra banken", 200, 1, "Nyttehave udbytte", false),
            //new ChanceCard("Modtag udbytte af Deres aktier, modtag 1000 kr.", 1000, 1, "Aktieudbytte", false),
            //new ChanceCard("De havde en række med elleve rigtige, modtag 1000 kr. banken", 1000, 1, "", false),
            //new ChanceCard("Modtag udbytte af Deres aktier, modtag 1000 kr.", 1000, 1, "Aktieudbytte", false),
            new ChanceCard("Gå i fængsel. Ryk direkte i fængslet. Selv om start passeres, modtager de ikke 4000 kr.", 0, 10, "Gå i Fængsel", true),
            new ChanceCard("Ryk frem til frederiksberg alle, hvis start passeres, inkassér 4000 kr.", 0, 11, "Ryk til frb. alle", false),
            new ChanceCard("Ryk frem til grønningen, hvis start passeres, inkassér 4000 kr.", 0, 24, "Ryk til Grønningen", false),
            new ChanceCard("Ryk frem til start", 0, 0, "START", false),
            new ChanceCard("Tag med øresundsbåden - Flyt brikken frem, hvis start passeres, modtag 4000 kr.", 0, 5, "Øresundsbåden", false),
            new ChanceCard("Besøg rådhuspladsen", 0, 39, "", false),
            //new ChanceCard("Ryk brikken frem til det nærmeste rederi og betal ejen to gange den leje, han ellers er berettiget til. Hvis selvskabet ikke ejes af nogen kan du købe det af banken",0,0,"",false),
            //new ChanceCard("Ryk tre felter tilbage",0,0,"",false),
            //new ChanceCard("Ryk brikken frem til det nærmeste rederi og betal ejen to gange den leje, han ellers er berettiget til. Hvis selvskabet ikke ejes af nogen kan du købe det af banken",0,0,"",false),
            //new ChanceCard("Det er deres fødselsdag, modtag 200 kr fra hver spiller!",200,0,"Fødselsdag",false),
            //new ChanceCard("De modtager \"Matadorlegatet\" for værdigt trængende på 40000kr, en værdigt trængende er en person hvis formue, dvs penge + skøder + bygninger, ikke overstiger 15000 kr.", 0,0,"Matadorlegatet",false),
            //new ChanceCard("I anledningen af Dronningensfødselsdag benådes De herved for fængsel, dette kort kan opbevares, indtil der er brug for det, ellers kan det sælges",0,0,"Benådet",false),
            //new ChanceCard("I anledningen af Dronningensfødselsdag benådes De herved for fængsel, dette kort kan opbevares, indtil der er brug for det, ellers kan det sælges",0,0,"Benådet",false),
            //new ChanceCard("Oliepriserne er steget, og De skal betale: 500 kr. pr. hus/2000 kr. pr. hotel",0,0,"Stigende oliepriser",false),
    };

    public ChanceCard draw() {
        return this.cards[(int) (Math.random() * (cards.length))];
    }
}


