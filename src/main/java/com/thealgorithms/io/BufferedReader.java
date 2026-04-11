package com.thealgorithms.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Mimics the behavior of a buffered reader with additional features like peek(n)
 * and block reading.
 *
 * <p>Provides lookahead functionality and efficient buffered reading.
 *
 * Author: Kumaraswamy B.G (Xoma Dev)
 */
public class BufferedReader {

    private static final int DEFAULT_BUFFER_SIZE = 5;

    private int bufferSize;
    private final byte[] buffer;

    private int posRead = 0;
    private int bufferPos = 0;

    private boolean foundEof = false;

    private InputStream input;

    public BufferedReader(byte[] input) throws IOException {
        this(new ByteArrayInputStream(input));
    }

    public BufferedReader(InputStream input) throws IOException {
        this(input, DEFAULT_BUFFER_SIZE);
    }

    public BufferedReader(InputStream input, int bufferSize) throws IOException {
        this.input = input;
        if (input.available() == -1) {
            throw new IOException("Empty or closed stream provided");
        }

        this.bufferSize = bufferSize;
        this.buffer = new byte[bufferSize];
    }

    /**
     * Reads a single byte from the stream.
     */
    public int read() throws IOException {
        if (needsRefill()) {
            if (foundEof) {
                return -1;
            }
            refill();
        }
        return buffer[posRead++] & 0xff;
    }

    /**
     * Returns number of bytes available.
     */
    public int available() throws IOException {
        int available = input.available();
        if (needsRefill()) {
            return available;
        }
        return bufferPos - posRead + available;
    }

    /**
     * Returns next byte without consuming it.
     */
    public int peek() throws IOException {
        return peek(1);
    }

    /**
     * Peeks nth byte ahead.
     */
    public int peek(int n) throws IOException {
        int available = available();
        if (n > available) {
            throw new IOException("Out of range: available %d, requested %d".formatted(available, n));
        }

        pushRefreshData();

        if (n > bufferSize) {
            throw new IllegalArgumentException("Cannot peek beyond buffer size: " + bufferSize);
        }

        return buffer[posRead + n - 1] & 0xff;
    }

    /**
     * Shifts unread data and refills buffer.
     */
    private void pushRefreshData() throws IOException {
        int unread = bufferPos - posRead;

        System.arraycopy(buffer, posRead, buffer, 0, unread);

        bufferPos = unread;
        posRead = 0;

        justRefill();
    }

    /**
     * Reads a full block.
     */
    public byte[] readBlock() throws IOException {
        pushRefreshData();

        byte[] result = new byte[bufferPos];
        System.arraycopy(buffer, 0, result, 0, bufferPos);

        refill();
        return result;
    }

    private boolean needsRefill() {
        return posRead >= bufferPos;
    }

    private void refill() throws IOException {
        posRead = 0;
        bufferPos = 0;
        justRefill();
    }

    private void justRefill() throws IOException {
        assertStreamOpen();

        while (bufferPos < bufferSize) {
            int read = input.read();

            if (read == -1) {
                foundEof = true;
                break; // ✅ FIX: stop immediately
            }

            buffer[bufferPos++] = (byte) read;
        }
    }

    private void assertStreamOpen() {
        if (input == null) {
            throw new IllegalStateException("Input stream already closed");
        }
    }

    public void close() throws IOException {
        if (input != null) {
            try {
                input.close();
            } finally {
                input = null;
            }
        }
    }
}
