import main.propertytaxcalculator.controller.TaxController;
import main.propertytaxcalculator.numberformat.NumberFormat;
import main.propertytaxcalculator.service.TaxSummaryService;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class HelperMethodTests {
    @Test
    public void isNumericReturnsTrueWithInteger() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = TaxController.class.getDeclaredMethod("isNumeric", String.class);
        method.setAccessible(true);
        TaxController taxController = new TaxController(new TaxSummaryService());
        assertTrue((boolean) method.invoke(taxController, "2"));
    }

    @Test
    public void isNumericReturnsTrueWithDecimal() throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        Method method = TaxController.class.getDeclaredMethod("isNumeric", String.class);
        method.setAccessible(true);
        TaxController taxController = new TaxController(new TaxSummaryService());
        assertTrue((boolean) method.invoke(taxController, "2.1"));
    }
    @Test
    public void isNumericReturnsTrueWithNegativeNumber() throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        Method method = TaxController.class.getDeclaredMethod("isNumeric", String.class);
        method.setAccessible(true);
        TaxController taxController = new TaxController(new TaxSummaryService());
        assertTrue((boolean) method.invoke(taxController, "-2.1"));
    }
    @Test
    public void isNumericReturnsFalseWithAlphaNumeric() throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        Method method = TaxController.class.getDeclaredMethod("isNumeric", String.class);
        method.setAccessible(true);
        TaxController taxController = new TaxController(new TaxSummaryService());
        assertFalse((boolean) method.invoke(taxController, "2abc"));
    }

    @Test
    public void addCommasAndPenceReturnsCorrectlyForZero() {
        assertEquals("0.00", new NumberFormat().addCommasAndPence("0"));
    }
    @Test
    public void addCommasAndPenceReturnsCorrectlyForAMillion() {

        assertEquals("1,000,000.00", new NumberFormat().addCommasAndPence("1000000"));
    }

    @Test
    public void addCommasAndPenceReturnsCorrectlyForNegativeNumber() {

        assertEquals("-1,000,000.00", new NumberFormat().addCommasAndPence("-1000000"));
    }

    @Test
    public void addCommasAndPenceReturnsCorrectlyForHundred()  {
        assertEquals("100.00", new NumberFormat().addCommasAndPence("100.00"));
    }
}
