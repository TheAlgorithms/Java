package com.thealgorithms.datastructures.trees;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Main {

    public static void main(String[] args) {
        NRKTree root = BuildTree();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number: ");
        int inputX0 = sc.nextInt();
        int toPrint = nearestRightKey(root, inputX0);
        System.out.println("Key: " + toPrint);
    }

    public static NRKTree BuildTree() {
        int randomX = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        NRKTree root = new NRKTree(null, null, randomX);

        for (int i = 0; i < 1000; i++) {
            randomX = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            root = root.insertKey(root, randomX);
        }

        return root;
    }

    public static int nearestRightKey(NRKTree root, int x0) {
        //Check whether tree is empty
        if (root == null) {
            return 0;
        } else {
            if (root.data - x0 > 0) {
                // Go left
                int temp = nearestRightKey(root.left, x0);
                if (temp == 0) {
                    return root.data;
                }
                return temp;
            } else {
                // Go right
                return nearestRightKey(root.right, x0);
            }

        }
    }

}

class NRKTree {

    public NRKTree left;
    public NRKTree right;
    public int data;

    public NRKTree(int x) {
        this.left = null;
        this.right = null;
        this.data = x;
    }

    public NRKTree(NRKTree right, NRKTree left, int x) {
        this.left = left;
        this.right = right;
        this.data = x;
    }

    public NRKTree insertKey(NRKTree current, int value) {
        if (current == null) {
            return new NRKTree(value);
        }

        if (value < current.data) {
            current.left = insertKey(current.left, value);
        } else if (value > current.data) {
            current.right = insertKey(current.right, value);
        }

        return current;
    }
}
