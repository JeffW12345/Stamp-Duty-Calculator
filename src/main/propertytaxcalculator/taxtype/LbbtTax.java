package propertytaxcalculator.taxtype;

import propertytaxcalculator.TaxBand;
import propertytaxcalculator.taxcalculation.TaxCalculation;

public class LbbtTax extends TaxType {


    public LbbtTax(TaxCalculation taxCalculation, double propertyValue) {
        super(taxCalculation, propertyValue);
    }

    @Override
    protected void setTaxThresholds() {
        taxBands.add(new TaxBand(0,145000,0));
        taxBands.add(new TaxBand(145000.01,250000,.02));
        taxBands.add(new TaxBand(250000.01,325000,0.05));
        taxBands.add(new TaxBand(325000.01,750000,0.1));
        taxBands.add(new TaxBand(750000.01,Double.MAX_VALUE,0.12));
    }
}
