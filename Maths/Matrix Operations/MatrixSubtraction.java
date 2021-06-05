import java.util.Scanner;
 
public class Matrix_Subtraction {
	
	Scanner scan;
	int matrix1[][], matrix2[][], sub[][];
	int row, column;
 
	void create() {
		
		scan = new Scanner(System.in);
		
		System.out.println("Matrix Subtraction");
		
		// First Matrix Creation..
		System.out.println("\nEnter number of rows & columns");
		row = Integer.parseInt(scan.nextLine());
		column = Integer.parseInt(scan.nextLine());
		
		matrix1 = new int[row][column];
		matrix2 = new int[row][column];
		sub = new int[row][column];
 
		System.out.println("Enter the data for first matrix :");
 
		for(int i=0; i<row; i++) {
			
			for(int j=0; j<column; j++) {
				
				matrix1[i][j] = scan.nextInt();
			}
		}
		
		// Second Matrix Creation..
		System.out.println("Enter the data for second matrix :");
 
		for(int i=0; i<row; i++) {
			
			for(int j=0; j<column; j++) {
				
				matrix2[i][j] = scan.nextInt();
			}
		}
	}
	
	void display() {
		
		System.out.println("\nThe First Matrix is :");
		
		for(int i=0; i<row; i++) {
			
			for(int j=0; j<column; j++) {
				
				System.out.print("\t" + matrix1[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("\n\nThe Second Matrix is :");
		
		for(int i=0; i<row; i++) {
			
			for(int j=0; j<column; j++) {
				
				System.out.print("\t" + matrix2[i][j]);
			}
			System.out.println();
		}
	}
	
	void sub() {
		
		for(int i=0; i<row; i++) {
			
			for(int j=0; j<column; j++) {
				
				sub[i][j] = matrix1[i][j] - matrix2[i][j];
			}
		}
		
		System.out.println("\n\nThe Subtraction is :");
		
		for(int i=0; i<row; i++) {
			
			for(int j=0; j<column; j++) {
				
				System.out.print("\t" + sub[i][j]);
			}
			System.out.println();
		}
	}
}
 
class MainClass {
	
	public static void main(String args[]) {
		
		Matrix_Subtraction obj = new Matrix_Subtraction();
		
		obj.create();
		obj.display();
		obj.sub();
	}
}
