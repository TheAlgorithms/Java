package com.thealgorithms.minimizinglateness;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MinimizingLateness {

    private static class Schedule { // Schedule class

        int t = 0; // Time required for the operation to be performed
        int d = 0; // Time the job should be completed
        int s = 0; // Start time of the task
        int f = 0; // End time of the operation

        public Schedule(int t, int d) {
            this.t = t;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer token;

        BufferedReader in = new BufferedReader(
            new FileReader("MinimizingLateness/lateness_data.txt")
        );
        String ch = in.readLine();
        if (ch == null || ch.isEmpty()) {
            in.close();
            return;
        }
        int indexCount = Integer.parseInt(ch);
        System.out.println("Input Data : ");
        System.out.println(indexCount); // number of operations
        Schedule[] array = new Schedule[indexCount]; // Create an array to hold the operation
        int i = 0;
        while ((ch = in.readLine()) != null) {
            token = new StringTokenizer(ch, " ");
            // Include the time required for the operation to be performed in the array and the time it
            // should be completed.
            array[i] =
                new Schedule(
                    Integer.parseInt(token.nextToken()),
                    Integer.parseInt(token.nextToken())
                );
            i++;
            System.out.println(array[i - 1].t + " " + array[i - 1].d);
        }

        int tryTime = 0; // Total time worked
        int lateness = 0; // Lateness
        for (int j = 0; j < indexCount - 1; j++) {
            array[j].s = tryTime; // Start time of the task
            array[j].f = tryTime + array[j].t; // Time finished
            tryTime = tryTime + array[j].t; // Add total work time
            // Lateness
            lateness = lateness + Math.max(0, tryTime - array[j].d);
        }
        System.out.println();
        System.out.println("Output Data : ");
        System.out.println(lateness);
        in.close();
    }
}
