package propertytaxcalculator;

import propertytaxcalculator.taxcalculation.LbbtCalculation;
import propertytaxcalculator.taxtype.LbbtTax;
import propertytaxcalculator.taxtype.TaxType;


public class TaxFactory {

    public TaxType create(TaxNames taxName, double propertyValue) {
        if(taxName == TaxNames.LBBT){
            return new LbbtTax(new LbbtCalculation(), propertyValue);
        }
        else{
            throw new InvalidTaxSpecified("Invalid tax specified");
        }
    }
}
