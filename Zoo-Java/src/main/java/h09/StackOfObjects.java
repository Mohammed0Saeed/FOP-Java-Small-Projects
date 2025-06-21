package h09;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.ArrayList;

/**
 * An object of class {@link StackOfObjects} represents a data structure of type stack.
 */
@SuppressWarnings({"ManualArrayCopy"})
public class StackOfObjects<O> {
    @StudentImplementationRequired("H9.1.1") // TODO: H9.1.1
    private ArrayList<O> objs = new ArrayList<O>();

    /**
     * Pushes the given object on this stack.
     *
     * @param obj the object
     */
    @StudentImplementationRequired("H9.1.2") // TODO: H9.1.2
    public void push(O obj) {
        objs.add(obj);
    }

    /**
     * Removes the given object from this stack.
     *
     * @param obj the object
     */
    @StudentImplementationRequired("H9.1.3") // TODO: H9.1.3
    public void remove(O obj) {
        objs.remove(obj);
    }

    /**
     * Returns the number of objects in this stack.
     *
     * @return the number of objects
     */
    @DoNotTouch
    public int size() {
        return objs.size();
    }

    /**
     * Returns the object at the given index in this stack.
     *
     * @param index the index
     * @return the object
     */
    @StudentImplementationRequired("H9.1.4") // TODO: H9.1.4
    public O get(int index) {
        return objs.get(index);
    }

    /**
     * Removes and returns the top object of this stack.
     *
     * @return the top object
     */
    @StudentImplementationRequired("H9.1.4") // TODO: H9.1.4
    public O pop() {
        O o = get(objs.size() - 1);
        remove(o);
        return o;
    }

    /**
     * Constructs and returns a stack with the given objects.
     * The last object is the top object.
     *
     * @param objs the objects
     * @return the stack
     */
    @SafeVarargs
    @StudentImplementationRequired("H9.1.5") // TODO: H9.1.5
    public static <O> StackOfObjects<O> of(O... objs) {
        StackOfObjects<O> stack = new StackOfObjects<O>();
        for (O obj : objs) {
            stack.push(obj);
        }
        return stack;
    }
}
