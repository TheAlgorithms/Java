package com.designpatterns.behavioral.cor.handler;

public abstract class AbstractHandler  implements Handler{
    private Handler next;

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    public Handler getNext() {
        return next;
    }
}
