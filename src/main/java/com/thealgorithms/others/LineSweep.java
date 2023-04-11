package com.thealgorithms.others;
import java.util.Arrays;

public class LineSweep {

     /** Line Sweep algorithm can be used to solve range problems by first sorting the list of ranges
     * by the start value of the range in non-decreasing order and doing a "sweep" through the number
     * line(x-axis) by incrementing the start point by 1 and decrementing end point by -1 on the
     * number line.
     * /

     * Find if any interval overlaps
     * param = ranges Array of range[start,end]
     * param = maximum Maximum length of number line or largest "end" value
     * return true if there overlap exists false
     * otherwise.
     */

    public static boolean isOverlap(int[][] ranges,int maximum) {

        Arrays.sort(ranges, (a, b) -> (a[0] - b[0]));
        int[] numberLine = new int[maximum+1];
        for (int[] range : ranges) {

            int start = range[0];
            int end = range[1];

            numberLine[start] += 1;
            numberLine[end] -= 1;
        }

        int current = 0;
        int overlaps = 0;
        for (int num : numberLine) {

            current += num;
            overlaps = Math.max(overlaps, current);
        }
        return overlaps >1 ;
    }
}
