package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class GaleShapleyTest {

    @Test
    public void testStableMatch() {
        Map<String, LinkedList<String>> womenPrefs = new HashMap<>();
        womenPrefs.put("A", new LinkedList<>(List.of("X", "Y", "Z")));
        womenPrefs.put("B", new LinkedList<>(List.of("Y", "X", "Z")));
        womenPrefs.put("C", new LinkedList<>(List.of("X", "Y", "Z")));

        Map<String, LinkedList<String>> menPrefs = new HashMap<>();
        menPrefs.put("X", new LinkedList<>(List.of("A", "B", "C")));
        menPrefs.put("Y", new LinkedList<>(List.of("B", "A", "C")));
        menPrefs.put("Z", new LinkedList<>(List.of("A", "B", "C")));

        Map<String, String> result = GaleShapley.stableMatch(womenPrefs, menPrefs);

        Map<String, String> expected = new HashMap<>();
        expected.put("A", "X");
        expected.put("B", "Y");
        expected.put("C", "Z");

        assertEquals(expected, result);
    }

    @Test
    public void testSinglePair() {
        Map<String, LinkedList<String>> womenPrefs = new HashMap<>();
        womenPrefs.put("A", new LinkedList<>(List.of("X")));

        Map<String, LinkedList<String>> menPrefs = new HashMap<>();
        menPrefs.put("X", new LinkedList<>(List.of("A")));

        Map<String, String> result = GaleShapley.stableMatch(womenPrefs, menPrefs);

        Map<String, String> expected = new HashMap<>();
        expected.put("A", "X");

        assertEquals(expected, result);
    }

    @Test
    public void testEqualPreferences() {
        Map<String, LinkedList<String>> womenPrefs = new HashMap<>();
        womenPrefs.put("A", new LinkedList<>(List.of("X", "Y", "Z")));
        womenPrefs.put("B", new LinkedList<>(List.of("X", "Y", "Z")));
        womenPrefs.put("C", new LinkedList<>(List.of("X", "Y", "Z")));

        Map<String, LinkedList<String>> menPrefs = new HashMap<>();
        menPrefs.put("X", new LinkedList<>(List.of("A", "B", "C")));
        menPrefs.put("Y", new LinkedList<>(List.of("A", "B", "C")));
        menPrefs.put("Z", new LinkedList<>(List.of("A", "B", "C")));

        Map<String, String> result = GaleShapley.stableMatch(womenPrefs, menPrefs);

        Map<String, String> expected = new HashMap<>();
        expected.put("A", "X");
        expected.put("B", "Y");
        expected.put("C", "Z");

        assertEquals(expected, result);
    }
}
