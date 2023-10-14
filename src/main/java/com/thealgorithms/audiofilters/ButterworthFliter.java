import audio_filters.iir_filter.IIRFilter;
import static java.lang.Math.*;

/**
 * This class creates various types of IIR filters based on the Audio EQ Cookbook.
 * More details can be found at https://webaudio.github.io/Audio-EQ-Cookbook/audio-eq-cookbook.html
 */
public class FilterCreator {

    /**
     * Creates a low-pass filter.
     *
     * @param frequency  the cutoff frequency of the filter
     * @param samplerate the sampling rate of the audio
     * @param qFactor    the Q factor of the filter
     * @return an IIRFilter object representing the low-pass filter
     */
    public static IIRFilter makeLowpass(int frequency, int samplerate, double qFactor) {
        double tau = 2 * PI;
        double w0 = tau * frequency / samplerate;
        double sin = sin(w0);
        double cos = cos(w0);
        double alpha = sin / (2 * qFactor);

        double b0 = (1 - cos) / 2;
        double b1 = 1 - cos;

        double a0 = 1 + alpha;
        double a1 = -2 * cos;
        double a2 = 1 - alpha;

        IIRFilter filt = new IIRFilter(2);
        filt.setCoefficients(new double[]{a0, a1, a2}, new double[]{b0, b1, b0});
        return filt;
    }

    /**
     * Creates a high-pass filter.
     *
     * @param frequency  the cutoff frequency of the filter
     * @param samplerate the sampling rate of the audio
     * @param qFactor    the Q factor of the filter
     * @return an IIRFilter object representing the high-pass filter
     */
    public static IIRFilter makeHighpass(int frequency, int samplerate, double qFactor) {
        double tau = 2 * PI;
        double w0 = tau * frequency / samplerate;
        double sin = sin(w0);
        double cos = cos(w0);
        double alpha = sin / (2 * qFactor);

        double b0 = (1 + cos) / 2;
        double b1 = -1 - cos;

        double a0 = 1 + alpha;
        double a1 = -2 * cos;
        double a2 = 1 - alpha;

        IIRFilter filt = new IIRFilter(2);
        filt.setCoefficients(new double[]{a0, a1, a2}, new double[]{b0, b1, b0});
        return filt;
    }

    // Implement other methods using the same structure.

    public static void main(String[] args) {
        // Example usage:
        int frequency = 1000;
        int samplerate = 48000;
        double qFactor = 1 / sqrt(2);

        IIRFilter lowpassFilter = makeLowpass(frequency, samplerate, qFactor);
        System.out.println(lowpassFilter.toString());
    }
}
