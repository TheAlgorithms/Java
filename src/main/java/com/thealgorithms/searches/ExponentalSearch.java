package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

class ExponentialSearch implements SearchAlgorithm {

    public static void main(String[] args) {
        Random r = ThreadLocalRandom.current();

        int size = 100;
        int maxElement = 100000;

        Integer[] integers = IntStream.generate(() -> r.nextInt(maxElement)).limit(size).sorted().boxed().toArray(Integer[] ::new);

        // The element that should be found
        int shouldBeFound = integers[r.nextInt(size - 1)];

        ExponentialSearch search = new ExponentialSearch();
        int atIndex = search.find(integers, shouldBeFound);

        System.out.printf("Should be found: %d. Found %d at index %d. An array length %d%n", shouldBeFound, integers[atIndex], atIndex, size);

        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.printf("Found by system method at an index: %d. Is equal: %b%n", toCheck, toCheck == atIndex);
    }

    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        if (array[0] == key) {
            return 0;
        }
        if (array[array.length - 1] == key) {
            return array.length;
        }

        int range = 1;

        while (range < array.length && array[range].compareTo(key) <= -1) {
            range = range * 2;
        }

        return Arrays.binarySearch(array, range / 2, Math.min(range, array.length), key);
    }
}
