package h05;

/**
 * Main entry point in executing the program.
 */
public class Main {
    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        // TODO: H5.6
        // scan the airspace
        Airspace.get().scanAirspace();

        // two runways for the planes
        Runway runwayMarika = new Runway(2000);
        Runway runwayMalenia = new Runway(4000);

        // create a new weather balloon
        WeatherBalloon nanami = new WeatherBalloon(99);
        nanami.start();

        // tank objects
        Tank tank1 = new Tank(FuelType.JetA);
        Tank tank2 = new Tank(FuelType.JetB);

        // one tanker plane
        TankerPlane sukuna = new TankerPlane("D-ABCD", 10000, FuelType.JetA, 1000);

        sukuna.loadFuel(FuelType.AvGas, 100000);
        sukuna.loadFuel(FuelType.BioKerosin, 100000);

        // passenger plane
        PassengerPlane abyssWatcher = new PassengerPlane("GAG-67", 10000, FuelType.JetA, 1700, 5);
        tank1.refuelPlane(abyssWatcher);
        abyssWatcher.board(100);
        abyssWatcher.takeOff();

        // scan the airspace
        Airspace.get().scanAirspace();

        // Cargo Plane
        CargoPlane soulOfCinder = new CargoPlane("D-AFFF", 8000, FuelType.JetB, 1500);
        soulOfCinder.loadContainer(1000);
        tank2.refuelPlane(soulOfCinder);

        // passengers from abyssWatcher PassengerPlane should leave the plane
        abyssWatcher.land();
        abyssWatcher.disembark();

        // scan the airspace
        Airspace.get().scanAirspace();

        // soulOfCinder Cargo Plan flies 1000 km
        soulOfCinder.takeOff();
        soulOfCinder.fly(1000);

        // scan the airspace
        Airspace.get().scanAirspace();

        // Combined Plane
        CombinedPlane maki = new CombinedPlane("D-ABBB", 9000, FuelType.AvGas, 10700, 5);
        sukuna.refuelPlane(maki);
        maki.board(30);
        maki.loadContainer(400);
        maki.takeOff();
        maki.fly(3000);

        // scan the airspace
        Airspace.get().scanAirspace();

        // let maki lands on runway Marika and soulOfCinder on runway Malenia
        runwayMarika.land(maki);
        runwayMalenia.land(soulOfCinder);

        // scan the airspace
        Airspace.get().scanAirspace();

        // let nanami WeatherBalloon lands
        nanami.pop();

        // scan the airspace twice
        Airspace.get().scanAirspace();
        Airspace.get().scanAirspace();
    }
}
