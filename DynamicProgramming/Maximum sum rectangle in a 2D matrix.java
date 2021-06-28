import java.lang.*;
import java.io.*;

class GFG {
	public static void main (String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		while(t-->0)
		{
		    int row=sc.nextInt();
		    int col=sc.nextInt();
		    int mat[][]=new int[row][col];
		    for(int i=0; i<row; i++)
		    for(int j=0; j<col; j++)
		    mat[i][j]=sc.nextInt();
		    
		   System.out.println( maxSR(mat, row, col));
		    
		}
		
	}
	
	static int maxSR(int mat[][], int row, int col)
	{
	   
	    int maxsum=Integer.MIN_VALUE;
	  
	    for(int lef=0; lef<col; lef++)
	    { int sum[]=new int[row];
	    for(int rt=lef; rt<col; rt++ )
	    {
	        
	    for(int i=0; i<row; i++)
	     sum[i]+=mat[i][rt];
	     
	     int ksum=Integer.MIN_VALUE; int currsum=0;
	     for(int i=0; i<row; i++)
	     {
	         currsum+=sum[i];
	         if(currsum<0)
	         currsum=0;
	         else
	         if(ksum<currsum)
	         ksum=currsum;
	     }
	     
	     if(ksum>maxsum)
	     maxsum=ksum;
	    }
	    }
	    return maxsum;
	}
	
}