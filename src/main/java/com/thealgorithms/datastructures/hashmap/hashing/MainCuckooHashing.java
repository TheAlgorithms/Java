package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.Scanner;

public class MainCuckooHashing {

    public static void main(String[] args) {
        int choice, key;

        HashMapCuckooHashing h = new HashMapCuckooHashing(7);
        Scanner In = new Scanner(System.in);

        while (true) {
            System.out.println("_________________________");
            System.out.println("Enter your Choice :");
            System.out.println("1. Add Key");
            System.out.println("2. Delete Key");
            System.out.println("3. Print Table");
            System.out.println("4. Exit");
            System.out.println("5. Search and print key index");
            System.out.println("6. Check load factor");
            System.out.println("7. Rehash Current Table");

            choice = In.nextInt();

            switch (choice) {
                case 1:
                    {
                        System.out.println("Enter the Key: ");
                        key = In.nextInt();
                        h.insertKey2HashTable(key);
                        break;
                    }
                case 2:
                    {
                        System.out.println("Enter the Key delete:  ");
                        key = In.nextInt();
                        h.deleteKeyFromHashTable(key);
                        break;
                    }
                case 3:
                    {
                        System.out.println("Print table:\n");
                        h.displayHashtable();
                        break;
                    }
                case 4:
                    {
                        In.close();
                        return;
                    }
                case 5:
                    {
                        System.out.println(
                            "Enter the Key to find and print:  "
                        );
                        key = In.nextInt();
                        System.out.println(
                            "Key: " +
                            key +
                            " is at index: " +
                            h.findKeyInTable(key) +
                            "\n"
                        );
                        break;
                    }
                case 6:
                    {
                        System.out.printf(
                            "Load factor is: %.2f\n",
                            h.checkLoadFactor()
                        );
                        break;
                    }
                case 7:
                    {
                        h.reHashTableIncreasesTableSize();
                        break;
                    }
            }
        }
    }
}
