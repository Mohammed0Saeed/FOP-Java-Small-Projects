package h05;

public interface CarriesPassengers {
    /**
     * add the passengers to the plane
     * @param peopleCount the number of passengers
     */
    void board(int peopleCount);

    /**
     * all the passengers leave the plane
     */
    void disembark();

    /**
     * count the number of the passengers in a plane
     * @return the number of passengers
     */
    int getPassengerCount();
}
