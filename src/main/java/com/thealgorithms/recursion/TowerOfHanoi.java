package com.thealgorithms.recursion;

/*Tower of Hanoi Problem on Wikipedia - https://en.wikipedia.org/wiki/Tower_of_Hanoi */

public final class TowerOfHanoi {
    private TowerOfHanoi(){}
    public static void solveHanoi(int n, char src, char dest, char aux) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + src + " to " + dest);
            return;
        }
        solveHanoi(n - 1, src, aux, dest);
        System.out.println("Move disk " + n + " from " + src + " to " + dest);
        solveHanoi(n - 1, aux, dest, src);
    }
}
