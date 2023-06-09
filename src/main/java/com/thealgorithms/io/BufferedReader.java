package com.thealgorithms.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Mimics the actions of the Original buffered reader
 * implements other actions, such as peek(n) to lookahead,
 * block() to read a chunk of size {BUFFER SIZE}
 * <p>
 * Author: Kumaraswamy B.G (Xoma Dev)
 */
public class BufferedReader {

    private static final int DEFAULT_BUFFER_SIZE = 5;

    /**
     * Maximum number of bytes the buffer can hold.
     * Value is changed when encountered Eof to not
     * cause overflow read of 0 bytes
     */

    private int bufferSize;
    private final byte[] buffer;

    /**
     * posRead -> indicates the next byte to read
     */
    private int posRead = 0, bufferPos = 0;

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
        if (input.available() == -1) throw new IOException("Empty or already closed stream provided");

        this.bufferSize = bufferSize;
        buffer = new byte[bufferSize];
    }

    /**
     * Reads a single byte from the stream
     */
    public int read() throws IOException {
        if (needsRefill()) {
            if (foundEof) return -1;
            // the buffer is empty, or the buffer has
            // been completely read and needs to be refilled
            refill();
        }
        return buffer[posRead++] & 0xff; // read and un-sign it
    }

    /**
     * Number of bytes not yet been read
     */

    public int available() throws IOException {
        int available = input.available();
        if (needsRefill())
            // since the block is already empty,
            // we have no responsibility yet
            return available;
        return bufferPos - posRead + available;
    }

    /**
     * Returns the next character
     */

    public int peek() throws IOException {
        return peek(1);
    }

    /**
     * Peeks and returns a value located at next {n}
     */

    public int peek(int n) throws IOException {
        int available = available();
        if (n >= available) throw new IOException("Out of range, available %d, but trying with %d".formatted(available, n));
        pushRefreshData();

        if (n >= bufferSize) throw new IllegalAccessError("Cannot peek %s, maximum upto %s (Buffer Limit)".formatted(n, bufferSize));
        return buffer[n];
    }

    /**
     * Removes the already read bytes from the buffer
     * in-order to make space for new bytes to be filled up.
     * <p>
     * This may also do the job to read first time data (whole buffer is empty)
     */

    private void pushRefreshData() throws IOException {
        for (int i = posRead, j = 0; i < bufferSize; i++, j++) buffer[j] = buffer[i];

        bufferPos -= posRead;
        posRead = 0;

        // fill out the spaces that we've
        // emptied
        justRefill();
    }

    /**
     * Reads one complete block of size {bufferSize}
     * if found eof, the total length of array will
     * be that of what's available
     *
     * @return a completed block
     */
    public byte[] readBlock() throws IOException {
        pushRefreshData();

        byte[] cloned = new byte[bufferSize];
        // arraycopy() function is better than clone()
        if (bufferPos >= 0)
            System.arraycopy(buffer, 0, cloned, 0,
                // important to note that, bufferSize does not stay constant
                // once the class is defined. See justRefill() function
                bufferSize);
        // we assume that already a chunk
        // has been read
        refill();
        return cloned;
    }

    private boolean needsRefill() {
        return bufferPos == 0 || posRead == bufferSize;
    }

    private void refill() throws IOException {
        posRead = 0;
        bufferPos = 0;
        justRefill();
    }

    private void justRefill() throws IOException {
        assertStreamOpen();

        // try to fill in the maximum we can until
        // we reach EOF
        while (bufferPos < bufferSize) {
            int read = input.read();
            if (read == -1) {
                // reached end-of-file, no more data left
                // to be read
                foundEof = true;
                // rewrite the BUFFER_SIZE, to know that we've reached
                // EOF when requested refill
                bufferSize = bufferPos;
            }
            buffer[bufferPos++] = (byte) read;
        }
    }

    private void assertStreamOpen() {
        if (input == null) throw new IllegalStateException("Input Stream already closed!");
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
