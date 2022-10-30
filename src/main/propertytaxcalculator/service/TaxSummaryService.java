package main.propertytaxcalculator.service;

import main.propertytaxcalculator.models.TaxNames;
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

    public void process(String taxType, String propertyValue) throws Exception {
        this.taxName = taxType;
        double propertyValueAsDouble = Double.parseDouble(propertyValue);
        this.taxAmount = new TaxFactory().create(taxEnum(taxType), propertyValueAsDouble).taxDueFormattedString();
        this.propertyValue = new NumberFormat().addCommasAndPence(propertyValue);
    }
    private TaxNames taxEnum(String taxType) {
        if(taxType.equals("lbbt")){
            return TaxNames.LBBT;
        }
        throw new InvalidTaxSpecified("Invalid tax specified");
    }
}
