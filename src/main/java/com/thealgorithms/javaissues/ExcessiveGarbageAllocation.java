package com.thealgorithms.javaissues;

public class ExcessiveGarbageAllocation {

    public static void shortLivedObjects(){
        String oneMillionHello = "";
        for (int i = 0; i < 1000000; i++) {
            oneMillionHello = oneMillionHello + "Hello!";
        }
        System.out.println(oneMillionHello.substring(0, 6));
    }

}
