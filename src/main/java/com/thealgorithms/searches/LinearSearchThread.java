package com.thealgorithms.searches;

import java.util.Scanner;

public class LinearSearchThread {
    public static void main(String[] args) {
        int[] list = new int[200];
        for (int j = 0; j < list.length; j++) {
            list[j] = (int) (Math.random() * 100);
        }
        for (int y : list) {
            System.out.print(y + " ");
        }
        System.out.println();
        System.out.print("Enter number to search for: ");
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        Searcher t = new Searcher(list, 0, 50, x);
        Searcher t1 = new Searcher(list, 50, 100, x);
        Searcher t2 = new Searcher(list, 100, 150, x);
        Searcher t3 = new Searcher(list, 150, 200, x);
        t.start();
        t1.start();
        t2.start();
        t3.start();
        try {
            t.join();
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
        }
        boolean found = t.getResult() || t1.getResult() || t2.getResult() || t3.getResult();
        System.out.println("Found = " + found);
    }
}

class Searcher extends Thread {
    private final int[] arr;
    private final int left, right;
    private final int x;
    private boolean found;

    Searcher(int[] arr, int left, int right, int x) {
        this.arr = arr;
        this.left = left;
        this.right = right;
        this.x = x;
    }

    @Override
    public void run() {
        int k = left;
        found = false;
        while (k < right && !found) {
            if (arr[k++] == x) {
                found = true;
            }
        }
    }

    boolean getResult() {
        return found;
    }
}
