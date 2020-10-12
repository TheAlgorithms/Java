// Java code to remove a subList 
// using removeRange() method 

import java.util.*; 

// since removeRange() is a protected method 
// ArrayList has to be extend the class 
public class GFG extends ArrayList<Integer> { 

	public static void main(String[] args) 
	{ 

		// create an empty array list 

		GFG arr = new GFG(); 

		// use add() method 
		// to add values in the list 
		arr.add(1); 
		arr.add(2); 
		arr.add(3); 
		arr.add(4); 
		arr.add(5); 
		arr.add(6); 
		arr.add(7); 
		arr.add(8); 

		// prints the list before removing 
		System.out.println("Original List: "
						+ arr); 

		// removing elements in the list 
		// from index 2 to 4 
		arr.removeRange(2, 4); 
		System.out.println("Final List: "
						+ arr); 
	} 
} 
