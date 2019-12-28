package com.designpatterns.behavioral.cor;

import com.designpatterns.behavioral.cor.domain.Request;
import com.designpatterns.behavioral.cor.domain.User;
import com.designpatterns.behavioral.cor.handler.AuthenticationHandler;
import com.designpatterns.behavioral.cor.handler.AuthorizationHandler;
import com.designpatterns.behavioral.cor.handler.Handler;
import com.designpatterns.behavioral.cor.handler.ValidationHandler;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Demo {

    private List<User> users;
    private Handler authorizationHandler;
    private Handler authenticationHandler;
    private Handler validationHandler;

    {
        users = new ArrayList<>();

        List<String> user1Resources = Arrays.asList("/get-user-by-id","/post-user");
        List<String> user2Resources = Arrays.asList("/get-user","/get-all-users","/put-user");

        User user1 = new User("user1","JSH*njhs9989LKH", user1Resources);
        User user2 = new User("user2","KJAS)(&TASGDHASAawjkas", user2Resources);

        users.add(user1);
        users.add(user2);

        authorizationHandler = new AuthorizationHandler(null, users);
        authenticationHandler = new AuthenticationHandler(authorizationHandler, users);
        validationHandler = new ValidationHandler(authenticationHandler);
    }


    @Test
    public void testChainOfResponsibility_AllHandlersShouldBePassed() {
        Request request = new Request("user1", "JSH*njhs9989LKH", "/get-user-by-id");
        validationHandler.execute(request);
    }

    @Test
    public void testChainOfResponsibility_AuthenticationShouldBreak() {
        Request request = new Request("user12", "JSH*njhs9989LKH", "/get-user-by-id");
        assertThrows(RuntimeException.class,
                () -> validationHandler.execute(request),
                "user is not authenticated, runtime exception should be thrown");
    }

    @Test
    public void testChainOfResponsibility_AuthorizationShouldBreak() {
        Request request = new Request("user1", "JSH*njhs9989LKH", "/get-list-of-users-by-id");
        assertThrows(RuntimeException.class,
                () -> validationHandler.execute(request),
                "user is not authorized to access to requested resource, runtime exception should be thrown");
    }

}
