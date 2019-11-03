package com.designpatterns.structural.proxy;

import com.designpatterns.structural.proxy.president.PresidentSecretary;

public class Citizen {

    public static void main(String[] args) {
        PresidentSecretary presidentSecretary = new PresidentSecretary();
        presidentSecretary.leaveValidMessageForPresident("Hello president.");
    }
}
