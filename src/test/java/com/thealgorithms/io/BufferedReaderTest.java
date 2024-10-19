package com.thealgorithms.io;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class BufferedReaderTest {

    private static final String TEST_STRING = "Hello, World!\nThis is a test.\n123 456 789";

    @Test
    void testConstructor() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);
        assertEquals(reader.bufferSize, BufferedReader.DEFAULT_BUFFER_SIZE);
        assertEquals(reader.available(), TEST_STRING.length());

        reader = new BufferedReader(input, 1024);
        assertEquals(reader.bufferSize, 1024);
    }

    @Test
    void testRead() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        for (int i = 0; i < TEST_STRING.length(); i++) {
            assertEquals(reader.read(), TEST_STRING.charAt(i));
        }
        assertEquals(reader.read(), -1);
    }

    @Test
    void testAvailable() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        assertEquals(reader.available(), TEST_STRING.length());
        reader.read(); // Consume one byte
        assertEquals(reader.available(), TEST_STRING.length() - 1);
        reader.readBlock();
        assertEquals(reader.available(), TEST_STRING.length() - 1 - reader.bufferSize);
    }

    @Test
    void testPeek() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        assertEquals(reader.peek(), 'H');
        assertEquals(reader.peek(), 'H'); // Peek multiple times should return the same value
        assertEquals(reader.read(), 'H'); // Now consume the byte
        assertEquals(reader.peek(), 'e');
        assertEquals(reader.available(), TEST_STRING.length() - 1);
    }

    @Test
    void testPeekOutOfRange() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        assertThrows(IOException.class, () -> reader.peek(TEST_STRING.length() + 1));
    }

    @Test
    void testReadBytes() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        byte[] expectedBytes = TEST_STRING.getBytes();
        byte[] actualBytes = reader.readBytes(expectedBytes.length);
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    void testReadBytesPartial() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        byte[] expectedBytes = "Hello".getBytes();
        byte[] actualBytes = reader.readBytes(expectedBytes.length);
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    void testReadBytesNegative() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        byte[] actualBytes = reader.readBytes(-1);
        assertEquals(actualBytes.length, 0);
    }

    @Test
    void testReadLine() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        assertEquals(reader.readLine(), "Hello, World!");
        assertEquals(reader.readLine(), "This is a test.");
        assertEquals(reader.readLine(), "123 456 789");
        assertEquals(reader.readLine(), null); // EOF
    }

    @Test
    void testReadStringDelimiter() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        assertEquals(reader.readString(' '), "Hello,");
        assertEquals(reader.readString(' '), "World!");
        assertEquals(reader.readString(' '), "This");
        assertEquals(reader.readString(' '), "is");
        assertEquals(reader.readString(' '), "a");
        assertEquals(reader.readString(' '), "test.");
        assertEquals(reader.readString(' '), "123");
        assertEquals(reader.readString(' '), "456");
        assertEquals(reader.readString(' '), "789");
        assertEquals(reader.readString(' '), null); // EOF
    }

    @Test
    void testReadInt() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream("123456789".getBytes());
        BufferedReader reader = new BufferedReader(input);

        assertEquals(reader.readInt(), 123456789);
    }

    @Test
    void testReadLong() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream("9876543210".getBytes());
        BufferedReader reader = new BufferedReader(input);

        assertEquals(reader.readLong(), 9876543210L);
    }

    @Test
    void testReadFloat() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream("3.14159".getBytes());
        BufferedReader reader = new BufferedReader(input);

        assertEquals(reader.readFloat(), 3.14159f);
    }

    @Test
    void testReadDouble() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream("2.71828".getBytes());
        BufferedReader reader = new BufferedReader(input);

        assertEquals(reader.readDouble(), 2.71828);
    }

    @Test
    void testReadBoolean() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream("true".getBytes());
        BufferedReader reader = new BufferedReader(input);

        assertTrue(reader.readBoolean());

        input = new ByteArrayInputStream("false".getBytes());
        reader = new BufferedReader(input);
        assertFalse(reader.readBoolean());
    }

    @Test
    void testReadByteArray() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        byte[] expectedBytes = "Hello, World!".getBytes();
        byte[] actualBytes = reader.readByteArray(expectedBytes.length);
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    void testReadStringEncoding() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes(StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(input);

        assertEquals(reader.readString(StandardCharsets.UTF_8), TEST_STRING);
    }

    @Test
    void testReadStringDefaultEncoding() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        assertEquals(reader.readString(), TEST_STRING);
    }

    @Test
    void testReadAllBytes() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        byte[] expectedBytes = TEST_STRING.getBytes();
        byte[] actualBytes = reader.readAllBytes();
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    void testReadAll() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        assertEquals(reader.readAll(), TEST_STRING);
    }

    @Test
    void testReadAllByteBuffer() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);

        ByteBuffer expectedBuffer = ByteBuffer.wrap(TEST_STRING.getBytes());
        ByteBuffer actualBuffer = reader.readAllByteBuffer();
        assertEquals(expectedBuffer, actualBuffer);
    }

    @Test
    void testReadBlock() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input, 5);

        byte[] block1 = reader.readBlock();
        assertEquals(new String(block1, StandardCharsets.UTF_8), "Hello");
        byte[] block2 = reader.readBlock();
        assertEquals(new String(block2, StandardCharsets.UTF_8), ", Worl");
        byte[] block3 = reader.readBlock();
        assertEquals(new String(block3, StandardCharsets.UTF_8), "d!\nThi");
        byte[] block4 = reader.readBlock();
        assertEquals(new String(block4, StandardCharsets.UTF_8), "s is a ");
        byte[] block5 = reader.readBlock();
        assertEquals(new String(block5, StandardCharsets.UTF_8), "test.\n12");
        byte[] block6 = reader.readBlock();
        assertEquals(new String(block6, StandardCharsets.UTF_8), "3 456 78");
        byte[] block7 = reader.readBlock();
        assertEquals(new String(block7, StandardCharsets.UTF_8), "9");
    }

    @Test
    void testClose() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(TEST_STRING.getBytes());
        BufferedReader reader = new BufferedReader(input);
        reader.close();

        assertThrows(IllegalStateException.class, () -> reader.read());
        assertThrows(IllegalStateException.class, () -> reader.available());
        assertThrows(IllegalStateException.class, () -> reader.peek());
        assertThrows(IllegalStateException.class, () -> reader.readBytes(10));
        assertThrows(IllegalStateException.class, () -> reader.readLine());
        assertThrows(IllegalStateException.class, () -> reader.readString(' '));
        assertThrows(IllegalStateException.class, () -> reader.readInt());
        assertThrows(IllegalStateException.class, () -> reader.readLong());
        assertThrows(IllegalStateException.class, () -> reader.readFloat());
        assertThrows(IllegalStateException.class, () -> reader.readDouble());
        assertThrows(IllegalStateException.class, () -> reader.readBoolean());
        assertThrows(IllegalStateException.class, () -> reader.readByteArray(10));
        assertThrows(IllegalStateException.class, () -> reader.readString(StandardCharsets.UTF_8));
        assertThrows(IllegalStateException.class, () -> reader.readString());
        assertThrows(IllegalStateException.class, () -> reader.readAllBytes());
        assertThrows(IllegalStateException.class, () -> reader.readAll());
        assertThrows(IllegalStateException.class, () -> reader.readAllByteBuffer());
        assertThrows(IllegalStateException.class, () -> reader.readBlock());
    }
}