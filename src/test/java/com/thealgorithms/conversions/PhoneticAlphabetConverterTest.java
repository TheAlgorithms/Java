package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PhoneticAlphabetConverterTest {

    @ParameterizedTest
    @CsvSource({
        "'AB', 'Alpha Bravo'", "'ABC', 'Alpha Bravo Charlie'", "'A1B2C3', 'Alpha One Bravo Two Charlie Three'", "'Hello', 'Hotel Echo Lima Lima Oscar'", "'123', 'One Two Three'",
        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', 'Alpha Bravo Charlie Delta Echo Foxtrot Golf Hotel India Juliett Kilo Lima Mike November Oscar Papa Quebec Romeo Sierra Tango Uniform Victor Whiskey X-ray Yankee Zulu Zero One Two Three Four Five Six Seven Eight Nine'",
        "'abcdefghijklmnopqrstuvwxyz0123456789', 'Alpha Bravo Charlie Delta Echo Foxtrot Golf Hotel India Juliett Kilo Lima Mike November Oscar Papa Quebec Romeo Sierra Tango Uniform Victor Whiskey X-ray Yankee Zulu Zero One Two Three Four Five Six Seven Eight Nine'",
        "'', ''", // Empty string case
        "'A B C', 'Alpha Bravo Charlie'", // String with spaces
        "'A@B#C', 'Alpha @ Bravo # Charlie'", // Special characters
        "'A B C 123', 'Alpha Bravo Charlie One Two Three'", // Mixed letters, digits, and spaces
        "'a b c', 'Alpha Bravo Charlie'", // Lowercase letters with spaces
        "'123!@#', 'One Two Three ! @ #'", // Numbers with special characters
        "'HELLO WORLD', 'Hotel Echo Lima Lima Oscar Whiskey Oscar Romeo Lima Delta'" // Words with space
    })
    public void
    testTextToPhonetic(String input, String expectedOutput) {
        assertEquals(expectedOutput, PhoneticAlphabetConverter.textToPhonetic(input));
    }
}
