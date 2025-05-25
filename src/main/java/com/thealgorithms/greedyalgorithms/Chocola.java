package com.thealgorithms.greedyalgorithms;

import java.util.Arrays;
import java.util.Collections;

/**
 * Greedy approach to minimize the cost of cutting a chocolate bar into 1x1 pieces.
 */
public class Chocola {

    /**
     * Calculates the minimum cost of cutting the chocolate bar.
     *
     * @param costVer Cost of vertical cuts (size m-1)
     * @param costHor Cost of horizontal cuts (size n-1)
     * @return Minimum total cost
     */
    public static int minCostOfCuttingChocolate(Integer[] costVer, Integer[] costHor) {
        Arrays.sort(costVer, Collections.reverseOrder());
        Arrays.sort(costHor, Collections.reverseOrder());

        int h = 0, v = 0;
        int hp = 1, vp = 1;

        int cost = 0;

        while (h < costHor.length && v < costVer.length) {
            if (costHor[h] >= costVer[v]) {
                cost += costHor[h] * vp;
                h++;
                hp++;
            } else {
                cost += costVer[v] * hp;
                v++;
                vp++;
            }
        }

        while (h < costHor.length) {
            cost += costHor[h] * vp;
            h++;
            hp++;
        }

        while (v < costVer.length) {
            cost += costVer[v] * hp;
            v++;
            vp++;
        }

        return cost;
    }

    public static void main(String[] args) {
        int n = 4, m = 5;
        Integer[] costVer = {2, 1, 3, 1}; // m-1 cuts
        Integer[] costHor = {4, 1, 2};    // n-1 cuts

        int totalCost = minCostOfCuttingChocolate(costVer, costHor);
        System.out.println("Cost = " + totalCost);
    }
}
