public class FFT {

    private final double[] cos;
    private final double[] sin;
    private final int N;
    private final int M;

    public FFT(int n) {
        this.N = n;
        this.M = (int) (Math.log(n) / Math.log(2));

        if (n != 1 << this.M) {
            throw new IllegalArgumentException("FFT length must be power of 2");
        }

        this.cos = new double[n / 2];
        this.sin = new double[n / 2];
        for (int i = 0; i < n / 2; i++) {
            this.cos[i] = Math.cos(-2 * Math.PI * i / n);
            this.sin[i] = Math.sin(-2 * Math.PI * i / n);
        }
    }

    public static void shiftFFT (float[] real, float[] img) {
        if (real == null || img == null) {
            throw new NullPointerException("Buffers can't be null");
        }
        if (real.length != img.length) {
            throw new IllegalArgumentException("Different buffer size");
        }
        int size = real.length;
        if (size % 2 != 0) {
            throw new IllegalArgumentException("Buffer size must be even");
        }
        for (int i = 0; i < size / 2; i++) {
            float tempReal = real[i];
            float tempImg = img[i];
            real[i] = real[i + size / 2];
            img[i] = img[i + size / 2];
            real[i + size / 2] = tempReal;
            img[i + size / 2] = tempImg;
        }
    }

    public void fft (float[] x, float[] y) {
        int i, j, k, n1, n2, a;
        double c, s, t1, t2;
        // Bit-reverse
        j = 0;
        n2 = this.N / 2;
        for (i = 1; i < this.N - 1; i++) {
            n1 = n2;
            while (j >= n1) {
                j = j - n1;
                n1 = n1 / 2;
            }
            j = j + n1;

            if (i < j) {
                t1 = x[i];
                x[i] = x[j];
                x[j] = (float) t1;
                t1 = y[i];
                y[i] = y[j];
                y[j] = (float) t1;
            }
        }
        // FFT
        n1 = 0;
        n2 = 1;
        for (i = 0; i < this.M; i++) {
            n1 = n2;
            n2 = n2 + n2;
            a = 0;

            for (j = 0; j < n1; j++) {
                c = this.cos[a];
                s = this.sin[a];
                a += 1 << this.M - i - 1;

                for (k = j; k < this.N; k = k + n2) {
                    t1 = c * x[k + n1] - s * y[k + n1];
                    t2 = s * x[k + n1] + c * y[k + n1];
                    x[k + n1] = (float) (x[k] - t1);
                    y[k + n1] = (float) (y[k] - t2);
                    x[k] = (float) (x[k] + t1);
                    y[k] = (float) (y[k] + t2);
                }
            }
        }
    }
}
