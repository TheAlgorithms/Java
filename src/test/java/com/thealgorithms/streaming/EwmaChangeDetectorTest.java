package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EwmaChangeDetectorTest {

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

    private static double[] stepSignal(int length, int stepAt, double before, double after) {
        double[] signal = new double[length];
        for (int i = 0; i < length; i++) {
            signal[i] = i < stepAt ? before : after;
        }
        return signal;
    }

    @Test
    void rejectsInvalidConfiguration() {
        assertThrows(IllegalArgumentException.class, () -> new EwmaChangeDetector(Double.NaN, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new EwmaChangeDetector(0.0, 0.0));
        assertThrows(IllegalArgumentException.class, () -> new EwmaChangeDetector(0.0, -2.0));
        assertThrows(IllegalArgumentException.class, () -> new EwmaChangeDetector(0.0, 1.0, 0.2, 0.0));
        assertThrows(IllegalArgumentException.class, () -> new EwmaChangeDetector(0.0, 1.0, 0.0, 3.0));
        assertThrows(IllegalArgumentException.class, () -> new EwmaChangeDetector(0.0, 1.0, 1.5, 3.0));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteSamples(double value) {
        EwmaChangeDetector detector = new EwmaChangeDetector(0.0, 1.0);
        assertThrows(IllegalArgumentException.class, () -> detector.accept(value));
    }

    @Test
    void exposesItsConfiguration() {
        EwmaChangeDetector detector = new EwmaChangeDetector(20.0, 0.5, 0.3, 2.5);
        assertEquals(20.0, detector.target());
        assertEquals(0.5, detector.standardDeviation());
        assertEquals(0.3, detector.alpha());
        assertEquals(2.5, detector.width());
        assertEquals(20.0, detector.statistic());
        assertEquals(ShiftSignal.NONE, detector.lastSignal());
        assertEquals(0L, detector.count());
        assertEquals(0L, detector.alarmCount());
    }

    @Test
    @DisplayName("the defaults are the customary alpha of 0.2 and a band of three sigmas")
    void usesCustomaryDefaults() {
        EwmaChangeDetector detector = new EwmaChangeDetector(0.0, 1.0);
        assertEquals(EwmaChangeDetector.DEFAULT_ALPHA, detector.alpha());
        assertEquals(EwmaChangeDetector.DEFAULT_WIDTH, detector.width());
    }

    @Test
    @DisplayName("a stream sitting exactly on target never raises an alarm")
    void staysQuietOnTarget() {
        EwmaChangeDetector detector = new EwmaChangeDetector(5.0, 1.0);
        for (int i = 0; i < 10_000; i++) {
            assertEquals(ShiftSignal.NONE, detector.accept(5.0));
        }
        assertEquals(5.0, detector.statistic(), 1e-12);
        assertEquals(0L, detector.alarmCount());
        assertEquals(10_000L, detector.count());
    }

    @Test
    @DisplayName("the control band widens during the warm-up and then settles")
    void controlLimitApproachesItsAsymptote() {
        double alpha = 0.2;
        double width = 3.0;
        double sigma = 2.0;
        EwmaChangeDetector detector = new EwmaChangeDetector(0.0, sigma, alpha, width);

        double asymptote = width * sigma * Math.sqrt(alpha / (2.0 - alpha));
        assertEquals(0.0, detector.controlLimit(), 1e-12);

        double previous = 0.0;
        for (int i = 0; i < 40; i++) {
            detector.accept(0.0);
            double limit = detector.controlLimit();
            assertTrue(limit > previous, "the limit stopped growing at step " + i);
            assertTrue(limit < asymptote, "the exact limit stays below its asymptote, but was " + limit);
            previous = limit;
        }

        for (int i = 0; i < 200; i++) {
            detector.accept(0.0);
        }
        assertEquals(asymptote, detector.controlLimit(), 1e-9);
    }

    @Test
    void detectsAnUpwardShift() {
        EwmaChangeDetector detector = new EwmaChangeDetector(0.0, 1.0, 0.2, 3.0);
        ShiftSignal[] verdicts = detector.scan(stepSignal(60, 30, 0.0, 2.0));

        int alarm = firstAlarm(verdicts);
        assertTrue(alarm >= 30, "alarmed before the shift, at index " + alarm);
        assertTrue(alarm <= 36, "took too long to alarm, index " + alarm);
        assertEquals(ShiftSignal.UPWARD, verdicts[alarm]);
    }

    @Test
    void detectsADownwardShift() {
        EwmaChangeDetector detector = new EwmaChangeDetector(100.0, 2.0, 0.2, 3.0);
        ShiftSignal[] verdicts = detector.scan(stepSignal(60, 30, 100.0, 94.0));

        int alarm = firstAlarm(verdicts);
        assertTrue(alarm >= 30 && alarm <= 36, "alarm at index " + alarm);
        assertEquals(ShiftSignal.DOWNWARD, verdicts[alarm]);
    }

    @Test
    @DisplayName("an alarm returns the statistic to the target so the detector does not latch")
    void resetsTheStatisticOnAnAlarm() {
        EwmaChangeDetector detector = new EwmaChangeDetector(0.0, 1.0, 0.2, 3.0);
        ShiftSignal[] verdicts = detector.scan(stepSignal(40, 0, 2.0, 2.0));

        assertTrue(firstAlarm(verdicts) >= 0);
        assertTrue(detector.alarmCount() > 1, "a sustained shift should keep alarming");
        for (int i = 0; i < verdicts.length; i++) {
            if (verdicts[i].isAlarm() && i + 1 < verdicts.length) {
                assertEquals(ShiftSignal.NONE, verdicts[i + 1], "the sample right after an alarm restarts from the target");
            }
        }
    }

    @Test
    @DisplayName("in-control noise rarely trips the band")
    void staysMostlyQuietOnInControlNoise() {
        Random random = new Random(20240517L);
        EwmaChangeDetector detector = new EwmaChangeDetector(0.0, 1.0, 0.2, 4.0);
        for (int i = 0; i < 2_000; i++) {
            detector.accept(random.nextGaussian());
        }
        assertTrue(detector.alarmCount() <= 3, "raised " + detector.alarmCount() + " false alarms in 2000 samples");
    }

    @Test
    @DisplayName("a shift buried in noise is still found")
    void detectsAShiftInNoisyData() {
        Random random = new Random(4242L);
        double[] signal = new double[400];
        for (int i = 0; i < signal.length; i++) {
            signal[i] = (i < 200 ? 0.0 : 1.5) + random.nextGaussian();
        }

        EwmaChangeDetector detector = new EwmaChangeDetector(0.0, 1.0, 0.2, 4.0);
        ShiftSignal[] verdicts = detector.scan(signal);
        int alarm = firstAlarm(verdicts);
        assertTrue(alarm >= 200, "alarmed before the shift, at index " + alarm);
        assertTrue(alarm < 220, "the shift should be found quickly, but took until " + alarm);
        assertEquals(ShiftSignal.UPWARD, verdicts[alarm]);
    }

    @Test
    void scanReportsOneVerdictPerSample() {
        EwmaChangeDetector detector = new EwmaChangeDetector(0.0, 1.0);
        ShiftSignal[] verdicts = detector.scan(new double[] {0.0, 0.0, 0.0});
        assertEquals(3, verdicts.length);
        assertEquals(3L, detector.count());
    }

    @Test
    void resetRestoresTheInitialState() {
        EwmaChangeDetector detector = new EwmaChangeDetector(4.0, 1.0, 0.5, 3.0);
        detector.scan(stepSignal(20, 0, 20.0, 20.0));
        detector.reset();

        assertEquals(4.0, detector.statistic());
        assertEquals(0.0, detector.controlLimit(), 1e-12);
        assertEquals(0L, detector.count());
        assertEquals(0L, detector.alarmCount());
        assertEquals(ShiftSignal.NONE, detector.lastSignal());
    }

    @Test
    void toStringMentionsTheState() {
        EwmaChangeDetector detector = new EwmaChangeDetector(7.0, 1.0);
        assertTrue(detector.toString().contains("target=7.0"), detector.toString());
    }

    @Test
    void rejectsNonFiniteConfiguration() {
        assertThrows(IllegalArgumentException.class, () -> new EwmaChangeDetector(0.0, Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> new EwmaChangeDetector(0.0, Double.POSITIVE_INFINITY));
        assertThrows(IllegalArgumentException.class, () -> new EwmaChangeDetector(0.0, 1.0, 0.2, Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> new EwmaChangeDetector(0.0, 1.0, 0.2, Double.POSITIVE_INFINITY));
    }
}
