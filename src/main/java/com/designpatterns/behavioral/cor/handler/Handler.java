package com.designpatterns.behavioral.cor.handler;

import com.designpatterns.behavioral.cor.domain.Request;

public interface Handler {
    void setNext(Handler next);
    void execute(Request request);
}
