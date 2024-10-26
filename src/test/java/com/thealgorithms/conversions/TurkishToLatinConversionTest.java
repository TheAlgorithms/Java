package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TurkishToLatinConversionTest {

    @ParameterizedTest
    @CsvSource({
        "'çalışma', 'calisma'", // Turkish to Latin conversion for lowercase
        "'ÇALIŞMA', 'CALISMA'", // Turkish to Latin conversion for uppercase
        "'İSTANBUL', 'ISTANBUL'", // Special case of 'İ' to 'I'
        "'istanbul', 'istanbul'", // Special case of 'ı' to 'i'
        "'GÜL', 'GUL'", // Special case of 'Ü' to 'U'
        "'gül', 'gul'", // Special case of 'ü' to 'u'
        "'ÖĞRENME', 'OGRENME'", // Special case of 'Ö' to 'O' and 'Ğ' to 'G'
        "'öğrenme', 'ogrenme'", // Special case of 'ö' to 'o' and 'ğ' to 'g'
        "'ŞEHIR', 'SEHIR'", // Special case of 'Ş' to 'S'
        "'şehir', 'sehir'", // Special case of 'ş' to 's'
        "'HELLO', 'HELLO'", // String with no Turkish characters, should remain unchanged
        "'Merhaba Dünya!', 'Merhaba Dunya!'", // Mixed Turkish and Latin characters with punctuation
        "'Çift kişilik yataklı odalar', 'Cift kisilik yatakli odalar'", // Full sentence conversion
        "'', ''" // Empty string case
    })
    public void
    testConvertTurkishToLatin(String input, String expectedOutput) {
        assertEquals(expectedOutput, TurkishToLatinConversion.convertTurkishToLatin(input));
    }
}
