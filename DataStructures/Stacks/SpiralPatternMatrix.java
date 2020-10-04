
import java.util.*;
public class spiral_pattern {
      
	     public static void main(String args[])
	     {
	    	 int t;
	    	 Scanner s = new Scanner(System.in);
	    	 t = s.nextInt();
	    	 while(t-->0)
	    	 {
	    		 int n = s.nextInt();
	    		 int m = s.nextInt();
	    		 int a[][] = new int[n][m];
	    		 int i,j,k=0,l=0;
	    		 for(i=0 ; i<n ; i++)
	    		 {
	    			 for(j=0 ; j<m  ; j++)
	    			  a[i][j] = s.nextInt();
	    		 }
	    		 while(k < m && l< n)
	    		 {
	    			 for(i=k ; i<m ; i++)
	    				   System.out.print(a[l][i]+" ");
	    			 l++;
	    			 for(i=l ; i<n ; i++)
	    				  System.out.print(a[i][m-1]+" ");
	    			 m--;
	    			 
	    			 if(l < n)
	    			 {
	    				 for(i=m-1 ; i>=k ; i--)
	    				 {
	    					 System.out.print(a[n-1][i]+" ");
	    				 }
	    				 n--;
	    			 }
	    			 if(k < m)
	    			 {
	    				 for(i=n-1 ; i>=l ; i--)
	    				 {
	    					 System.out.print(a[i][k]+" ");
	    				 }
	    				 k++;
	    			 }
	    			

	    		 }
                          System.out.println();
                         	    			
	    		 
	    	 }
	     }
}
