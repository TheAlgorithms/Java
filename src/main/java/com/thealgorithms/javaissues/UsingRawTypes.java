package com.thealgorithms.javaissues;

import java.util.ArrayList;
import java.util.List;

public class UsingRawTypes {
    public static void useRawTypes() {

        List listOfNumbers = new ArrayList();
        listOfNumbers.add(10);
        listOfNumbers.add("Twenty");
        listOfNumbers.forEach(n -> System.out.println((int) n * 2));
    }
}
