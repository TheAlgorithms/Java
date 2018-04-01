package adt.linkedList;

/**
 * The interface of a generic linked list.
 */
public interface LinkedList<T> {
	/**
	 * @return true if the list is empty or false, otherwise
	 */
	public boolean isEmpty();

	/**
	 * @return the number of elements on the list
	 */
	public int size();

	/**
	 * Searches for a given element in the list.
	 * 
	 * @param element
	 *            the element being searched for
	 * @return the element if it is in the list or null, otherwise
	 */
	public T search(T element);

	/**
	 * Inserts a new element at the end of the list. Null elements must be
	 * ignored.
	 * 
	 * @param element
	 *            the element to be inserted
	 */
	public void insert(T element);

	/**
	 * Removes an element from the list. If the element does not exist the list
	 * is not changed.
	 * 
	 * @param element
	 *            the element to be removed
	 */
	public void remove(T element);

	/**
	 * Returns an array containing all elements in the structure. The array does
	 * not contain empty spaces (or null elements). The elements are put into
	 * the array from the beginning to the end of the list.
	 * 
	 * @return an array containing all elements in the structure in the order
	 *         they appear
	 */
	public T[] toArray();
}