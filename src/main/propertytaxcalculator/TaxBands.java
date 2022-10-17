package propertytaxcalculator;

import java.util.ArrayList;

public class TaxBands {

    private ArrayList<TaxBand> taxBandList = new ArrayList<TaxBand>();


    public void add(TaxBand taxBand){
        taxBandList.add(taxBand);
    }

    public double calculateTax(double propertyValue) {
        double taxDue = 0;
        double remainder = propertyValue;
        for(TaxBand taxBand: taxBandList){
            if(remainder <= 0){
                break;
            }
            taxDue += taxBand.calcTaxThisBand(remainder);
            remainder -= taxBand.getSize();
        }
        return Math.round(taxDue * 100.0) / 100.0;
    }
}
