package src.main.java.com.datastructure.stacks;


/**
 * Stack is a linear data structure that use of the term LIFO (Last In First Out),
 * stack is an abstract data types that serves as collection of elements with two principal
 * operations:-
 * <ul>
 * <li><b>push</b>, which adds an element to the collection.</li>
 * <li><b>pop</b>, which removes the most recently added element that was not yet removed.</li>
 * </ul>
 *
 * @param <E> the type of elements.
 * @see <a href="https://en.wikipedia.org/wiki/Stack_(abstract_data_type)">Stack in Wikipedia</a>
 * @see ArrayStack
 */
public interface Stack<E> {
    /**
     * Stores the element last element entered in the top of the stack,
     * so we can pop or peek as first element.
     *
     * @param element to be stored.
     */
    void push(E element);

    /**
     * Removes and returns the last element entered in the Stack.
     *
     * @return top element.
     */
    E pop();

    /**
     * Returns the number of elements currently in the Stack.
     *
     * @return number of elements.
     */
    int size();

    /**
     * @return true if the stack is empty or false if not.
     */
    boolean isEmpty();

}
