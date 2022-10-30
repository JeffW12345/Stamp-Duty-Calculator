package main.propertytaxcalculator.taxcalculations;

public class TaxableAmountProcessed {

    private double remaining;
    protected TaxableAmountProcessed(double propertyValue) {
        this.remaining = propertyValue;
    }
    public boolean zeroRemaining(){
        return remaining <= 0;
    }
    protected void add(double taxableInThisBand) {
        remaining -= taxableInThisBand;
    }
}
