package src.main.java.com.crypto.codec;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * <p>This class implements the Base64 codec as it is specified in the RFC 4648.
 * Base64 represents binary data as a text string. It is used to store or transfer
 * data in legacy systems that are restricted to 7 bit per character. There are
 * many variations of Base64, e.g. MIME (RFC 2045) specifies Base64 slightly
 * different. The RFC 4648 tries to solve this disambiguation.
 *
 * @see <a href="https://tools.ietf.org/html/rfc4648">RFC 4648</a>
 */
public class Base64 {
    private static final char pad = '=';
    private static final char[] encodeAlphabet = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'
    };
    private static final byte[] decodeAlphabet = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57,
            58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,
            -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38,
            39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51
    };

    /**
     * <p>Encodes user-provided data with the Base64 encoding. For this, the data
     * is split in to blocks of 3-byte length. The last block may be of a shorter
     * length. The blocks are then encoded, and the last block is padded.
     *
     * @param data The data that is to be Base64-encoded
     * @return Base64-encoded data
     * @see <a href="https://tools.ietf.org/html/rfc4648#section-4">RFC 4648 - Base64 encoding</a>
     */
    public static String encode(byte[] data) {
        StringBuilder builder = new StringBuilder();
        int blockCount = data.length / 3;

        // if last block is shorter than 3 bytes, then it's not buffered
        ByteBuffer buffer = ByteBuffer.wrap(data, 0, blockCount * 3);

        // encode buffered data
        while (buffer.hasRemaining()) {
            byte[] dataBlock = new byte[3];
            for (int i = 0; i < 3; i++) {
                dataBlock[i] = buffer.get();
            }
            builder.append(encodeBlock(dataBlock));
        }

        // encode (pad) last block, if it was shorter than 3 bytes
        int remaining = data.length % 3;
        if (remaining > 0) {
            byte[] lastBlock = Arrays.copyOfRange(data, data.length - remaining, data.length);
            char[] padding = padBlock(lastBlock);
            builder.append(padding);
        }

        return builder.toString();
    }

    /**
     * <p>Encodes one block of data.
     *
     * @param block The block to be encoded with a length of 3 bytes
     * @return The encoded block
     */
    private static char[] encodeBlock(byte[] block) {
        char[] encodedBlock = new char[4];

        // split 3 bytes in to 4 6-bit blocks
        encodedBlock[0] = encodeAlphabet[block[0] >>> 2];
        encodedBlock[1] = encodeAlphabet[(block[0] & 0b11) << 4 | block[1] >>> 4];
        encodedBlock[2] = encodeAlphabet[(block[1] & 0b1111) << 2 | block[2] >>> 6];
        encodedBlock[3] = encodeAlphabet[block[2] & 0b11_1111];

        return encodedBlock;
    }

    /**
     * <p>Pads a block of data with a padding character ('='). A data block can
     * have one or two elements. The resulting encoded block has always four
     * characters.
     * <p>There are two cases:
     * <ul>
     * <li>If data block has one element, then the encoded block has two
     * characters and two padding characters</li>
     * <li>If data block has two elements, the the encoded block has three
     * characters and one padding character</li>
     * </ul>
     *
     * @param block A block of data with a length of one or two
     * @return The encoded and padded block
     * @see <a href="https://tools.ietf.org/html/rfc4648#section-4">RFC 4648 - Base64 encoding</a>
     */
    private static char[] padBlock(byte[] block) {
        char[] paddedBlock = new char[4];

        paddedBlock[0] = encodeAlphabet[block[0] >>> 2];
        if (block.length == 1) {
            // pad with 2 padding characters
            paddedBlock[1] = encodeAlphabet[(block[0] & 0b11) << 4];
            paddedBlock[2] = pad;
            paddedBlock[3] = pad;
        } else { // block.length == 2
            // pad with 1 padding character
            paddedBlock[1] = encodeAlphabet[(block[0] & 0b11) << 4 | block[1] >>> 4];
            paddedBlock[2] = encodeAlphabet[(block[1] & 0b1111) << 2];
            paddedBlock[3] = pad;
        }

        return paddedBlock;
    }

    /**
     * <p>Decodes original data from a Base64 string. The string has always
     * a length of the multiple of four characters (empty string included). The
     * last block of four characters can contain one or two padding characters ('=').
     * <p>Each block is decoded in to 3 bytes of data. The last block is
     * processed separately to reverse the padding.
     *
     * @param base64String Base64 encoded data
     * @return original data
     */
    public static byte[] decode(String base64String) {
        if (base64String == null) {
            throw new IllegalArgumentException("Base64 string must not be null!");
        }

        base64String = base64String.strip();
        if (!isValidBase64String(base64String)) {
            throw new IllegalArgumentException("String is not a valid Base64 string!");
        }

        // an empty base64 string decodes in to an empty byte array
        if (base64String.length() == 0) {
            return new byte[0];
        }

        int blockCount = base64String.length() / 4;
        ByteBuffer stringBuffer = ByteBuffer.wrap(base64String.getBytes(StandardCharsets.ISO_8859_1),
                0, base64String.length());
        // separate last block from data
        ByteBuffer dataBuffer = ByteBuffer.allocate((blockCount - 1) * 3);

        byte[] encodedBlock = new byte[4];
        // decode bulk of data
        for (int i = 0; i < blockCount - 1; ++i) {
            for (int j = 0; j < 4; j++) {
                encodedBlock[j] = stringBuffer.get();
            }
            dataBuffer.put(decodeBlock(encodedBlock));
        }
        // decode last block
        for (int i = 0; i < 4; i++) {
            encodedBlock[i] = stringBuffer.get();
        }
        byte[] lastDataBlock = undoPadding(encodedBlock);

        // glue bulk of data and last block together
        ByteBuffer decodedData = ByteBuffer.allocate(dataBuffer.capacity() + lastDataBlock.length);
        decodedData.put(dataBuffer.array());
        decodedData.put(lastDataBlock);
        return decodedData.array();
    }

    /**
     * <p>Decodes an encoded block.
     *
     * @param block The encoded block with a length of 4 bytes
     * @return The decoded block
     */
    private static byte[] decodeBlock(byte[] block) {
        byte[] decodedBlock = new byte[3];

        decodedBlock[0] = (byte) (decodeAlphabet[block[0]] << 2 | decodeAlphabet[block[1]] >>> 4);
        decodedBlock[1] = (byte) (decodeAlphabet[block[1]] << 4 | decodeAlphabet[block[2]] >>> 2);
        decodedBlock[2] = (byte) (decodeAlphabet[block[2]] << 6 | decodeAlphabet[block[3]]);

        return decodedBlock;
    }

    /**
     * <p>Removes the padding from the last block.
     *
     * @param block
     * @return  The decoded last block of data
     */
    private static byte[] undoPadding(byte[] block) {
        int padCount = 0;
        byte[] decodedBlock;

        // count padding characters
        if (block[3] == (byte) pad) padCount++;
        if (block[2] == (byte) pad) padCount++;

        if (padCount == 2) {
            decodedBlock = new byte[1];
            decodedBlock[0] = (byte) (decodeAlphabet[block[0]] << 2 | decodeAlphabet[block[1]] >>> 4);
        } else if (padCount == 1) {
            decodedBlock = new byte[2];
            decodedBlock[0] = (byte) (decodeAlphabet[block[0]] << 2 | decodeAlphabet[block[1]] >>> 4);
            decodedBlock[1] = (byte) (decodeAlphabet[block[1]] << 4 | decodeAlphabet[block[2]] >>> 2);
        } else {
            decodedBlock = decodeBlock(block);
        }
        return decodedBlock;
    }

    /**
     * <p>Check if the provided string is a valid Base64 string.
     * <p>The specification (RFC 4648) says: <i>"Implementations MUST reject the
     * encoded data if it contains characters outside the base alphabet when
     * interpreting base-encoded data, unless the specification referring to this
     * document explicitly states otherwise."</i>
     *
     * @param base64String
     * @return <code>true</code> if the string is valid;
     * <code>false</code> otherwise
     */
    private static boolean isValidBase64String(String base64String) {
        return base64String.matches("^(?:[A-Za-z0-9+\\/]{4})*(?:[A-Za-z0-9+\\/]{2}==|[A-Za-z0-9+\\/]{3}=)?$");
    }
}
