package propertytaxcalculator.numberformat;

import java.text.DecimalFormat;

public class NumberFormat {

    public String addCommasAndPence(String numberToRender) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return Double.parseDouble(numberToRender) == 0 ? "0.00" : df.format(Double.parseDouble(numberToRender));
    }
}
