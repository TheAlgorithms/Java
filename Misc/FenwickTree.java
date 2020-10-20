package Misc;

/*
---------------- Definition taken from Wikipedia------------------------------------

 A Fenwick tree or binary indexed tree is a data structure that can efficiently update 
 elements and calculate prefix sums in a table of numbers. 
 
 When compared with a flat array of numbers, the Fenwick tree achieves a much better 
 balance between two operations: element update and prefix sum calculation.
 
 Fenwick trees allow both operations to be performed in O(log n) time. This is achieved by 
 representing the numbers as a tree, where the value of each node is the sum of the numbers in 
 that subtree. The tree structure allows operations to be performed using only O(log n) 
 node accesses.

 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class FenwickTree {

	final static int MAX_SIZE = 1000;	// max size of tree
	static int tree[] = new int[MAX_SIZE];	// represents fenwick tree
	
	/* 
	 n - number of elements present in input array
	 tree[0...n] - represents fenwick tree (0 is the Dummy Node)
	 arr[0...n-1] - represents the input array
	 */
	
	
	// calculates sum of arr[0...index]
	int getSum(int index) {
		int sum = 0;
		index = index + 1; 	// index of fenwick tree  = index of input array + 1
		
		while(index > 0) {
			sum += tree[index];
			
			// move index to parent node
			index -= index & (-index);
		}
		
		return sum;
	}
	
	// updates the fenwick tree. The node at index is updated by adding value val.
	// All other nodes that are ancestors of the node is also updated by adding value val.
	public static void updateTree(int n, int index, int val) {
		index = index + 1;
		
		while(index <= n) {
			tree[index] += val;
			
			index += index & (-index);
		}
	}
	
	// constructs the fenwick tree initially
	void constructFenwickTree(int arr[], int n) {
		for(int i=1;i<=n;i++)
			tree[i] = 0;
		
		for(int i=0;i<n;i++)
			updateTree(n, i, arr[i]);
	}
	
	public static void main(String args[]) {
		int arr[] = {2, 4, 5, 7, 9, 10, 1, 11, 99};
		int n = arr.length;
		
		FenwickTree fenwicktree = new FenwickTree();
		
		fenwicktree.constructFenwickTree(arr, n);
		
		System.out.println("Sum of elements from position [0...6] is " + fenwicktree.getSum(6));
		
		arr[4] += 5;
		
		updateTree(n, 4, 5);
		
		System.out.println("Sum of elements from position [0...6] after updating is " + fenwicktree.getSum(6));
	}
}
