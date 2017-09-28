package datastructures.matrix;

/**
* matrix data-type.
*
* @author Kyler Smith, 2017
*/


public class Matrix {

    /**
     *  Data needs to be a deep copy as not to change the original state.
     */
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
    * Returns the number of rows in the matrix
    *
    * @return rows
    */
    public int getRows() {
    	if(this.data == null)
    		return 0;

        return data.length;
    }

    /**
    * Returns the number of rows in the matrix
    *
    * @return columns
    */
    public int getColumns() {
    	if(this.data == null)
    		return 0;
        return data[0].length;
    }

    /**
	* Returns this matrix scaled by a factor. That is, computes sA where s is a
	* constant and A is a matrix (this object).
	*
	* @param scalar : value to scale by
	* @return A new matrix scaled by the scalar value
	*/
    public Matrix scale(int scalar) {
    	int[][] newData = new int[this.data.length][this.data[0].length];
		for (int i = 0; i < this.getRows(); ++i)
			for(int j = 0; j < this.getColumns(); ++j)
				newData[i][j] = this.data[i][j] * scalar;
		return new Matrix(newData);
    }

    /**
    * Adds this matrix to another matrix.
    *
    * @param other : matrix to be added
    * @return addend
    */
    public Matrix plus(Matrix other) throws RuntimeException {
    	int[][] newData = new int[this.data.length][this.data[0].length];
    	if(this.getRows() != other.getRows() || this.getColumns() != other.getColumns())
			throw new RuntimeException("Not the same size matrix.");
    	for (int i = 0; i < this.getRows(); ++i)
			for(int j = 0; j < this.getColumns(); ++j)
				newData[i][j] = this.data[i][j] + other.getElement(i, j);
        return new Matrix(newData);
    }

    /**
    * Subtracts this matrix from another matrix.
    *
    * @param other : matrix to be subtracted
    * @return difference
    */
    public Matrix minus(Matrix other) throws RuntimeException {

    	int[][] newData = new int[this.data.length][this.data[0].length];

    	if(this.getRows() != other.getRows() || this.getColumns() != other.getColumns())
			throw new RuntimeException("Not the same size matrix.");

    	for (int i = 0; i < this.getRows(); ++i)
			for(int j = 0; j < this.getColumns(); ++j)
				newData[i][j] = this.data[i][j] - other.getElement(i, j);

        return new Matrix(newData);
    }

    /**
    * Checks if the matrix passed is equal to this matrix
    *
	* @param other : the other matrix
	* @return boolean
    */
    public boolean equals(Matrix other) {
        return this == other;
    }

    /**
    * Returns the matrix as a String in the following format
    *
    * [ a b c ] ...
    * [ x y z ] ...
    * [ i j k ] ...
    *    ...
    *
    * @return matrix as String
	* TODO: Work formatting for different digit sizes
    */
    public String toString() {
        String str = "";

        for(int i = 0; i < this.data.length; i++) {
        	str += "[ ";
            for(int j = 0; j < this.data[0].length; j++) {
            	str += data[i][j];
            	str += " ";
            }
            str += "]";
            str += "\n";
        }

        return str;
    }
}
