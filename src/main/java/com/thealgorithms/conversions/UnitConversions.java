package com.thealgorithms.conversions;

import static java.util.Map.entry;

import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;

public final class UnitConversions {
    private UnitConversions() {
    }

    public static final UnitsConverter TEMPERATURE = new UnitsConverter(Map.ofEntries(entry(Pair.of("Kelvin", "Celsius"), new AffineConverter(1.0, -273.15)), entry(Pair.of("Celsius", "Fahrenheit"), new AffineConverter(9.0 / 5.0, 32.0)),
        entry(Pair.of("Réaumur", "Celsius"), new AffineConverter(5.0 / 4.0, 0.0)), entry(Pair.of("Delisle", "Celsius"), new AffineConverter(-2.0 / 3.0, 100.0)), entry(Pair.of("Rankine", "Kelvin"), new AffineConverter(5.0 / 9.0, 0.0))));
}
