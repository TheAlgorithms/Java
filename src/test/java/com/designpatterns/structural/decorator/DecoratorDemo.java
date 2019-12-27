package com.designpatterns.structural.decorator;

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
    public void testDecorator_sendEmailAsEncodedText() {
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

    @Test
    public void testDecorator_sendEmailAsCompressedText()  {
        String message = "Java is a general-purpose programming language that is class-based, object-oriented, and designed to have as few implementation dependencies as possible. It is intended to let application developers write once, run anywhere (WORA),[15] meaning that compiled Java code can run on all platforms that support Java without the need for recompilation.[16] Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but it has fewer low-level facilities than either of them. As of 2019, Java was one of the most popular programming languages in use according to GitHub,[17][18] particularly for client-server web applications, with a reported 9 million developers.";
        Sender sender = new SenderDecorator(
                new CompressingDecorator(
                        new EmailSender()
                )
        );
        String compressedContent = sender.send(message);
        Assertions.assertTrue(message.length()>= compressedContent.length());
    }

    @Test
    public void testDecorator_sendEmailAsEncodedCompressedText()  {
        String message = "Java is a general-purpose programming language that is class-based, object-oriented, and designed to have as few implementation dependencies as possible. It is intended to let application developers write once, run anywhere (WORA),[15] meaning that compiled Java code can run on all platforms that support Java without the need for recompilation.[16] Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but it has fewer low-level facilities than either of them. As of 2019, Java was one of the most popular programming languages in use according to GitHub,[17][18] particularly for client-server web applications, with a reported 9 million developers.";
        Sender sender = new SenderDecorator(
                new EncodingDecorator(
                        new CompressingDecorator(
                                new EmailSender()
                        )
                )

        );
        String encodedCompressedContent = sender.send(message);

        String decodedCompressed = new String( Base64.getDecoder().decode (encodedCompressedContent));
        Assertions.assertTrue(message.length()>= decodedCompressed.length());
    }
}
