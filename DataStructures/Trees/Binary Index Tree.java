import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

class Ideone{

	//Binary Indexed Trees aka Fenwick Trees

	public static Scanner scn = new Scanner(System.in);

	public static class BinaryIndexedTree{

		int size;
		//table array is one indexed integer array 
		int[] table;

		//input array is one indexed integer array 
		public BinaryIndexedTree(int[] arr){

			this.size = arr.length;
			table = new int[this.size];
			int[] prefix = new int[arr.length];

			int sum = 0;
			for(int i = 1; i < this.size; i++){
				sum += arr[i];
				prefix[i] = sum;
			}

			//filling table array for the first time
			for(int i = 1; i < this.size; i++){
				
				int responsibility = Integer.lowestOneBit(i);

				int idx2 = i;
				int idx1 = i - responsibility + 1;

				int rangeSum = prefix[idx2] - prefix[idx1 - 1];

				table[i] = rangeSum;
			}
		} 

		//increment position idx by delta
		public void update(int idx, int delta){

			while(idx < size){
				table[idx] += delta;
				idx += Integer.lowestOneBit(idx);
			}
		}

		//find sum of [1, idx]
		public int sum(int idx){

			if(idx >= size)
				return 0;

			int sum = 0;

			while(idx > 0){

				sum += table[idx];
				idx -= Integer.lowestOneBit(idx);
			}

			return sum;
		}

		//find sum in range [idx1 , idx2]
		public int rangeSum(int idx1, int idx2){

			return sum(idx2) - sum(idx1 - 1);
		}

	}

	public static void main (String[] args) throws java.lang.Exception{

		int[] arr = {1,3,5,9,11};

		BinaryIndexedTree BT = new BinaryIndexedTree(arr);

		System.out.println("" + BT.rangeSum(1,5));	

	}
}