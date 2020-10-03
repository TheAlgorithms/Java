/* 1) In this problem 

In a hypothetical scenario, there are soldiers protecting farmers.

soldiers should remain at the end / edge of the line. 
Farmers should always be in between soldiers;So that Farmers are always been protected by Soldiers.
Like if soldier is = 1
and farmer is = 0
So output is as follows...
    101010101
 Do it in O(1) space complexity.
*/

// The code for this problem is as follows...

/*
         |
         |    
         V
*/

import java.util.*;
import java.util.Random;
class FarmerSoldierProblem
{
	public static void main(String[]args)
	{
		int temp = 0,f1 = 0,f = 0;
		int s1 = 0,s = 0,n=0;
		Scanner sc = new Scanner(System.in);
			Random rd = new Random();
			System.out.println("enter FMR");
			f = sc.nextInt();
			s = f+1;
			int size = s+f;
			
			int[] a = new int[size];
			
	while(n!=size)
	{
		int r = rd.nextInt(2);					
		if(r==1)
		{
			if(s!=0)
			{
			a[n]=1;
			s--;
			}else
			{
				a[n]=0;
				f--;
			}
			
		}else
		{
			if(f!=0)
			{
			a[n]=0;
			f--;
			}else
			{
				a[n]=1;
				s--;
			}
		}
		n++;
	}
				System.out.print("unsorted array");				
					System.out.println("");
					System.out.print("      ");
					for(int g = 0 ; g< a.length; g++)
					{
				
						System.out.print(a[g]);
					}
					for(int j = 0; j< a.length; j++)//counting f=0;s=1
						{
							if(a[j] == 1)
						{
							s1++;
							}else{
							f1++;
						}
					}
	
					System.out.println(" ");
					System.out.println("no of soldiers");
					System.out.println(s1);
					System.out.println("no of farmer");
					System.out.println(f1);
					if(s1>f1)
			{
				for(int i = 0; i<a.length;i++)
				{
					if(i%2 == 1)
					{
						if(a[i] != 0)
						{
							int j =i;
							while(a[j]!=0)
							{
								j++;
							}
							temp = a[j];
							a[j] = a[i];
							a[i] = temp;
						}
					}else{
						if(a[i] != 1)
						{
							int j =i;
							while(a[j]!=1)
							{
								j++;
							}
							temp = a[j];
							a[j] = a[i];
							a[i] = temp;
						}
					}
				}
				System.out.print("sorted array");	
				System.out.println("");
				System.out.print("      ");
				for(int g = 0 ; g< a.length; g++)
				{
				
					System.out.print(a[g]);
				}
			}
	}
}	