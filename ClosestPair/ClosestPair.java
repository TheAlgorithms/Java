import java.io.*;
import java.util.*;

public class ClosestPair {
	static int count = 0;// array length
	static int secondCount = 0;// array length
	static Location array[] = new Location[10000];
	static Location point1 = null; // Minimum point coordinate
	static Location point2 = null; // Minimum point coordinate
	static double minNum = Double.MAX_VALUE;// Minimum point length

	private static class Location { // Location class
		double x = 0, y = 0;

		public Location(double x, double y) { //Save x, y coordinates
			this.x = x;
			this.y = y;
		}
	}

	public static int xPartition(Location[] a, int first, int last) { // x-axis Quick Sorting
		Location pivot = a[last]; // pivot
		int pIndex = last;
		int i = first - 1;
		Location temp; // Temporarily store the value for position transformation
		for (int j = first; j <= last - 1; j++) {
			if (a[j].x <= pivot.x) { // Less than or less than pivot
				i++;
				temp = a[i]; // array[i] <-> array[j]
				a[i] = a[j];
				a[j] = temp;
			}
		}
		i++;
		temp = a[i];// array[pivot] <-> array[i]
		a[i] = a[pIndex];
		a[pIndex] = temp;
		return i;// pivot index
	}
	public static int yPartition(Location[] a, int first, int last) { //y-axis Quick Sorting
		Location pivot = a[last]; // pivot
		int pIndex = last;
		int i = first - 1;
		Location temp; // Temporarily store the value for position transformation
		for (int j = first; j <= last - 1; j++) {
			if (a[j].y <= pivot.y) { // Less than or less than pivot
				i++;
				temp = a[i]; // array[i] <-> array[j]
				a[i] = a[j];
				a[j] = temp;
			}
		}
		i++;
		temp = a[i];// array[pivot] <-> array[i]
		a[i] = a[pIndex];
		a[pIndex] = temp;
		return i;// pivot index
	}

	public static void xQuickSort(Location[] a, int first, int last) { //x-axis Quick Sorting
		if (first < last) {
			int q = xPartition(a, first, last); // pivot
			xQuickSort(a, first, q - 1); // Left
			xQuickSort(a, q + 1, last); // Right
		}
	}

	public static void yQuickSort(Location[] a, int first, int last) { //y-axis Quick Sorting
		if (first < last) {
			int q = yPartition(a, first, last); // pivot
			yQuickSort(a, first, q - 1); // Left
			yQuickSort(a, q + 1, last); // Right
		}
	}

	public static double closestPair(Location[] a, int indexNum, int first, int last) {// closestPair
		Location divideArray[] = new Location[indexNum]; // array stored before divide
		System.arraycopy(a, 0, divideArray, 0, indexNum); // Copy from previous array

		int totalNum = indexNum; // number of coordinates in the divideArray array
		int divideX = indexNum / 2; // Intermediate value for divide
		Location leftArray[] = new Location[divideX]; //divide - left array
		Location rightArray[] = new Location[totalNum - divideX]; //divide - right array

		if (indexNum <= 3) { // If the number of coordinates is 3 or less
			return bruteForce(divideArray);
		}
		System.arraycopy(divideArray, 0, leftArray, 0, divideX); //divide - left array
		System.arraycopy(divideArray, divideX, rightArray, 0, totalNum - divideX); //divide - right array

		double minLeftArea = 0; //Minimum length of left array
		double minRightArea = 0; //Minimum length of right array
		double minValue = 0; //Minimum lengt

		minLeftArea = closestPair(leftArray, divideX, 0, divideX - 1); // recursive closestPair
		minRightArea = closestPair(rightArray, totalNum - divideX, divideX, totalNum - divideX - 1);
		minValue = Math.min(minLeftArea, minRightArea);// window size (= minimum length)

		// Create window
		for (int i = 0; i < totalNum; i++) { // Set the size for creating a window and creating a new array for the coordinates in the window
			double xGap = Math.abs(divideArray[divideX].x - divideArray[i].x);
			if (xGap < minValue) {
				secondCount++; // size of the array
			} else {
				if (divideArray[i].x > divideArray[divideX].x) {
					break;
				}
			}
		}
		Location firstWindow[] = new Location[secondCount]; // new array for coordinates in window
		int k = 0;
		for (int i = 0; i < totalNum; i++) {
			double xGap = Math.abs(divideArray[divideX].x - divideArray[i].x);
			if (xGap < minValue) { // if it's inside a window
				firstWindow[k] = divideArray[i]; // put in an array
				k++;
			} else {
				if (divideArray[i].x > divideArray[divideX].x) {
					break;
				}
			}
		}
		yQuickSort(firstWindow, 0, secondCount - 1);// Sort by y coordinates
		/ * Coordinates in Window * /
		double length = 0; 
		for (int i = 0; i < secondCount - 1; i++) { // size comparison within window
			for (int j = (i + 1); j < secondCount; j++) {
				double xGap = Math.abs(firstWindow[i].x - firstWindow[j].x);
				double yGap = Math.abs(firstWindow[i].y - firstWindow[j].y);
				if (yGap < minValue) {
					length = (double) Math.sqrt(Math.pow(xGap, 2) + Math.pow(yGap, 2));
					if (length < minValue) { // If the measured distance is less than the current minimum distance
						minValue = length;// Change minimum distance to current distance
						if (length < minNum) { // Conditional statement for registering final coordinate
							minNum = length;
							point1 = firstWindow[i];
							point2 = firstWindow[j];
						}
					}
				} 
				else
					break;
			}
		}
		secondCount = 0;
		return minValue;
	}

