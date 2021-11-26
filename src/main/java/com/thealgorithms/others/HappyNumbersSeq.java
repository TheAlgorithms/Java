package com.thealgorithms.others;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HappyNumbersSeq {
    private static final Set<Integer> CYCLE_NUMS = new HashSet<>(Arrays.asList(4, 16, 20, 37, 58, 145));

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter number: ");
        int n = in.nextInt();
        while (n != 1 && !isSad(n)) {
            System.out.print(n + " ");
            n = sumSquares(n);
        }
        String res = n == 1 ? "1 Happy number" : "Sad number";
        System.out.println(res);
    }

    private static int sumSquares(int n) {
        int s = 0;
        for (; n > 0; n /= 10) {
            int r = n % 10;
            s += r * r;
        }
        return s;
    }

    private static boolean isSad(int n) {
        return CYCLE_NUMS.contains(n);
    }
}
