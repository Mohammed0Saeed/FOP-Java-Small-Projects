package h09;

import h09.abilities.Swims;
import h09.animals.Animal;
import h09.animals.Lion;
import h09.animals.Penguin;
import org.junit.jupiter.api.Test;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.function.Consumer;

import static h09.Enclosure.EAT_AND_SINK;
import static org.junit.jupiter.api.Assertions.*;

/**
 * An example JUnit test class.
 */
public class EnclosureTest {
    Lion l1 = new Lion("l.1", 3);
    Lion l2 = new Lion("l.2", 2);
    Lion l3 = new Lion("l.3", 5);

    Penguin p1 = new Penguin("p.1", 2, -5);
    Penguin p2 = new Penguin("p.2", 1, -5);

    GroundEnclosure<Lion> testGroundEnclosure = new GroundEnclosure<>();

    WaterEnclosure<Penguin> testWaterEnclosure = new WaterEnclosure<>();




    @Test
    @StudentImplementationRequired("H9.5.1")
    public void testForEach() {
        // TODO: H9.5.1
        testGroundEnclosure.getStack().push(l1);
        testGroundEnclosure.getStack().push(l2);
        testGroundEnclosure.getStack().push(l3);

        assertTrue(l1.isHungry());
        assertTrue(l2.isHungry());
        assertTrue(l3.isHungry());

        testGroundEnclosure.forEach(Animal::eat);

        assertFalse(l1.isHungry());
        assertFalse(l2.isHungry());
        assertFalse(l3.isHungry());
    }


    @Test
    @StudentImplementationRequired("H9.5.2")
    public void testFilter() {
        // TODO: H9.5.2
        testWaterEnclosure.getStack().push(p1);
        testWaterEnclosure.getStack().push(p2);

        p2.eat();

        assertTrue(p1.isHungry());
        assertFalse(p2.isHungry());
        assertTrue(p1.getElevation() < Swims.HIGH_ELEVATION);
        assertTrue(p2.getElevation() < Swims.HIGH_ELEVATION);

        testWaterEnclosure.filterFunc(WaterEnclosure::new, Enclosure.SWIMS_AT_LOW_ELEVATION);

        testWaterEnclosure.forEach(Penguin::swimUp);

        testWaterEnclosure.forEach(EAT_AND_SINK());

        assertTrue(p1.getElevation() < Swims.HIGH_ELEVATION);
        assertTrue(p2.getElevation() < Swims.HIGH_ELEVATION);

        //org.tudalgo.algoutils.student.Student.crash("H9.5.2 - Remove if implemented");
    }
}
