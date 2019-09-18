package com.others;

import java.util.LinkedList;
import java.util.List;

public class ReturnChange {
    /**
     * Iterative solution to this algorithm. This algorithm has a complexity order equals to O(N)
     * where N is the billAmount/maxMoneyValue. At the end, linear to the value passed as parameter.
     * @param moneys The array of money you have
     * @param billAmount Amount to calculate
     **/
    public static  List<Integer> calculate(int[] moneys, int billAmount) {
        if (moneys == null || moneys.length == 0 || billAmount <= 0) {
            throw new IllegalArgumentException("You can't use a null or empty array of moneys or a bill amount equals or less than zero.");
        }
        List<Integer> change = new LinkedList<Integer>();
        while (billAmount > 0 && moneys.length > 0) {
            for (int i = moneys.length - 1; i >= 0; i--) {
                if (billAmount - moneys[i] >= 0) {
                    change.add(moneys[i]);
                    billAmount -= moneys[i];
                    break;
                }
            }
        }
        return change;
    }
}
