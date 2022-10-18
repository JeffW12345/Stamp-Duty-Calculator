import org.junit.jupiter.api.Test;
import propertytaxcalculator.TaxFactory;
import propertytaxcalculator.tax.TaxNames;
import propertytaxcalculator.taxtype.LbbtTax;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryTests {

    @Test
    public void returnsLbbt(){
        assertThat(new TaxFactory().create(TaxNames.LBBT, 0)).isExactlyInstanceOf(LbbtTax.class);
    }

}