package com.others;

import java.util.ArrayList;
import java.util.Collections;

public class FindMultiples {

    /**
     * This function generates a list of multiples for a particular number
     *
     * @param number a number
     * @return a list of multiples
     */
    public static ArrayList<Integer> Of(int number){
        ArrayList<Integer> multiples = new ArrayList<>();

        for(int i = 2; i <= number; i++){
            if((number % i) == 0){
                multiples.add(i);
                number = number / i;
                i = 1;
            }
        }

        Collections.sort(multiples);

        return multiples;
    }
}
