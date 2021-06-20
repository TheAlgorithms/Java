/**
 * @author Joseph Acevedo
 * @file MatrixUtilities
 * @purpose This file provides utilities for math with nxm matrices. The 
 * following are provided methods: 
 * matrixMultiply(double[][] mat_a, double[][] mat_b)
 * 		Takes in two matrices of doubles and attempts to multiply them if they
 * 		are of compatible sizes. If they are not compatible then an
 * 		IllegalArgumentException is thrown
 * matrixTranspose(double[][] mat)
 * 		Transposes the given matrix for any nxm matrix and returns the
 * 		resulting mxn matrix
 */

public class MatrixUtilites {
	
	/**
	 * Multiples 2 matrices together given their dimensions are compatible
	 * @param mat_a A 2-Dimensional matrix to be multiplied
	 * @param mat_b A 2-Dimensional matrix to be multiplied
	 * @return The product of the two matrices if compatible dimensions, 
	 * 			otherwise an IllegalArgumentException is thrown
	 */
	public static double[][] matrixMultiply2D(double[][] mat_a, double[][] mat_b)
	{
		int aRows = mat_a.length;
        int aColumns = mat_a[0].length;
        int bRows = mat_b.length;
        int bColumns = mat_b[0].length;

        if (aColumns != bRows)
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");

        double[][] mat_result = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
        	for (int j = 0; j < bColumns; j++) {
        		mat_result[i][j] = 0.00000;
        	}
        }

        for (int i = 0; i < aRows; i++) { // aRow
        	for (int j = 0; j < bColumns; j++) { // bColumn
        		for (int k = 0; k < aColumns; k++) { // aColumn
        			mat_result[i][j] += mat_a[i][k] * mat_b[k][j];
                }
            }
        }
        return mat_result;
	}

	/**
	 * @param mat The matrix to transpose
	 * @return The transposed matrix, or null if the matrix is invalid
	 */
	public static double[][] matrixTranspose2D(double[][] mat)
	{
		if (mat.length == 0) {
			return null;
		}
		
		double[][] result = new double[mat[0].length][mat.length];
		
		for(int x = 0; x < mat.length; x++)
		{
			for(int y = 0; y < mat[0].length; y++)
			{
				result[y][x] = mat[x][y];
			}
		}
		return result;
	}
}
