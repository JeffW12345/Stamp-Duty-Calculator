package main.propertytaxcalculator.taxcalculations;

public class RunningTaxTotal {
    private double totalSoFar = 0;
    public double getTotalSoFar() {
        return totalSoFar;
    }
    public void add(double toAdd) {
        totalSoFar += toAdd;
    }
}
