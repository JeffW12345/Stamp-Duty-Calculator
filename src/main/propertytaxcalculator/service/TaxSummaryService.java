package main.propertytaxcalculator.service;

import main.propertytaxcalculator.models.TaxNames;
import main.propertytaxcalculator.models.factory.InvalidTaxSpecified;
import main.propertytaxcalculator.models.factory.TaxFactory;
import main.propertytaxcalculator.models.TaxType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")

public class TaxSummaryService {
    private String taxName;
    private double taxAmount;

    public String getTaxName() {
        return taxName;
    }
    public double getTaxAmount() {
        return taxAmount;
    }
    public void process(String taxType, String propertyValue) throws Exception {
        this.taxName = taxType;
        double propertyValueAsDouble = Double.parseDouble(propertyValue);
        TaxType taxObject = new TaxFactory().create(taxEnum(taxType), propertyValueAsDouble);
        this.taxAmount = taxObject.taxDue();
    }
    private TaxNames taxEnum(String taxType) throws Exception {
        if(taxType.equals("lbbt")){
            return TaxNames.LBBT;
        }
        throw new InvalidTaxSpecified("Invalid tax specified");
    }
}
