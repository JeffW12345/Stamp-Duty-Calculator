package propertytaxcalculator.models.factory;

import propertytaxcalculator.models.LbbtTax;
import propertytaxcalculator.models.TaxNames;
import propertytaxcalculator.models.TaxType;

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
