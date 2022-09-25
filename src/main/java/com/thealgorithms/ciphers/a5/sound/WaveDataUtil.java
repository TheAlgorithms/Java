package com.thealgorithms.ciphers.a5.sound;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

public class WaveDataUtil {
    public boolean saveToFile( String name, AudioFileFormat.Type fileType, AudioInputStream audioInputStream ) {
        System.out.println( "Saving..." );
        if ( null == name || null == fileType || audioInputStream == null ) {
            return false;
        }
        File myFile = new File( name + "." + fileType.getExtension() );
        try {
            audioInputStream.reset();
        } catch ( Exception e ) {
            return false;
        }
        int i = 0;
        while ( myFile.exists() ) {
            String temp = "" + i + myFile.getName();
            myFile = new File( temp );
        }
        try {
            AudioSystem.write( audioInputStream, fileType, myFile );
        } catch ( Exception ex ) {
            return false;
        }
        System.out.println( "Saved " + myFile.getAbsolutePath() );
        return true;
    }
}
