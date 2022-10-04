package com.thealgorithms.maths;

import java.util.Scanner;

public class RamanujanSeries {

    private static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        int n, count = 0, count1;
        System.out.println("Enter the limit: ");
        n = s.nextInt();
        int i = 1;
        while (count < n) {
            count1 = 0;
            for (int j = 1; j <= Math.pow(i, 1.0 / 3); j++) {
                for (int k = j + 1; k <= Math.pow(i, 1.0 / 3); k++) {
                    if (j * j * j + k * k * k == i)
                        count1++;
                }
            }
            if (count1 == 2) {
                count++;
                System.out.println("Ramanujan Series :");
                System.out.println(i);
            }
            i++;
        }
    }
}
