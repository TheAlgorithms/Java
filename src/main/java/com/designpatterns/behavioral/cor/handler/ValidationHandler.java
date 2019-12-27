package com.designpatterns.behavioral.cor.handler;

import com.designpatterns.behavioral.cor.domain.Request;

public class ValidationHandler extends AbstractHandler{

    public ValidationHandler(Handler next) {
        setNext(next);
    }


    @Override
    public void execute(Request request) {
        if (isRequestValid(request)) {
            if (getNext()!=null) {
                System.out.println("user is valid, passing request to the next handler...");
                getNext().execute(request);
            } else
                System.out.println("all handlers are applied.");
        }

        else throw new RuntimeException("request is not valid");


    }

    private boolean isRequestValid(Request request) {
        String username = request.getUsername();
        String password = request.getPassword();
        String requestedResource = request.getRequestedResource();

        if(username == null || username.length()<5 || username.length()>150)
            return false;

        if(password == null || password.length()<10 || password.length()>150)
            return false;

        if(requestedResource== null || requestedResource.length()<10 || requestedResource.length()>150)
            return false;

        return true;

    }
}
