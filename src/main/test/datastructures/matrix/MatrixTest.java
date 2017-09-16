package datastructures.matrix;

import org.junit.Assert;
import org.junit.Test;

public class MatrixTest {

    @Test
    public void test() {
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
        Assert.assertFalse(m2.equals(null));
        Assert.assertFalse(m2.equals("MATRIX"));
        Assert.assertFalse(m2.equals(m1));
        Assert.assertTrue(m2.equals(m2));
        Assert.assertFalse(m2.equals(m3));

        //test operations (valid)
        System.out.println("2 * m2:\n" + m2.scale(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));
    }
}
