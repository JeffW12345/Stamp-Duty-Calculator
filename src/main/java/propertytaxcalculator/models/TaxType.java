package propertytaxcalculator.models;


import propertytaxcalculator.numberformat.NumberFormat;
import propertytaxcalculator.taxcalculations.TaxBands;

public abstract class TaxType {

    protected TaxBands taxBands = new TaxBands();
    protected double propertyValue;

    public TaxType(double propertyValue) {
        this.propertyValue = propertyValue;
        setTaxThresholds();
    }
    protected abstract void setTaxThresholds();

    private double taxDue() {
        return taxBands.calculateTax(propertyValue);
    }

    public String taxDueFormattedString(){
        return new NumberFormat().addCommasAndPence(taxDue() + "");
    }

}
