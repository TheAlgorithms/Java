import java.util.Random;

/*
   This class implements the Randomized Matrix Multiplication Verification.
   It generates a random vector and performs verification using Freivalds' Algorithm.
   @author: Menil-dev
 */
public class MatrixMultiplicationVerifier {

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
    public static boolean verify(int[][] A, int[][] B, int[][] C, int iterations) {
        if (A.length == 0 || B.length == 0 || C.length == 0 || A[0].length == 0 || B[0].length == 0 || C[0].length == 0) {
            return A.length == B[0].length && B.length == C.length && C[0].length == A[0].length; // Basic dimension consistency check
        }

        if (iterations <= 0) {
            throw new IllegalArgumentException("Number of iterations must be positive");
        }

        int n = A.length;
        if (iterations > 2 * n) {
            throw new IllegalArgumentException("Number of iterations should not exceed 2 * n where n is the matrix size");
        }

        Random rand = new Random();
        for (int t = 0; t < iterations; t++) {
            int[] r = new int[n];
            for (int i = 0; i < n; i++) {
                r[i] = rand.nextInt(2);
            }

            int[] Br = multiply(B, r);
            int[] ABr = multiply(A, Br);
            int[] Cr = multiply(C, r);

            for (int i = 0; i < n; i++) {
                if (ABr[i] != Cr[i]) {
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
    public static boolean verify(double[][] A, double[][] B, double[][] C, int iterations) {
        if (A.length == 0 || B.length == 0 || C.length == 0 || A[0].length == 0 || B[0].length == 0 || C[0].length == 0) {
            return A.length == B[0].length && B.length == C.length && C[0].length == A[0].length;
        }

        if (iterations <= 0) {
            throw new IllegalArgumentException("Number of iterations must be positive");
        }

        int m = A.length;
        if (iterations > 2 * m) {
            throw new IllegalArgumentException("Number of iterations should not exceed 2 times m where n is the matrix size");
        }

        Random rand = new Random();
        for (int t = 0; t < iterations; t++) {
            double[] randomizedVector = new double[m];
            for (int i = 0; i < m; i++) {
                randomizedVector[i] = rand.nextInt(2); // Random binary values 0 or 1
            }

            double[] Br = multiply(B, randomizedVector);
            double[] ABr = multiply(A, Br);
            double[] Cr = multiply(C, randomizedVector);

            for (int i = 0; i < m; i++) {
                if (Math.abs(ABr[i] - Cr[i]) > 1e-9) {
                    return false;
                }
            }
        }

        return true;
    }
}
