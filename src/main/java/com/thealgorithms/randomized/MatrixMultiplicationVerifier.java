import java.util.Random;

/*
   This class implements the Randomized Matrix Multiplication Verification.
   It generates a random vector and performs verification using Freivalds' Algorithm.
   @author Menil-dev
 */
public final class MatrixMultiplicationVerifier {

    private MatrixMultiplicationVerifier() {
        throw new UnsupportedOperationException("Utility class");
    }

    /*
        It multiplies input matrix with randomized vector.
        @params matrix which is being multiplied currently with random vector
        @params random vector generated for every iteration.

        This basically calculates dot product for every row, which is used to verify whether the product of matrices is valid or not.
        @returns matrix of calculated dot product.
    */
    static int[] multiply(int[][] matrix, int[] vector) {
        int n = vector.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    /*
        Actual function that performs verification function
        @params, all three input matrices of int type, number of iterations
    */
    public static boolean verify(int[][] matrixA, int[][] matrixB, int[][] matrixC, int iterations) {
        if (matrixA.length == 0 || matrixB.length == 0 || matrixC.length == 0
                || matrixA[0].length == 0 || matrixB[0].length == 0 || matrixC[0].length == 0) {
            return matrixA.length == matrixB[0].length
                    && matrixB.length == matrixC.length
                    && matrixC[0].length == matrixA[0].length;
        }

        if (iterations <= 0) {
            throw new IllegalArgumentException("Number of iterations must be positive");
        }

        int n = matrixA.length;
        if (iterations > 2 * n) {
            throw new IllegalArgumentException("Number of iterations should not exceed 2 * n where n is the matrix size");
        }

        Random rand = new Random();
        for (int t = 0; t < iterations; t++) {
            int[] r = new int[n];
            for (int i = 0; i < n; i++) {
                r[i] = rand.nextInt(2);
            }

            int[] matrixBtimesR = multiply(matrixB, r);
            int[] matrixAtimesBtimesR = multiply(matrixA, matrixBtimesR);
            int[] matrixCtimesR = multiply(matrixC, r);

            for (int i = 0; i < n; i++) {
                if (matrixAtimesBtimesR[i] != matrixCtimesR[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
        It multiplies input matrix of double type with randomized vector.
        @params matrix which is being multiplied currently with random vector.
        @params random vector generated for every iteration.

        This basically calculates dot product for every row, which is used to verify whether the product of matrices is valid or not.
    */
    static double[] multiply(double[][] matrix, double[] vector) {
        int n = vector.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    /*
        Actual function that performs the verification.
        @params, all three input matrices of double type, number of iterations
    */
    public static boolean verify(double[][] matrixA, double[][] matrixB, double[][] matrixC, int iterations) {
        if (matrixA.length == 0 || matrixB.length == 0 || matrixC.length == 0
                || matrixA[0].length == 0 || matrixB[0].length == 0 || matrixC[0].length == 0) {
            return matrixA.length == matrixB[0].length
                    && matrixB.length == matrixC.length
                    && matrixC[0].length == matrixA[0].length;
        }

        if (iterations <= 0) {
            throw new IllegalArgumentException("Number of iterations must be positive");
        }

        int m = matrixA.length;
        if (iterations > 2 * m) {
            throw new IllegalArgumentException("Number of iterations should not exceed 2 times m where m is the matrix size");
        }

        Random rand = new Random();
        for (int t = 0; t < iterations; t++) {
            double[] randomizedVector = new double[m];
            for (int i = 0; i < m; i++) {
                randomizedVector[i] = rand.nextInt(2);
            }

            double[] matrixBtimesR = multiply(matrixB, randomizedVector);
            double[] matrixAtimesBtimesR = multiply(matrixA, matrixBtimesR);
            double[] matrixCtimesR = multiply(matrixC, randomizedVector);

            for (int i = 0; i < m; i++) {
                if (Math.abs(matrixAtimesBtimesR[i] - matrixCtimesR[i]) > 1e-9) {
                    return false;
                }
            }
        }
        return true;
    }
}
import java.util.Random;

/*
   This class implements the Randomized Matrix Multiplication Verification.
   It generates a random vector and performs verification using Freivalds' Algorithm.
   @author Menil-dev
 */
public final class MatrixMultiplicationVerifier {

    private MatrixMultiplicationVerifier() {
        throw new UnsupportedOperationException("Utility class");
    }

    /*
        It multiplies input matrix with randomized vector.
        @params matrix which is being multiplied currently with random vector
        @params random vector generated for every iteration.

        This basically calculates dot product for every row, which is used to verify whether the product of matrices is valid or not.
        @returns matrix of calculated dot product.
    */
    static int[] multiply(int[][] matrix, int[] vector) {
        int n = vector.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    /*
        Actual function that performs verification function
        @params, all three input matrices of int type, number of iterations
    */
    public static boolean verify(int[][] matrixA, int[][] matrixB, int[][] matrixC, int iterations) {
        if (matrixA.length == 0 || matrixB.length == 0 || matrixC.length == 0
                || matrixA[0].length == 0 || matrixB[0].length == 0 || matrixC[0].length == 0) {
            return matrixA.length == matrixB[0].length
                    && matrixB.length == matrixC.length
                    && matrixC[0].length == matrixA[0].length;
        }

        if (iterations <= 0) {
            throw new IllegalArgumentException("Number of iterations must be positive");
        }

        int n = matrixA.length;
        if (iterations > 2 * n) {
            throw new IllegalArgumentException("Number of iterations should not exceed 2 * n where n is the matrix size");
        }

        Random rand = new Random();
        for (int t = 0; t < iterations; t++) {
            int[] r = new int[n];
            for (int i = 0; i < n; i++) {
                r[i] = rand.nextInt(2);
            }

            int[] matrixBtimesR = multiply(matrixB, r);
            int[] matrixAtimesBtimesR = multiply(matrixA, matrixBtimesR);
            int[] matrixCtimesR = multiply(matrixC, r);

            for (int i = 0; i < n; i++) {
                if (matrixAtimesBtimesR[i] != matrixCtimesR[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
        It multiplies input matrix of double type with randomized vector.
        @params matrix which is being multiplied currently with random vector.
        @params random vector generated for every iteration.

        This basically calculates dot product for every row, which is used to verify whether the product of matrices is valid or not.
    */
    static double[] multiply(double[][] matrix, double[] vector) {
        int n = vector.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    /*
        Actual function that performs the verification.
        @params, all three input matrices of double type, number of iterations
    */
    public static boolean verify(double[][] matrixA, double[][] matrixB, double[][] matrixC, int iterations) {
        if (matrixA.length == 0 || matrixB.length == 0 || matrixC.length == 0
                || matrixA[0].length == 0 || matrixB[0].length == 0 || matrixC[0].length == 0) {
            return matrixA.length == matrixB[0].length
                    && matrixB.length == matrixC.length
                    && matrixC[0].length == matrixA[0].length;
        }

        if (iterations <= 0) {
            throw new IllegalArgumentException("Number of iterations must be positive");
        }

        int m = matrixA.length;
        if (iterations > 2 * m) {
            throw new IllegalArgumentException("Number of iterations should not exceed 2 times m where m is the matrix size");
        }

        Random rand = new Random();
        for (int t = 0; t < iterations; t++) {
            double[] randomizedVector = new double[m];
            for (int i = 0; i < m; i++) {
                randomizedVector[i] = rand.nextInt(2);
            }

            double[] matrixBtimesR = multiply(matrixB, randomizedVector);
            double[] matrixAtimesBtimesR = multiply(matrixA, matrixBtimesR);
            double[] matrixCtimesR = multiply(matrixC, randomizedVector);

            for (int i = 0; i < m; i++) {
                if (Math.abs(matrixAtimesBtimesR[i] - matrixCtimesR[i]) > 1e-9) {
                    return false;
                }
            }
        }
        return true;
    }
}
