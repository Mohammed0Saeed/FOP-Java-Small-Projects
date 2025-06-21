package h05;

public class Tank implements Refuelling{
    private final FuelType fuelType;

    public Tank(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public void refuelPlane(Plane plane) {
        if (this.fuelType == plane.getFuelType()) {
            double requiredFuel = plane.getFuelCapacity() - plane.getCurrentFuelLevel();
            plane.refuel(requiredFuel);
        }
        else
            System.out.println("The fuel type of " + plane.getIdentifier() + " is not from type " + this.fuelType + ". So refuelling failed");
    }
}
