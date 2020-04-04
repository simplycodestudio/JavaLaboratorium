package zjazd3;

public class NaszWyjatek extends Exception {

    public enum Akcja {POWTORZ, ZREZYGNUJ};

    public final Akcja akcja;

    public NaszWyjatek(String wiadomosc, Akcja coRobic) {
        super(wiadomosc);
        akcja = coRobic;
    }
}
