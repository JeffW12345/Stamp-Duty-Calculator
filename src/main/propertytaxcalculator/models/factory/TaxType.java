package main.propertytaxcalculator.models.factory;
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

    public double getTaxDue() {
        return taxDue;
    }

    protected abstract void setTaxThresholds();

    public void calculate() {
        taxDue = taxBands.calculateTax(propertyValue);
    }
}