package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice, key;

        HashMap h = new HashMap(7);
        Scanner In = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your Choice :");
            System.out.println("1. Add Key");
            System.out.println("2. Delete Key");
            System.out.println("3. Print Table");
            System.out.println("4. Exit");

            choice = In.nextInt();

            switch (choice) {
                case 1:
                    {
                        System.out.println("Enter the Key: ");
                        key = In.nextInt();
                        h.insertHash(key);
                        break;
                    }
                case 2:
                    {
                        System.out.println("Enter the Key delete:  ");
                        key = In.nextInt();
                        h.deleteHash(key);
                        break;
                    }
                case 3:
                    {
                        System.out.println("Print table");
                        h.displayHashtable();
                        break;
                    }
                case 4:
                    {
                        In.close();
                        return;
                    }
            }
        }
    }
}
