package com.designpatterns.structural.decorator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * this is one of the concrete decorators
 */
public class CompressingDecorator extends SenderDecorator {

    public CompressingDecorator(Sender sender) {
        super(sender);
    }

    @Override
    public String send(String content) {
        String compressedContent = new String(compressContent(content));
        super.send(compressedContent);
        return compressedContent;
    }

    private byte[] compressContent(String content) {
        try{
            ByteArrayOutputStream baostream = new ByteArrayOutputStream();
            OutputStream outStream = new GZIPOutputStream(baostream);
            outStream.write(content.getBytes("UTF-8"));
            outStream.close();
            byte[] compressedBytes = baostream.toByteArray(); // toString not always possible
            return compressedBytes;
        } catch (IOException e) {
            throw new RuntimeException("exception happened while compressing email content");

        }
    }


}
