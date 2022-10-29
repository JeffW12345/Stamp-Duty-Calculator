import org.junit.jupiter.api.Test;
import main.propertytaxcalculator.models.factory.TaxFactory;
import main.propertytaxcalculator.models.TaxNames;
import main.propertytaxcalculator.models.LbbtTax;
import static org.assertj.core.api.Assertions.assertThat;

public class FactoryTests {

    @Test
    public void returnsLbbt(){
        assertThat(new TaxFactory().create(TaxNames.LBBT, 0)).isExactlyInstanceOf(LbbtTax.class);
    }

}