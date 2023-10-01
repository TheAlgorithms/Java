import java.util.* ;
/**
 * This file contains an implementation of painter's problem
 using dynamic programming.

 * Wikipedia : https://en.wikipedia.org/wiki/Partition_problem

 * Time Complexity : Exponential
 * Space complexity : O(1)

 * @author ANSHUMAN KHATUA (https://github.com/zenith-78)
 */

class PainterProblem{
    /**
     * This method calculates the minimum amount time to paint the board by K painters
     *
     * @param args an Array of boards of size n , the size of the array N as Integer
     *             and K as number of painters or say partition.
     *
     * @return minimum amount of time required to paint the boards
     *
     */
     static int partition (int[] arr , int n , int k){

         /**
          *  If there is only one painter --> base case
          *  If there is only one board --> base case
           */
         if (k == 1) {
             return sum(arr, 0 , n - 1);
         }
         if (n == 1) {
             return arr[0] ;
         }
         /*
          * this is the maximum amount of time that can be considered if the
          * input of the boards array  is infinity.
          */

         int max  = Integer.MAX_VALUE;

         /*
          * find minimum of all possible maximum k -1 partitions
          * to the left of arr[i], with i elements, put k-1th divider
          * between arr[i-1] & arr[i] to get k-th partition.
          */

         for (int i = 0; i <= n ; i++) {
             max =  Math.min(max,Math.max(partition(arr,i,k-1),sum(arr,i,n-1))) ;

         }

         return max ;
     }

    /**
     * This method calculates the sum between given two index.
     *
     * @param An integer array same, A start index and A end index.
     *
     * @return total sum of array in between the specified index.
     */
    static int sum (int [] arr , int start ,  int end){
        int sum =  0 ;
        for (int i = start; i <=  end ; i++) {
            sum += arr[i] ;
        }
        return  sum ;
    }

    //Main method for a small test case.
    public static void main(String[] args) {
        int [] arr = { 30 ,80 , 67 , 54 ,90 ,54 ,76};
        int n  =  arr.length ;
        int k = 2 ;
        System.out.println(partition(arr,n,k));
    }

}