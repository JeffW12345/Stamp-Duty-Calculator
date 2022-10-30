import org.junit.Test;
import main.propertytaxcalculator.models.factory.TaxFactory;
import main.propertytaxcalculator.models.TaxNames;
import main.propertytaxcalculator.models.TaxType;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculationTests {
    private TaxType taxType;

    // Expected figures calculated manually and using https://revenue.scot/calculate-tax/calculate-property-transactions#calculator

    @Test
    public void propertyValZeroReturnsZeroLbbt(){
        taxType = new TaxFactory().create(TaxNames.LBBT, 0);
        assertEquals("0.00", taxType.taxDueFormattedString());
    }

    @Test
    public void propertyValNegativeReturnsZeroLbbt(){
        taxType = new TaxFactory().create(TaxNames.LBBT, -1);
        assertEquals("0.00", taxType.taxDueFormattedString());
    }
    // Tax is 0 % <= 145000
    @Test
    public void propertyVal145000ReturnsZeroLbbt(){
        taxType = new TaxFactory().create(TaxNames.LBBT, 145000);
        assertEquals("0.00", taxType.taxDueFormattedString());
    }

    @Test
    public void propertyVal146000Returns20PoundsLbbt(){
        taxType = new TaxFactory().create(TaxNames.LBBT, 146000);
        assertEquals("20.00", taxType.taxDueFormattedString());
    }

    @Test
    public void propertyVal199999point99Returns1100PoundsLbbt(){
        taxType = new TaxFactory().create(TaxNames.LBBT, 199999.99);
        assertEquals("1,100.00", taxType.taxDueFormattedString());
    }
    @Test
    public void propertyValuedAMillionReturns78350Lbbt(){
        taxType = new TaxFactory().create(TaxNames.LBBT, 1000000);
        assertEquals("78,350.00", taxType.taxDueFormattedString());
    }
}
