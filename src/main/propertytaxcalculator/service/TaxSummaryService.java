package main.propertytaxcalculator.service;

import main.propertytaxcalculator.models.TaxNames;
import main.propertytaxcalculator.models.factory.InvalidTaxSpecified;
import main.propertytaxcalculator.models.factory.TaxFactory;
import main.propertytaxcalculator.models.TaxType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

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
        this.taxAmount =
                formatAsCurrency(new TaxFactory().create(taxEnum(taxType), propertyValueAsDouble).taxDue()
                        + "");
        this.propertyValue = formatAsCurrency(propertyValue);
    }
    private TaxNames taxEnum(String taxType) {
        if(taxType.equals("lbbt")){
            return TaxNames.LBBT;
        }
        throw new InvalidTaxSpecified("Invalid tax specified");
    }

    public String formatAsCurrency(String renderToCurrency) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        double amount = Double.parseDouble(renderToCurrency);
        return amount == 0 ? "0.00" : df.format(amount);
    }
}
