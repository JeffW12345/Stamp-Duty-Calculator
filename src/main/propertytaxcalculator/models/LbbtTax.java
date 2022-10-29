package main.propertytaxcalculator.models;

import main.propertytaxcalculator.taxcalculations.TaxBand;

public class LbbtTax extends TaxType {

    public LbbtTax(double propertyValue) {
        super(propertyValue);
        name = "Lbbt";
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
