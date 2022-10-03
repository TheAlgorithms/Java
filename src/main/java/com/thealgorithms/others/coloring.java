import java.util.Scanner;
import java.util.Arrays;

class coloring
{
	static boolean safecoloring(int[][] graph, int[] colors, int V, int i, int j)  //we check if color j can be assigned to node the  i
	{
		for(int k=0;k<V;k++)
		{
			if(graph[i][k]==1 && colors[k]==j)               //there is an adjacent node with color j, so not safe to assign
			{
				return false;
			}
		}
		
		return true;                                          // there is no adjacent node with color j.
	}

	
	static boolean func(int[][] graph, int[] colors, int V, int m,int i)       // V is the number of verices, i gives the no.of colored verices so far. 
	{
		if(i==V)                                                            
		{
			return true;
		}
		
		for(int j=0;j<V;j++)
		{
			if(safecoloring(graph,colors,V,i,j))                           //check if color j can be safely assigned to vertex i
			{
				colors[i]=j;                                             //if yes, then assign color j to vertex i
				if (func(graph,colors,V,m,i+1))                          //see if the next node can also be colored safely(dynamic programming principle)
				{
					return true;                                      
				}

				colors[i]=-1;                                           // if the next node cannot be colored safely, then backtrack and de assign color j to vertex i
			}

		}
		
		return false;
	}


	public static void main(String[] args)
	{
		Scanner x=new Scanner(System.in);
		int V=x.nextInt();
		int[][] graph=new int[V][V];                                     //adjacency matrix declaration

		for(int i=0;i<V;i++)
		{
			for(int j=0;j<V;j++)
			{	
				System.out.println("enter edge "+i+" "+j);          //initializing adjacency matrix
				graph[i][j]=x.nextInt();
			}
		
		}

		int[] colors=new int[V];                                         // color of each node.
		for(int i=0;i<V;i++)
		{
			colors[i]=-1;                                             // no color given initially, so assign '-1' to all nodes.
		}
		
		System.out.println("enter the no.of colors availible to paint");
		int m=x.nextInt();                                             
		boolean result=func(graph,colors,V,m,0);                           //returns true if the V verices can be colored with 'm' colors, 0 means that coloring is yet to begin.     
		System.out.println(result);
		System.out.println(Arrays.toString(colors));                       // print the array with assigned colors.
	}
}
		
				
		
