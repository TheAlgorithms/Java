package Misc;

import java.util.*;

interface List
{
   Object get(Object key);
   Object put(Object key, Object theElement);
   Object remove(Object key);
}

public class SkipList implements List
{
	protected static class Node
	{
		protected Comparable key;
		protected Object element;
		protected Node []  next;

		protected Node(Object theKey, Object theElement, int size)
		{
			key = (Comparable) theKey;
			element = theElement;
			next = new Node [size];
		}
	}

	protected float probability;        // probability used to decide level number
	protected int maxLevel;       		// max permissible chain level
	protected int maxNonEmptyLevel;     // max current nonempty chain
	protected int size;          	 	// current number of elements
	protected Comparable largestKey; 	// a large key
	protected Node head;  				// head node
	protected Node tail;  				// tail node
	protected Node [] lastNodeAtLevel;  // last node seen on each level
	protected Random random;            // needed for random numbers

	// Constructor
	public SkipList(Comparable largeKey, int maxElements, float prob)
	{
	      probability = prob;
	      maxLevel = (int) Math.round(Math.log(maxElements) / Math.log(1/probability)) - 1;

	      largestKey = largeKey;

	      // create head and tail nodes and last nodes at each level array
	      head = new Node (null, null, maxLevel + 1);
	      tail = new Node (largestKey, null, 0);
	      lastNodeAtLevel = new Node [maxLevel + 1];

	      // head points to tail node at all levels initially
	      for (int i = 0; i <= maxLevel; i++)
	          head.next[i] = tail;

	      random = new Random();
	}

	public boolean isEmpty()
    {
		return size == 0;
	}

	public int size()
    {
		return size;
	}


	public Object get(Object Key)
	{
	      if (largestKey.compareTo(Key) <= 0)
	         return null;

	      // find the position just before the node where Key maybe by moving downwards initially and then forward
	      Node node = head;
	      for (int i = maxNonEmptyLevel; i >= 0; i--)
	         while (node.next[i].key.compareTo(Key) < 0)
	            node = node.next[i];

	      // check if next node has theKey
	      if (node.next[0].key.equals(Key))
	         return node.next[0].element;
	      return null;  // no matching element
	}

	Node search(Object Key)
	{
	      // find position just before possible node with Key
	      Node node = head;
	      for (int i = maxNonEmptyLevel; i >= 0; i--)
	      {
	         while (node.next[i].key.compareTo(Key) < 0)
	            node = node.next[i];
	         lastNodeAtLevel[i] = node;  // last level i node seen
	      }
	      return (node.next[0]);
	}

	int getLevel()
	{
	      int level = 0;
	      while (random.nextFloat() <= probability)
	         level++;

	      return (level <= maxLevel) ? level : maxLevel;
	}

	public Object put(Object newKey, Object newElement)
	{
	      if (largestKey.compareTo(newKey) <= 0)
	         throw new IllegalArgumentException("key is too large");

	      // Check if element already present
	      Node node = search(newKey);

	      if (node.key.equals(newKey))
	      {
	         Object elementReturned = node.element;
	         node.element = newElement;
	         return elementReturned;
	      }

	      // If not present, find the level where it will go
	      int level = getLevel(); // level of new node

	      if (level > maxNonEmptyLevel)
	      {
	         level = ++maxNonEmptyLevel;
	         lastNodeAtLevel[level] = head;
	      }

	      // get and insert new node
	      Node newNode = new Node (newKey, newElement, level + 1);
	      for (int i = 0; i <= level; i++)
	      {
	         newNode.next[i] = lastNodeAtLevel[i].next[i];
	         lastNodeAtLevel[i].next[i] = newNode;
	      }
	      size++;
	      return null;
	}


	public Object remove(Object Key)
	{
	      if (largestKey.compareTo(Key) <= 0)
	         return null;

	      // check if matching element present
	      Node node = search(Key);
	      if (!node.key.equals(Key))
	         return null;

	      // delete node from skip list
	      for (int i = 0; i <= maxNonEmptyLevel &&
	    		  lastNodeAtLevel[i].next[i] == node; i++)
	    	  lastNodeAtLevel[i].next[i] = node.next[i];

	      // update Levels
	      while (maxNonEmptyLevel > 0 && head.next[maxNonEmptyLevel] == tail)
	    	  maxNonEmptyLevel--;

	      size--;
	      return node.element;
	}


	public String toString()
	{
	      StringBuffer sb = new StringBuffer("[");

	      // follow level 0 chain
	      Node currentNode = head.next[0];
	      if (currentNode != tail)
	      {

	         sb.append(currentNode.element.toString());

	         currentNode = currentNode.next[0];
	         while(currentNode != tail)
	         {
	            sb.append(", " + currentNode.element.toString());
	            currentNode = currentNode.next[0];
	         }
	      }
	      sb.append("]");

	      // create equivalent String
	      return new String(sb);
	}

	public Iterator iterator()
  {
    return new SkipListIterator();
  }


	private class SkipListIterator implements Iterator
	{
	      // data member
	      private Node nextNode;

	      // constructor
	      public SkipListIterator()
	      {
	    	  nextNode = head.next[0];
	      }

	      // checks if next element is present or not
	      public boolean hasNext()
	      {
	    	  return nextNode != tail;
	      }

	      // get the next element in the list
	      public Object next()
	      {
	         if (nextNode != tail)
	         {
	            Object obj = nextNode.element;
	            nextNode = nextNode.next[0];
	            return obj;
	         }
	         else throw new NoSuchElementException("No next element");
	      }

	}

	public static void main(String [] args)
	{
	      // test constructor
	      SkipList list = new SkipList(new Integer(1001), 100, (float) 0.5);

	      // test put
	      int n = 10;
	      for (int i = 1; i <= n; i++)
	         list.put(new Integer(2 * i), new Integer(i));

	      System.out.println("The list after adding first 10 elements : \n" + list);

	      for (int i = 1; i <= n + 1; i++)
	         list.put(new Integer(2 * i - 1), new Integer(n + i));
	      System.out.println("The list after adding some more elements : \n" + list);

	      // test get
	      System.out.println("element " + list.get(new Integer(1)) + " has key 1");
	      System.out.println("element " + list.get(new Integer(2)) + " has key 2");
	      System.out.println("element " + list.get(new Integer(6)) + " has key 6");

	      // test remove
	      for (int i = 1; i <= n + 1; i++)
	         System.out.println("removed " + list.remove(new Integer(2 * i - 1)));
	      System.out.println("The list after deleting elements : \n" + list);
	}
}
