package propertytaxcalculator.taxcalculation;

import propertytaxcalculator.TaxBands;


public interface TaxCalculation {
    public double calculateTax(TaxBands taxbands, double propertyValue);
}
