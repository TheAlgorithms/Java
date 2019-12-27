package com.designpatterns.behavioral.cor.handler;

import com.designpatterns.behavioral.cor.domain.Request;
import com.designpatterns.behavioral.cor.domain.User;

import java.util.List;

public class AuthorizationHandler extends  AbstractHandler {

    private List<User> usersList;

    public AuthorizationHandler(Handler handler, List<User> usersList) {
        setNext(handler);
        this.usersList = usersList;
    }

    @Override
    public void execute(Request request) {

        if(!
                usersList.stream().anyMatch(
                        e -> e.getValidResources().contains(request.getRequestedResource()))
        ) throw new RuntimeException("user is not allowed to access +" + request.getRequestedResource());

        else {
            if (getNext()!=null) {
                System.out.println("user is authorized to access:" + request.getRequestedResource() +", passing request to the next handler...");
                getNext().execute(request);
            } else
                System.out.println("user is authorized to access:" + request.getRequestedResource() + ", there is not any other handler. Request processing is finished.");
        }



    }
}
