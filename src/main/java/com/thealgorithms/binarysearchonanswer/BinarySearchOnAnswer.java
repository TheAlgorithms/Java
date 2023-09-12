package com.thealgorithms.binarysearchonanswer;
import java.util.Scanner;

/**
 # BinarySearchOnAnswer
 
 #Question : 
 Koko loves to eat bananas. There are n piles of bananas, 
 the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 Koko can decide her bananas-per-hour eating speed of k. 
 Each hour, she chooses some pile of bananas and eats k bananas from that pile. 
 If the pile has less than k bananas, 
 she eats all of them instead and will not eat any more bananas during this hour.
 Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 Return the minimum integer k such that she can eat all the bananas within h hours.
 
 example test cases : 
 Input: piles = [3,6,7,11], h = 8
 Output: 4

 leetcode link : https://leetcode.com/problems/koko-eating-bananas/descripti

 ## The brute force O(n^2) :
 This can be done by checking each value for (k) starting from 1
 until we get an acceptable value.
 
 ## Optimized Approach O(n*log(n))
 Instead of searching for (k) linearly we can do a binary search on k
 until we get the lowest acceptable answer.
 We have to make another function (optional : done to keep the code clean)
 to check if a value could be a possible answer that satisfies our conditions.
  

 */



public class BinarySearchOnAnswer {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of banana piles (Length of array) : ");
        int length = Integer.parseInt(sc.nextLine());
        
        System.out.print("Enter number of hours : ");
        int h = Integer.parseInt(sc.nextLine());
        
        System.out.println("Enter number of bananas in each pile. (Enter space separated integers for input.)");
        
        int piles[] = new int[length];
        
        for (int i = 0; i < piles.length; i++) {
            piles[i] = Integer.parseInt(sc.next());
        }

        int low = 1;
        int high = 0;
        // keeping the value of high equal tp size of largest pile
        // so our search space gets as small as possible.
        for(int i : piles) high = Math.max(i,high);

        int answer = 0;

        // This is a binary function search to find the lower bound
        // i.e the lowest acceptable value.

        while (low <= high) {
            int k = low + (high-low)/2;
            if(blackBox(piles, h, k)){
                answer = k;
                high = k-1;
            }
            else low = k+1;
        }


        System.out.println("Required speed for eating bananas : "  + answer + " per hour.");

        sc.close();
    }

    /*
     * This is a function which checks whether koko can eat all the bananas 
     * in given time (h) if she eats with a speed of k bananas per hour.
     * 
     * It returns true if she can , otherwise false.
     * 
     * Simple speed = quantity/hours formula is used to 
     * decrement variable (h).
     * 
     * Basically we are finding k to satisfy this equation :
     * sum of ceil(piles[i]/k) <= h for every i between 0 to n-1.
     *
     *
     */


    private static boolean blackBox(int piles[],int h,int k){
        for(int i = 0;i < piles.length;i++){
            h -=  (int)Math.ceil((double)piles[i]/(double)k); 
        }
        return (h >= 0);
    }
}