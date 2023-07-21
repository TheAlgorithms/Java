package com.thealgorithms.javaissues.util;

import java.util.Arrays;
import java.util.List;

public class Person {

    public static Selfie shootASelfie(){
        return new Selfie();
    }

    public static List<String> getAccountIds(){return List.of("account1", "account2");}
}
