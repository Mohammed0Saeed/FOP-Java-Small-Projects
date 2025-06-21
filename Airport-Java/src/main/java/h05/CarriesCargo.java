package h05;

public interface CarriesCargo {
    /**
     * add the weight of the cargos to the plane
     * @param cargoWeight the weight of the cargo
     */
    void loadContainer(int cargoWeight);

    /**
     * check if at least one container is loaded
     * @return true if at least one container is loaded
     */
    boolean hasFreightLoaded();

    /**
     * unload the next container
     * @return the mass of the container
     */
    int unloadNextContainer();
}
