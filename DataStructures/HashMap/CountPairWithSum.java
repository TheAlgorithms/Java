import java.util.Scanner;
import java.util.HashMap; 

class Test 
{  
	static int getPairsCount(int n, int sum, int[] arr) 
	{ 
		HashMap<Integer, Integer> hm = new HashMap<>(); 

		// Store counts of all elements in map hm 
		for (int i=0; i<n; i++){ 
			
			// initializing value to 0, if key not found 
			if(!hm.containsKey(arr[i])) 
				hm.put(arr[i],0); 
				
			hm.put(arr[i], hm.get(arr[i])+1); 
		} 
		int twice_count = 0; 

		// iterate through each element and increment the 
		// count (Notice that every pair is counted twice) 
		for (int i=0; i<n; i++) 
		{ 
			if(hm.get(sum-arr[i]) != null) 
				twice_count += hm.get(sum-arr[i]); 

			if (sum-arr[i] == arr[i]) 
				twice_count--; 
		} 

		// return the half of twice_count 
		return twice_count/2; 
	} 

	// Driver method to test the above function 
	public static void main(String[] args) { 
		
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int sum = scn.nextInt();

		int[] arr = new int[n];
		for(int i=0; i<n; i++){
			arr[i] = scn.nextInt();
		}

		System.out.println("Count of pairs is " + getPairsCount(arr.length, sum, arr)); 
	} 
} 
