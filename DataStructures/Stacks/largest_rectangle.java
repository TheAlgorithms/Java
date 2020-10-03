/*package whatever //do not write package name here */
//Name - soumyajit Chakraborty
//Date - 3/10/20
//Question taken --> Geeksgorgeeks
//-------------------------------------------------------------
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