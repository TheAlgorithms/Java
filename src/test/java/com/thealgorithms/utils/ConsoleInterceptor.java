package com.thealgorithms.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Utility for mocking System.in and capturing System.out in a single-threaded environment.
 * Designed for use with try-with-resources inside test methods to automatically restore them after usage.
 * <p>
 * Recommended usage:
 * <ul>
 *   <li>Inside test methods with try-with-resources for automatic restoration of streams</li>
 *   <li>
 *       Or as a global instance in the test class, in which case you should call {@link #close()}
 *       for good measures in a teardown method
 *    </li>
 * </ul>
 */
public class ConsoleInterceptor implements AutoCloseable {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    /** Saved reference to stdout */
    private final PrintStream originalOut = System.out;
    /** Saved reference to stdin */
    private final InputStream originalIn = System.in;
    private boolean isCapturing;

    /* ===========================
        Input
    =========================== */

    /**
     * Mock System.in with the provided input.
     * Used in test, mainly for simulating user input from the keyboard for scanners.
     * <p>
     * Each line of user input must end with a newline character (<code>\n</code>),
     * because {@link java.util.Scanner#nextLine()} and similar methods read input line by line.
     * <p>
     * Example input:
     * <p>
     * "This is input line one\nAnd this is the second line of input\nAnd so on...\n"
     */
    public void mockInput(String mockedInput) {
        System.setIn(new ByteArrayInputStream(mockedInput.getBytes()));
    }

    /* ===========================
        Output
    =========================== */

    /**
     * Start capturing System.out by replacing stdout with a custom PrintStream.
     * All printed data will be stored in outContent for later retrieval.
     */
    public void captureOutput() {
        if (!isCapturing) {
            System.setOut(new PrintStream(outContent));
            isCapturing = true;
        }
    }

    /**
     * Get current captured output and clears the buffer.
     * @return the captured output as a string
     * @throws IllegalStateException if output hasn't been captured yet
     */
    public String getAndClearConsoleOutput() {
        if (isCapturing && outContent.size() > 0) {
            String output = outContent.toString();
            outContent.reset();
            return output;
        } else {
            throw new IllegalStateException("Output hasn't been captured yet.");
        }
    }

    /** Clears the output buffer. */
    public void clearConsoleOutput() {
        outContent.reset();
    }

    /* ===========================
        Input And Output
    =========================== */
    /**
     * {@inheritDoc}
     * <p>
     * This override restores the original System.out and System.in streams,
     * resets the captured output stored in the internal OutputStream,
     * and sets the {@code isCapturing} flag to {@code false} to indicate
     * that capturing has stopped and prevent further access to {@code outContent}.
     */
    @Override
    public void close() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        outContent.reset();
        isCapturing = false;
    }
}
