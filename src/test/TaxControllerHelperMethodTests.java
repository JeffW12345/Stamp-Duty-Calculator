import main.propertytaxcalculator.controller.TaxController;
import main.propertytaxcalculator.service.TaxSummaryService;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaxControllerHelperMethodTests {
    @Test
    public void isNumericReturnsTrueWithInteger() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = TaxController.class.getDeclaredMethod("isNumeric", String.class);
        method.setAccessible(true);
        TaxController taxController = new TaxController(new TaxSummaryService());
        assertTrue((boolean) method.invoke(taxController, 2));
    }
}
