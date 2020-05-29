package Others;

import java.util.*;

/**
 * Rotation of array without using extra space
 * 
 *
 * @author Ujjawal Joshi
 * @date 2020.05.18
 *
 * Test Cases:

 	Input:
 	2 //Size of matrix
	1 2
 	3 4
 	Output:
 	3 1
 	4 2
 ------------------------------
 	Input:
 	3 //Size of matrix
	1 2 3 
	4 5 6
	7 8 9  
	Output:
	7 4 1 
	8 5 2
	9 6 3
 * 
 */

class main{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int a[][]=new int[n][n];
		
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				a[i][j]=sc.nextInt();
			}
		}
		int temp=0;

		// Rotation of array by swapping their values
		for(int i=0;i<n/2;i++)
		{	
			for(int j=i;j<n-i-1;j++)
			{	
				temp=a[i][j];
				a[i][j]=a[n-j-1][i];
				a[n-j-1][i]=a[n-i-1][n-j-1];
				a[n-i-1][n-j-1]=a[j][n-i-1];
				a[j][n-i-1]=temp;
			}
		}

		// printing of sorted array
    for(int i=0;i<n;i++){
      for(int j=0;j<n;j++){
         System.out.print(a[i][j]+" ");

      }
      System.out.println();
    }
    
  }
    
    
}
