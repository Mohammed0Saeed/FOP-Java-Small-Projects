package h05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * An example JUnit test class.
 */
public class ExampleJUnitTest {
    @Test
    public void testCargoPlane() {
        // create test objects
        CargoPlane testPlane = new CargoPlane("test01", 7000, FuelType.JetA, 1000);
        Tank jetATest = new Tank(FuelType.JetA);

        // check if the id works
        assertEquals("test01", testPlane.getIdentifier());

        // check if the cargo plane can be loaded
        testPlane.loadContainer(1000);
        assertEquals(testPlane.getBaseWeight() + 1000, testPlane.mass());
        assertTrue(testPlane.hasFreightLoaded());

        // check if the cargo plane can be unloaded
        testPlane.unloadNextContainer();
        assertEquals(testPlane.getBaseWeight(), testPlane.mass());
        assertFalse(testPlane.hasFreightLoaded());
    }

    @Test
    public void testPassengerPlane() {
        // create test objects
        PassengerPlane testPlane = new PassengerPlane("test02", 8000, FuelType.AvGas, 200, 7);

        // board 100 passenger
        testPlane.board(100);
        assertEquals(100, testPlane.getPassengerCount());

        // disembark all the passengers
        testPlane.disembark();
        assertEquals(0, testPlane.getPassengerCount());
    }

    @Test
    public void testCombinedPlane() {
        // create test objects
        CombinedPlane testPlane = new CombinedPlane("test03", 10000, FuelType.JetB, 150, 10);

        // board 150 passenger
        testPlane.board(150);
        assertEquals(150, testPlane.getPassengerCount());

        // disembark all the passengers
        testPlane.disembark();
        assertEquals(0, testPlane.getPassengerCount());

        // check if the cargo plane can be loaded
        testPlane.loadContainer(2000);;
        assertTrue(testPlane.hasFreightLoaded());

        // check if the cargo plane can be unloaded
        testPlane.unloadNextContainer();
        assertFalse(testPlane.hasFreightLoaded());
    }
}
