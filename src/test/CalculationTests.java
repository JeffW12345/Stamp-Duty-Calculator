import org.junit.Test;
import propertytaxcalculator.TaxFactory;
import propertytaxcalculator.TaxNames;
import propertytaxcalculator.taxtype.TaxType;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculationTests {
    private TaxType taxType;

    @Test
    public void propertyValZeroReturnsZeroLbbt(){
        taxType = new TaxFactory().create(TaxNames.LBBT, 0);
        assertEquals(0, taxType.getTaxDue());
    }

    @Test
    public void propertyValNegativeReturnsZeroLbbt(){
        taxType = new TaxFactory().create(TaxNames.LBBT, -1);
        taxType.calculate();
        assertEquals(0, taxType.getTaxDue());
    }
    // Tax is 0 % <= 145000
    @Test
    public void propertyVal145000ReturnsZeroLbbt(){
        taxType = new TaxFactory().create(TaxNames.LBBT, 145000);
        taxType.calculate();
        assertEquals(0, taxType.getTaxDue());
    }

    @Test
    public void propertyVal146000Returns20PoundsLbbt(){
        taxType = new TaxFactory().create(TaxNames.LBBT, 146000);
        taxType.calculate();
        assertEquals(20, taxType.getTaxDue());
    }

    @Test
    public void propertyVal199999point99Returns1100PoundsLbbt(){
        taxType = new TaxFactory().create(TaxNames.LBBT, 199999.99);
        taxType.calculate();
        assertEquals(1100, taxType.getTaxDue());
    }

}
