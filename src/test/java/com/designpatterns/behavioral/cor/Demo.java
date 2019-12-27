package com.designpatterns.behavioral.cor;

import com.designpatterns.behavioral.cor.domain.Request;
import com.designpatterns.behavioral.cor.domain.User;
import com.designpatterns.behavioral.cor.handler.AuthenticationHandler;
import com.designpatterns.behavioral.cor.handler.AuthorizationHandler;
import com.designpatterns.behavioral.cor.handler.Handler;
import com.designpatterns.behavioral.cor.handler.ValidationHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        Request request = new Request("user1", "JSH*njhs9989LKH", "/get-user-by-id");

        List<User> users = instantiateUsersList();

        Handler handler = getHandler(users) ;

        handler.execute(request);


    }


    private static List<User> instantiateUsersList(){
        List<User> users = new ArrayList<>();

        List<String> user1Resources = Arrays.asList("/get-user-by-id","/post-user");
        List<String> user2Resources = Arrays.asList("/get-user","/get-all-users","/put-user");

        User user1 = new User("user1","JSH*njhs9989LKH", user1Resources);
        User user2 = new User("user2","KJAS)(&TASGDHASAawjkas", user2Resources);

        users.add(user1);
        users.add(user2);

        return users;

    }

    private static Handler getHandler(List<User> users){
        Handler authorizationHandler = new AuthorizationHandler(null, users);
        Handler authenticationHandler = new AuthenticationHandler(authorizationHandler, users);
        Handler validationHandler = new ValidationHandler(authenticationHandler);

        return validationHandler;

    }
}
