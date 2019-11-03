package com.designpatterns.structural.proxy.president;

public class PresidentSecretary {

    private President president;

    public PresidentSecretary() {
        this.president = President.getInstance();
    }

    public void leaveValidMessageForPresident(String message){

        if(!isMessageValid(message))
            throw new RuntimeException("invalid message");

        System.out.println("message is being sent to the President...");
        president.talkToThePresident(message);
        System.out.println("message is received by the President.");

    }

    private boolean isMessageValid(String message) {
        return message != null && !message.isEmpty() && message.length() >= 10 && message.length() <= 100;
    }
}
