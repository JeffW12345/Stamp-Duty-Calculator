package propertytaxcalculator.tax;

import propertytaxcalculator.RunningTaxTotal;
import propertytaxcalculator.TaxableAmountProcessed;
import propertytaxcalculator.tax.TaxBand;

import java.util.ArrayList;

public class TaxBands {

    private ArrayList<TaxBand> taxBandList = new ArrayList<TaxBand>();

    public void add(TaxBand taxBand){
        taxBandList.add(taxBand);
    }

    public double calculateTax(double propertyValue) {
        RunningTaxTotal runningTaxTotal = new RunningTaxTotal();
        TaxableAmountProcessed taxableAmountProcessed = new TaxableAmountProcessed(propertyValue);
        for(TaxBand taxBand: taxBandList){
            if(taxableAmountProcessed.zeroRemaining()){
                break;
            }
            taxBand.updateTaxProcessed(propertyValue, taxableAmountProcessed);
            taxBand.updateRunningTotal(propertyValue, runningTaxTotal);
        }
        return runningTaxTotal.getTotalSoFar();
    }
}
