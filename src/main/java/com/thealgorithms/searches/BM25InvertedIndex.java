package com.thealgorithms.searches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Inverted Index implementation with BM25 Scoring for movie search.
 * This class supports adding movie documents and searching for terms
 * within those documents using the BM25 algorithm.
 * @author Prayas Kumar (https://github.com/prayas7102)
 */

class Movie {
    int docId; // Unique identifier for the movie
    String name; // Movie name
    double imdbRating; // IMDb rating of the movie
    int releaseYear; // Year the movie was released
    String content; // Full text content (could be the description or script)

    /**
     * Constructor for the Movie class.
     * @param docId Unique identifier for the movie.
     * @param name Name of the movie.
     * @param imdbRating IMDb rating of the movie.
     * @param releaseYear Release year of the movie.
     * @param content Content or description of the movie.
     */
    Movie(int docId, String name, double imdbRating, int releaseYear, String content) {
        this.docId = docId;
        this.name = name;
        this.imdbRating = imdbRating;
        this.releaseYear = releaseYear;
        this.content = content;
    }

    /**
     * Get all the words from the movie's name and content.
     * Converts the name and content to lowercase and splits on non-word characters.
     * @return Array of words from the movie name and content.
     */
    public String[] getWords() {
        return (name + " " + content).toLowerCase().split("\\W+");
    }

    @Override
    public String toString() {
        return "Movie{"
            + "docId=" + docId + ", name='" + name + '\'' + ", imdbRating=" + imdbRating + ", releaseYear=" + releaseYear + '}';
    }
}

class SearchResult {
    int docId; // Unique identifier of the movie document
    double relevanceScore; // Relevance score based on the BM25 algorithm

    /**
     * Constructor for SearchResult class.
     * @param docId Document ID (movie) for this search result.
     * @param relevanceScore The relevance score based on BM25 scoring.
     */
    SearchResult(int docId, double relevanceScore) {
        this.docId = docId;
        this.relevanceScore = relevanceScore;
    }

    public int getDocId() {
        return docId;
    }

    @Override
    public String toString() {
        return "SearchResult{"
            + "docId=" + docId + ", relevanceScore=" + relevanceScore + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchResult that = (SearchResult) o;
        return docId == that.docId && Double.compare(that.relevanceScore, relevanceScore) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(docId, relevanceScore);
    }

    public double getRelevanceScore() {
        return this.relevanceScore;
    }
}

public final class BM25InvertedIndex {
    private Map<String, Map<Integer, Integer>> index; // Inverted index mapping terms to document id and frequency
    private Map<Integer, Movie> movies; // Mapping of movie document IDs to Movie objects
    private int totalDocuments; // Total number of movies/documents
    private double avgDocumentLength; // Average length of documents (number of words)
    private static final double K = 1.5; // BM25 tuning parameter, controls term frequency saturation
    private static final double B = 0.75; // BM25 tuning parameter, controls length normalization

    /**
     * Constructor for BM25InvertedIndex.
     * Initializes the inverted index and movie storage.
     */
    BM25InvertedIndex() {
        index = new HashMap<>();
        movies = new HashMap<>();
        totalDocuments = 0;
        avgDocumentLength = 0.0;
    }

    /**
     * Add a movie to the index.
     * @param docId Unique identifier for the movie.
     * @param name Name of the movie.
     * @param imdbRating IMDb rating of the movie.
     * @param releaseYear Release year of the movie.
     * @param content Content or description of the movie.
     */
    public void addMovie(int docId, String name, double imdbRating, int releaseYear, String content) {
        Movie movie = new Movie(docId, name, imdbRating, releaseYear, content);
        movies.put(docId, movie);
        totalDocuments++;

        // Get words (terms) from the movie's name and content
        String[] terms = movie.getWords();
        int docLength = terms.length;

        // Update the average document length
        avgDocumentLength = (avgDocumentLength * (totalDocuments - 1) + docLength) / totalDocuments;

        // Update the inverted index
        for (String term : terms) {
            // Create a new entry if the term is not yet in the index
            index.putIfAbsent(term, new HashMap<>());

            // Get the list of documents containing the term
            Map<Integer, Integer> docList = index.get(term);
            if (docList == null) {
                docList = new HashMap<>();
                index.put(term, docList); // Ensure docList is added to the index
            }
            // Increment the term frequency in this document
            docList.put(docId, docList.getOrDefault(docId, 0) + 1);
        }
    }

    public int getMoviesLength() {
        return movies.size();
    }

    /**
     * Search for documents containing a term using BM25 scoring.
     * @param term The search term.
     * @return A list of search results sorted by relevance score.
     */
    public List<SearchResult> search(String term) {
        term = term.toLowerCase(); // Normalize search term
        if (!index.containsKey(term)) {
            return new ArrayList<>(); // Return empty list if term not found
        }

        Map<Integer, Integer> termDocs = index.get(term); // Documents containing the term
        List<SearchResult> results = new ArrayList<>();

        // Compute IDF for the search term
        double idf = computeIDF(termDocs.size());

        // Calculate relevance scores for all documents containing the term
        for (Map.Entry<Integer, Integer> entry : termDocs.entrySet()) {
            int docId = entry.getKey();
            int termFrequency = entry.getValue();
            Movie movie = movies.get(docId);
            if (movie == null) {
                continue; // Skip this document if movie doesn't exist
            }
            double docLength = movie.getWords().length;

            // Compute BM25 relevance score
            double score = computeBM25Score(termFrequency, docLength, idf);
            results.add(new SearchResult(docId, score));
        }

        // Sort the results by relevance score in descending order
        results.sort((r1, r2) -> Double.compare(r2.relevanceScore, r1.relevanceScore));
        return results;
    }

    /**
     * Compute the BM25 score for a given term and document.
     * @param termFrequency The frequency of the term in the document.
     * @param docLength The length of the document.
     * @param idf The inverse document frequency of the term.
     * @return The BM25 relevance score for the term in the document.
     */
    private double computeBM25Score(int termFrequency, double docLength, double idf) {
        double numerator = termFrequency * (K + 1);
        double denominator = termFrequency + K * (1 - B + B * (docLength / avgDocumentLength));
        return idf * (numerator / denominator);
    }

    /**
     * Compute the inverse document frequency (IDF) of a term.
     * The IDF measures the importance of a term across the entire document set.
     * @param docFrequency The number of documents that contain the term.
     * @return The inverse document frequency (IDF) value.
     */
    private double computeIDF(int docFrequency) {
        // Total number of documents in the index
        return Math.log((totalDocuments - docFrequency + 0.5) / (docFrequency + 0.5) + 1);
    }
}
