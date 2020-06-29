package Sorts;

import java.util.Random;
 
public class BucketSort 
{
    static int[] sort(int[] sequence, int maxValue) 
    {
        // Bucket Sort
        int[] Bucket = new int[maxValue + 1];
        int[] sorted_sequence = new int[sequence.length];
 
        for (int i = 0; i < sequence.length; i++)
            Bucket[sequence[i]]++;
 
        int outPos = 0;
        for (int i = 0; i < Bucket.length; i++)
            for (int j = 0; j < Bucket[i]; j++)
                sorted_sequence[outPos++] = i;
 
        return sorted_sequence;
    }
 
    static void printSequence(int[] sorted_sequence) 
    {
        for (int i = 0; i < sorted_sequence.length; i++)
            System.out.print(sorted_sequence[i] + " ");
    }
 
    static int maxValue(int[] sequence) 
    {
        int maxValue = 0;
        for (int i = 0; i < sequence.length; i++)
            if (sequence[i] > maxValue)
                maxValue = sequence[i];
        return maxValue;
    }
 
    public static void main(String args[]) 
    {
        System.out.println("Sorting of randomly generated numbers using BUCKET SORT");
        Random random = new Random();
        int N = 20;
        int[] sequence = new int[N];
 
        for (int i = 0; i < N; i++)
            sequence[i] = Math.abs(random.nextInt(100));
 
        int maxValue = maxValue(sequence);
 
        System.out.println("\nOriginal Sequence: ");
        printSequence(sequence);
 
        System.out.println("\nSorted Sequence: ");
        printSequence(sort(sequence, maxValue));
    }
}


/*
#Output:

$ javac Bucket_Sort.java
$ java Bucket_Sort
 
Sorting of randomly generated numbers using BUCKET SORT
 
Original Sequence: 
95 9 95 87 8 81 18 54 57 53 92 15 38 24 8 56 29 69 64 66 
Sorted Sequence: 
8 8 9 15 18 24 29 38 53 54 56 57 64 66 69 81 87 92 95 95
*/
