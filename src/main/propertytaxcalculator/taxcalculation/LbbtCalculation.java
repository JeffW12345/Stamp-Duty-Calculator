package propertytaxcalculator.taxcalculation;

import propertytaxcalculator.TaxBand;
import propertytaxcalculator.TaxBands;

import java.math.BigDecimal;

public class LbbtCalculation implements TaxCalculation{
    @Override
    public double calculateTax(TaxBands taxbands, double propertyValue) {
        double remaining = propertyValue;
        double taxDue = 0;
        for(TaxBand band : taxbands.taxBandList){
            if(remaining <= 0) break;
            if(remaining > band.size){
                taxDue += (band.rate * band.size);
            }
            else{
                taxDue += (band.rate * remaining);
            }
            remaining -= band.size;
        }
        return Math.round(taxDue * 100.0) / 100.0;
    }
}
