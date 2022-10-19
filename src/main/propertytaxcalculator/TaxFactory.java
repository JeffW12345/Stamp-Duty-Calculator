package main.propertytaxcalculator;

import main.propertytaxcalculator.tax.TaxNames;
import main.propertytaxcalculator.taxtype.LbbtTax;
import main.propertytaxcalculator.taxtype.TaxType;

public class TaxFactory {

    public TaxType create(TaxNames taxName, double propertyValue) {
        if(taxName == TaxNames.LBBT){
            return new LbbtTax(propertyValue);
        }
        else{
            throw new InvalidTaxSpecified("Invalid tax specified");
        }
    }
}
