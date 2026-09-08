package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.streaming.CusumDetector.ShiftSignal;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CusumDetectorTest {

    /**
     * Index of the first alarm in a sequence of verdicts, or {@code -1} if there is none.
     */
    private static int firstAlarm(ShiftSignal[] signals) {
        for (int i = 0; i < signals.length; i++) {
            if (signals[i].isAlarm()) {
                return i;
            }
        }
        return -1;
    }

    @Test
    void rejectsInvalidConfiguration() {
        assertThrows(IllegalArgumentException.class, () -> new CusumDetector(Double.NaN, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new CusumDetector(0.0, 0.0));
        assertThrows(IllegalArgumentException.class, () -> new CusumDetector(0.0, -1.0));
        assertThrows(IllegalArgumentException.class, () -> new CusumDetector(0.0, 1.0, -0.5, 5.0));
        assertThrows(IllegalArgumentException.class, () -> new CusumDetector(0.0, 1.0, 0.5, 0.0));
        assertThrows(IllegalArgumentException.class, () -> new CusumDetector(0.0, 1.0, 0.5, Double.NaN));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteSamples(double value) {
        CusumDetector detector = new CusumDetector(0.0, 1.0);
        assertThrows(IllegalArgumentException.class, () -> detector.accept(value));
    }

    @Test
    void exposesItsConfiguration() {
        CusumDetector detector = new CusumDetector(20.0, 0.5, 0.4, 4.0);
        assertEquals(20.0, detector.target());
        assertEquals(0.5, detector.standardDeviation());
        assertEquals(0.4, detector.allowance());
        assertEquals(4.0, detector.threshold());
        assertEquals(ShiftSignal.NONE, detector.lastSignal());
        assertEquals(0L, detector.count());
        assertEquals(0L, detector.alarmCount());
    }

    @Test
    @DisplayName("a stream sitting exactly on target never raises an alarm")
    void staysQuietOnTarget() {
        CusumDetector detector = new CusumDetector(10.0, 1.0);
        for (int i = 0; i < 100_000; i++) {
            assertEquals(ShiftSignal.NONE, detector.accept(10.0));
        }
        assertEquals(0.0, detector.upperSum());
        assertEquals(0.0, detector.lowerSum());
        assertEquals(0L, detector.alarmCount());
        assertEquals(100_000L, detector.count());
    }

    @Test
    @DisplayName("the toll keeps in-control noise from accumulating")
    void staysMostlyQuietOnInControlNoise() {
        Random random = new Random(20240517L);
        CusumDetector detector = new CusumDetector(0.0, 1.0, 0.5, 8.0);
        for (int i = 0; i < 2_000; i++) {
            detector.accept(random.nextGaussian());
        }
        assertTrue(detector.alarmCount() <= 2, "raised " + detector.alarmCount() + " false alarms in 2000 samples");
    }

    @Test
    @DisplayName("a persistent upward shift is detected within a handful of samples")
    void detectsAnUpwardShift() {
        double[] signal = new double[60];
        Random random = new Random(11L);
        for (int i = 0; i < signal.length; i++) {
            signal[i] = (i < 30 ? 0.0 : 2.0) + 0.1 * random.nextGaussian();
        }

        CusumDetector detector = new CusumDetector(0.0, 1.0, 0.5, 5.0);
        ShiftSignal[] verdicts = detector.scan(signal);
        int alarm = firstAlarm(verdicts);
        assertTrue(alarm >= 30, "alarmed before the shift, at index " + alarm);
        assertTrue(alarm <= 35, "took too long to alarm, index " + alarm);
        assertEquals(ShiftSignal.UPWARD, verdicts[alarm]);
    }

    @Test
    void detectsADownwardShift() {
        double[] signal = new double[60];
        Random random = new Random(12L);
        for (int i = 0; i < signal.length; i++) {
            signal[i] = (i < 30 ? 100.0 : 96.0) + 0.5 * random.nextGaussian();
        }

        CusumDetector detector = new CusumDetector(100.0, 1.0, 0.5, 5.0);
        ShiftSignal[] verdicts = detector.scan(signal);
        int alarm = firstAlarm(verdicts);
        assertTrue(alarm >= 30 && alarm <= 35, "alarm at index " + alarm);
        assertEquals(ShiftSignal.DOWNWARD, verdicts[alarm]);
    }

    @Test
    @DisplayName("catches a drift far too small to see in any single sample")
    void detectsASmallPersistentDrift() {
        double[] signal = new double[400];
        Random random = new Random(13L);
        for (int i = 0; i < signal.length; i++) {
            // The stream is noise free until the drift starts, so an early alarm can only be a
            // reaction to the drift itself.
            signal[i] = i < 200 ? 0.0 : 0.5 + random.nextGaussian();
        }

        CusumDetector detector = new CusumDetector(0.0, 1.0, 0.25, 5.0);
        int alarm = firstAlarm(detector.scan(signal));
        assertTrue(alarm >= 200, "alarmed before the drift, at index " + alarm);
        assertTrue(alarm < 260, "a half sigma drift should be caught quickly, but took until " + alarm);
    }

    @Test
    void sumsNeverGoNegativeAndClearOnAlarm() {
        CusumDetector detector = new CusumDetector(0.0, 1.0, 0.5, 3.0);
        for (int i = 0; i < 50; i++) {
            detector.accept(-5.0);
            assertTrue(detector.upperSum() >= 0.0);
            assertTrue(detector.lowerSum() >= 0.0);
        }
        assertTrue(detector.alarmCount() > 1, "a sustained shift should keep alarming");
    }

    @Test
    void scanReportsOneVerdictPerSample() {
        CusumDetector detector = new CusumDetector(0.0, 1.0);
        ShiftSignal[] signals = detector.scan(new double[] {0.0, 0.0, 0.0});
        assertEquals(3, signals.length);
        for (ShiftSignal signal : signals) {
            assertEquals(ShiftSignal.NONE, signal);
            assertFalse(signal.isAlarm());
        }
    }

    @Test
    void resetClearsTheEvidence() {
        CusumDetector detector = new CusumDetector(0.0, 1.0, 0.5, 5.0);
        for (int i = 0; i < 10; i++) {
            detector.accept(1.5);
        }
        assertTrue(detector.upperSum() > 0.0 || detector.alarmCount() > 0);

        detector.reset();
        assertEquals(0.0, detector.upperSum());
        assertEquals(0.0, detector.lowerSum());
        assertEquals(0L, detector.count());
        assertEquals(0L, detector.alarmCount());
        assertEquals(ShiftSignal.NONE, detector.lastSignal());
    }

    @Test
    void toStringMentionsTheState() {
        CusumDetector detector = new CusumDetector(7.0, 1.0);
        assertTrue(detector.toString().contains("target=7.0"), detector.toString());
    }

    @Test
    void shiftSignalDescribesItself() {
        assertFalse(ShiftSignal.NONE.isAlarm());
        assertTrue(ShiftSignal.UPWARD.isAlarm());
        assertTrue(ShiftSignal.DOWNWARD.isAlarm());
        assertEquals(3, ShiftSignal.values().length);
        assertEquals(ShiftSignal.UPWARD, ShiftSignal.valueOf("UPWARD"));
    }

    @Test
    void rejectsNonFiniteConfiguration() {
        assertThrows(IllegalArgumentException.class, () -> new CusumDetector(0.0, Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> new CusumDetector(0.0, 1.0, Double.NaN, 5.0));
        assertThrows(IllegalArgumentException.class, () -> new CusumDetector(0.0, 1.0, Double.POSITIVE_INFINITY, 5.0));
        assertThrows(IllegalArgumentException.class, () -> new CusumDetector(0.0, 1.0, 0.5, Double.POSITIVE_INFINITY));
    }
}
