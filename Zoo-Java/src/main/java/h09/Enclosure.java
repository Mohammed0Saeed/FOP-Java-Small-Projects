package h09;

import h09.animals.Animal;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import h09.abilities.*;

/**
 * An object of a class implementing {@link Enclosure} has the ability to contain and manage a stack of {@link Animal}s.
 */
// TODO: H9.2.1
public interface Enclosure<A extends Animal> {
    /**
     * @return the stack of animals which is used manage the contained {@link Animal}s
     */
    @StudentImplementationRequired("H9.2.1")
    // TODO: H9.2.1
    StackOfObjects<A> getStack();

    /**
     * Feeds all contained animals.
     */
    @DoNotTouch
    void feed();

    /**
     * Counts the number of hungry {@link Animal}s in the enclosure.
     *
     * @return number of hungry {@link Animal}s in the enclosure
     */
    @DoNotTouch
    @SuppressWarnings("RedundantCast")
    default int countHungry() {
        int count = 0;
        for (int i = 0; i < this.getStack().size(); i++)
            if (((Animal) this.getStack().get(i)).isHungry()) count++;
        return count;
    }


    /**
     * Applies a {@link Consumer} operation on each {@link Animal} in the enclosure.
     *
     * @param func operation to be applied to each {@link Animal} in the enclosure
     */
    @StudentImplementationRequired("H9.3.1") // TODO: H9.3.1
    default void forEach(Consumer<? super A> func) {
        for (int i = 0; i < this.getStack().size(); i++)
            func.accept(this.getStack().get(i));
    }

    /**
     * Tests a {@link Predicate} operation on each {@link Animal} in the enclosure and removes every {@link Animal}
     * which does not satisfy the predicate. That means only {@link Animal}s for which the predicate returns 'true'
     * remain in the enclosure.
     *
     * @param filter operation to test to each {@link Animal} in the enclosure
     */
    @StudentImplementationRequired("H9.3.2") // TODO: H9.3.2
    default void filterObj(Predicate<? super A> filter) {
        for (int i = 0; i < this.getStack().size(); i++) {
            A a = this.getStack().get(i);
            if (!filter.test(a)) {
                this.getStack().remove(a);
                i--;
            }
        }
    }

    /**
     * Returns a new {@link Enclosure} that contains only the {@link Animal}s of the previous {@link Enclosure} which
     * satisfied the predicate. That means only {@link Animal}s for which the predicate returns 'true' are included
     * in the new enclosure.
     *
     * @param supp   {@link Supplier} which is used to create the new {@link Enclosure} to be returned
     * @param filter operation to test to each {@link Animal} in the enclosure
     * @param <E>    Type of the new {@link Enclosure} which is returned
     * @return a new {@link Enclosure} that contains only the {@link Animal}s of the previous {@link Enclosure} which
     * satisfied the predicate
     */
    @StudentImplementationRequired("H9.3.3")
    default <E extends Enclosure<A>> Enclosure<A> filterFunc(Supplier<? extends E> supp, Predicate<? super A> filter) {
        // TODO: H9.3.3

        E newEnclosure = supp.get();

        for (int i = 0; i < this.getStack().size(); i++) {
            A a = this.getStack().get(i);
            if (filter.test(a)) {
                newEnclosure.getStack().push(a);
            }
        }

        return newEnclosure;
    }

    /**
     * {@link Predicate} which returns true if an {@link Animal} is old (that means older than 10).
     */
    @DoNotTouch
    Predicate<Animal> IS_OLD = animal -> animal.getAge() > 10;

    /**
     * {@link Predicate} which returns true if a swimming {@link Animal} swims at a low elevation.
     */
    @StudentImplementationRequired("H9.4.1") // TODO: H9.4.1
    Predicate<Swims> SWIMS_AT_LOW_ELEVATION = animal -> animal.getElevation() < Swims.HIGH_ELEVATION;

    /**
     * {@link Consumer} which lets the consumed {@link Animal} eat and sleep.
     */
    @StudentImplementationRequired("H9.4.2") // TODO: H9.4.2
    Consumer<Animal> FEED_AND_SLEEP = animal -> {
        animal.eat();
        animal.sleep();
    };

    /**
     * Returns a {@link Consumer} which lets the consumed swimming {@link Animal} eat and swim down.
     *
     * @param <T> Type of the swimming {@link Animal} to eat and swim down
     * @return a {@link Consumer} which lets the consumed swimming {@link Animal} eat and swim down
     */
    @StudentImplementationRequired("H9.4.3")
    public static <T extends Animal & Swims> Consumer<T> EAT_AND_SINK() {
        // TODO: H9.4.3
        return animal -> {
            animal.eat();
            animal.swimDown();
        };
    }
}