	public static double bruteForce(Location[] array) { // When the number of coordinates is less than 3
		double minValue = Double.MAX_VALUE; // minimum distance
		double length = 0; 
		double xGap = 0, yGap = 0; // Difference between x, y coordinates
		if (array.length == 2) { // When there are two coordinates
			xGap = (array[0].x - array[1].x); // Difference between x coordinates
			yGap = (array[0].y - array[1].y); // Difference between y coordinates
			length = (double) Math.sqrt(Math.pow(xGap, 2) + Math.pow(yGap, 2)); // distance between coordinates
			if (length < minNum) { // Conditional statement for registering final coordinate
				minNum = length;
				point1 = array[0];
				point2 = array[1];
			}
			return length;
		} else if (array.length == 3) { // When there are 3 coordinates
			for (int i = 0; i < array.length - 1; i++) {
				for (int j = (i + 1); j < array.length; j++) {
					xGap = (array[i].x - array[j].x); // Difference between x coordinates
					yGap = (array[i].y - array[j].y); // Difference between y coordinates
					length = (double) Math.sqrt(Math.pow(xGap, 2) + Math.pow(yGap, 2));  // distance between coordinates
					if (length < minValue) { // If the measured distance is less than the current minimum distance
						minValue = length; // Change minimum distance to current distance
						if (length < minNum) { // Conditional statement for registering final coordinate
							minNum = length;
							point1 = array[i];
							point2 = array[j];
						}
					}
				}
			}
			return minValue;
		}
		return minValue;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		StringTokenizer token;

		BufferedReader in = new BufferedReader(new FileReader("closest_data.txt"));
		//Input data consists of one x-coordinate and one y-coordinate
		String ch;

		System.out.println("Input data");
		while ((ch = in.readLine()) != null) {
			token = new StringTokenizer(ch, " "); 

			array[count] = new Location(Double.parseDouble(token.nextToken()), Double.parseDouble(token.nextToken())); // put in an array
			count++; // the number of coordinates actually in the array
			System.out.println("x: "+array[count - 1].x + ", y: " + array[count - 1].y);
		}

		xQuickSort(array, 0, count - 1); // Sorting by x value

		double result; // minimum distance
		result = closestPair(array, count, 0, count - 1); // ClosestPair start
		System.out.println("Output Data");// minimum distance coordinates and distance output
		System.out.println("(" + point1.x + ", " + point1.y + ")");
		System.out.println("(" + point2.x + ", " + point2.y + ")");
		System.out.println("Minimum Distance : " + result);

	}
}
