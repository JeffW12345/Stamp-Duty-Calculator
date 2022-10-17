package propertytaxcalculator;


public class TaxBand {
    private double from;
    private double to;
    private double rate;


    private double size;

    public TaxBand(double from, double to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
        size = to - from;
    }

    public double getSize() {
        return size;
    }

    public double calcTaxThisBand(double remainder) {
        return remainder >= size ? rate * size : rate * remainder;
    }
}
