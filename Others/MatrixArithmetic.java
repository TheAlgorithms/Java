
/**
 * 
 * @author Daniel Diaz
 * 
 * Given two matrices, matrix A and matrix B, MatrixArithmetic calculates and tests
 * the sum, difference, and product (dot product) of the two matrices.
 *
 */

public class MatrixArithmitic {

	public static void main(String[] args) {
		System.out.println("Arithmetic Matrix Operations\n");
		
		int[][] matrixA = {{1,1,0,1,2}, {2,1,1,1,1}, {1,2,3,1,4}, {3,1,1,2,3}};
		int[][] matrixB = {{2,3,1,5,5}, {2,1,1,2,1}, {2,2,1,3,4}, {1,2,6,2,4}};
						
		Matrix newMatrix = new Matrix(4,5);
		newMatrix.addMatrix(matrixA);
		System.out.println("Matrix A:\n");
		System.out.println(newMatrix);
		System.out.println("Matrix B:\n");
		newMatrix.addMatrix(matrixB);
		System.out.println(newMatrix);
		newMatrix.addMatrix(matrixA);
		
		System.out.println("\nA + B:\n");
		newMatrix.addMatrices(matrixB);
		System.out.println("\nA - B:\n");
		newMatrix.subtractMatrices(matrixB);
		System.out.println("\nA * B:\n");
		newMatrix.multiplyMatrices(matrixB);
	}
}

class Matrix{
	private static int rows, columns;
	private static int[][] matrix;
	
	Matrix(int rows, int columns){
		Matrix.rows = rows;
		Matrix.columns = columns;
		matrix = new int[rows][columns];
	}
	
	public void addMatrix(int[][] matrix) {
		Matrix.matrix = matrix;
	}
	
	public static int getRows() {
		return rows;
	}
	
	public static int getColumns(){
		return columns;
	}
	
	public static void addMatrices(int[][] matrix){
		int[][] resultMatrix = new int[rows][columns];
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				System.out.print((Matrix.matrix[i][j] + matrix[i][j]) + " ");
			}
			System.out.println();
		}		
	}
	
	public static void subtractMatrices(int[][] matrix){
		int[][] resultMatrix = new int[rows][columns];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				System.out.print((Matrix.matrix[i][j] + (-1)*matrix[i][j]) + " ");
			}
			System.out.println();
		}		
	}
	
	public static void multiplyMatrices(int[][] matrix){
		int[][] resultMatrix = new int[rows][columns];
		int sum = 0;
		for(int row = 0; row < rows; row++) {
		  for(int i = 0; i < columns; i++) {
			for(int j = 0; j < columns-1; j++) {
				sum += Matrix.matrix[row][j]*matrix[j][i];
			}
			System.out.print(sum + " ");
			sum = 0;
		  }
		  System.out.println();
		}
	}
	
	public String toString() {
		String output = "";
		for(int i = 0; i < rows; i++) {
	    	for(int j = 0; j < columns; j++) {
	    		output += matrix[i][j] + " ";
	    	}
	    	output += "\n";
		}
		return output;
	}
}
