package h05;

public abstract class Plane implements Flying{
    private final String aircraftRegisteration;
    private final int baseWeight;
    private FuelType fuelType;
    private final double fuelCapacity;
    private double currentFuelLevel = 0;
    private final double CONSUMPTION_PER_KM_KG = 1.1494 * 10e-4;

    public Plane(String aircraftRegisteration, int baseWeight, FuelType fuelType, double fuelCapacity) {
        this.aircraftRegisteration = aircraftRegisteration;
        this.baseWeight = baseWeight;
        this.fuelType = fuelType;
        this.fuelCapacity = fuelCapacity;
    }

    @Override
    public String getIdentifier() {
        return this.aircraftRegisteration;
    }

    public FuelType getFuelType() {
        return this.fuelType;
    }

    public double getFuelCapacity() {
        return this.fuelCapacity;
    }

    public double getCurrentFuelLevel() {
        return this.currentFuelLevel;
    }

    public int getBaseWeight() {
        return this.baseWeight;
    }

    /**
     * refuel the plane
     * @param amount the amount of fuel
     */
    public void refuel(double amount) {
        // add the amount of the fuel to be added to the fuel tank
        this.currentFuelLevel += amount;
        // check if the amount of fuel causes overflow
        if (this.currentFuelLevel > this.fuelCapacity) {
            // if the amount of fuel is overflowed, then print that in the screen
            System.out.println("The Tank of Plane " + this.aircraftRegisteration + " has Overflowed");
            // set the current fuel level to the maximum capacity to avoid overflow
            this.currentFuelLevel = this.fuelCapacity;
        }
    }

    /**
     * calculates the total mass of the plane after boarding
     * @return the total mass
     */
    protected abstract double mass();

    /**
     * calculate fuel consumption per kilometer
     * @return the consumption per km
     */
    protected double getFuelConsumptionPerKilometer() {
        double totalMass = mass();
        double consumptionMultiplicator = this.fuelType.getConsumpitionMuliplicator();

        double consumptionPerKm = CONSUMPTION_PER_KM_KG * totalMass * consumptionMultiplicator;

        return consumptionPerKm;
    }

    /**
     * check the amount of required fuel for a flight
     * @param distance the distance of the flight
     */
    public void fly(double distance) {
        double consumptionPerKm = getFuelConsumptionPerKilometer();

        double requiredAmountOfFuel = distance * consumptionPerKm;

        if (requiredAmountOfFuel > this.currentFuelLevel) {
            System.out.println("This " + this.aircraftRegisteration + " does not have enough fuel to fly " + distance + " km.");
        }
        else {
            this.currentFuelLevel -= requiredAmountOfFuel;
            System.out.println("This " + this.aircraftRegisteration + " flew " + distance + " km and has " + this.currentFuelLevel + " fuel left.");
        }
    }

    /**
     * register the plane when taking off
     */
    public void takeOff() {
        Airspace.get().register(this);
    }

    /**
     * deregister the plane when landing
     */
    public void land() {
        Airspace.get().deregister(this);
    }
}
