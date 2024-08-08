package propertytaxcalculator.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import propertytaxcalculator.models.TaxNames;
import propertytaxcalculator.models.TaxType;
import propertytaxcalculator.models.factory.InvalidTaxSpecified;
import propertytaxcalculator.models.factory.TaxFactory;
import propertytaxcalculator.numberformat.NumberFormat;

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
        this.taxName = fullTaxName(taxType);
        this.propertyValue = new NumberFormat().addCommasAndPence(propertyValue);
    }

    private String fullTaxName(String taxType) {
        if (taxType.equals("lbbt")){
            return "Lbbt (Scottish property tax)";
        }
        throw new InvalidTaxSpecified("Invalid tax specified");
    }

    private TaxNames taxEnum(String taxType) {
        if(taxType.equals("lbbt")){
            return TaxNames.LBBT;
        }
        throw new InvalidTaxSpecified("Invalid tax specified");
    }
}
