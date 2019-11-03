package com.designpatterns.structural.decorator;

/**
 * this is the class which should be decorated without any modification
 */
public class EmailSender implements Sender {
    @Override
    public String send(String content) {
        System.out.println("sending \"" + content  + "\" as email");
        return content;
    }
}
