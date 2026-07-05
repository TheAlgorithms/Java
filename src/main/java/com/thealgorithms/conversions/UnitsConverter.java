package com.thealgorithms.conversions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a directed conversion between two units using an affine transformation.
 */
record UnitPair(String from, String to) {
    UnitPair {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
    }

    @Override
    public String toString() {
        return from + " → " + to;
    }
}

/**
 * Abstraction for a conversion graph that can compute transitive closures.
 */
interface ConversionGraph {
    double convert(String from, String to, double value);
    Set<String> availableUnits();
    boolean hasConversion(String from, String to);
}

/**
 * Main public API — thin facade following SRP.
 */
public final class UnitsConverter implements ConversionGraph {

    private final ConversionGraph graph;

    /**
     * Creates a new UnitsConverter with automatic inverse and transitive conversions.
     *
     * @param basicConversions initial direct conversions
     */
    public UnitsConverter(Map<UnitPair, AffineConverter> basicConversions) {
        this.graph = new AffineConversionGraph(basicConversions);
    }

    @Override
    public double convert(String from, String to, double value) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("Input and output units must be different.");
        }
        return graph.convert(from, to, value);
    }

    @Override
    public Set<String> availableUnits() {
        return graph.availableUnits();
    }

    @Override
    public boolean hasConversion(String from, String to) {
        return graph.hasConversion(from, to);
    }
}

/**
 * Internal implementation handling the conversion graph and transitive closure.
 * Follows SRP and DIP.
 */
final class AffineConversionGraph implements ConversionGraph {

    private final Map<UnitPair, AffineConverter> conversions;
    private final Set<String> units;

    AffineConversionGraph(Map<UnitPair, AffineConverter> basicConversions) {
        Map<UnitPair, AffineConverter> initial = basicConversions != null
                ? new HashMap<>(basicConversions)
                : Map.of();

        this.conversions = computeFullClosure(initial);
        this.units = extractUnits(this.conversions);
    }

    private Map<UnitPair, AffineConverter> computeFullClosure(Map<UnitPair, AffineConverter> basic) {
        Map<UnitPair, AffineConverter> current = addInversions(basic);

        boolean changed;
        do {
            Map<UnitPair, AffineConverter> next = addCompositions(current);
            changed = next.size() > current.size();
            current = next;
        } while (changed);

        return Map.copyOf(current); // immutable
    }

    private Map<UnitPair, AffineConverter> addInversions(Map<UnitPair, AffineConverter> known) {
        Map<UnitPair, AffineConverter> result = new HashMap<>(known.size() * 2);

        for (var entry : known.entrySet()) {
            UnitPair pair = entry.getKey();
            AffineConverter conv = entry.getValue();

            putIfDifferent(result, pair.from(), pair.to(), conv);
            putIfDifferent(result, pair.to(), pair.from(), conv.invert());
        }
        return result;
    }

    private Map<UnitPair, AffineConverter> addCompositions(Map<UnitPair, AffineConverter> known) {
        Map<UnitPair, AffineConverter> result = new HashMap<>(known);

        for (var first : known.entrySet()) {
            UnitPair firstPair = first.getKey();
            AffineConverter firstConv = first.getValue();

            for (var second : known.entrySet()) {
                UnitPair secondPair = second.getKey();
                if (firstPair.to().equals(secondPair.from())) {
                    AffineConverter composed = second.getValue().compose(firstConv);
                    putIfDifferent(result, firstPair.from(), secondPair.to(), composed);
                }
            }
        }
        return result;
    }

    private void putIfDifferent(Map<UnitPair, AffineConverter> map, String from, String to, AffineConverter conv) {
        if (!from.equals(to)) {
            map.putIfAbsent(new UnitPair(from, to), conv);
        }
    }

    private Set<String> extractUnits(Map<UnitPair, AffineConverter> convs) {
        Set<String> unitsSet = new HashSet<>();
        for (UnitPair pair : convs.keySet()) {
            unitsSet.add(pair.from());
        }
        return Set.copyOf(unitsSet);
    }

    @Override
    public double convert(String from, String to, double value) {
        UnitPair key = new UnitPair(from, to);
        AffineConverter converter = conversions.get(key);
        if (converter == null) {
            throw new NoSuchElementException("No converter found for: " + key);
        }
        return converter.convert(value);
    }

    @Override
    public Set<String> availableUnits() {
        return units;
    }

    @Override
    public boolean hasConversion(String from, String to) {
        return conversions.containsKey(new UnitPair(from, to));
    }
}