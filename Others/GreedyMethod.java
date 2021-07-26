package Others;

import java.util.*;


public class GreedyMethod 
{
	public static void main(String[] args) {
		int size = 50;
		ArrayList<Item> itemlist = new ArrayList<Item>();
		
		itemlist.add(new Item(10,90));
		itemlist.add(new Item(20,100));
		itemlist.add(new Item(30,120));
		
		int n = itemlist.size();
		
		double AR = greedyMethod(itemlist, size, n);
		double ER = 270;		//90 + 100 + (2/3 * 120)
		
		assert AR == (ER);
		
	}
	
	
	/**
	 * 
	 * @param items list of item to be choose
	 * @param size	size of the bag
	 * @param n	    amount of items available
	 * @return		totalVal represent the maximum value return by greedyMethod
	 */
	public static double greedyMethod(ArrayList<Item> items, int size, int n)  throws IllegalArgumentException 
	{
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
			
			if(size-iw >= 0) // if the space available is not 0 , then add in the items that is fit with the space available currently
			{																
				size -= iw;
				totalVal += iv;
			}
			else
			{
				fraction = ((double)size/ (double)iw);
				totalVal += (iv * fraction);
				size = (int)(size - (iv * fraction));
				break;		
			}				
		}
		
		return totalVal;
	}
	
	static class Item {
		 int wt;
		 int val;
		
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

}
