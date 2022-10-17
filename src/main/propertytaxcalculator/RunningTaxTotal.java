package propertytaxcalculator;

public class RunningTaxTotal {
    private double totalSoFar = 0;

    public double getTotalSoFar() {
        return totalSoFar;
    }

    public void updateFor(TaxBand taxBand, double propertyValue) {
        totalSoFar += taxBand.calculateBandTaxFor(propertyValue);
    }
}
