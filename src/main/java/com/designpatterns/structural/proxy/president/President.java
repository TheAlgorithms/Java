package com.designpatterns.structural.proxy.president;

import com.designpatterns.creational.singleton.Singleton;

/**
 * This is a class which is gonna be proxied by PresidentSecretary.
 * Whenever any citizen decides to contact the President, they have to talk to the Secretary.
 */
public class President {

    private volatile static President instance = null;

    private President() {}

    static President getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new President();
                }
            }
        }
        return instance;
    }



    void talkToThePresident(String message){
        System.out.println("President: I have received the message:" + message);
    }
}
