package com.designpatterns.decorator;

import com.designpatterns.structural.decorator.EmailSender;
import com.designpatterns.structural.decorator.EncodingDecorator;
import com.designpatterns.structural.decorator.Sender;
import com.designpatterns.structural.decorator.SenderDecorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Base64;

public class DecoratorDemo {

    @Test
    public void testDecorator_sendEmailAsPlainText() {
        String message = "test message";
        EmailSender sender = new EmailSender();
        String content = sender.send(message);

        Assertions.assertEquals(content, message);

    }

    @Test
    public void testDecorator_sendEmailAsEncodedTest() {
        String message = "test message";
        Sender sender = new SenderDecorator(
                new EncodingDecorator(
                        new EmailSender()
                )
        );

        String encodedContent = sender.send(message);

        Assertions.assertEquals(
                new String(Base64.getDecoder().decode (encodedContent)),
                message
        );



    }
}
