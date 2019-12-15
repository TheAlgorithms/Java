package com.designpatterns.structural.decorator;

/**
 * this is the base decorator.
 */
public class SenderDecorator implements Sender {
    private Sender sender;

    public SenderDecorator(Sender sender) {
        this.sender = sender;
    }

    @Override
    public String send(String content) {
        return this.sender.send(content);
    }
}
