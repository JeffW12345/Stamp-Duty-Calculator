package propertytaxcalculator.taxcalculation;

import propertytaxcalculator.TaxBands;


public interface TaxCalculation {
    double calculateTax(TaxBands taxbands, double propertyValue);
}
