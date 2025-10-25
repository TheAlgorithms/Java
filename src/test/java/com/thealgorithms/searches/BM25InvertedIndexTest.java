package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test Cases for Inverted Index with BM25
 * @author Prayas Kumar (https://github.com/prayas7102)
 */

class BM25InvertedIndexTest {

    private static BM25InvertedIndex index;

    @BeforeAll
    static void setUp() {
        index = new BM25InvertedIndex();
        index.addMovie(1, "The Shawshank Redemption", 9.3, 1994, "Hope is a good thing. Maybe the best of things. And no good thing ever dies.");
        index.addMovie(2, "The Godfather", 9.2, 1972, "I'm gonna make him an offer he can't refuse.");
        index.addMovie(3, "The Dark Knight", 9.0, 2008, "You either die a hero or live long enough to see yourself become the villain.");
        index.addMovie(4, "Pulp Fiction", 8.9, 1994, "You know what they call a Quarter Pounder with Cheese in Paris? They call it a Royale with Cheese.");
        index.addMovie(5, "Good Will Hunting", 8.3, 1997, "Will Hunting is a genius and he has a good heart. The best of his abilities is yet to be explored.");
        index.addMovie(6, "It's a Wonderful Life", 8.6, 1946, "Each man's life touches so many other lives. If he wasn't around, it would leave an awfully good hole.");
        index.addMovie(7, "The Pursuit of Happyness", 8.0, 2006, "It was the pursuit of a better life, and a good opportunity to change things for the better.");
        index.addMovie(8, "A Few Good Men", 7.7, 1992, "You can't handle the truth! This movie has a lot of good moments and intense drama.");
    }

    @Test
    void testAddMovie() {
        // Check that the index contains the correct number of movies
        int moviesLength = index.getMoviesLength();
        assertEquals(8, moviesLength);
    }

    @Test
    void testSearchForTermFound() {
        int expected = 1;
        List<SearchResult> result = index.search("hope");
        int actual = result.getFirst().getDocId();
        assertEquals(expected, actual);
    }

    @Test
    void testSearchRanking() {
        // Perform search for the term "good"
        List<SearchResult> results = index.search("good");
        assertFalse(results.isEmpty());
        for (SearchResult result : results) {
            System.out.println(result);
        }
        // Validate the ranking based on the provided relevance scores
        assertEquals(1, results.get(0).getDocId()); // The Shawshank Redemption should be ranked 1st
        assertEquals(8, results.get(1).getDocId()); // A Few Good Men should be ranked 2nd
        assertEquals(5, results.get(2).getDocId()); // Good Will Hunting should be ranked 3rd
        assertEquals(7, results.get(3).getDocId()); // The Pursuit of Happyness should be ranked 4th
        assertEquals(6, results.get(4).getDocId()); // It's a Wonderful Life should be ranked 5th

        // Ensure the relevance scores are in descending order
        for (int i = 0; i < results.size() - 1; i++) {
            assertTrue(results.get(i).getRelevanceScore() > results.get(i + 1).getRelevanceScore());
        }
    }

    @Test
    void testSearchForTermNotFound() {
        List<SearchResult> results = index.search("nonexistent");
        assertTrue(results.isEmpty());
    }

    @Test
    void testSearchForCommonTerm() {
        List<SearchResult> results = index.search("the");
        assertFalse(results.isEmpty());
        assertTrue(results.size() > 1);
    }

    @Test
    void testBM25ScoreCalculation() {
        List<SearchResult> results = index.search("cheese");
        assertEquals(1, results.size());
        assertEquals(4, results.getFirst().docId); // Pulp Fiction should have the highest score
    }

    @Test
    void testCaseInsensitivity() {
        List<SearchResult> resultsLowerCase = index.search("hope");
        List<SearchResult> resultsUpperCase = index.search("HOPE");
        assertEquals(resultsLowerCase, resultsUpperCase);
    }
}
