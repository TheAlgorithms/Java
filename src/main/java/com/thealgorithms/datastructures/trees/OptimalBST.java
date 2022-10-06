package com.thealgorithms.datastructures.trees;

/*
Given a sorted array key [0.. n-1] of search keys and an array freq[0.. n-1] of frequency counts, where freq[i] is the number of searches for keys[i]. 
Construct a binary search tree of all keys such that the total cost of all the searches is as small as possible.
*/

public class OptimalBST
{
	// A recursive function to calculate cost of
		// optimal binary search tree
	static int optCost(int freq[], int i, int j)
	{
	// Base cases
	if (j < i)	 // no elements in this subarray
		return 0;
	if (j == i)	 // one element in this subarray
		return freq[i];
	
	// Get sum of freq[i], freq[i+1], ... freq[j]
	int fsum = sum(freq, i, j);
	
	// Initialize minimum value
	int min = Integer.MAX_VALUE;
	
	// One by one consider all elements as root and
		// recursively find cost of the BST, compare the
		// cost with min and update min if needed
	for (int r = i; r <= j; ++r)
	{
		int cost = optCost(freq, i, r-1) +
						optCost(freq, r+1, j);
		if (cost < min)
			min = cost;
	}
	
	// Return minimum value
	return min + fsum;
	}
	
	// The main function that calculates minimum cost of
		// a Binary Search Tree. It mainly uses optCost() to
		// find the optimal cost.
	static int optimalSearchTree(int keys[], int freq[], int n)
	{
		// Here array keys[] is assumed to be sorted in
			// increasing order. If keys[] is not sorted, then
			// add code to sort keys, and rearrange freq[]
			// accordingly.
		return optCost(freq, 0, n-1);
	}
	
	// A utility function to get sum of array elements
		// freq[i] to freq[j]
	static int sum(int freq[], int i, int j)
	{
		int s = 0;
		for (int k = i; k <=j; k++)
		s += freq[k];
		return s;
	}
	
	// Driver code
	public static void main(String[] args) {
		int keys[] = {10, 12, 20};
		int freq[] = {34, 8, 50};
		int n = keys.length;
		System.out.println("Cost of Optimal BST is " +
						optimalSearchTree(keys, freq, n));
	}
}
// This code is contributed by Sumit Ghosh
