package com.thealgorithms.audiofilters;

public class ButterworthDesign {
    enum FilterType {
        LOWPASS,
        HIGHPASS,
        BANDPASS,  // using peak gain = Q to match LPF/HPF
        ALLPASS,
        LOWSHELF,
        HIGHSHELF,
        NOTCH,
        PEAK,
    }

    /**
     * Design a 2nd-order IIR filter with the given parameters using the Butterworth method.
     * https://en.wikipedia.org/wiki/Butterworth_filter
     *
     * @param type The type of filter to design
     * @param cutoff The significant frequency
     * @param samplerate The sample rate of the filter
     * @param bandwidth The bandwidth of the filter in octaves (recommended: 1.9)
     * @param dbGain The gain of the filter in decibels (only used for Lowshelf, Highshelf and Peak)
     * @return
     */
    public static IIRFilter design(FilterType type, double cutoff, double samplerate, double bandwidth, double dbGain) {
        IIRFilter filter = new IIRFilter(2);
        double w0 = 2 * Math.PI * cutoff / samplerate;
        double alpha = Math.sin(w0) * Math.sinh(Math.log(2) / 2 * bandwidth * w0 / Math.sin(w0));
        double qFactor = Math.sin(w0) / (2 * alpha);  // EE definition of Q
        double amp = Math.pow(10.0, dbGain / 40.0);

        switch (type) {
            case LOWPASS -> designLowpass(filter, alpha, w0);
            case HIGHPASS -> designHighpass(filter, alpha, w0);
            case BANDPASS -> designBandpass(filter, alpha, w0, qFactor);
            case ALLPASS -> designAllpass(filter, alpha, w0);
            case LOWSHELF -> designLowshelf(filter, alpha, w0, amp);
            case HIGHSHELF -> designHighshelf(filter, alpha, w0, amp);
            case NOTCH -> designNotch(filter, alpha, w0);
            case PEAK -> designPeak(filter, alpha, w0, amp);
        }

        return filter;
    }

    private static void designLowpass(IIRFilter filter, double alpha, double w0) {
        // H(s) = \frac{1}{s^2 + \frac{s}{Q} + 1}
        double cosw0 = Math.cos(w0);
        filter.setCoeffs(
                new double[]{1 + alpha, -2 * cosw0, 1 - alpha},
                new double[]{(1 - cosw0) / 2, 1 - cosw0, (1 - cosw0) / 2}
        );
    }

    private static void designHighpass(IIRFilter filter, double alpha, double w0) {
        // H(s) = \frac{s^2}{s^2 + \frac{s}{Q} + 1}
        double cosw0 = Math.cos(w0);
        filter.setCoeffs(
                new double[]{1 + alpha, -2 * cosw0, 1 - alpha},
                new double[]{(1 + cosw0) / 2, -1 - cosw0, (1 + cosw0) / 2}
        );
    }

    private static void designBandpass(IIRFilter filter, double alpha, double w0, double qFactor) {
        // H(s) = \frac{s}{s^2 + \frac{s}{Q} + 1}
        filter.setCoeffs(
                new double[]{1 + alpha, -2 * Math.cos(w0), 1 - alpha},
                new double[]{qFactor * alpha, 0, -qFactor * alpha}
        );
    }

    private static void designAllpass(IIRFilter filter, double alpha, double w0) {
        // H(s) = \frac{s^2 - \frac{s}{Q} + 1}{s^2 + \frac{s}{Q} + 1}
        filter.setCoeffs(
                new double[]{1 + alpha, -2 * Math.cos(w0), 1 - alpha},
                new double[]{1 - alpha, -2 * Math.cos(w0), 1 + alpha}
        );
    }

    private static void designLowshelf(IIRFilter filter, double alpha, double w0, double amp) {
        // H(s) = A \frac{s^2 + \sqrt{A} \frac{s}{Q} + A}{As^2 + \sqrt{A} \frac{s}{Q} + 1}
        double cosw0 = Math.cos(w0);
        double beta = 2 * alpha * Math.sqrt(amp);
        filter.setCoeffs(
                new double[]{amp + 1 + (amp - 1) * cosw0 + beta, -2 * (amp - 1 + (amp + 1) * cosw0), amp + 1 + (amp - 1) * cosw0 - beta},
                new double[]{amp * (amp + 1 - (amp - 1) * cosw0 + beta), -2 * amp * (amp - 1 - (amp + 1) * cosw0), amp * (amp + 1 - (amp - 1) * cosw0 - beta)}
        );
    }

    private static void designHighshelf(IIRFilter filter, double alpha, double w0, double amp) {
        // H(s) = A \frac{As^2 + \sqrt{A} \frac{s}{Q} + 1}{s^2 + \sqrt{A} \frac{s}{Q} + A}
        double cosw0 = Math.cos(w0);
        double beta = 2 * alpha * Math.sqrt(amp);
        filter.setCoeffs(
                new double[]{amp + 1 - (amp - 1) * cosw0 + beta, 2 * (amp - 1 - (amp + 1) * cosw0), amp + 1 - (amp - 1) * cosw0 - beta},
                new double[]{amp * (amp + 1 + (amp - 1) * cosw0 + beta), -2 * amp * (amp - 1 + (amp + 1) * cosw0), amp * (amp + 1 + (amp - 1) * cosw0 - beta)}
        );
    }

    private static void designNotch(IIRFilter filter, double alpha, double w0) {
        // H(s) = \frac{s^2 + 1}{s^2 + \frac{s}{Q} + 1}
        double cosw0 = Math.cos(w0);
        filter.setCoeffs(
                new double[]{1 + alpha, -2 * cosw0, 1 - alpha},
                new double[]{1, -2 * cosw0, 1}
        );
    }

    private static void designPeak(IIRFilter filter, double alpha, double w0, double amp) {
        // H(s) = \frac{s^2 + A \frac{s}{Q} + 1}{s^2 + \frac{s}{AQ} + 1}
        double cosw0 = Math.cos(w0);
        filter.setCoeffs(
                new double[]{1 + alpha / amp, -2 * cosw0, 1 - alpha / amp},
                new double[]{1 + alpha * amp, -2 * cosw0, 1 - alpha * amp}
        );
    }
}
