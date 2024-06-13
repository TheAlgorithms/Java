package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        return Stream.of(Arguments.of(null, List.of("")), Arguments.of(new int[] {}, List.of("")), Arguments.of(new int[] {2}, List.of("a", "b", "c")), Arguments.of(new int[] {2, 3}, List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")),
            Arguments.of(new int[] {2, 3, 4}, List.of("adg", "adh", "adi", "aeg", "aeh", "aei", "afg", "afh", "afi", "bdg", "bdh", "bdi", "beg", "beh", "bei", "bfg", "bfh", "bfi", "cdg", "cdh", "cdi", "ceg", "ceh", "cei", "cfg", "cfh", "cfi")),
            Arguments.of(new int[] {3, 3}, List.of("dd", "de", "df", "ed", "ee", "ef", "fd", "fe", "ff")), Arguments.of(new int[] {8, 4}, List.of("tg", "th", "ti", "ug", "uh", "ui", "vg", "vh", "vi")), Arguments.of(new int[] {2, 0}, List.of("a ", "b ", "c ")),
            Arguments.of(new int[] {9, 2}, List.of("wa", "wb", "wc", "xa", "xb", "xc", "ya", "yb", "yc", "za", "zb", "zc")), Arguments.of(new int[] {0}, List.of(" ")), Arguments.of(new int[] {1}, List.of("")), Arguments.of(new int[] {2}, List.of("a", "b", "c")),
            Arguments.of(new int[] {1, 2, 0, 4}, List.of("a g", "a h", "a i", "b g", "b h", "b i", "c g", "c h", "c i")));
    }

    private static Stream<Arguments> wrongInputs() {
        return Stream.of(Arguments.of(new int[] {-1}), Arguments.of(new int[] {10}), Arguments.of(new int[] {2, 2, -1, 0}), Arguments.of(new int[] {0, 0, 0, 10}));
    }
}
