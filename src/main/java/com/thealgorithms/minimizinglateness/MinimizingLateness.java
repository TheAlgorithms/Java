
import java.io.IOException;
import java.util.StringTokenizer;

// The inputData variable represents six jobs with their processing times (t) and deadlines (d).
// The program calculates the lateness for each job and outputs the total lateness.

public class MinimizingLateness {

    private static class Schedule { // Schedule class

        int t; // Time required for the operation to be performed
        int d; // Time the job should be completed
        public Schedule(int t, int d) {
            this.t = t;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        String inputData = "6\n"
            + "3 6\n"
            + "2 8\n"
            + "1 9\n"
            + "4 9\n"
            + "3 14\n"
            + "2 15";

        StringTokenizer token = new StringTokenizer(inputData, "\n");

        int indexCount = Integer.parseInt(token.nextToken());
        System.out.println("Input Data : ");
        System.out.println(indexCount); // number of operations
        Schedule[] array = new Schedule[indexCount]; // Create an array to hold the operation
        int i = 0;
        while (token.hasMoreTokens()) {
            String ch = token.nextToken();
            StringTokenizer tokenizer = new StringTokenizer(ch, " ");
            // Include the time required for the operation to be performed in the array and the time
            // it should be completed.
            array[i] = new Schedule(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            i++;
            System.out.println(array[i - 1].t + " " + array[i - 1].d);
        }

        int tryTime = 0; // Total time worked
        int lateness = 0; // Lateness
        for (int j = 0; j < indexCount - 1; j++) {
            tryTime = tryTime + array[j].t; // Add total work time
            // Lateness
            lateness = lateness + Math.max(0, tryTime - array[j].d);
        }
        System.out.println();
        System.out.println("Output Data : ");
        System.out.println(lateness);
    }
}
