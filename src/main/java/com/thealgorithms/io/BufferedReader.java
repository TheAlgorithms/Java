package com.thealgorithms.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Enhanced and optimized BufferedReader with additional functionalities.
 *
 * This class provides a more versatile and efficient way to read data from input
 * streams, offering features like peek, block reading, various data type
 * handling, and advanced error management.
 *
 * @author Kumaraswamy B.G (Xoma Dev)
 */
public class BufferedReader {

    private static final int DEFAULT_BUFFER_SIZE = 8192; // 8KB

    private int bufferSize;
    private final byte[] buffer;

    private int posRead = 0;
    private int bufferPos = 0;

    private boolean foundEof = false;

    private InputStream input;

    /**
     * Constructs a BufferedReader using a byte array as input.
     *
     * @param input The byte array containing the data to read.
     * @throws IOException If an error occurs during initialization.
     */
    public BufferedReader(byte[] input) throws IOException {
        this(new ByteArrayInputStream(input));
    }

    /**
     * Constructs a BufferedReader using an InputStream as input.
     *
     * @param input The InputStream to read from.
     * @throws IOException If an error occurs during initialization.
     */
    public BufferedReader(InputStream input) throws IOException {
        this(input, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Constructs a BufferedReader with a specified buffer size.
     *
     * @param input       The InputStream to read from.
     * @param bufferSize The buffer size in bytes.
     * @throws IOException If an error occurs during initialization.
     */
    public BufferedReader(InputStream input, int bufferSize) throws IOException {
        this.input = input;
        if (input.available() == -1) {
            throw new IOException("Empty or already closed stream provided");
        }

        this.bufferSize = bufferSize;
        buffer = new byte[bufferSize];
    }

    /**
     * Reads a single byte from the stream.
     *
     * @return The byte read as an integer, or -1 if the end of the stream is
     * reached.
     * @throws IOException If an I/O error occurs.
     */
    public int read() throws IOException {
        if (needsRefill()) {
            if (foundEof) {
                return -1;
            }
            refill();
        }
        return buffer[posRead++] & 0xff; // Read and unsign it
    }

    /**
     * Returns the number of bytes that can be read without blocking.
     *
     * @return The number of bytes available.
     * @throws IOException If an I/O error occurs.
     */
    public int available() throws IOException {
        int available = input.available();
        if (needsRefill()) {
            return available; // No responsibility for empty block
        }
        return bufferPos - posRead + available;
    }

    /**
     * Peeks at the next byte in the stream without consuming it.
     *
     * @return The next byte as an integer, or -1 if the end of the stream is
     * reached.
     * @throws IOException If an I/O error occurs.
     */
    public int peek() throws IOException {
        return peek(1);
    }

    /**
     * Peeks at the specified number of bytes ahead in the stream without
     * consuming them.
     *
     * @param n The number of bytes to peek ahead.
     * @return The byte at the specified position as an integer, or -1 if the
     * end of the stream is reached.
     * @throws IOException If an I/O error occurs or if the peek position is out
     * of bounds.
     */
    public int peek(int n) throws IOException {
        int available = available();
        if (n >= available) {
            throw new IOException("Out of range, available %d, but trying with %d".formatted(available, n));
        }
        pushRefreshData();

        if (n >= bufferSize) {
            throw new IllegalAccessError("Cannot peek %s, maximum upto %s (Buffer Limit)".formatted(n, bufferSize));
        }
        return buffer[n];
    }

    /**
     * Reads a specified number of bytes from the stream into a byte array.
     *
     * @param n The number of bytes to read.
     * @return The byte array containing the read bytes.
     * @throws IOException If an I/O error occurs.
     */
    public byte[] readBytes(int n) throws IOException {
        if (n <= 0) {
            return new byte[0]; // Handle empty or negative read requests
        }
        byte[] bytes = new byte[n];
        int read = 0;
        while (read < n) {
            int currentRead = read();
            if (currentRead == -1) {
                break; // End of stream
            }
            bytes[read++] = (byte) currentRead;
        }
        if (read < n) {
            // If EOF is encountered before reading all bytes, return a
            // partially filled array
            return Arrays.copyOf(bytes, read);
        }
        return bytes;
    }

    /**
     * Reads a line of text from the stream.
     *
     * @return The line of text, or null if the end of the stream is reached.
     * @throws IOException If an I/O error occurs.
     */
    public String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        int currentByte;
        while ((currentByte = read()) != -1) {
            if (currentByte == '\n') {
                return sb.toString(); // Found line terminator
            } else if (currentByte == '\r') {
                // Handle both \r\n and \r line endings
                if (peek() == '\n') {
                    read(); // Consume the newline
                }
                return sb.toString();
            }
            sb.append((char) currentByte);
        }
        return sb.length() > 0 ? sb.toString() : null; // Handle EOF with partial line
    }

    /**
     * Reads a string from the stream, delimited by the specified delimiter.
     *
     * @param delimiter The delimiter to use for separating strings.
     * @return The read string, or null if the end of the stream is reached or
     * no delimiter is found.
     * @throws IOException If an I/O error occurs.
     */
    public String readString(char delimiter) throws IOException {
        StringBuilder sb = new StringBuilder();
        int currentByte;
        while ((currentByte = read()) != -1) {
            if (currentByte == delimiter) {
                return sb.toString(); // Delimiter found
            }
            sb.append((char) currentByte);
        }
        return sb.length() > 0 ? sb.toString() : null; // Handle EOF with partial string
    }

    /**
     * Reads an integer value from the stream.
     *
     * @return The read integer value.
     * @throws IOException If an I/O error occurs.
     */
    public int readInt() throws IOException {
        return Integer.parseInt(readString(' '));
    }

    /**
     * Reads a long value from the stream.
     *
     * @return The read long value.
     * @throws IOException If an I/O error occurs.
     */
    public long readLong() throws IOException {
        return Long.parseLong(readString(' '));
    }

    /**
     * Reads a float value from the stream.
     *
     * @return The read float value.
     * @throws IOException If an I/O error occurs.
     */
    public float readFloat() throws IOException {
        return Float.parseFloat(readString(' '));
    }

    /**
     * Reads a double value from the stream.
     *
     * @return The read double value.
     * @throws IOException If an I/O error occurs.
     */
    public double readDouble() throws IOException {
        return Double.parseDouble(readString(' '));
    }

    /**
     * Reads a boolean value from the stream.
     *
     * @return The read boolean value.
     * @throws IOException If an I/O error occurs.
     */
    public boolean readBoolean() throws IOException {
        return Boolean.parseBoolean(readString(' '));
    }

    /**
     * Reads a byte array from the stream, using the specified length.
     *
     * @param length The length of the byte array to read.
     * @return The read byte array.
     * @throws IOException If an I/O error occurs.
     */
    public byte[] readByteArray(int length) throws IOException {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) read();
        }
        return bytes;
    }

    /**
     * Reads a string from the stream with the specified encoding.
     *
     * @param charset The encoding to use for reading the string.
     * @return The read string.
     * @throws IOException If an I/O error occurs.
     */
    public String readString(Charset charset) throws IOException {
        byte[] bytes = readBytes(available());
        return new String(bytes, charset);
    }

    /**
     * Reads a string from the stream using UTF-8 encoding.
     *
     * @return The read string.
     * @throws IOException If an I/O error occurs.
     */
    public String readString() throws IOException {
        return readString(StandardCharsets.UTF_8);
    }

    /**
     * Reads the remaining bytes from the stream and returns them as a byte
     * array.
     *
     * @return The byte array containing the remaining bytes from the stream.
     * @throws IOException If an I/O error occurs.
     */
    public byte[] readAllBytes() throws IOException {
        byte[] bytes = readBytes(available());
        while (available() > 0) {
            byte[] remaining = readBytes(available());
            bytes = Arrays.copyOf(bytes, bytes.length + remaining.length);
            System.arraycopy(remaining, 0, bytes, bytes.length - remaining.length, remaining.length);
        }
        return bytes;
    }

    /**
     * Reads the remaining bytes from the stream and returns them as a string
     * using UTF-8 encoding.
     *
     * @return The string containing the remaining bytes from the stream.
     * @throws IOException If an I/O error occurs.
     */
    public String readAll() throws IOException {
        return new String(readAllBytes(), StandardCharsets.UTF_8);
    }

    /**
     * Reads the entire content of the stream into a ByteBuffer.
     *
     * @return The ByteBuffer containing the content of the stream.
     * @throws IOException If an I/O error occurs.
     */
    public ByteBuffer readAllByteBuffer() throws IOException {
        byte[] bytes = readAllBytes();
        return ByteBuffer.wrap(bytes);
    }

    /**
     * Reads a block of data from the stream into a byte array.
     *
     * @return The byte array containing the read block of data.
     * @throws IOException If an I/O error occurs.
     */
    public byte[] readBlock() throws IOException {
        pushRefreshData();

        byte[] cloned = new byte[bufferSize];
        if (bufferPos >= 0) {
            System.arraycopy(buffer, 0, cloned, 0, bufferSize);
        }
        refill();
        return cloned;
    }

    /**
     * Removes already read bytes from the buffer to make space for new data.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void pushRefreshData() throws IOException {
        for (int i = posRead, j = 0; i < bufferSize; i++, j++) {
            buffer[j] = buffer[i];
        }

        bufferPos -= posRead;
        posRead = 0;

        justRefill();
    }

    /**
     * Checks if the buffer needs to be refilled.
     *
     * @return True if the buffer needs to be refilled, false otherwise.
     */
    private boolean needsRefill() {
        return bufferPos == 0 || posRead == bufferSize;
    }

    /**
     * Refills the buffer from the input stream.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void refill() throws IOException {
        posRead = 0;
        bufferPos = 0;
        justRefill();
    }

    /**
     * Refills the buffer with data from the input stream.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void justRefill() throws IOException {
        assertStreamOpen();

        while (bufferPos < bufferSize) {
            int read = input.read();
            if (read == -1) {
                foundEof = true;
                bufferSize = bufferPos; // Update bufferSize for EOF
                break; // Stop refilling when EOF is reached
            }
            buffer[bufferPos++] = (byte) read;
        }
    }

    /**
     * Ensures that the input stream is not closed.
     */
    private void assertStreamOpen() {
        if (input == null) {
            throw new IllegalStateException("Input Stream already closed!");
        }
    }

    /**
     * Closes the input stream.
     *
     * @throws IOException If an I/O error occurs.
     */
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