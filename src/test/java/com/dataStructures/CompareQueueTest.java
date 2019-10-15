package com.dataStructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class CompareQueueTest {
    @Test
    void testIntSubarrayWindow() {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(2, 3, 1, 5, 4, 7, 8, 9, 3, 2, 1));

        int window_length = 2;

        // for each [l, r] sub-array of length ${window_length} where 0 < l <= r < ${arr.size()},
        // ${q.element()} should be the minimum in that sub-array.

        ArrayList<Integer> raw_results = new ArrayList<>(Arrays.asList(2, 1, 1, 4, 4, 7, 8, 3, 2, 1));

        CompareQueue<Integer> q = new CompareQueue<>(null);

        for (int i = 0; i < window_length; ++i) q.add(arr.get(i));

        Assertions.assertEquals(q.element(), raw_results.get(0));

        for (int i = window_length, result_index = 1; i < arr.size(); ++i, ++result_index) {
            q.add(arr.get(i));
            q.remove();
            Assertions.assertEquals(q.element(), raw_results.get(result_index));
        }
    }

    @Test
    void testEmpty() {
        CompareQueue<String> q = new CompareQueue<>();
        Assertions.assertEquals(q.peek(), null);
    }

    private class IntegerGreater implements Comparator<Integer> {
		@Override
		public int compare(Integer x1, Integer x2) {
			return x2.compareTo(x1);
		}
    }

    @Test
    void testIntReversedWholeArray() {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(2, 3, 1, 5, 4, 7, 8, 9, 3, 2, 1));

        // every element in arr is sequentially added to the compare queue

        CompareQueue<Integer> q = new CompareQueue<>(new IntegerGreater());

        ArrayList<Integer> raw_results = new ArrayList<>(Arrays.asList(2, 3, 3, 5, 5, 7, 8, 9, 9, 9, 9));

        for (int i = 0; i < arr.size(); ++i) {
            q.add(arr.get(i));
            Assertions.assertEquals(q.peek(), raw_results.get(i));
        }
    }

    @Test
    void testString() {
        CompareQueue<String> q = new CompareQueue<>();

        q.add("test");
        Assertions.assertEquals(q.element(), "test");

        q.add("t");
        Assertions.assertEquals(q.element(), "t");

        q.add("o");
        Assertions.assertEquals(q.element(), "o");

        q.add("u");
        Assertions.assertEquals(q.element(), "o");

        q.remove();
        q.remove();
        q.remove();

        Assertions.assertEquals(q.element(), "u");
    }
}