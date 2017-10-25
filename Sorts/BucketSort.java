/**
 * 
 * Bucket Sort Algorithm
 * 
 * @author yashashvi (github.com/YJDave)
 * 
 */


/**
 * Definition for Bucket Sort:
 * 		Wikipedia: https://en.wikipedia.org/wiki/Bucket_sort
 * 		Bucket sort, or bin sort, is a sorting algorithm that works by distributing the elements of an array into a number of buckets. 
 * 		Each bucket is then sorted individually, either using a different sorting algorithm, or by recursively applying the bucket sorting algorithm.
 * 
 * Time complexity:
 * 		n: length of input sequence
 * 		k: number of buckets
 * 		Average case: o(n+k)
 * 		Worst case: o(n^2)
 * 
 */


/**
 * Implementation of Bucket Sort:
 * 		1. Create and Initialize all Buckets
 * 		2(i). Take input from user (any unsorted numbers)
 * 		2(ii). Insert this numbers into specific Bucket according to HashFunction 
 * 			(we use here HashFunction to find that in which Bucket number should be added)
 * 			(you can use your custom HashFunction depending on input data's property)
 * 		
 *		3. Sort all Bucket (you can any sorting algorithm, here I have used Insertion Sort)
 * 		4. Place sorted no into SortedArray from Bucket
 * 		5. Print SortedArray
 * 
 *  Data-structure used:
 *  	Array: for storing sorted and unsorted numbers
 *  	Double Dimentional Array: for storing Buckets 
 *			(I have used double dimenstional array for simple implementation but,
 *  		If you want you can use linked list for storing buckets, for efficient usage of memory)
 *  	
 */



import java.util.Scanner;

public class BucketSort {
	
	
	public static void main(String[] args) {
	
		int MaxBuckets = 10;

		
		System.out.println("Sort numbers using Bucket Sort Alogrithm");

		Scanner input = new Scanner(System.in);
		System.out.print("Enter total no of input: ");
		int totalNo = input.nextInt();

		//		1. Create and Initialize all Buckets
		
		int[][] Buckets = new int[MaxBuckets][totalNo+1];
		System.out.println(totalNo+1);
		// we use Buckets[0][*] for storing array top
		// intialize it to 0
		
		for(int i=0;i<MaxBuckets;i++)
			Buckets[i][0] = 1;

		//		2(i). Take input from user (any unsorted numbers)
		
		System.out.println("Enter all no:");

		int[] inputArray = new int[totalNo];
		int inputIndex = 0;

		for(int i=0;i<totalNo;i++) {
			
			int no = input.nextInt();
			inputArray[inputIndex++] = no;
		}
		input.close();

		//		2(ii). Insert this numbers into specific Bucket

		for(int i=0;i<totalNo;i++) {
			// System.out.println("\n"+"Deciding for no: "+inputArray[i]);
			int hashKey = HashFunction(inputArray[i]);
			int top = Buckets[hashKey][0];

			// System.out.println("Hash key is: "+hashKey+" and top is: "+top+" so we will store no at top and increase top");
			Buckets[hashKey][top] = inputArray[i];
			Buckets[hashKey][0] = top+1;

			// System.out.println("So our Bucket is: +"+"\n"+ "Bucket["+hashKey+"]["+0+"] = "+Buckets[hashKey][0]+" and "+"Bucket["+hashKey+"]["+top+"] = "+Buckets[hashKey][top]);

		}
		

		//		3. Sort all Bucket (you can any sorting algorithm, here I have used Insertion Sort)

		for(int i=0;i<MaxBuckets;i++) {	
			int top = Buckets[i][0];
			
			// System.out.println("Sorting Bucket no:"+i);
		    for(int k=1;k<top-1;k++) {
		        int j = k +1;
		        int temp = Buckets[i][j];
		        while( k>0 && Buckets[i][k]>temp) {
		        	Buckets[i][k+1] = Buckets[i][k];
		            k--;
		        }
		        Buckets[i][k+1] = temp;
		    }
		}
		
		for(int i=0;i<MaxBuckets;i++) {
			int top = Buckets[i][0];
			for(int j=0;j<top;j++) {
				System.out.println("Bucket["+i+"]["+j+"] = "+Buckets[i][j]);
			}
		}

		//		4. Place sorted no into SortedArray from Bucket
		
		int[] SortedArray = new int[totalNo];
		int sortedIndex=0;

		for(int i=0;i<MaxBuckets;i++) {
			int top = Buckets[i][0];
			for(int j=1;j<top;j++) {
				SortedArray[sortedIndex++] = Buckets[i][j];
			}
		}
		
		
		//		5. Print SortedArray
		
		System.out.println("Sorted Array is: ");
		for(int i=0;i<totalNo;i++)
			System.out.print(SortedArray[i]+", ");

	}
	
	public static int HashFunction(int no) {
		
		int Key = no/10;
		if (Key>9)
			Key=9;
		// System.out.println(Key);
		return Key;
		
	}

	
}
