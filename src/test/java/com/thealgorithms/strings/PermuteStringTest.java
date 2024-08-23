package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PermuteStringTest {

    private static Stream<TestData> provideTestCases() {
        return Stream.of(new TestData("ABC", Set.of("ABC", "ACB", "BAC", "BCA", "CAB", "CBA")), new TestData("AB", Set.of("AB", "BA")), new TestData("A", Set.of("A")), new TestData("AA", Set.of("AA")), new TestData("123", Set.of("123", "132", "213", "231", "312", "321")),
            new TestData("aA", Set.of("aA", "Aa")), new TestData("AaB", Set.of("AaB", "ABa", "aAB", "aBA", "BAa", "BaA")), new TestData("!@", Set.of("!@", "@!")), new TestData("!a@", Set.of("!a@", "!@a", "a!@", "a@!", "@!a", "@a!")),
            new TestData("ABCD", Set.of("ABCD", "ABDC", "ACBD", "ACDB", "ADBC", "ADCB", "BACD", "BADC", "BCAD", "BCDA", "BDAC", "BDCA", "CABD", "CADB", "CBAD", "CBDA", "CDAB", "CDBA", "DABC", "DACB", "DBAC", "DBCA", "DCAB", "DCBA")),
            new TestData("A B", Set.of("A B", "AB ", " AB", " BA", "BA ", "B A")),
            new TestData("abcd", Set.of("abcd", "abdc", "acbd", "acdb", "adbc", "adcb", "bacd", "badc", "bcad", "bcda", "bdac", "bdca", "cabd", "cadb", "cbad", "cbda", "cdab", "cdba", "dabc", "dacb", "dbac", "dbca", "dcab", "dcba")));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testPermutations(TestData testData) {
        Set<String> actualPermutations = PermuteString.getPermutations(testData.input);
        assertEquals(testData.expected, actualPermutations, "The permutations of '" + testData.input + "' are not correct.");
    }

    record TestData(String input, Set<String> expected) {
    }
}
