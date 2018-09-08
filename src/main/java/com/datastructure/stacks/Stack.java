package src.main.java.com.datastructure.stacks;


/**
 * Stack is a linear data structure that use of the term LIFO (Last In First Out),
 * stack is an abstract data types that serves as collection of elements with two principal
 * operations:-
 * push, which adds an element to the collection.
 * pop, which removes the most recently added element that was not yet removed.
 *
 * @param <E> the type of elements.
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

    int size();

    boolean isEmpty();

}
