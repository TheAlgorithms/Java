import java.util.Scanner;

final class sparse {
    private sparse() {
    }

    static double sparsity(int[][] mat, int rows, int cols, int zero) {
        int size = rows * cols;
        return (double) zero / size;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of rows in matrix: ");
        int n = in.nextInt();
        System.out.println("Enter number of columns in matrix: ");
        int m = in.nextInt();

        int[][] mat = new int[n][m];
        int count = 0; // Counter for zeros

        System.out.println("Enter Matrix elements: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = in.nextInt();
                if (mat[i][j] == 0) {
                    count++; // Increment zero count directly
                }
            }
        }

        double sparsityValue = sparsity(mat, n, m, count);
        System.out.println("Sparsity of the matrix is: " + sparsityValue);
    }
}
