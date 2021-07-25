package src.test.java.com.others;

import src.main.java.com.others.GreedyAlgorithm;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class GreedyAlgorithmTest {

	@Test
	public void testGreedyAlgorithm() 
	{
		int size = 50;
		ArrayList<Item> itemlist = new ArrayList<Item>();
		
		itemlist.add(new Item(20,100));
		itemlist.add(new Item(10, 90));
		itemlist.add(new Item(30,120));
		
		int n = itemlist.size();
		
		GreedyAlgorithm bag = new GreedyAlgorithm();
		
		Item[] AR = bag.knapsack(itemlist, size, n);
		Item[] ER = {new Item(30,120), new Item(20,100)};
		
		assertSame(ER.length, AR.length);
		
		for(int i = 0; i < ER.length; i++)
		{
			assertEquals(ER[i].getWeight(), AR[i].getWeight());
			assertEquals(ER[i].getValues(), AR[i].getValues());
		}
		
	}

}
