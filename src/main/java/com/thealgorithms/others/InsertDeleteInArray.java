package com.thealgorithms.others;

import java.util.*;

public class InsertDeleteInArray {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in); // Input statement
        System.out.println("Enter the size of the array");
        int size = s.nextInt();
        int a[] = new int[size];
        //int i; //In java code no need to declare variable before for loop//

        // To enter the initial elements
        System.out.println("Enter the elements of the array"); // Input statement//
        for (int i = 0; i < size; i++) { // i declared in for loop//
            // System.out.println("Enter the element"); 
            //When you initialize array elements then print this statement before the loop, if you don't then every time you enter new elements (Enter the element) this message shown every time//
            a[i] = s.nextInt();
        }

        // To insert a new element(we are creating a new array)
        System.out.println("Enter the index at which the element should be inserted");  
        int insert_pos = s.nextInt();
        System.out.println("Enter the element to be inserted");
        int ins = s.nextInt();
        int size2 = size + 1;
        int b[] = new int[size2];
        for (int i = 0; i < size2; i++) { // i declared in for loop//
            if (i <= insert_pos) {
                b[i] = a[i];
            } else {
                b[i] = a[i - 1];
            }
        }
        b[insert_pos] = ins;
        System.out.println("After inserting new element the new array is"); // Output statement//
        for (int i = 0; i < size2; i++) { // i declared in for loop//
            System.out.println(b[i]);
        }

        // To delete an element given the index
        System.out.println("Enter the index at which element is to be deleted");
        int del_pos = s.nextInt();
        for (int i = del_pos; i < size2 - 1; i++) { // i declared in for loop//
            b[i] = b[i + 1];
        }
        for (int i = 0; i < size2 - 1; i++) { // i declared in for loop//
            System.out.println(b[i]);
        }
//        s.close();
    }
}
