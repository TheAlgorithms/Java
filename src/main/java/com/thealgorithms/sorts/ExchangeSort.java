package com.thealgorithms.sorts;

public class ExchangeSort {
    public int[] sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {

            for (int j = i + 1; j < arr.length; j++) {

                if (arr[i] > arr[j]) {

                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] array = {22, 45, 78, 70, 15, 1, 0, 99};

        ExchangeSort exchangeSort = new ExchangeSort();
        array = exchangeSort.sort(array);

        for (int n : array) {
            System.out.print(n + " ");
        }
    }
}
