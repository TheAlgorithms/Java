package com.thealgorithms.conversions;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

public class UnitsConverterTest {

    @Test
    void testConvertThrowsForSameUnits() {
        final UnitsConverter someConverter = new UnitsConverter(Map.ofEntries(entry(Pair.of("A", "B"), new AffineConverter(10.0, -20.0))));
        assertThrows(IllegalArgumentException.class, () -> someConverter.convert("A", "A", 20.0));
        assertThrows(IllegalArgumentException.class, () -> someConverter.convert("B", "B", 20.0));
    }

    @Test
    void testConvertThrowsForUnknownUnits() {
        final UnitsConverter someConverter = new UnitsConverter(Map.ofEntries(entry(Pair.of("A", "B"), new AffineConverter(10.0, -20.0))));
        assertThrows(NoSuchElementException.class, () -> someConverter.convert("A", "X", 20.0));
        assertThrows(NoSuchElementException.class, () -> someConverter.convert("X", "A", 20.0));
        assertThrows(NoSuchElementException.class, () -> someConverter.convert("X", "Y", 20.0));
    }

    @Test
    void testAvailableUnits() {
        final UnitsConverter someConverter = new UnitsConverter(Map.ofEntries(entry(Pair.of("Celsius", "Fahrenheit"), new AffineConverter(9.0 / 5.0, 32.0)), entry(Pair.of("Kelvin", "Celsius"), new AffineConverter(1.0, -273.15))));
        assertEquals(Set.of("Celsius", "Fahrenheit", "Kelvin"), someConverter.availableUnits());
    }

    @Test
    void testInvertConversion() {
        final UnitsConverter someConverter = new UnitsConverter(Map.ofEntries(entry(Pair.of("A", "B"), new AffineConverter(2.0, 5.0))));
        // Check conversion from A -> B
        assertEquals(25.0, someConverter.convert("A", "B", 10.0), 0.0001);
        // Check inverse conversion from B -> A
        assertEquals(10.0, someConverter.convert("B", "A", 25.0), 0.0001);
    }
}
