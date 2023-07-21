package com.thealgorithms.javaissues;

import com.thealgorithms.javaissues.util.Person;
import com.thealgorithms.javaissues.util.Selfie;

public class IgnoringExceptions {

    public static void ignoreExceptions() {
        Person person = new Person();
        Selfie selfie = person.shootASelfie();
        try {
            selfie.show();
        } catch (NullPointerException e) {
            // Maybe, invisible man. Who cares, anyway?
        }
    }
}
