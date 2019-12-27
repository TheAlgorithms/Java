package com.designpatterns.behavioral.cor.handler;

import com.designpatterns.behavioral.cor.domain.Request;
import com.designpatterns.behavioral.cor.domain.User;

import java.util.List;

public class AuthenticationHandler extends AbstractHandler{

    private List<User> usersList;

    public AuthenticationHandler(Handler next, List<User> usersList) {
        setNext(next);
        this.usersList = usersList;

    }

    @Override
    public void execute(Request request) {

        if(! usersList.stream().anyMatch(e ->
                e.getUsername().equals(request.getUsername()) && e.getPassword().equals(request.getPassword())
        ))
            throw new RuntimeException("user is not authenticated");

        else {
            if (getNext()!=null) {
                System.out.println("user is authenticated, passing request to the next handler...");
                getNext().execute(request);
            } else
                System.out.println("user is authenticated. There is not any other handler. Request processing is finished.");

        }

    }





}

