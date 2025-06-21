package h05;

public class CombinedPlane extends PassengerPlane implements CarriesCargo{
    private CargoStack containers = new CargoStack();

    public CombinedPlane(String aircraftRegistration, int baseWeight, FuelType fuelType, double fuelCapacity, int crewCount) {
        super(aircraftRegistration, baseWeight, fuelType, fuelCapacity, crewCount);
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
        double peopleWeight = (super.getPassengerCount() * super.AVERAGE_PEOPLE_WEIGHT) + (getCrewCount() * super.AVERAGE_PEOPLE_WEIGHT);
        double luggageWeight = super.getPassengerCount() * super.AVERAGE_LUGGAGE_WEIGHT;
        double containersWeight = containers.getSum();

        double totalMass = peopleWeight + luggageWeight + containersWeight + getBaseWeight();

        return totalMass;
    }
}
