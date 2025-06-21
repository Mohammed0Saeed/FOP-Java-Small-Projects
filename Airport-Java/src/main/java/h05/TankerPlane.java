package h05;

public class TankerPlane extends Plane implements Refuelling{
    private final double[] availableAmount = new double[FuelType.values().length];

    public TankerPlane(String aircraftRegisteration, int baseWeight, FuelType fuelType, double fuelCapacity) {
        super(aircraftRegisteration, baseWeight, fuelType, fuelCapacity);
    }

    public void loadFuel(FuelType fuelType, double amount) {
        availableAmount[fuelType.ordinal()] = amount;
    }

    @Override
    public double mass() {
        double fuelMass = 0;

        for (double i : availableAmount) {
            fuelMass += i;
        }

        double totalMass = fuelMass + getBaseWeight();

        return totalMass;
    }

    @Override
    public void refuelPlane(Plane plane) {
        double requiredFuel = plane.getFuelCapacity() - plane.getCurrentFuelLevel();
        double availableAmountInTankerPlane = availableAmount[plane.getFuelType().ordinal()];
        double availableAmountOfFuel = Math.min(availableAmountInTankerPlane, requiredFuel);

        plane.refuel(availableAmountOfFuel);
    }
}
