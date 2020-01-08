//https://www.geeksforgeeks.org/singleton-class-java/ singleton kode hentet her fra

public class Deck {
    private static Deck single_instance = null;

    public static Deck getInstance()
    {
        if (single_instance == null)
            single_instance = new Deck();

        return single_instance;
    }
}

}
