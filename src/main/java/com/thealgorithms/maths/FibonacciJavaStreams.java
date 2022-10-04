package com.thealgorithms.maths;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author: caos321
 * @date: 14 October 2021 (Thursday)
 */
public class FibonacciJavaStreams {

    public static Optional<BigDecimal> calculate(final BigDecimal index) {
        if (index == null || index.compareTo(BigDecimal.ZERO) < 0) {
            return Optional.empty();
        }

        if (index.compareTo(BigDecimal.ONE) < 0) {
            return Optional.of(BigDecimal.ZERO);
        }

        if (index.compareTo(new BigDecimal(2)) < 0) {
            return Optional.of(BigDecimal.ONE);
        }

        final List<BigDecimal> results = Stream
            .iterate(
                index,
                x -> x.compareTo(BigDecimal.ZERO) > 0,
                x -> x.subtract(BigDecimal.ONE)
            )
            .reduce(
                List.of(),
                (list, current) ->
                    list.isEmpty() || list.size() < 2
                        ? List.of(BigDecimal.ZERO, BigDecimal.ONE)
                        : List.of(list.get(1), list.get(0).add(list.get(1))),
                (list1, list2) -> list1
            );

        return results.isEmpty()
            ? Optional.empty()
            : Optional.of(results.get(results.size() - 1));
    }

    public static void assertThat(final Object actual, final Object expected) {
        if (!Objects.equals(actual, expected)) {
            throw new AssertionError(
                String.format("expected=%s but was actual=%s", expected, actual)
            );
        }
    }

    public static void main(final String[] args) {
        {
            final Optional<BigDecimal> result = calculate(new BigDecimal(-1));
            assertThat(result.isEmpty(), true);
        }
        {
            final Optional<BigDecimal> result = calculate(BigDecimal.ZERO);
            assertThat(result.isPresent(), true);
            result.ifPresent(value -> assertThat(value, BigDecimal.ZERO));
        }
        {
            final Optional<BigDecimal> result = calculate(BigDecimal.ONE);
            assertThat(result.isPresent(), true);
            result.ifPresent(value -> assertThat(value, BigDecimal.ONE));
        }
        {
            final Optional<BigDecimal> result = calculate(new BigDecimal(2));
            assertThat(result.isPresent(), true);
            result.ifPresent(value -> assertThat(value, BigDecimal.ONE));
        }
        {
            final Optional<BigDecimal> result = calculate(new BigDecimal(3));
            assertThat(result.isPresent(), true);
            result.ifPresent(value -> assertThat(value, new BigDecimal(2)));
        }
        {
            final Optional<BigDecimal> result = calculate(new BigDecimal(10));
            assertThat(result.isPresent(), true);
            result.ifPresent(value -> assertThat(value, new BigDecimal(55)));
        }
        {
            final Optional<BigDecimal> result = calculate(new BigDecimal(20));
            assertThat(result.isPresent(), true);
            result.ifPresent(value -> assertThat(value, new BigDecimal(6765)));
        }
        {
            final Optional<BigDecimal> result = calculate(new BigDecimal(30));
            assertThat(result.isPresent(), true);
            result.ifPresent(value -> assertThat(value, new BigDecimal(832040))
            );
        }
        {
            final Optional<BigDecimal> result = calculate(new BigDecimal(40));
            assertThat(result.isPresent(), true);
            result.ifPresent(value ->
                assertThat(value, new BigDecimal(102334155))
            );
        }
        {
            final Optional<BigDecimal> result = calculate(new BigDecimal(50));
            assertThat(result.isPresent(), true);
            result.ifPresent(value ->
                assertThat(value, new BigDecimal(12586269025L))
            );
        }
        {
            final Optional<BigDecimal> result = calculate(new BigDecimal(100));
            assertThat(result.isPresent(), true);
            result.ifPresent(value ->
                assertThat(value, new BigDecimal("354224848179261915075"))
            );
        }
        {
            final Optional<BigDecimal> result = calculate(new BigDecimal(200));
            assertThat(result.isPresent(), true);
            result.ifPresent(value ->
                assertThat(
                    value,
                    new BigDecimal("280571172992510140037611932413038677189525")
                )
            );
        }
    }
}
