/*
 Write a java program that created jagged array with 3 rows.
 Ask the user for input for number of columns in each row. Pass the given input 
 values to the array take input from user for the array and print the array.
 Input format: 
 	Enter no. of columns for row 1: 2
 	Enter no. of columns for row 2: 3
 	Enter no. of columns for row 3: 2
 	Enter elements: 1 2 3 4 5 6 6 
 Output: 
	The elements are: 
       		1 2
		3 4 5
		6 6	
 */
import java.util.*;
class jaggedArray{

	public static void main(String [] args){
		
		Scanner sc=new Scanner(System.in);
		
		int arr[][]= new int[3][];

		int c1,c2,c3;
		
		System.out.println("Enter Number of columns for row 1:");
		c1= sc.nextInt();
		arr[0]=new int[c1];
	
		System.out.println("Enter Number of columns for row 2:");
		c2= sc.nextInt();
		arr[1]=new int[c2];
		
		System.out.println("Enter Number of columns for row 3:");
		c3= sc.nextInt();
		arr[2]=new int[c3];
	

		for(int i=0; i<3; i++){
		
			for(int j=0; j<arr[i].length; j++){
			
				System.out.print("Enter element:");
				arr[i][j]=sc.nextInt();
			}
		}
		for(int i=0; i<3; i++){
		
			for(int j=0; j<arr[i].length; j++){
			
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();	
		}

	}
}
