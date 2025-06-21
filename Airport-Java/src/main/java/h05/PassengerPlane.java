package h05;

public class PassengerPlane extends Plane implements CarriesPassengers{
    private int passengerCount = 0;
    private final int crewCount;
    protected char AVERAGE_PEOPLE_WEIGHT = 100;
    protected char AVERAGE_LUGGAGE_WEIGHT = 15;

    public PassengerPlane(String aircraftRegistration, int baseWeight, FuelType fuelType, double fuelCapacity, int crewCount) {
        super(aircraftRegistration, baseWeight, fuelType, fuelCapacity);
        this.crewCount = crewCount;
    }

    public int getCrewCount() {
        return crewCount;
    }

    @Override
    public void board(int currentPassengerCount) { this.passengerCount += currentPassengerCount; }

    @Override
    public void disembark() {
        this.passengerCount = 0;
    }

    @Override
    public int getPassengerCount() {
        return this.passengerCount;
    }

    @Override
    public double mass() {
        double peopleWeight = (getPassengerCount() * AVERAGE_PEOPLE_WEIGHT) + (getCrewCount() * AVERAGE_PEOPLE_WEIGHT);
        double luggageWeight = this.AVERAGE_LUGGAGE_WEIGHT * getPassengerCount();

        double totalWeight = peopleWeight + luggageWeight + getBaseWeight() + this.crewCount;

        return totalWeight;
    }
}
