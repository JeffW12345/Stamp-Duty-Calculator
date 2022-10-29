package main.propertytaxcalculator.models.factory;

import main.propertytaxcalculator.models.LbbtTax;
import main.propertytaxcalculator.models.TaxNames;
import main.propertytaxcalculator.models.TaxType;

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
