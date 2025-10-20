package com.thealgorithms.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleInterceptor {
    ByteArrayOutputStream outContent;
    PrintStream originalOut;

    /* ===========================
        Input And Output
    =========================== */

    public void mockInputAndCaptureOutput(String mockedInput) {
        mockInput(mockedInput);
        captureOutput();
    }

    public void restoreInAndOutput() {
        restoreInput();
        restoreOutput();
    }

    /* ===========================
        Input
    =========================== */

    public void mockInput(String mockedInput) {
        System.setIn(new ByteArrayInputStream(mockedInput.getBytes()));
    }

    public void restoreInput() {
        System.setIn(System.in);
    }

    /* ===========================
        Output
    =========================== */

    public void captureOutput() {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    public void restoreOutput() {
        System.setOut(originalOut);
    }

    public String getConsoleOutput() {
        return outContent.toString().trim().replace("\n", "");
    }
}
