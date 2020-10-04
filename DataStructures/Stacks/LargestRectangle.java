/*package whatever //do not write package name here */
//Name - soumyajit Chakraborty
//Date - 3/10/20
//Question taken --> Geeksgorgeeks
//-------------------------------------------------------------

//Question 
/*
Given a binary matrix. Find the maximum area of a rectangle formed only of 1s in the given matrix.

Test cases
-----------
Input:
n = 4, m = 4
M[][] = {{0 1 1 0},
         {1 1 1 1},
         {1 1 1 1},
         {1 1 0 0}}
Output: 8
Explanation: For the above test case the
matrix will look like
0 1 1 0
1 1 1 1
1 1 1 1
1 1 0 0
the max size rectangle is 
1 1 1 1
1 1 1 1
and area is 4 *2 = 8.
----------------------------------------
Expected Time Complexity : O(n*m)
Expected Auixiliary Space : O(m)

Constraints:
1<=n,m<=1000
0<=M[][]<=1

*/
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main (String[] args) {
		//code
                
                int i,j,k,n;
                Scanner s = new Scanner(System.in);
                int t = s.nextInt();
                while(t --> 0)
                {
                     long max_area = Integer.MIN_VALUE,area;
                     n = s.nextInt();
                     long[] arr = new long[n];
                     for(i=0 ; i<arr.length ; i++)
                     {
                          arr[i] = s.nextInt();
                     }
                     Stack<Integer> stack = new Stack<Integer>();
                     long right[] = new long[n];
                     long left[] = new long[n];
                     
                     for(i=arr.length-1 ; i>=0 ; i--)
                     {
                          if(i == arr.length-1)
                          {
                               stack.push(i);
                               right[i] = arr.length;
                          }
                          else
                          {
                               while(!stack.isEmpty() && arr[stack.peek()] >= arr[i] )
                               {
                                   stack.pop();
                               }
                               if(!stack.isEmpty())
                               {
                                   right[i] = stack.peek();
                                    stack.push(i);    
                               }
                               else
                               {
                                   stack.push(i);
                                   right[i] = arr.length;
                               }
                          }
                     }
                     while(!stack.isEmpty())
                           stack.pop();
                     for(i=0 ; i<arr.length ; i++)
                     {
                          if(i == 0)
                          {
                               stack.push(i);
                               left[i] = -1;
                          }
                          else{
                               while(!stack.isEmpty() && arr[stack.peek()] >= arr[i] )
                               {
                                   stack.pop();
                               }
                               if(!stack.isEmpty())
                               {
                                   left[i] = stack.peek();
                                    stack.push(i);    
                               }
                               else
                               {
                                   stack.push(i);
                                   left[i] = -1;
                               }
                          }
                     }
                     for(i=0 ; i<n ; i++)
                     {
                          area = (right[i] - left[i] - 1)*arr[i];
                          if(area > max_area)
                                max_area = area;
                     }
                 System.out.println(max_area);
                }
	}
}
