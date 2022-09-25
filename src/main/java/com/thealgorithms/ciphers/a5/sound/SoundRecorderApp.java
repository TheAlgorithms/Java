package com.thealgorithms.ciphers.a5.sound;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;

class SoundRecorderApp {
    public static void main(String[] args) throws Exception {

        AudioFormat format = buildAudioFormatInstance();

//        SoundRecorder soundRecorder = new SoundRecorder();
        A5SoundRecorder soundRecorder = new A5SoundRecorder();
        soundRecorder.build(format);

        System.out.println("Start recording ....");
        soundRecorder.start();
        Thread.sleep(3000);
        soundRecorder.stop();

        WaveDataUtil wd = new WaveDataUtil();
        Thread.sleep(3000);
        wd.saveToFile("SoundClip", AudioFileFormat.Type.WAVE, soundRecorder.getAudioInputStream());

        System.out.println("Decrypting...");
        soundRecorder.decryptLastRecording();
        wd = new WaveDataUtil();
        Thread.sleep(3000);
        wd.saveToFile("SoundClipD", AudioFileFormat.Type.WAVE, soundRecorder.getAudioInputStream());
    }

    public static AudioFormat buildAudioFormatInstance() {
        ApplicationProperties aConstants = new ApplicationProperties();
        AudioFormat.Encoding encoding = aConstants.ENCODING;
        float rate = aConstants.RATE;
        int channels = aConstants.CHANNELS;
        int sampleSize = aConstants.SAMPLE_SIZE;
        boolean bigEndian = aConstants.BIG_ENDIAN;

        return new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);
    }
}
