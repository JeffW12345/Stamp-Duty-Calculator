import org.junit.jupiter.api.Test;
import propertytaxcalculator.InvalidTaxSpecified;
import propertytaxcalculator.TaxFactory;
import propertytaxcalculator.TaxNames;
import propertytaxcalculator.taxtype.LbbtTax;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class FactoryTests {

    @Test
    public void returnsLbbt(){
        assertThat(new TaxFactory().create(TaxNames.LBBT, 0)).isExactlyInstanceOf(LbbtTax.class);
    }

    @Test
    public void throwsException(){
        assertThrows(InvalidTaxSpecified.class, () -> new TaxFactory().create(TaxNames.IMAGINARYTAX, 0));
    }

}