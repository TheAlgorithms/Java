package Maths;

public class NthFibonacciNumber {
    public static int nthFibo(int n) {
        if (n == 1) {
            return 0;
        }

        int a = 0;
        int b = 1;
        while (--n > 1) {
            int c = b;
            b += a;
            a = c;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(nthFibo(6));
    }
}
