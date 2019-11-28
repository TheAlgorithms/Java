package com.designpatterns.structural.proxy.president;

import com.designpatterns.creational.singleton.Singleton;

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
