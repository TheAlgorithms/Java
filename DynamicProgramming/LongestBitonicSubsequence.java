/*
1. You are given a number n, representing the number of elements.
2. You are given n numbers, representing the contents of array of length n.
3. You are required to print the length of longest bitonic subsequence of array.
Note -> bitonic subsequence begins with elements in increasing order, followed by elements in decreasing order.
*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
         Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        
        int[] inc = new int[arr.length];
        int[] dec = new int[arr.length];
        int[] ans = new int[arr.length];
        inc[0]=1;

        for(int i=1;i<arr.length;i++){
           int max=0;

            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j]){
                    if(max<inc[j]){
                        
                         max=inc[j];
                    }
                }
            }
             inc[i]=max+1;
             
        }

        dec[arr.length-1]=1;
         for(int i=arr.length-2;i>=0;i--){
           int max=0;

            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    if(max<dec[j]){
                        
                         max=dec[j];
                    }
                }
            }
             dec[i]=max+1;
             
        }

        // System.out.println(Arrays.toString(inc));
        // System.out.println(Arrays.toString(dec));
        
        int lb=-1;

        for(int i=0;i<ans.length;i++){
            ans[i]=inc[i]+dec[i]-1;
            if(lb<ans[i]){
                lb=ans[i];
            }
        }
        // System.out.println(Arrays.toString(ans));
        System.out.println(lb);
        
    }

}
