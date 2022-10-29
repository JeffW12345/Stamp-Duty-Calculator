import main.propertytaxcalculator.controller.TaxController;
import main.propertytaxcalculator.service.TaxSummaryService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//import org.springframework.data.util.ReflectionUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaxControllerHelperMethodTests {
    @Test
    public void isNumericReturnsTrueWithInteger() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Method method = TaxController.class.getClass().getDeclaredMethod("isNumeric", String.class);
        //method.setAccessible(true);
        //boolean output = (boolean) method.invoke(new TaxController(new TaxSummaryService()), "2");
        //assertTrue(output);
    }
}
