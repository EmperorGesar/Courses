package ca.uwo.pricingStrategies.individual;

public class IndividualPricingStrategy2 implements IndividualPricingStrategy {

    @Override
    public double calculate(int quantity, double price) {
        return quantity * price * 0.9;
    }

}
