package propertytaxcalculator.taxcalculations;

import java.util.ArrayList;

public class TaxBands {

    private final ArrayList<TaxBand> taxBandList = new ArrayList<>();

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
