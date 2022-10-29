package main.propertytaxcalculator.service;

import main.propertytaxcalculator.models.TaxNames;
import main.propertytaxcalculator.models.factory.TaxFactory;
import main.propertytaxcalculator.models.factory.TaxType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
@Scope("prototype")

public class TaxSummaryService {

    private String taxName;
    private double propertyValue;
    private double taxAmount;

    public String getTaxName() {
        return taxName;
    }
    public double getPropertyValue() {
        return propertyValue;
    }
    public double getTaxAmount() {
        return taxAmount;
    }
    public void process(String taxType, String propertyValue) throws Exception {
        this.taxName = taxType;
        this.propertyValue = Double.parseDouble(propertyValue);
        TaxType taxObject = new TaxFactory().create(taxEnum(taxType), this.propertyValue);
        taxObject.calculate();
        this.taxAmount = taxObject.getTaxDue();
    }
    private TaxNames taxEnum(String taxType) throws Exception {
        if(taxType.equals("lbbt")){
            return TaxNames.LBBT;
        }
        throw new Exception("Invalid tax type");
    }
}
