package main.propertytaxcalculator.tax;

import main.propertytaxcalculator.InvalidTaxSpecified;
import main.propertytaxcalculator.tax.TaxNames;
import main.propertytaxcalculator.tax.LbbtTax;
import main.propertytaxcalculator.tax.TaxType;

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
