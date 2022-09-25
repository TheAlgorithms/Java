package com.thealgorithms.ciphers.a5.sound;

import com.thealgorithms.ciphers.a5.A5Cipher;

import javax.sound.sampled.TargetDataLine;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;

public class A5SoundRecorder extends SoundRecorder {
    private final BitSet sessionKey = BitSet.valueOf( new byte[]{ 58, (byte) 139, (byte) 184, 30, 62, 77, 47, 78 } );
    private final BitSet frameCounter = BitSet.valueOf( new byte[]{ (byte) 203, (byte) 179, 58 } );
    private final A5Cipher a5 = new A5Cipher( sessionKey, frameCounter );
    private static final int STREAM_ENCRYPTION_SIZE_BYTES = 28; // we won't encrypt that 0.5 byte for convenience now
    private static final int STREAM_ENCRYPTION_SIZE_BITS = 228;

    @Override
    public void buildByteOutputStream( final ByteArrayOutputStream out, final TargetDataLine line, int frameSizeInBytes, final int bufferLengthInBytes ) throws IOException {
        final byte[] data = new byte[ bufferLengthInBytes ];
        int numBytesRead;

        line.start();
        while ( thread != null ) {
            if ( ( numBytesRead = line.read( data, 0, bufferLengthInBytes ) ) == -1 ) {
                break;
            }
            encryptBytes( out, data, numBytesRead );
        }
    }

    private void encryptBytes( final ByteArrayOutputStream out, byte[] data, int numBytesRead ) {
        int paddedBytesSize = numBytesRead + ( STREAM_ENCRYPTION_SIZE_BYTES - ( numBytesRead % STREAM_ENCRYPTION_SIZE_BYTES ) );
        var paddedData = Arrays.copyOf( data, paddedBytesSize );

        for ( int i = 0; i < paddedData.length; i += STREAM_ENCRYPTION_SIZE_BYTES ) {
            var currentBytes = Arrays.copyOfRange( paddedData, i, i + STREAM_ENCRYPTION_SIZE_BYTES );
            var currentBits = BitSet.valueOf( currentBytes );
            var encryptedBits = a5.encrypt( currentBits );
            var currentEncryptedBytes= Arrays.copyOf( encryptedBits.toByteArray(), STREAM_ENCRYPTION_SIZE_BYTES );

            out.write( currentEncryptedBytes, 0, currentEncryptedBytes.length );
        }
    }

    public void decryptLastRecording() {
        byte[] data = this.getByteAudio();
        a5.resetCounter();

        try ( final ByteArrayOutputStream out = new ByteArrayOutputStream() ) {
            var format = getFormat();
            int frameSizeInBytes = format.getFrameSize();
            encryptBytes( out, data, data.length );
            setAudioInputStream( convertToAudioIStream( out, frameSizeInBytes ) );
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }
}
