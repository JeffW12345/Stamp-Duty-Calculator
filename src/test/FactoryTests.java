import org.junit.jupiter.api.Test;
import main.propertytaxcalculator.TaxFactory;
import main.propertytaxcalculator.tax.TaxNames;
import main.propertytaxcalculator.taxtype.LbbtTax;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryTests {

    @Test
    public void returnsLbbt(){
        assertThat(new TaxFactory().create(TaxNames.LBBT, 0)).isExactlyInstanceOf(LbbtTax.class);
    }

}