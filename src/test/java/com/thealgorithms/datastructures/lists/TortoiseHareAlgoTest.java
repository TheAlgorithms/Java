package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class TortoiseHareAlgoTest {

    @Test
    void testAppendAndToString() {
        TortoiseHareAlgo<Integer> list = new TortoiseHareAlgo<>();
        list.append(10);
        list.append(20);
        list.append(30);
        assertEquals("[10, 20, 30]", list.toString());
    }

    @Test
    void testGetMiddleOdd() {
        TortoiseHareAlgo<Integer> list = new TortoiseHareAlgo<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        assertEquals(3, list.getMiddle());
    }

    @Test
    void testGetMiddleEven() {
        TortoiseHareAlgo<Integer> list = new TortoiseHareAlgo<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        assertEquals(3, list.getMiddle()); // returns second middle
    }

    @Test
    void testEmptyList() {
        TortoiseHareAlgo<Integer> list = new TortoiseHareAlgo<>();
        assertNull(list.getMiddle());
        assertEquals("[]", list.toString());
    }
}
