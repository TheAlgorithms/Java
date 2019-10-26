package Maths;

public class NthFibonacciNumberMatrix {
    static int a[][] = {{0, 1}, {1, 1}};
    static int b[][] = {{0, 1}, {1, 1}};

    public static int[][] matrixMultiply(int a[][], int b[][]) {
        int c00 = a[0][0] * b[0][0] + a[0][1] * b[1][0];
        int c01 = a[0][0] * b[0][1] + a[0][1] * b[1][1];
        int c10 = a[1][0] * b[0][0] + a[1][1] * b[1][0];
        int c11 = a[1][0] * b[0][1] + a[1][1] * b[1][1];
        int c[][] = {{c00, c01}, {c10, c11}};
        return c;
    }

    public static int[][] multiplyWrapper(int n) {
        if (n == 1) {
            return a;
        }

        int c[][] = multiplyWrapper(n / 2);
        c = matrixMultiply(c, c);
        if (n % 2 == 1) {
            c = matrixMultiply(c, a);
        }

        return c;
    }

    public static int nthFibo(int n) {
        int c[][] = multiplyWrapper(n);
        return c[0][0];
    }

    public static void main(String[] args) {
        System.out.println(nthFibo(8));
    }
}
