package com.interview.dynamic;

/**
 * http://www.geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-four-keys/
 * Test cases
 * Negative number
 * Number less than 7
 * Number greater than equal to 7
 */
public class CountAs {

    public int countAsRec(int n){
    
        if(n < 7){
            return n;
        }
        int max = Integer.MIN_VALUE;
        int result = 0;
        for(int b=n-3; b > 0; b--){
            result = (n-b-1)*countAs(b);
            if(max < result){
                max = result;
            }
        }
        return max;
    }
 
    public int countAs(int n){
        if(n < 7){
            return n;
        }
        
        int T[] = new int[n+1];
        for(int i=1; i < 7 ; i++){
            T[i] = i;
        }
        for(int i=7; i <= n; i++){
            for(int b = i-3; b > 0; b--){
                T[i] = Math.max(T[i], T[b]*(i-b-1));
            }
        }
        return T[n];
    }
    
    public static void main(String args[]){
        CountAs ca =new CountAs();
        System.out.println(ca.countAsRec(25));
        System.out.println(ca.countAs(25));
              
    }
}
