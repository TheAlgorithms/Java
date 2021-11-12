package com.thealgorithms.maths;

import java.util.*;

/*A magic square of order n is an arrangement of distinct n^2 integers,in a square, such that the n numbers in all
rows, all columns, and both diagonals sum to the same constant. A magic square contains the integers from 1 to n^2.*/
public class MagicSquare {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Input a number: ");
        int num = sc.nextInt();
        if ((num % 2 == 0) || (num <= 0)) {
            System.out.print("Input number must be odd and >0");
            System.exit(0);
        }

        int[][] magic_square = new int[num][num];

        int row_num = num / 2;
        int col_num = num - 1;
        magic_square[row_num][col_num] = 1;

        for (int i = 2; i <= num * num; i++) {
            if (magic_square[(row_num - 1 + num) % num][(col_num + 1) % num] == 0) {
                row_num = (row_num - 1 + num) % num;
                col_num = (col_num + 1) % num;
            } else {
                col_num = (col_num - 1 + num) % num;
            }
            magic_square[row_num][col_num] = i;
        }

        // print the square
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (magic_square[i][j] < 10) {
                    System.out.print(" ");
                }
                if (magic_square[i][j] < 100) {
                    System.out.print(" ");
                }
                System.out.print(magic_square[i][j] + " ");
            }
            System.out.println();
        }

    }
}
