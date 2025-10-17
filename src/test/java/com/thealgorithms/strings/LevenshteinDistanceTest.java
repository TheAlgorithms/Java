package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LevenshteinDistanceTest {

    @Test
    void sameStringsHaveZeroDistance() {
        assertThat(LevenshteinDistance.compute("test", "test")).isZero();
    }

    @Test
    void emptyToNonEmpty() {
        assertThat(LevenshteinDistance.compute("", "abc")).isEqualTo(3);
        assertThat(LevenshteinDistance.compute("abc", "")).isEqualTo(3);
    }

    @Test
    void nullHandling() {
        assertThat(LevenshteinDistance.compute(null, null)).isZero();
        assertThat(LevenshteinDistance.compute(null, "abc")).isEqualTo(3);
        assertThat(LevenshteinDistance.compute("abc", null)).isEqualTo(3);
    }

    @Test
    void typicalExamples() {
        assertThat(LevenshteinDistance.compute("kitten", "sitting")).isEqualTo(3);
        assertThat(LevenshteinDistance.compute("flaw", "lawn")).isEqualTo(2);
        assertThat(LevenshteinDistance.compute("gumbo", "gambol")).isEqualTo(2);
    }
}
