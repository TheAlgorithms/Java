package adt.linkedList;

/**
 * The interface of a double linked list with a head and a last (Deque).
 * 
 * @param <T>
 */
public interface DoubleLinkedList<T> extends LinkedList<T> {

	/**
	 * Inserts a new element in the first position (head) of the list. The
	 * "head" reference must be updated.
	 * 
	 * @param element
	 */
	public void insertFirst(T element);

	/**
	 * Removes the first element (head) of the list. The "head" reference must
	 * be updated.
	 */
	public void removeFirst();

	/**
	 * Removes the last element (last) of the list. The "last" reference must be
	 * updated.
	 */
	public void removeLast();
}
