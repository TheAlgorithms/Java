package com.thealgorithms.javaissues;

import java.util.HashSet;
import java.util.Set;

public class UseBoat {
    public static void main(String[] args) {
        Set<BreakingContractBoat> boats = new HashSet<>();
        boats.add(new BreakingContractBoat("Enterprise"));

        System.out.printf("We have a boat named 'Enterprise' : %b\n", boats.contains(new BreakingContractBoat("Enterprise")));
    }
}
