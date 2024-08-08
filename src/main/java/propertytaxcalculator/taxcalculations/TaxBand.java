package propertytaxcalculator.taxcalculations;


public class TaxBand {
    private final double from;
    private final double to;
    private final double rate;
    private final double size;

    public TaxBand(double from, double to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.size = to - from;
    }

    protected void updateRunningTotal(double propertyValue, RunningTaxTotal runningTaxTotal) {
        runningTaxTotal.add(Math.round(taxableInThisBand(propertyValue) * rate * 100.0) / 100.0);
    }

    private double taxableInThisBand(double propertyValue) {
        if(propertyValue < from) return 0;
        if(propertyValue >= from & propertyValue < to){
            return propertyValue - from;
        }
        return to - from;
    }

    protected void updateTaxProcessed(double propertyValue, TaxableAmountProcessed taxableAmountProcessed){
        taxableAmountProcessed.add(taxableInThisBand(propertyValue));
    }
}
