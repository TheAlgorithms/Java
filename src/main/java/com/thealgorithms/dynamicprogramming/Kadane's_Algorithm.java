import java.util.*;
public class Kadane's_Algorithm {

    // Kadane's Algorithm Time Complexity - O(n)
    
    
    /**
     * @param array
     * @param n
     */
    public static void kadanes(int array[], int n) {
        int max = Integer.MIN_VALUE;
        int currsum = 0;
        for(int i=0;i<n;i++){
            currsum += array[i];
            if(currsum < 0){
                currsum = 0; // if the current sum is -ve, currsum will be updated with 0
            }
            max = Math.max(currsum, max);

        }
        
        //For Exception if all the numbers are -ve, then it'll return max -ve number;
        if(max == 0){
            max = Integer.MIN_VALUE; 
            for(int i=0;i<n;i++){
                max = Math.max(max, array[i]);
            }
        }
        System.out.println("Maximum Subarray sum = "+max);
    }
    
    //Main
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int array[] = new int[20];
        System.out.println("enter array Length");
        int n = sc.nextInt();
        System.out.println("Enter array elements");
        for(int i=0;i<n;i++){
            array[i] = sc.nextInt();
        }
        sc.close();
        kadanes(array, n);

    }
}
