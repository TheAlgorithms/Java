package com.thealgorithms.conversions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

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

    public UnitsConverter(final Map<Pair<String, String>, AffineConverter> basicConversions) {
        conversions = computeAllConversions(basicConversions);
        units = extractUnits(conversions);
    }

    public double convert(final String inputUnit, final String outputUnit, final double value) {
        if (inputUnit.equals(outputUnit)) {
            throw new IllegalArgumentException("inputUnit must be different from outputUnit.");
        }
        final var conversionKey = Pair.of(inputUnit, outputUnit);
        return conversions.get(conversionKey).convert(value);
    }

    public Set<String> availableUnits() {
        return units;
    }
}
