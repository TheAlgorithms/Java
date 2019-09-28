package com.others;

public class MaximizeDistancetoClosestPerson {

    public static void  main(String args[]) {
        int [] intArray = new int [] {1,0,0,0,1,0,1};
        int out = maxDistToClosest(intArray);
        System.out.println(out);
    }

    public static int maxDistToClosest(int[] a) {
        int result = 0;
        int count = 0;
        int firstZeros = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                count++;
            } else {
                result = Math.max(result, count / 2 + count % 2);
                if (firstZeros == 0 && a[0] == 0)
                    firstZeros = count;
                count = 0;
            }
        }
        return Math.max(Math.max(result, firstZeros), count);
    }
}