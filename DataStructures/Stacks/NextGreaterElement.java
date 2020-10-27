package DataStructures.Stacks;

import java.util.Stack;

public class NextGreaterElement{ 
	public static void printNGE(int arr[], int n) { 
		int i = 0; 
		Stack<Integer> s = new Stack(); 
		int element, next; 
		s.push(arr[0]); 
		for (i = 1; i < n; i++) { 
			next = arr[i]; 
			if (s.isEmpty() == false) { 
				element = s.pop(); 
				while (element < next) { 
					System.out.println(element + " --> " + next); 
					if (s.isEmpty() == true) 
						break; 
					element = s.pop(); 
				} 
				if (element > next) {
					s.push(element); 
                }    
			} 
			s.push(next); 
		} 
		while (s.isEmpty() == false) { 
			element = s.pop(); 
			next = -1; 
			System.out.println(element + " -- " + next); 
		} 
	} 

	public static void main(String[] args) { 
		int arr[] = { 11, 13, 21, 3 }; 
		int n = arr.length; 
		printNGE(arr, n); 
	} 
}
 