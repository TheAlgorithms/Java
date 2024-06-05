package com.thealgorithms.others;

import java.util.Scanner;

final class Krishnamurthy {
    private Krishnamurthy() {
    }

    static int fact(int n) {
        int i;
        int p = 1;
        for (i = n; i >= 1; i--) {
            p = p * i;
        }
        return p;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a;
        int b;
        int s = 0;
        System.out.print("Enter the number : ");
        a = sc.nextInt();
        int n = a;
        while (a > 0) {
            b = a % 10;
            s = s + fact(b);
            a = a / 10;
        }
        if (s == n) {
            System.out.print(n + " is a krishnamurthy number");
        } else {
            System.out.print(n + " is not a krishnamurthy number");
        }
        sc.close();
    }
}
