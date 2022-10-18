package propertytaxcalculator;

public class TaxableAmountProcessed {

    private double remaining;

    public TaxableAmountProcessed(double propertyValue) {
        this.remaining = propertyValue;
    }

    boolean zeroRemaining(){
        return remaining <= 0;
    }

    public void add(double taxableInThisBand) {
        remaining -= taxableInThisBand;
    }
}
