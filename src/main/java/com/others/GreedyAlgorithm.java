package src.main.java.com.others;

/*
 *  Using greedy Algorithms for 0 - 1 knapsack Problem
 *  Greedy Algorithm take consideration on the cost item before include them into bag
 *  Item will highest cost will be include first and the following until the next item weight/size is exceed the bag remaining space.
 *  then the remaining space will be fill in by the fractional items.
 */

import java.util.*;

public class GreedyAlgorithm {
	
	/**
	 * 
	 * @param items item refer to the object to be consider keep in bag
	 * @param size	size refer to the capacity of bag
	 * @param n	n refer to the number of items to be choose from.
	 * @return totalVal  maximum value contribute by the selected items.
	 * @throws IllegalArgumentException
	 */
	public double knapsack(ArrayList<Item> items, int size, int n) throws IllegalArgumentException {
		int iw, iv;
		double fraction, totalVal = 0d;
		
		if(items.isEmpty())
			throw new IllegalArgumentException();

		items.sort(new Comparator<Item>()  				// rearrange the list of items user keyed in according to its cost in descending order
        { 
            public int compare(Item i1, Item i2)  
            { 
                return Double.valueOf(i2.getCost()).compareTo(Double.valueOf(i1.getCost())) ; 
            } 																						
        }); 
		
		Iterator<Item> iterator = items.iterator();
		
		// to check whether the item can be added into the sack wihtout exceeding the maximum capacity of sack
		while(iterator.hasNext())							
		{											
			Item currentItem = iterator.next();
			iw = currentItem.getWeight();
			iv = currentItem.getValues();
			
			if(size-iw >= 0) // if the space available is not 0 , then add in full item that is fit with the space available currently
			{																
				size -= iw;
				totalVal += iv;
			}
			else
			{
				fraction = ((double)size/ (double)iw);
				totalVal += (iv * fraction);
				size = (int)(size - (iv * fraction));
				break;		// get the fractional part of the item to fill in available space
			}				
		}
		
		return totalVal;
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
	 * @return the cost is the weight divided by value
	 */
	public double getCost()
	{
		return (double)val/(double)wt ;
	}
}
