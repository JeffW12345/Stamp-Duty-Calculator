package main.propertytaxcalculator.service;

import main.propertytaxcalculator.models.TaxNames;
import main.propertytaxcalculator.models.TaxType;
import main.propertytaxcalculator.models.factory.InvalidTaxSpecified;
import main.propertytaxcalculator.models.factory.TaxFactory;
import main.propertytaxcalculator.numberformat.NumberFormat;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")

public class TaxSummaryService {
    private String taxName;
    private String taxAmount;
    private String propertyValue;

    public String getTaxName() {
        return taxName;
    }

    public String getPropertyValue() { return propertyValue;}

    public String getTaxAmount() {
        return taxAmount;
    }

    public void process(String taxType, String propertyValue) {
        double propertyValueAsDouble = Double.parseDouble(propertyValue);
        TaxType typeOfTax = new TaxFactory().create(taxEnum(taxType), propertyValueAsDouble);
        this.taxAmount = typeOfTax.taxDueFormattedString();
        this.taxName = typeOfTax.getName();
        this.propertyValue = new NumberFormat().addCommasAndPence(propertyValue);
    }
    private TaxNames taxEnum(String taxType) {
        if(taxType.equals("lbbt")){
            return TaxNames.LBBT;
        }
        throw new InvalidTaxSpecified("Invalid tax specified");
    }
}
