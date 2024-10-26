package com.thealgorithms.conversions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

/**
 * A class that handles unit conversions using affine transformations.
 *
 * <p>The {@code UnitsConverter} allows converting values between different units using
 * pre-defined affine conversion formulas. Each conversion is represented by an
 * {@link AffineConverter} that defines the scaling and offset for the conversion.
 *
 * <p>For each unit, both direct conversions (e.g., Celsius to Fahrenheit) and inverse
 * conversions (e.g., Fahrenheit to Celsius) are generated automatically. It also computes
 * transitive conversions (e.g., Celsius to Kelvin via Fahrenheit if both conversions exist).
 *
 * <p>Key features include:
 * <ul>
 *   <li>Automatic handling of inverse conversions (e.g., Fahrenheit to Celsius).</li>
 *   <li>Compositional conversions, meaning if conversions between A -> B and B -> C exist,
 *       it can automatically generate A -> C conversion.</li>
 *   <li>Supports multiple unit systems as long as conversions are provided in pairs.</li>
 * </ul>
 *
 * <h2>Example Usage</h2>
 * <pre>
 * Map&lt;Pair&lt;String, String&gt;, AffineConverter&gt; basicConversions = Map.ofEntries(
 *     entry(Pair.of("Celsius", "Fahrenheit"), new AffineConverter(9.0 / 5.0, 32.0)),
 *     entry(Pair.of("Kelvin", "Celsius"), new AffineConverter(1.0, -273.15))
 * );
 *
 * UnitsConverter converter = new UnitsConverter(basicConversions);
 * double result = converter.convert("Celsius", "Fahrenheit", 100.0);
 * // Output: 212.0 (Celsius to Fahrenheit conversion of 100°C)
 * </pre>
 *
 * <h2>Exception Handling</h2>
 * <ul>
 *   <li>If the input unit and output unit are the same, an {@link IllegalArgumentException} is thrown.</li>
 *   <li>If a conversion between the requested units does not exist, a {@link NoSuchElementException} is thrown.</li>
 * </ul>
 */
public final class UnitsConverter {
    private final Map<Pair<String, String>, AffineConverter> conversions;
    private final Set<String> units;

    private static void putIfNeeded(Map<Pair<String, String>, AffineConverter> conversions, final String inputUnit, final String outputUnit, final AffineConverter converter) {
        if (!inputUnit.equals(outputUnit)) {
            final var key = Pair.of(inputUnit, outputUnit);
            conversions.putIfAbsent(key, converter);
        }
    }

    private static Map<Pair<String, String>, AffineConverter> addInversions(final Map<Pair<String, String>, AffineConverter> knownConversions) {
        Map<Pair<String, String>, AffineConverter> res = new HashMap<Pair<String, String>, AffineConverter>();
        for (final var curConversion : knownConversions.entrySet()) {
            final var inputUnit = curConversion.getKey().getKey();
            final var outputUnit = curConversion.getKey().getValue();
            putIfNeeded(res, inputUnit, outputUnit, curConversion.getValue());
            putIfNeeded(res, outputUnit, inputUnit, curConversion.getValue().invert());
        }
        return res;
    }

    private static Map<Pair<String, String>, AffineConverter> addCompositions(final Map<Pair<String, String>, AffineConverter> knownConversions) {
        Map<Pair<String, String>, AffineConverter> res = new HashMap<Pair<String, String>, AffineConverter>();
        for (final var first : knownConversions.entrySet()) {
            final var firstKey = first.getKey();
            putIfNeeded(res, firstKey.getKey(), firstKey.getValue(), first.getValue());
            for (final var second : knownConversions.entrySet()) {
                final var secondKey = second.getKey();
                if (firstKey.getValue().equals(secondKey.getKey())) {
                    final var newConversion = second.getValue().compose(first.getValue());
                    putIfNeeded(res, firstKey.getKey(), secondKey.getValue(), newConversion);
                }
            }
        }
        return res;
    }

    private static Map<Pair<String, String>, AffineConverter> addAll(final Map<Pair<String, String>, AffineConverter> knownConversions) {
        final var res = addInversions(knownConversions);
        return addCompositions(res);
    }

    private static Map<Pair<String, String>, AffineConverter> computeAllConversions(final Map<Pair<String, String>, AffineConverter> basicConversions) {
        var tmp = basicConversions;
        var res = addAll(tmp);
        while (res.size() != tmp.size()) {
            tmp = res;
            res = addAll(tmp);
        }
        return res;
    }

    private static Set<String> extractUnits(final Map<Pair<String, String>, AffineConverter> conversions) {
        Set<String> res = new HashSet<>();
        for (final var conversion : conversions.entrySet()) {
            res.add(conversion.getKey().getKey());
        }
        return res;
    }

    /**
     * Constructor for {@code UnitsConverter}.
     *
     * <p>Accepts a map of basic conversions and automatically generates inverse and
     * transitive conversions.
     *
     * @param basicConversions the initial set of unit conversions to add.
     */
    public UnitsConverter(final Map<Pair<String, String>, AffineConverter> basicConversions) {
        conversions = computeAllConversions(basicConversions);
        units = extractUnits(conversions);
    }

    /**
     * Converts a value from one unit to another.
     *
     * @param inputUnit the unit of the input value.
     * @param outputUnit the unit to convert the value into.
     * @param value the value to convert.
     * @return the converted value in the target unit.
     * @throws IllegalArgumentException if inputUnit equals outputUnit.
     * @throws NoSuchElementException if no conversion exists between the units.
     */
    public double convert(final String inputUnit, final String outputUnit, final double value) {
        if (inputUnit.equals(outputUnit)) {
            throw new IllegalArgumentException("inputUnit must be different from outputUnit.");
        }
        final var conversionKey = Pair.of(inputUnit, outputUnit);
        return conversions.computeIfAbsent(conversionKey, k -> { throw new NoSuchElementException("No converter for: " + k); }).convert(value);
    }

    /**
     * Retrieves the set of all units supported by this converter.
     *
     * @return a set of available units.
     */
    public Set<String> availableUnits() {
        return units;
    }
}
