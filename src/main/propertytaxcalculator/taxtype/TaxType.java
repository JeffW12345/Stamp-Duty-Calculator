package propertytaxcalculator.taxtype;
import propertytaxcalculator.TaxBands;
import propertytaxcalculator.taxcalculation.TaxCalculation;

public abstract class TaxType {

    protected TaxBands taxBands = new TaxBands();
    protected TaxCalculation taxCalculation;
    protected double propertyValue;
    protected double taxDue;

    public TaxType(TaxCalculation taxCalculation, double propertyValue) {
        this.taxCalculation = taxCalculation;
        this.propertyValue = propertyValue;
        setTaxThresholds();
    }

    public double getTaxDue() {
        return taxDue;
    }

    protected abstract void setTaxThresholds();

    public void calculate() {
        taxDue = taxCalculation.calculateTax(taxBands, propertyValue);
    }
}
