package propertytaxcalculator;


public class TaxBand {
    public double from;
    public double to;
    public double rate;
    public double size;

    public TaxBand(double from, double to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
        size = to - from;
    }
}
