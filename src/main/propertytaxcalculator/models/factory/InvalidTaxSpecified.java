package main.propertytaxcalculator.models.factory;

public class InvalidTaxSpecified extends RuntimeException {
    public InvalidTaxSpecified(String reason) {
        super(reason);
    }
}