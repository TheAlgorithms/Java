

import java.util.*;
public class matrix_Add
{
	public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
        String title = "Length for first array";
        System.out.println(title.toUpperCase());
        System.out.println("Number of rows for 1st array");
        int rows_1 = s.nextInt();//This variables will be used to define number of rows for both arrays
        System.out.println("Number of columns for 1st array");
        int columns_1 = s.nextInt();//This variables will be used to define number of columns for both arrays
        int array_1[][] = new int[rows_1][columns_1];//this is first array which will contain our first matrix
       
        int array_2[][] = new int[rows_1][columns_1];//this is second array which will contain our first matrix
//LENGTH FOR BOTH ARRAYS WOULD BE SAME 
        System.out.println("Values for first array");
        //This loop will be used to give values to our first array or first matrix
        for(int i = 0;i<array_1.length;i++) {
        	for(int j = 0;j<array_1.length;j++) {
        		array_1[i][j] = s.nextInt();
        	}
        		
        }
        //This loop will be used to give values to our second array or second matrix
        System.out.println("Values for second array");
        for(int i = 0;i<array_2.length;i++) {
        	for(int j = 0;j<array_2.length;j++) {
        		array_2[i][j] = s.nextInt();
        	}
        		
        }
       
       
     //This loop will be adding both matrix values and printing it at the same time 
System.out.println("Addition for both matrix or array would be...");
        for(int i = 0;i<array_1.length;i++) {
        	for(int j = 0;j<array_1.length;j++) {
        		array_1[i][j] = array_1[i][j] + array_2[i][j];
        		System.out.print(array_1[i][j] + " ");
        	}
        	System.out.println();
        		
        }
	}

}
