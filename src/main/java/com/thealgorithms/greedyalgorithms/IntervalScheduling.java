import java.util.Scanner;
import java.util.Arrays;

public class IntervalScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input the number of intervals
        System.out.print("Enter the number of intervals: ");
        int n = scanner.nextInt();
        
        // Array to store the intervals
        int[][] intervals = new int[n][2];
        
        // Input each interval's start and end time
        for (int i = 0; i < n; i++) {
            System.out.print("Enter start time for interval " + (i + 1) + ": ");
            intervals[i][0] = scanner.nextInt();
            System.out.print("Enter end time for interval " + (i + 1) + ": ");
            intervals[i][1] = scanner.nextInt();
        }
        
        // Sort intervals by their end times
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        // Greedy algorithm to select the maximum number of non-overlapping intervals
        int count = 0; // To count the maximum number of intervals
        int lastEnd = Integer.MIN_VALUE; // Variable to track the end time of the last selected interval
        
        for (int i = 0; i < n; i++) {
            // If the start time of the current interval is after the last selected interval's end time
            if (intervals[i][0] >= lastEnd) {
                count++; // Select this interval
                lastEnd = intervals[i][1]; // Update lastEnd to the current interval's end time
            }
        }
        
        System.out.println("Maximum number of non-overlapping intervals: " + count);
    }
}
