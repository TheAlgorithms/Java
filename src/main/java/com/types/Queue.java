package com.types;


import java.util.NoSuchElementException;

/**
 * Interface to provide queue specific functionality to the implementing class
 * This interface only defines the functionality which the queue implementing classes require.
 * Any class having queue behaviour should implement this interface and override all of its methods
 *
 * @param <T>
 */
public interface Queue<T> extends DataStructure<T> {

    /**
     * Method to add element
     *
     * @param t element
     * @return boolean
     * @throws NullPointerException
     */
    boolean offer(T t) throws NullPointerException;

    /**
     * Method to remove element
     *
     * @return element
     */
    T poll();

    /**
     * Method to check element on head
     *
     * @return element
     */
    T peek();

    /**
     * Method to check element on head. This throws exception on runtime if the queue is empty
     *
     * @return element
     * @throws NoSuchElementException
     */
    T element() throws NoSuchElementException;
}
