package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.streaming.TimestampOrderChecker.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimestampOrderCheckerTest {

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteTimestamps(double timestamp) {
        TimestampOrderChecker checker = new TimestampOrderChecker();
        assertThrows(IllegalArgumentException.class, () -> checker.accept(timestamp));
    }

    @Test
    void startsEmpty() {
        TimestampOrderChecker checker = new TimestampOrderChecker();

        assertEquals(0, checker.count());
        assertEquals(Order.INCREASING, checker.lastOrder());
        assertTrue(checker.isOrdered());
        assertEquals(0.0, checker.worstBackwardsStep());
        assertThrows(IllegalStateException.class, checker::lastTimestamp);
    }

    @Test
    void theFirstTimestampIsAlwaysAccepted() {
        TimestampOrderChecker checker = new TimestampOrderChecker();

        assertEquals(Order.INCREASING, checker.accept(-500.0));
        assertEquals(-500.0, checker.lastTimestamp());
    }

    @Test
    void anOrderedStreamHasNoViolations() {
        TimestampOrderChecker checker = new TimestampOrderChecker();

        for (int i = 0; i < 20; i++) {
            assertEquals(Order.INCREASING, checker.accept(i));
        }
        assertTrue(checker.isOrdered());
        assertEquals(20, checker.count());
    }

    @Test
    void reportsADuplicate() {
        TimestampOrderChecker checker = new TimestampOrderChecker();
        checker.accept(10.0);

        assertEquals(Order.DUPLICATE, checker.accept(10.0));
        assertEquals(1, checker.duplicateCount());
        assertEquals(0, checker.backwardsCount());
        assertFalse(checker.isOrdered());
    }

    @Test
    void reportsAStepBackwards() {
        TimestampOrderChecker checker = new TimestampOrderChecker();
        checker.accept(10.0);

        assertEquals(Order.BACKWARDS, checker.accept(7.5));
        assertEquals(1, checker.backwardsCount());
        assertEquals(2.5, checker.worstBackwardsStep());
    }

    @Test
    @DisplayName("the worst backwards step is the largest one, not the last one")
    void keepsTheWorstBackwardsStep() {
        TimestampOrderChecker checker = new TimestampOrderChecker();

        checker.scan(new double[] {100.0, 60.0, 61.0, 59.0, 200.0, 199.0});

        assertEquals(3, checker.backwardsCount());
        assertEquals(40.0, checker.worstBackwardsStep());
    }

    @Test
    @DisplayName("the checker reports without reordering, so the last timestamp is the most recent one")
    void doesNotReorderTheStream() {
        TimestampOrderChecker checker = new TimestampOrderChecker();

        checker.accept(100.0);
        checker.accept(50.0);

        assertEquals(50.0, checker.lastTimestamp());
        assertEquals(Order.INCREASING, checker.accept(60.0), "the next sample is compared with the previous one, not with the maximum");
    }

    @Test
    void scanReportsOneVerdictPerTimestamp() {
        TimestampOrderChecker checker = new TimestampOrderChecker();

        Order[] verdicts = checker.scan(new double[] {1.0, 2.0, 2.0, 1.5, 3.0});

        Assertions.assertArrayEquals(new Order[] {Order.INCREASING, Order.INCREASING, Order.DUPLICATE, Order.BACKWARDS, Order.INCREASING}, verdicts);
    }

    @Test
    void theVerdictKnowsWhetherItIsAViolation() {
        assertFalse(Order.INCREASING.isViolation());
        assertTrue(Order.DUPLICATE.isViolation());
        assertTrue(Order.BACKWARDS.isViolation());
    }

    @Test
    void resetRestoresTheInitialState() {
        TimestampOrderChecker checker = new TimestampOrderChecker();
        checker.scan(new double[] {5.0, 4.0, 4.0});

        checker.reset();

        assertEquals(0, checker.count());
        assertEquals(0, checker.duplicateCount());
        assertEquals(0, checker.backwardsCount());
        assertEquals(0.0, checker.worstBackwardsStep());
        assertTrue(checker.isOrdered());
        assertThrows(IllegalStateException.class, checker::lastTimestamp);
    }

    @Test
    void toStringMentionsTheState() {
        TimestampOrderChecker checker = new TimestampOrderChecker();
        checker.scan(new double[] {5.0, 4.0});

        String text = checker.toString();

        assertTrue(text.contains("TimestampOrderChecker"));
        assertTrue(text.contains("backwards=1"));
    }
}
