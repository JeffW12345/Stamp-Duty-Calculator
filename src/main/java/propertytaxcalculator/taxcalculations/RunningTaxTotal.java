package propertytaxcalculator.taxcalculations;

public class RunningTaxTotal {
    private double totalSoFar = 0;
    protected double getTotalSoFar() {
        return totalSoFar;
    }
    protected void add(double toAdd) {
        totalSoFar += toAdd;
    }
}
