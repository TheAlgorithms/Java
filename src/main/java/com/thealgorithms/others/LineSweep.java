package com.thealgorithms.others;
import java.util.Arrays;
import java.util.Comparator;

/* Line Sweep algorithm can be used to solve range problems by first sorting the list of ranges
 * by the start value of the range in non-decreasing order and doing a "sweep" through the number
 * line(x-axis) by incrementing the start point by 1 and decrementing the end point+1 by 1 on the
 * number line.
 * An overlapping range is defined as (StartA <= EndB) AND (EndA >= StartB)
 * References
 * https://en.wikipedia.org/wiki/Sweep_line_algorithm
 * https://en.wikipedia.org/wiki/De_Morgan%27s_laws>
 */
public class LineSweep {

    /**
     * Find Maximum end point
     *   param = ranges : Array of range[start,end]
     *   return Maximum Endpoint
     */
    public static int FindMaximumEndPoint(int[][] ranges) {
        Arrays.sort(ranges, Comparator.comparingInt(a -> a[1]));
        return ranges[ranges.length - 1][1];
    }

    /**
     * Find if any ranges overlap
     *   param = ranges : Array of range[start,end]
     *   return true if overlap exists false otherwise.
     */
    public static boolean isOverlap(int[][] ranges) {

        int maximumEndPoint = FindMaximumEndPoint(ranges);
        Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));
        int[] numberLine = new int[maximumEndPoint + 2];
        for (int[] range : ranges) {

            int start = range[0];
            int end = range[1];

            numberLine[start] += 1;
            numberLine[end + 1] -= 1;
        }

        int current = 0;
        int overlaps = 0;
        for (int num : numberLine) {
            current += num;
            overlaps = Math.max(overlaps, current);
        }
        return overlaps > 1;
    }
}
