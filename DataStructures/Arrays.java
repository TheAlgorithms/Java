//Trapping Rain Water
//Given an array arr[] of N non-negative integers representing height of blocks at index i as Ai where the width of each block is 1. Compute how much water can be trapped in between blocks after raining.

{
import java.io.*;
import java.util.*;
class Array {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim()); //Inputting the testcases
		while(t-->0){
		    int n = Integer.parseInt(br.readLine().trim());
		    int arr[] = new int[n];
		    String inputLine[] = br.readLine().trim().split(" ");
		    for(int i=0; i<n; i++){
		        arr[i] = Integer.parseInt(inputLine[i]);
		    }
		    
		    Trap obj = new Trap();
		    
		    System.out.println(obj.trappingWater(arr, n));
		}
	}
}

}

class Trap{
    
    // function to find the trapped water in between buildings
    // arr: input array
    // n: size of array
    static int trappingWater(int arr[], int n) { 
        
        // Your code here
        int res = 0;
        int[] lMax = new int[n];
        int[] rMax = new int[n];
        
        lMax[0] = arr[0];
        for(int i=1;i<n;i++)
            lMax[i] = Math.max(arr[i],lMax[i-1]);
        
        rMax[n-1] = arr[n-1];
        for(int i=n-2;i>=0;i--)
            rMax[i] = Math.max(arr[i],rMax[i+1]);
            
        for(int i=1;i<n-1;i++)
            res = res + (Math.min(lMax[i],rMax[i])-arr[i]);
        
        return res;    
    } 
}
