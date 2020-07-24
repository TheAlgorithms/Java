package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixChainMultiplication {
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<Matrix> mArray = new ArrayList<>();
    private static int size;
    private static int[][] m;
    private static int[][] s;
    private static int[] p;

    public static void main(String[] args) {
        int count = 1;
        while (true) {
            String[] mSize = input("input size of matrix A(" + count + ") ( ex. 10 20 ) : ");
            int col = Integer.parseInt(mSize[0]);
            if (col == 0) break;
            int row = Integer.parseInt(mSize[1]);

            Matrix matrix = new Matrix(count, col, row);
            mArray.add(matrix);
            count++;
        }
        for (Matrix m : mArray) {
            System.out.format("A(%d)  =  %2d  x  %2d%n", m.count(), m.col(), m.row());
        }

        size = mArray.size();
        m = new int[size + 1][size + 1];
        s = new int[size + 1][size + 1];
        p = new int[size + 1];

        for (int i = 0; i < size + 1; i++) {
            Arrays.fill(m[i], -1);
            Arrays.fill(s[i], -1);
        }

        for (int i = 0; i < p.length; i++) {
            p[i] = i == 0 ? mArray.get(i).col() : mArray.get(i - 1).row();
        }

        matrixChainOrder();
        for (int i = 0; i < size; i++) {
            System.out.print("-------");
        }
        System.out.println();
        printArray(m);
        for (int i = 0; i < size; i++) {
            System.out.print("-------");
        }
        System.out.println();
        printArray(s);
        for (int i = 0; i < size; i++) {
            System.out.print("-------");
        }
        System.out.println();

        System.out.println("Optimal solution : " + m[1][size]);
        System.out.print("Optimal parens : ");
        printOptimalParens(1, size);
    }

    private static void printOptimalParens(int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printOptimalParens(i, s[i][j]);
            printOptimalParens(s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    private static void printArray(int[][] array) {
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                System.out.print(String.format("%7d", array[i][j]));
            }
            System.out.println();
        }
    }

    private static void matrixChainOrder() {
        for (int i = 1; i < size + 1; i++) {
            m[i][i] = 0;
        }

        for (int l = 2; l < size + 1; l++) {
            for (int i = 1; i < size - l + 2; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    private static String[] input(String string) {
        System.out.print(string);
        return (scan.nextLine().split(" "));
    }

}

class Matrix {
    private int count;
    private int col;
    private int row;

    Matrix(int count, int col, int row) {
        this.count = count;
        this.col = col;
        this.row = row;
    }

    int count() {
        return count;
    }

    int col() {
        return col;
    }

    int row() {
        return row;
    }
}
