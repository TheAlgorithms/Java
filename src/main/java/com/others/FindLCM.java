package com.others;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class depends on FindMultiples.java
 */

public class FindLCM {

    /**
     * This function is to find the LCM of a list of numbers
     *
     * @param numbers a list of numbers
     * @return the product of the LCM (int)
     */
    public static int Of(int[] numbers) {
        ArrayList<ArrayList<Integer>> multiples = new ArrayList<>();

        int result = 1;

        // Map multiples of each numbers in the param to a dictionary
        Dictionary<Integer, Integer> dictionary = new Hashtable<>();

        // Find the multiples of all numbers in the param : numbers
        for (int number : numbers) {
            multiples.add(FindMultiples.Of(number));
        }

        for (ArrayList<Integer> multiple : multiples) {
            for (int number : multiple) {
                AtomicInteger count = new AtomicInteger();
                multiple.forEach(
                        item -> {
                            if (item == number) {
                                count.getAndIncrement();
                            }
                        }
                );

                boolean keyExists = false;

                for (Enumeration<Integer> enumeration = dictionary.keys(); enumeration.hasMoreElements(); ) {
                    int key = enumeration.nextElement();
                    keyExists = key == number;
                    if (keyExists) break;
                }

                if (keyExists) {
                    if (dictionary.get(number) < count.get())
                        dictionary.put(number, count.get());
                } else {
                    dictionary.put(number, count.get());
                }

            }
        }

        for (Enumeration<Integer> enumeration = dictionary.keys(); enumeration.hasMoreElements(); ) {
            int base = enumeration.nextElement();
            int power = dictionary.get(base);

            int tempResult = 1;

            for(int i = 0; i < power; i ++) {
                tempResult *= base;
            }

            result *= tempResult;
        }

        return result;
    }

    /**
     * This function is to find the LCM of a list of numbers
     *
     * @param numbers a list of numbers
     * @return the product of the LCM (int)
     */
    public static int Of(ArrayList<Integer> numbers) {
        ArrayList<ArrayList<Integer>> multiples = new ArrayList<>();

        int result = 1;

        // Map multiples of each numbers in the param to a dictionary
        Dictionary<Integer, Integer> dictionary = new Hashtable<>();

        // Find the multiples of all numbers in the param : numbers
        for (int number : numbers) {
            multiples.add(FindMultiples.Of(number));
        }

        for (ArrayList<Integer> multiple : multiples) {
            for (int number : multiple) {
                AtomicInteger count = new AtomicInteger();
                multiple.forEach(
                        item -> {
                            if (item == number) {
                                count.getAndIncrement();
                            }
                        }
                );

                boolean keyExists = false;

                for (Enumeration<Integer> enumeration = dictionary.keys(); enumeration.hasMoreElements(); ) {
                    int key = enumeration.nextElement();
                    keyExists = key == number;
                    if (keyExists) break;
                }

                if (keyExists) {
                    if (dictionary.get(number) < count.get())
                        dictionary.put(number, count.get());
                } else {
                    dictionary.put(number, count.get());
                }

            }
        }

        for (Enumeration<Integer> enumeration = dictionary.keys(); enumeration.hasMoreElements(); ) {
            int base = enumeration.nextElement();
            int power = dictionary.get(base);

            int tempResult = 1;

            for(int i = 0; i < power; i ++) {
                tempResult *= base;
            }

            result *= tempResult;
        }

        return result;
    }
}
