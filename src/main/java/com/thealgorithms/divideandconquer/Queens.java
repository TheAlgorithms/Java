import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;
class Queens{

	static boolean Place(int[] x,int k,int i)
	{
		for(int j=0;j<k;j++)
		{
			if((x[j]==i) || (Math.abs(x[j]-i)==Math.abs(j-k)))               // check queen can be placed safely in column k
			{
				return false;
			}
		}
		
		return true;
	}

	
	static void NQueens(int[] x, int k, int n)
	{
		for(int i=0;i<n;i++)
		{
			if(Place(x,k,i))                                               // queen can be placed safely in column x
			{
				x[k]=i;
				if(k==n-1)                                              //all queens have been placed
				{ 
					System.out.println(Arrays.toString(x));           // print the queens arrangement
				}
		
				else
				{
					NQueens(x,k+1,n);                                // place the next queen
				}
			}
		}
	}



	public static void main(String[] args)
	{
		Scanner x=new Scanner(System.in);
		int n=x.nextInt();
		int[] arr=new int[n];
		NQueens(arr,0,n);
	}
}