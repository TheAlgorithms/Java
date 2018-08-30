import java.util.*;

/**
 *
 * Java implementation of Matrix fast power
 * It can calculate the high power of constant Matrix with O( log(K) )
 * where K is the power of the Matrix
 *
 * In order to do that, Matrix must be square Matrix ( columns equals rows)
 *
 * Notice : large power of Matrix may cause overflow
 *
 *
 * other Matrix basic operator is based on  @author Kyler Smith, 2017
 *
 * @author DDullahan, 2018
 * 
 */

class Matrix {
    private int[][] data;

    /**
     * Constructor for the matrix takes in a 2D array
     *
     * @param pData
     */
    public Matrix(int[][] pData) {

        /** Make a deep copy of the data */
        if(pData.length != 0) {
            int[][] newData = new int[pData.length][pData[0].length];

            for(int i = 0; i < pData.length; i++)
                for(int j = 0; j < pData[0].length; j++)
                    newData[i][j] = pData[i][j];

            this.data = newData;
        } else {
            this.data = null;
        }
    }

    /**
     * Returns the element specified by the given location
     *
     * @param x : x cooridinate
     * @param y : y cooridinate
     * @return int : value at location
     */
    public int getElement(int x, int y) {
        return data[x][y];
    }

    /**
     * Returns the number of rows in the Matrix
     *
     * @return rows
     */
    public int getRows() {
        if(this.data == null)
            return 0;

        return data.length;
    }

    /**
     * Returns the number of rows in the Matrix
     *
     * @return columns
     */
    public int getColumns() {
        if(this.data == null)
            return 0;

        return data[0].length;
    }

    /**
     * Multiplies this matrix with another matrix.
     *
     * @param other : Matrix to be multiplied with
     * @return product
     */
    public Matrix multiply(Matrix other) throws RuntimeException {

        int[][] newData = new int[this.data.length][other.getColumns()];

        if(this.getColumns() != other.getRows())
            throw new RuntimeException("The two matrices cannot be multiplied.");

        int sum;

        for (int i = 0; i < this.getRows(); ++i)
            for(int j = 0; j < other.getColumns(); ++j) {
                sum = 0;

                for(int k = 0; k < this.getColumns(); ++k) {
                    sum += this.data[i][k] * other.getElement(k, j);
                }

                newData[i][j] = sum;
            }

        return new Matrix(newData);
    }

    /**
     * Matrix Fast Power
     *
     * @param k : power of Matrix
     * @return product
     */
    public Matrix MatrixFastPower(int k) throws RuntimeException {

        if(this.getColumns() != this.getRows())
            throw new RuntimeException("Matrix is not square Matrix.");

        int[][] newData = new int[this.getColumns()][this.getRows()];

        for(int i = 0; i < this.getColumns(); i++)
            newData[i][i] = 1;

        Matrix newMatrix = new Matrix(newData),
        coMatrix = new Matrix(this.data);

        while(k != 0) {

            if((k & 1) != 0)
                newMatrix = newMatrix.multiply(coMatrix);

            k >>= 1;
            coMatrix = coMatrix.multiply(coMatrix);

        }

        return newMatrix;
    }
}
