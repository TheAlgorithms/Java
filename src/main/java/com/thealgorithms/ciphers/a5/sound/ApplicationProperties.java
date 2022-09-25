package com.thealgorithms.ciphers.a5.sound;

import javax.sound.sampled.AudioFormat;

public class ApplicationProperties {
    public final AudioFormat.Encoding ENCODING = AudioFormat.Encoding.PCM_SIGNED;
    public final float RATE = 44100.0f;
    public final int CHANNELS = 1;
    public final int SAMPLE_SIZE = 16;
    public final boolean BIG_ENDIAN = true;
}
