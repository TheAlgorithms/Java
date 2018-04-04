package DataStructures.Matrix;

/**
* Matrix data-type.
*
* @author Kyler Smith, 2017
*/


public class Matrix {

	public static void main(String[] args) {

		int[][] data1 = new int[0][0];
        int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};

        Matrix m1 = new Matrix(data1);
        Matrix m2 = new Matrix(data2);
        Matrix m3 = new Matrix(data3);

        System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());

        //check for reference issues
        System.out.println("m2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        //test equals
        System.out.println("m2==null: " + m2.equals(null));             //false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
        System.out.println("m2==m1: " + m2.equals(m1));                 //false
        System.out.println("m2==m2: " + m2.equals(m2));                 //true
        System.out.println("m2==m3: " + m2.equals(m3));                 //false

        //test operations (valid)
        System.out.println("2 * m2:\n" + m2.scale(2));
        System.out.println("m2 / 2:\n" + m2.divide(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));
        System.out.println("m2 * m3: \n"+m2.multiply(m3));
	}


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
	* Returns this matrix divided by a factor. That is, computes sA where s is a
	* constant and A is a matrix (this object).
	*
	* @param scalar : value to divide by
	* @return A new matrix scaled by the scalar value
	*/
    public Matrix divide(int scalar) {

    	int[][] newData = new int[this.data.length][this.data[0].length];

		for (int i = 0; i < this.getRows(); ++i)
			for(int j = 0; j < this.getColumns(); ++j)
				newData[i][j] = this.data[i][j] / scalar;

		return new Matrix(newData);
    }

    /**
    * Adds this matrix to another matrix.
    *
    * @param other : Matrix to be added
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
    * @param other : Matrix to be subtracted
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
     * Multiplies this matrix with another matrix.
     *
     * @param other : Matrix to be multiplied with
     * @return product
     */
     public Matrix multiply(Matrix other) throws RuntimeException {

     	int[][] newData = new int[this.data.length][other.getColumns()];

     	if(this.getColumns() !=other.getRows())
 			throw new RuntimeException("The two matrices cannot be multiplied.");
     	int sum;
     	for (int i = 0; i < this.getRows(); ++i)
 			for(int j = 0; j < other.getColumns(); ++j){
 				sum = 0;
 				for(int k=0;k<this.getColumns();++k){
 					sum += this.data[i][k] * other.getElement(k, j);
 				}
 				newData[i][j] = sum; 
 			}
 				

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
    * Returns the Matrix as a String in the following format
    *
    * [ a b c ] ...
    * [ x y z ] ...
    * [ i j k ] ...
    *    ...
    *
    * @return Matrix as String
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
	
	/**
    * Returns transposed matrix of this matrix.
    *
    * @return transposed Matrix. 
    */
	public Matrix transpose() {
		
		int[][] newData = new int[this.data[0].length][this.data.length];

		for (int i = 0; i < this.getColumns(); ++i)
			for(int j = 0; j < this.getRows(); ++j)
				newData[i][j] = this.data[j][i];

		return new Matrix(newData);
	}		
}
