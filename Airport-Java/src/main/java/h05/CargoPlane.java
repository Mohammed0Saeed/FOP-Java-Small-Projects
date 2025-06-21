package h05;

public class CargoPlane extends Plane implements CarriesCargo{
    private CargoStack containers = new CargoStack();

    public CargoPlane(String aircraftRegistration, int baseWeight, FuelType fuelType, double fuelCapacity) {
        super(aircraftRegistration, baseWeight, fuelType, fuelCapacity);
    }

    @Override
    public void loadContainer(int cargoWeight) {
        containers.push(cargoWeight);
    }

    @Override
    public boolean hasFreightLoaded() {
        return (containers.getSum() > 0);
    }

    @Override
    public int unloadNextContainer() {
        return containers.pop();
    }

    @Override
    public double mass() {
        return containers.getSum() + getBaseWeight();
    }
}
