package com.thealgorithms.bitmanipulation;
import java.util.ArrayList;
import java.util.List;


/**
 * Gray Code Sequence Generator
 * @author Akanksha Singh (https://github.com/singhakanksha03)
 */


public class GrayCodeSequenceGenerator {

    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            result.add(i ^ (i >> 1));
        }
        return result;
    }

    // public static void main(String[] args) {
    //     GrayCodeSequenceGenerator grayCodeGenerator = new GrayCodeSequenceGenerator();
    //     int n = 2;
    //     List<Integer> graySequence = grayCodeGenerator.grayCode(n);
    //     System.out.println("Gray Code Sequence for n = " + n + ": " + graySequence);
    // }
}
