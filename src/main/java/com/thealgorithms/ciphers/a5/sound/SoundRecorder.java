package com.thealgorithms.ciphers.a5.sound;

// https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-os/src/main/java/com/baeldung/example/soundapi/SoundRecorder.java

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class SoundRecorder implements Runnable {
    private AudioInputStream audioInputStream;
    private AudioFormat format;
    public Thread thread;
    private double duration;
    private byte[] output;

    public SoundRecorder() {
        super();
    }

    public SoundRecorder(AudioFormat format) {
        this.format = format;
    }

    public SoundRecorder build(AudioFormat format) {
        this.format = format;
        return this;
    }

    public void start() {
        thread = new Thread(this);
        thread.setName("Capture Microphone");
        thread.start();
    }

    public void stop() {
        thread = null;
    }

    @Override
    public void run() {
        duration = 0;

        try (final ByteArrayOutputStream out = new ByteArrayOutputStream(); final TargetDataLine line = getTargetDataLineForRecord();) {

            int frameSizeInBytes = format.getFrameSize();
            int bufferLengthInFrames = line.getBufferSize() / 8;
            final int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
            buildByteOutputStream(out, line, frameSizeInBytes, bufferLengthInBytes);
            this.audioInputStream = new AudioInputStream(line);
            setAudioInputStream(convertToAudioIStream(out, frameSizeInBytes));
            audioInputStream.reset();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    public void buildByteOutputStream(final ByteArrayOutputStream out, final TargetDataLine line, int frameSizeInBytes, final int bufferLengthInBytes) throws IOException {
        final byte[] data = new byte[bufferLengthInBytes];
        int numBytesRead;

        line.start();
        while (thread != null) {
            if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
                break;
            }
            out.write(data, 0, numBytesRead);
        }
    }

    public void setAudioInputStream(AudioInputStream aStream) {
        this.audioInputStream = aStream;
    }

    public AudioInputStream convertToAudioIStream(final ByteArrayOutputStream out, int frameSizeInBytes) {
        byte[] audioBytes = out.toByteArray();
        this.output = Arrays.copyOf( audioBytes, audioBytes.length );
        ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
        AudioInputStream audioStream = new AudioInputStream(bais, format, audioBytes.length / frameSizeInBytes);
        long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / format.getFrameRate());
        duration = milliseconds / 1000.0;
        System.out.println("Recorded duration in seconds:" + duration);
        return audioStream;
    }

    public TargetDataLine getTargetDataLineForRecord() {
        TargetDataLine line;
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if (!AudioSystem.isLineSupported(info)) {
            return null;
        }
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format, line.getBufferSize());
        } catch (final Exception ex) {
            return null;
        }
        return line;
    }

    public AudioInputStream getAudioInputStream() {
        return audioInputStream;
    }

    public byte[] getByteAudio() {
        return output;
    }

    public AudioFormat getFormat() {
        return format;
    }

    public void setFormat(AudioFormat format) {
        this.format = format;
    }

    public Thread getThread() {
        return thread;
    }

    public double getDuration() {
        return duration;
    }
}
