package com.thealgorithms.maths;

import java.util.Scanner;

/*A magic square of order n is an arrangement of distinct n^2 integers,in a square, such that the n
numbers in all rows, all columns, and both diagonals sum to the same constant. A magic square
contains the integers from 1 to n^2.*/
public final class MagicSquare {
    private MagicSquare() {
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input a number: ");
        int num = sc.nextInt();
        if ((num % 2 == 0) || (num <= 0)) {
            System.out.print("Input number must be odd and >0");
            System.exit(0);
        }

        int[][] magicSquare = new int[num][num];

        int rowNum = num / 2;
        int colNum = num - 1;
        magicSquare[rowNum][colNum] = 1;

        for (int i = 2; i <= num * num; i++) {
            if (magicSquare[(rowNum - 1 + num) % num][(colNum + 1) % num] == 0) {
                rowNum = (rowNum - 1 + num) % num;
                colNum = (colNum + 1) % num;
            } else {
                colNum = (colNum - 1 + num) % num;
            }
            magicSquare[rowNum][colNum] = i;
        }

        // print the square
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (magicSquare[i][j] < 10) {
                    System.out.print(" ");
                }
                if (magicSquare[i][j] < 100) {
                    System.out.print(" ");
                }
                System.out.print(magicSquare[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
