package Searches;

import java.util.*;

/* Binary search is an efficient method to search for an element.
 * This PerfectBinarySearch function uses recursive approach to solve the problem.
 * Works only for sorted array.
 * Worst-case performance    O(log n) base 2
 * Best-case performance     O(1) 
 * Average-case performance   O(log n) base 2
 * Worst-case space complexity   O(1)
*/


class PerfectBinarySearch{
    public static int binarySearch(int[] arr,int low, int high, int target){
    	if(low <= high){
    		int mid = low + (high - low)/2;
    		if(arr[mid] == target)
    			return mid;
    		else if(arr[mid] > target)
    			return binarySearch(arr,low,mid-1,target);
    		else
    			return binarySearch(arr,mid+1,high,target);
    	}
    	return -1;
    }

    public static void main(String[] args){
        PerfectBinarySearch BinarySearch = new PerfectBinarySearch();
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Enter size of array to be made : ");
        int size = input.nextInt();
        int[] array = new int[size];
        for(int i=0;i<size;i++){
        	array[i] = rand.nextInt(100);
        }
        Arrays.sort(array);
    
        System.out.print("Enter the element to search for : ");
        int key = input.nextInt();
        int index = BinarySearch.binarySearch(array,0, array.length-1, key);

        if(index == (-1))
        	System.out.println("Element not found.");
        else
        	System.out.println("Your query element is found at index "+ index);
        System.out.print("The array was : ");
        for(int i=0;i<size;i++)
        	System.out.print(array[i]+ " ");
        System.out.println();

    }
}
