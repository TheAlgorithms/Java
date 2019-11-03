package com.designpatterns.structural.proxy;

import com.designpatterns.structural.proxy.president.PresidentSecretary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Citizen {


    private PresidentSecretary presidentSecretary = new PresidentSecretary();

    @Test
    public void talkToPresident_secretaryShouldRejectTooShortMessage() {
        String message = "Hi there.";

        Assertions.assertThrows(RuntimeException.class, () -> {
            presidentSecretary.leaveValidMessageForPresident(message);
        });
    }

    @Test
    public void talkToPresident_secretaryShouldRejectTooLongMessage() {
        String message = "Hi there. this is a message about some personal issue which I have decided to share with Mr.President.";

        Assertions.assertThrows(RuntimeException.class, () -> {
            presidentSecretary.leaveValidMessageForPresident(message);
        });
    }

    @Test
    public void talkToPresident_secretaryShouldAcceptTheMessage() {
        String message = "Hello Mr.President";

        presidentSecretary.leaveValidMessageForPresident(message);
        Assertions.assertTrue(true);
    }


}
