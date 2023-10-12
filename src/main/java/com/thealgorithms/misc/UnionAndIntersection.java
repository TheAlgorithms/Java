package com.thealgorithms.misc;

import java.util.HashSet;
import java.util.Set;

public class UnionAndIntersection {

    // Program which uses Set to find out count of Union and Intersection in two arrays

    public static void main(String[] args) {
        int a[] = {1, 1, 3, 3, 4, 7, 6, 5, 1, 2, 3};
        int b[] = {1, 2, 3};
        unionArray(a, b);
        intersectionArray(a, b);
    }

    private static void intersectionArray(int[] a, int[] b) {
        // TODO Auto-generated method stub
        Set<Integer> inter = new HashSet<Integer>();
        int count = 0;

        for (int ele : a) {
            inter.add(ele);
        }

        for (int ele : b) {
            if (inter.contains(ele)) {
                count++;
                inter.remove(ele);
            }
        }

        System.out.println("Intersection: " + count);
    }

    /*
     * Considering set won't allow multiple values in it
     * We store both elements in a single set and return its size
     */
    private static void unionArray(int[] a, int[] b) {
        // TODO Auto-generated method stub
        Set<Integer> union = new HashSet<Integer>();

        for (int ele : a) {
            union.add(ele);
        }

        for (int ele : b) {
            union.add(ele);
        }

        System.out.println("Union: " + union.size());
    }
}
