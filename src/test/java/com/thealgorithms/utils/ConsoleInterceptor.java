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
 *   <li>Or as a global instance in the test class, in which case you must call {@link #close()} in a teardown method</li>
 * </ul>
 */
public class ConsoleInterceptor implements AutoCloseable {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private boolean isCapturing;

    /* ===========================
        Input
    =========================== */

    /** Mock System.in with provided input */
    public void mockInput(String mockedInput) {
        System.setIn(new ByteArrayInputStream(mockedInput.getBytes()));
    }

    /* ===========================
        Output
    =========================== */

    /** Start capturing System.out */
    public void captureOutput() {
        if (!isCapturing) {
            System.setOut(new PrintStream(outContent));
            isCapturing = true;
        }
    }

    /**
     * Get current captured output and clear the buffer
     * @return the console output as a string
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

    /** Clears the output buffer */
    public void clearConsoleOutput() {
        outContent.reset();
    }

    /* ===========================
        Input And Output
    =========================== */

    @Override
    public void close() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        outContent.reset();
        isCapturing = false;
    }
}
