package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LetterCombinationsOfPhoneNumberTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testLetterCombinationsOfPhoneNumber(int[] numbers, List<String> expectedOutput) {
        assertEquals(expectedOutput, LetterCombinationsOfPhoneNumber.getCombinations(numbers));
    }

    @ParameterizedTest
    @MethodSource("wrongInputs")
    void throwsForWrongInput(int[] numbers) {
        assertThrows(IllegalArgumentException.class, () -> LetterCombinationsOfPhoneNumber.getCombinations(numbers));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(null, List.of("")), Arguments.of(new int[] {}, List.of("")), Arguments.of(new int[] {2}, Arrays.asList("a", "b", "c")), Arguments.of(new int[] {2, 3}, Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")),
            Arguments.of(new int[] {2, 3, 4}, Arrays.asList("adg", "adh", "adi", "aeg", "aeh", "aei", "afg", "afh", "afi", "bdg", "bdh", "bdi", "beg", "beh", "bei", "bfg", "bfh", "bfi", "cdg", "cdh", "cdi", "ceg", "ceh", "cei", "cfg", "cfh", "cfi")),
            Arguments.of(new int[] {3, 3}, Arrays.asList("dd", "de", "df", "ed", "ee", "ef", "fd", "fe", "ff")), Arguments.of(new int[] {8, 4}, Arrays.asList("tg", "th", "ti", "ug", "uh", "ui", "vg", "vh", "vi")), Arguments.of(new int[] {2, 0}, Arrays.asList("a ", "b ", "c ")),
            Arguments.of(new int[] {9, 2}, Arrays.asList("wa", "wb", "wc", "xa", "xb", "xc", "ya", "yb", "yc", "za", "zb", "zc")));
    }

    private static Stream<Arguments> wrongInputs() {
        return Stream.of(Arguments.of(new int[] {-1}), Arguments.of(new int[] {10}), Arguments.of(new int[] {2, 2, -1, 0}), Arguments.of(new int[] {0, 0, 0, 10}));
    }
}
