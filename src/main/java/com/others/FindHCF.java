package com.others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class FindHCF {

    /**
     *
     * @param numbers a list of numbers
     * @return a number which is the Highest Common Factor
     */
    public static int Of(int[] numbers){
        int result = 0;

        ArrayList<Integer> factors = new ArrayList<>();

        for(int number : numbers){
            for(int i = 2; i <= number; i++){
                if((number % i) == 0) factors.add(i);
            }
        }

        // Sort the factors
        Collections.sort(factors);

        // Obtain the factor with the highest common factor
        int count = 0;
        for(int factor : factors){
            AtomicInteger tempCount = new AtomicInteger();
            factors.forEach(
                    item -> {
                        if(item == factor){
                            tempCount.getAndIncrement();
                        }
                    }
            );

            count = (tempCount.get() > count) ? tempCount.get() : count;
            result = (tempCount.get() == count) ? factor : result;
        }
        return result;
    }

    /**
     *
     * @param numbers a list of numbers
     * @return a number which is the Highest Common Factor
     */
    public static int Of(ArrayList<Integer> numbers){
        int result = 0;

        ArrayList<Integer> factors = new ArrayList<>();

        for(int number : numbers){
            for(int i = 2; i <= number; i++){
                if((number % i) == 0) factors.add(i);
            }
        }

        // Sort the factors
        Collections.sort(factors);

        // Obtain the factor with the highest common factor
        int count = 0;
        for(int factor : factors){
            AtomicInteger tempCount = new AtomicInteger();
            factors.forEach(
                    item -> {
                        if(item == factor){
                            tempCount.getAndIncrement();
                        }
                    }
            );

            count = (tempCount.get() > count) ? tempCount.get() : count;
            result = (tempCount.get() == count) ? factor : result;
        }
        return result;
    }
}
