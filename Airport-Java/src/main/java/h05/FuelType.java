package h05;

public enum FuelType {
    JetA(1.0),
    JetB(1.2),
    AvGas(0.99),
    BioKerosin(1.02);

    public final double consumptionMuliplicator;

    // getter for Consumption Multiplication
    public double getConsumpitionMuliplicator() {
        return consumptionMuliplicator;
    }

    // constructor
    FuelType(final double consumption) {
        this.consumptionMuliplicator = consumption;
    }
}


