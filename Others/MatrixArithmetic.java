
/**
 * 
 * @author Daniel Diaz
 * 
 * Given two matrices, matrix A and matrix B, MatrixArithmetic calculates and prints
 * the sum, difference, and product (dot product) of the two matrices.
 *
 */

public class MatrixArithmetic {

	public static void main(String[] args) {
		System.out.println("Arithmetic Matrix Operations\n");
		
		int[][] matrixA = {{1,1,0,1,2}, {2,1,1,1,1}, {1,2,3,1,4}, {3,1,1,2,3}};
		int[][] matrixB = {{2,3,1,5,5}, {2,1,1,2,1}, {2,2,1,3,4}, {1,2,6,2,4}};
		
		System.out.println("Matrix A:\n");
		MatrixArithmetic.printMatrix(matrixA);
		System.out.println("Matrix B:\n");
		MatrixArithmetic.printMatrix(matrixB);

		int[][] sum = MatrixArithmetic.addMatrices(matrixA, matrixB);
		int[][] sub = MatrixArithmetic.subtractMatrices(matrixA, matrixB);
		int[][] product = MatrixArithmetic.multiplyMatrices(matrixA, matrixB);
		
		System.out.println("A + B:\n");
		MatrixArithmetic.printMatrix(sum);
		System.out.println("A - B:\n");
		MatrixArithmetic.printMatrix(sub);
		System.out.println("A * B:\n");
		MatrixArithmetic.printMatrix(product);
		
	}
	
	public static int[][] addMatrices(int[][] matrix1, int[][] matrix2){
		int[][] resultMatrix = new int[3][4];
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				resultMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
			}
		}
		
		return resultMatrix;
	}
	
	public static int[][] subtractMatrices(int[][] matrix1, int[][] matrix2){
		int[][] resultMatrix = new int[3][4];
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				resultMatrix[i][j] = matrix1[i][j] - matrix2[i][j];
			}
		}
		
		return resultMatrix;
	}
	
	public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2){
		int[][] resultMatrix = new int[3][4];
		int sum = 0;
		for(int row = 0; row < 3; row++) {
		  for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				sum += matrix1[i][j]*matrix2[j][i];
			}
			resultMatrix[row][i] = sum;
			sum = 0;
		  }
		}
		return resultMatrix;
	}
	
	public static void printMatrix(int[][] matrix) {
		for(int i = 0; i < 3; i++) {
	    	for(int j = 0; j < 4; j++) {
	    		System.out.print(matrix[i][j] + " ");
	    	}
	    	System.out.println();
	}
		System.out.println();
  }
}
