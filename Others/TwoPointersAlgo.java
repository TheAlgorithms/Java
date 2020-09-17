import java.util.*;
import java.lang.*;
import java.io.*;

class TwoPointersAlgo {
    //This function prints all pairs in the array that sum to a number X. If no such pair exists then output will be -1.
	static void twoSum(int A[], int X)
    {
        Arrays.sort(A);
        
        //Array sorting is necessary for this algo to function correctly
        
        int n = A.length;
        int i = 0, j = n-1, flag=0;
        //Implementation of the algorithm starts
        while(i<j)
        {
            if(A[i]+A[j]==X)
            {
                System.out.println(A[i] + " " + A[j] + " " + X);
                flag = 1;
                i++;
                j--;
            }
            else if(A[i]+A[j]<X)
            {
                i++;
            }
            else j--;
        }
        
        //Implementation ends
        
        if(flag==0)
        System.out.println(-1);
    }//end of function twoSum
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int t,n;
		t = in.nextInt();
		//for number of test cases
		while(t>0)
		{
		    t--;
		    n = in.nextInt();
		    int a[] = new int[n];
		    for(int i = 0; i<n; i++)
		    {
		        a[i] = in.nextInt();
		    }
		    //taking array input
		    int k = in.nextInt(); //the total of the pair entered by the user
		    twoSum(a, k);
		    
		}
	}//end of main
}//end of class

/*Sample Input/Output
Input:
2
7
1 2 3 4 5 6 7
98
7
1 2 3 4 5 6 7
8
Output:
-1
1 7 8
2 6 8
3 5 8
*/