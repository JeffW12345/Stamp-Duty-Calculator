package main.propertytaxcalculator;

public class InvalidTaxSpecified extends RuntimeException {
    public InvalidTaxSpecified(String reason) {
        super(reason);
    }
}