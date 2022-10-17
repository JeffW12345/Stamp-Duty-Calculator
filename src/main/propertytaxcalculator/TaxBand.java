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

    public double calculateBandTaxFor(double propertyValue) {
        return Math.round(taxableInThisBand(propertyValue) * rate * 100.0) / 100.0;
    }

    public double taxableInThisBand(double propertyValue) {
        if(propertyValue < from) return 0;
        if(propertyValue >= from & propertyValue < to){
            return propertyValue - from;
        }
        return to - from;
    }
}
