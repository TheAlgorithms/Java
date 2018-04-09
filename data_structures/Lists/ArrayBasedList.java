public class ArrayBasedList<T>
{
  public static final int INITIAL_CAPACITY = 10;
	private T[] items;  // an array of list items
	private int numItems;  // number of items in list

	public ArrayBasedList()
	{
		this.items = (T[]) new Object[INITIAL_CAPACITY];
		this.numItems = 0;
	}

	/*
     * Internal method for resizing the array and copying the items over
     */
	private void resize()
	{
		// create a temporary array with a size 2 times larger then current array
		T[] new_items = (T[]) new Object[this.items.length * 2];

		// iterate through current array and add elements to new array
		for (int i = 0; i < this.items.length; i++) {
			new_items[i] = this.items[i];
		}

		this.items = new_items;
	}

	/*
     * Determines whether the list if empty
     *
     * @return True if the list is empty, false otherwise
     */
	public boolean isEmpty()
	{
		return (this.numItems == 0);
	}

	/*
     * Returns the number of items in the list
     *
     * @return The number of items in the list
     */
	public int size()
	{
		return this.numItems;
	}

	/*
     * Empty the list
     */
	public void removeAll()
	{
		// set items equal to a new array, old one is deleted
		this.items = (T[]) new Object[INITIAL_CAPACITY];
		this.numItems = 0;
	}

	/*
     * Add an item to the list at specified index
     *
     * @param index The position to add the item at
     * @param item The item to add to the list
     */
	public void add(int index, T item)
	{
		// if we have no more space, resize the array
		if (numItems == items.length) {
			resize();
		}

		if (index >= 0 && index <= numItems) {
			// move all items greater than or equal to index to the right 
			// to make room for new item
			for (int i = numItems - 1; i >= index; i--) {
				items[i + 1] = items[i];
			}
			// insert new item
			items[index] = item;
			numItems++;
		}
	}

	/*
     * Retrieves an item from the list
     *
     * @param index The index to retrieve
     * @return The item at the specified index
     */
	public T get(int index)
	{
		if (index >= 0 && index < numItems) {
			return items[index];
		} else {
			return null;
    }
	}

	/*
     * Remove an item from the list
     *
     * @param index The position of the item to remove
     */
	public void remove(int index)
	{
		if (index >= 0 && index < numItems) {
			// remove item by shifting all items above it down
			for (int i = index + 1; i < numItems; i++) {
				items[i - 1] = items[i];
			}
			items[--numItems] = null; // drop a null to fix memory leak
		}
	}

	/*
 	 * Format a string that displays the items in the list
 	 *
 	 * @return A string displaying the list
 	 */
	public String toString()
	{
		String output = "List of size " + numItems + " has the following items: ";
		for (T o : items) {
			if (o != null) {
				output += o + " ";
			}
		}
		return output;
	}
}
