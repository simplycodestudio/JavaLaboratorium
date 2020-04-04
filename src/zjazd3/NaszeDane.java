package zjazd3;

public class NaszeDane {

    public final int i;
    public final double x;
    public final String opis;

    public NaszeDane(int i, double _x, String _s) {
        this.i = i;
        x = _x;
        opis = _s;
    }

    @Override
    public String toString() {
        return "i=" + i +
                " x=" + x +
                " Opis: " + opis;
    }
}
