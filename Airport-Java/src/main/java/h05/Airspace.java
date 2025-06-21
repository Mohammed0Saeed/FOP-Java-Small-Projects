package h05;

import java.util.HashSet;
import java.util.Set;

public class Airspace {

    private static Airspace INSTANCE = null;


    public static Airspace get() {
        if (INSTANCE == null) {
            INSTANCE = new Airspace();
        }
        return INSTANCE;
    }

    // TODO: H5.1.1 - Uncomment the following lines
    private final Set<Flying> flyingInAirspace = new HashSet<>();

    private Airspace(){

    }

    void register(Flying flying){
        flyingInAirspace.add(flying);
    }

    void deregister(Flying flying){
        flyingInAirspace.remove(flying);
    }

    void scanAirspace() {
        // TODO: H5.5
        System.out.println("Scanning...");
        if (flyingInAirspace.isEmpty())
            System.out.println("Airspace is empty");
        else {
            for (Flying flying : flyingInAirspace) {
                System.out.print(flying.getIdentifier() + " is flying in airspace");
                if (flying.getClass() == PassengerPlane.class || flying.getClass() == CombinedPlane.class)
                    System.out.println(" (" + ((PassengerPlane) flying).getPassengerCount() +" PAX)");
                else
                    System.out.println();
            }
        }
    }
}
