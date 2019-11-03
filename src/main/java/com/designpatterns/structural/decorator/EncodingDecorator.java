package com.designpatterns.structural.decorator;

import java.util.Base64;

/**
 * this is another concrete decorator.
 */
public class EncodingDecorator extends SenderDecorator {

    public EncodingDecorator(Sender sender) {
        super(sender);
    }

    @Override
    public String send(String content) {
        String encodedContent = encodeContent(content);
        super.send(encodedContent);
        return encodedContent;
    }

    private String encodeContent(String content){
        return Base64.getEncoder().encodeToString(content.getBytes());
    }
}
