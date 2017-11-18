import java.util.*;

/**
 * Program to perform Saddleback Search
 * Given a sorted 2D array(elements are sorted across every row and column, assuming ascending order)
 * of size n*m we can search a given element in O(n+m) 
 * 
 * we start from bottom left corner
 * if the current element is greater than the given element then we move up
 * else we move right
 * Sample Input:
 * 5 5 ->Dimensions
 * -10 -5 -3 4 9
 * -6 -2 0 5 10
 * -4 -1 1 6 12
 * 2 3 7 8 13
 * 100 120 130 140 150
 * 140 ->element to be searched
 * output: 4 3 // first value is row, second one is column
 * 
 * @author Nishita Aggarwal
 *
 */

public class SaddlebackSearch {
	
	/**
	* This method performs Saddleback Search
	* 
     	* @param arr The **Sorted** array in which we will search the element.
	* @param crow the current row.
     	* @param ccol the current column.
	* @param ele the element that we want to search for.
     	* 
	* @return The index(row and column) of the element if found.
     	* Else returns -1 -1.
     	*/
	static int[] search(int arr[][],int crow,int ccol,int ele){
		
		//array to store the answer row and column
		int ans[]={-1,-1};
		if(crow<0 || ccol>=arr[crow].length){
			return ans;
		}
		if(arr[crow][ccol]==ele)
		{
			ans[0]=crow;
			ans[1]=ccol;
			return ans;
		}
		//if the current element is greater than the given element then we move up
		else if(arr[crow][ccol]>ele)
		{
			return search(arr,crow-1,ccol,ele);
		}
		//else we move right
		return search(arr,crow,ccol+1,ele);
	}
	
	/**
	 * Main method
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int arr[][];
		int i,j,rows=sc.nextInt(),col=sc.nextInt();
		arr=new int[rows][col];
		for(i=0;i<rows;i++)
		{
			for(j=0;j<col;j++){
				arr[i][j]=sc.nextInt();
			}
		}
		int ele=sc.nextInt();
		//we start from bottom left corner
		int ans[]=search(arr,rows-1,0,ele);
		System.out.println(ans[0]+" "+ans[1]);
		sc.close();
	}

}
