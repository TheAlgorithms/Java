//package src.main.java.com.others;
package Other;

/*
 *  Using greedy Algorithms for 0 - 1 knapsack Problem
 *  Greedy Algorithm take consideration on the cost item before include them into bag
 *  Item will highest cost will be include first and the following until the next item weight/size is exceed the bag remaining space.
 */

import java.util.*;

public class GreedyAlgorithm {
	
	/**
	 * 
	 * @param items item refer to the object to be consider keep in bag
	 * @param size	size refer to the capacity of bag
	 * @param n		n refer to the number of items to be choose from.
	 * @return rI = result items that is selected to be keep in limited size sack.
	 * @throws IllegalArgumentException
	 */
	public Item[] knapsack(ArrayList<Item> items, int size, int n) throws IllegalArgumentException {
		ArrayList<Item> rI = new ArrayList<Item>();
		int iw, iv;
		
		if(items.isEmpty())
			throw new IllegalArgumentException();

		items.sort(new Comparator<Item>()  				// rearrange the list of items user keyed in according to its cost in descending order
        { 
            public int compare(Item i1, Item i2)  
            { 
                return Integer.valueOf(i2.getCost()).compareTo(Integer.valueOf(i1.getCost())) ; 
            } 																						
        }); 
		
		Iterator<Item> iterator = items.iterator();
		
		// to check whether the item can be added into the sack wihtout exceeding the maximum capacity of sack
		while(iterator.hasNext())							
		{											
			Item currentItem = iterator.next();
			iw = currentItem.getWeight();
			iv = currentItem.getValues();
			
			if(size-iw >= 0) // if the space available is not 0 , then add in the items that is fit with the space available currently
			{																
				rI.add(currentItem);
				size -= iw;
			}
			else
			{
				break;		// if the space is not enough for the item, then exit the loop
			}				
		}
		
		Item[] myItem = new Item[rI.size()];
	    rI.toArray(myItem);
		
		return myItem;
	}
	
}

class Item {
	private int wt;
	private int val;
	
	/**
	 * Constructor to create item to be keep in the knapsack with 
	 * its weight and value.
	 * 
	 * @param w  weight of the item
	 * @param v  value of the item
	 */
	public Item(int w, int v)
	{
		this.wt = w;
		this.val = v;
	}
	
	//getter for weight
	public int getWeight() 
	{
		return wt;
	}

	//getter for value
	public int getValues() 
	{
		return val;
	}
	
	/**
	 * @return the cost is the product of its weight and value
	 */
	public int getCost()
	{
		return wt * val;
	}
}
