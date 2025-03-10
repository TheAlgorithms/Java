package com.thealgorithms.others;

import java.util.Scanner;

public final class InsertDeleteInArray {
    private InsertDeleteInArray() {
    }

    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("Enter the size of the array");
            int size = s.nextInt();
            int[] a = new int[size];
            int i;

            // To enter the initial elements
            for (i = 0; i < size; i++) {
                System.out.println("Enter the element");
                a[i] = s.nextInt();
            }

            // To insert a new element(we are creating a new array)
            System.out.println("Enter the index at which the element should be inserted");
            int insertPos = s.nextInt();
            System.out.println("Enter the element to be inserted");
            int ins = s.nextInt();
            int size2 = size + 1;
            int[] b = new int[size2];
            for (i = 0; i < size2; i++) {
                if (i <= insertPos) {
                    b[i] = a[i];
                } else {
                    b[i] = a[i - 1];
                }
            }
            b[insertPos] = ins;
            for (i = 0; i < size2; i++) {
                System.out.println(b[i]);
            }

            // To delete an element given the index
            System.out.println("Enter the index at which element is to be deleted");
            int delPos = s.nextInt();
            for (i = delPos; i < size2 - 1; i++) {
                b[i] = b[i + 1];
            }
            for (i = 0; i < size2 - 1; i++) {
                System.out.println(b[i]);
            }
        }
    }
}
