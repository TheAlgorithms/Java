import java.util.Scanner;
import java.util.Arrays;

class Warshall
{
	static void update(double[][] paths, int n, int medial)
	{
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				double d=paths[i][medial]+paths[medial][j];
				if(d<paths[i][j])
				{
					paths[i][j]=d;
				}
			}
		}
	}

	static void func(double[][] paths,int n)
	{
		for(int i=0;i<n;i++)
		{
			update(paths,n,i);
			System.out.println("after iteration "+i);
			for(double[] j:paths)
		{
			System.out.println(Arrays.toString(j));
		}
		}
	}
	
	
	public static void main(String[] args)
	{
		Scanner x=new Scanner(System.in);
		System.out.println("enter the number of nodes");
		int n=x.nextInt();
		
		double[][] paths=new double[n][n];
		double inf=Double.POSITIVE_INFINITY;
		for(int i=0;i<n;i++)
		{	
			for(int j=0;j<n;j++)
			{	System.out.println("enter element "+i+" "+j);
				double d=x.nextDouble();
				if(d==0)
				{
					if(i==j)
					{
						paths[i][j]=d;
					}
					
					else
					{
						paths[i][j]=inf;
					}
				}
				else
				{
					paths[i][j]=d;
				}
			}
		}
		
		func(paths,n);
		System.out.println("the shortest paths are");
		for(double[] i:paths)
		{
			System.out.println(Arrays.toString(i));
		}
	}
}

			
		
		
