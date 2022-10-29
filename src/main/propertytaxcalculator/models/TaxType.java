package main.propertytaxcalculator.models;
import main.propertytaxcalculator.taxcalculations.TaxBands;

public abstract class TaxType {

    protected TaxBands taxBands = new TaxBands();
    protected double propertyValue;
    protected double taxDue;

    protected String name;

    public TaxType(double propertyValue) {
        this.propertyValue = propertyValue;
        setTaxThresholds();
    }

    protected abstract void setTaxThresholds();

    public double taxDue() {
        return taxBands.calculateTax(propertyValue);
    }
}
