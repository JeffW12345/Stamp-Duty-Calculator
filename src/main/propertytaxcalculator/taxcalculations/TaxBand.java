package main.propertytaxcalculator.taxcalculations;


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
