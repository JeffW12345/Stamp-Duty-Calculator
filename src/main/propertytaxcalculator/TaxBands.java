package propertytaxcalculator;

import propertytaxcalculator.TaxBand;

import java.util.ArrayList;

public class TaxBands {

    public ArrayList<TaxBand> taxBandList = new ArrayList<TaxBand>();

    public void add(TaxBand taxBand){
        taxBandList.add(taxBand);
    }
}
