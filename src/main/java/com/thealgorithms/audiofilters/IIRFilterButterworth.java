package com.thealgorithms.audiofilters;

public class IIRFilterButterworth {

    // Constants
    private static final double tau = 6.283185307179586;

    // Lowpass Filter
    public static IIRFilter makeLowpass(int frequency, int samplerate, double qFactor) {
        double w0 = tau * frequency / samplerate;
        double sin = Math.sin(w0);
        double cos = Math.cos(w0);
        double alpha = sin / (2 * qFactor);

        double b0 = (1 - cos) / 2;
        double b1 = 1 - cos;

        double a0 = 1 + alpha;
        double a1 = -2 * cos;
        double a2 = 1 - alpha;

        IIRFilter filt = new IIRFilter(2);
        filt.setCoeffs(new double[] {a0, a1, a2}, new double[] {b0, b1, b0});
        return filt;
    }

    // Highpass Filter
    public static IIRFilter makeHighpass(int frequency, int samplerate, double qFactor) {
        double w0 = tau * frequency / samplerate;
        double sin = Math.sin(w0);
        double cos = Math.cos(w0);
        double alpha = sin / (2 * qFactor);

        double b0 = (1 + cos) / 2;
        double b1 = -1 - cos;

        double a0 = 1 + alpha;
        double a1 = -2 * cos;
        double a2 = 1 - alpha;

        IIRFilter filt = new IIRFilter(2);
        filt.setCoeffs(new double[] {a0, a1, a2}, new double[] {b0, b1, b0});
        return filt;
    }

    // Bandpass Filter
    public static IIRFilter makeBandpass(int frequency, int samplerate, double qFactor) {
        double w0 = tau * frequency / samplerate;
        double sin = Math.sin(w0);
        double cos = Math.cos(w0);
        double alpha = sin / (2 * qFactor);

        double b0 = sin / 2;
        double b1 = 0;
        double b2 = -b0;

        double a0 = 1 + alpha;
        double a1 = -2 * cos;
        double a2 = 1 - alpha;

        IIRFilter filt = new IIRFilter(2);
        filt.setCoeffs(new double[] {a0, a1, a2}, new double[] {b0, b1, b2});
        return filt;
    }

    // Allpass Filter
    public static IIRFilter makeAllpass(int frequency, int samplerate, double qFactor) {
        double w0 = tau * frequency / samplerate;
        double sin = Math.sin(w0);
        double cos = Math.cos(w0);
        double alpha = sin / (2 * qFactor);

        double b0 = 1 - alpha;
        double b1 = -2 * cos;
        double b2 = 1 + alpha;

        IIRFilter filt = new IIRFilter(2);
        filt.setCoeffs(new double[] {b2, b1, b0}, new double[] {b0, b1, b2});
        return filt;
    }

    // Peak Filter
    public static IIRFilter makePeak(int frequency, int samplerate, double gainDB, double qFactor) {
        double w0 = tau * frequency / samplerate;
        double sin = Math.sin(w0);
        double cos = Math.cos(w0);
        double alpha = sin / (2 * qFactor);
        double bigA = Math.pow(10, gainDB / 40);

        double b0 = 1 + alpha * bigA;
        double b1 = -2 * cos;
        double b2 = 1 - alpha * bigA;
        double a0 = 1 + alpha / bigA;
        double a1 = -2 * cos;
        double a2 = 1 - alpha / bigA;

        IIRFilter filt = new IIRFilter(2);
        filt.setCoeffs(new double[] {a0, a1, a2}, new double[] {b0, b1, b2});
        return filt;
    }

    // Lowshelf Filter
    public static IIRFilter makeLowshelf(int frequency, int samplerate, double gainDB, double qFactor) {
        double w0 = tau * frequency / samplerate;
        double sin = Math.sin(w0);
        double cos = Math.cos(w0);
        double alpha = sin / (2 * qFactor);
        double bigA = Math.pow(10, gainDB / 40);
        double pmc = (bigA + 1) - (bigA - 1) * cos;
        double ppmc = (bigA + 1) + (bigA - 1) * cos;
        double mpc = (bigA - 1) - (bigA + 1) * cos;
        double pmpc = (bigA - 1) + (bigA + 1) * cos;
        double aa2 = 2 * Math.sqrt(bigA) * alpha;

        double b0 = bigA * (pmc + aa2);
        double b1 = 2 * bigA * mpc;
        double b2 = bigA * (pmc - aa2);
        double a0 = ppmc + aa2;
        double a1 = -2 * pmpc;
        double a2 = ppmc - aa2;

        IIRFilter filt = new IIRFilter(2);
        filt.setCoeffs(new double[] {a0, a1, a2}, new double[] {b0, b1, b2});
        return filt;
    }

    // Highshelf Filter
    public static IIRFilter makeHighshelf(int frequency, int samplerate, double gainDB, double qFactor) {
        double w0 = tau * frequency / samplerate;
        double sin = Math.sin(w0);
        double cos = Math.cos(w0);
        double alpha = sin / (2 * qFactor);
        double bigA = Math.pow(10, gainDB / 40);
        double pmc = (bigA + 1) - (bigA - 1) * cos;
        double ppmc = (bigA + 1) + (bigA - 1) * cos;
        double mpc = (bigA - 1) - (bigA + 1) * cos;
        double pmpc = (bigA - 1) + (bigA + 1) * cos;
        double aa2 = 2 * Math.sqrt(bigA) * alpha;

        double b0 = bigA * (ppmc + aa2);
        double b1 = -2 * bigA * pmpc;
        double b2 = bigA * (ppmc - aa2);
        double a0 = pmc + aa2;
        double a1 = 2 * mpc;
        double a2 = pmc - aa2;

        IIRFilter filt = new IIRFilter(2);
        filt.setCoeffs(new double[] {a0, a1, a2}, new double[] {b0, b1, b2});
        return filt;
    }
}
