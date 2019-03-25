import java.io.*;
import java.util.*;

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
		// TODO Auto-generated method stub
		StringTokenizer token;

		String ch;
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		int indexCount; // size of array index
		ch = in.readLine();
		indexCount = Integer.parseInt(ch); // The first line specifies the size of the operation (= the size of the array)
		System.out.println("Input Data : ");
		System.out.println(indexCount); // number of operations
		Schedule array[] = new Schedule[indexCount]; // Create an array to hold the operation
		int i = 0;
		while ((ch = in.readLine()) != null) {
			token = new StringTokenizer(ch, " "); 
			// Include the time required for the operation to be performed in the array and the time it should be completed.
			array[i] = new Schedule(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
			i++; // 다음 인덱스
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
	}
}
