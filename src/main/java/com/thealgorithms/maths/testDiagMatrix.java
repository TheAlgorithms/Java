package com.thealgorithms.maths;


import java.util.Arrays;



public class testDiagMatrix
{
    public static void main(String[] args) throws Exception {
        double[][] testMat = {{2,3}, {4,7}};
        System.out.println(Arrays.deepToString(MatrixDiag2x2.getDiagonalMatrix(testMat)));

    }
}