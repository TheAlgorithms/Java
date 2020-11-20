package Misc;
import java.util.*;
public class matrixTranspose {
/** @author RajatJain */
// It takes input from user and transpose the matrix
	public static void main(String[] args) {
		//Declartion of array
		int a[][]=new int[3][3];
        
		Scanner sc=new Scanner(System.in);
		// Declartion of variables 
		int i,j;
		System.out.println("Take input for forming a matrix");
		for(i=0;i<3;i++) {
			for(j=0;j<3;j++) 
			{
// Take input from user
				a[i][j]=sc.nextInt();
			} 
	         	}
		System.out.println("Matrix before transpose:");
		for(i=0;i<3;i++) {
			for(j=0;j<3;j++) 
			{
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("Matrix after transpose:");
		for(i=0;i<3;i++) {
			for(j=0;j<3;j++) 
			{
				System.out.print(a[j][i]+" ");
			}
			System.out.println();
		}	
	}
}