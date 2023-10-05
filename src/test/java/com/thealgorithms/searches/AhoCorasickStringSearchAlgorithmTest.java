package com.thealgorithms.searches;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AhoCorasickStringSearchAlgorithmTest {

    @Test
    public void testTrial1() {
        String array[] = {"ac", "isw", "cisw", "qs"};
        String text = "acisweqs";

        AhoCorasickStringSearchAlgorithm algorithm = new AhoCorasickStringSearchAlgorithm();
        String ans = algorithm.searchWords(array, array.length, text);

        String expected = "Word ac appears from 0 to 1\nWord isw appears from 2 to 4\nWord cisw appears from 1 to 4\nWord qs appears from 6 to 7\n";
        assertEquals(expected, ans);
    }

    @Test
    public void testTrial2() {
        String array[] = {"ha", "it", "diya", "ars"};
        String text = "harshitchordiya";

        AhoCorasickStringSearchAlgorithm algorithm = new AhoCorasickStringSearchAlgorithm();
        String ans = algorithm.searchWords(array, array.length, text);

        String expected = "Word ha appears from 0 to 1\nWord ars appears from 1 to 3\nWord it appears from 5 to 6\nWord diya appears from 11 to 14\n";
        assertEquals(expected, ans);
    }

    @Test
    public void testTrial3() {
        String array[] = {"av", "mo", "tra", "agha"};
        String text = "mohitraghav";

        AhoCorasickStringSearchAlgorithm algorithm = new AhoCorasickStringSearchAlgorithm();
        String ans = algorithm.searchWords(array, array.length, text);

        String expected = "Word mo appears from 0 to 1\nWord tra appears from 4 to 6\nWord agha appears from 6 to 9\nWord av appears from 9 to 10\n";
        assertEquals(expected, ans);
    }
}
