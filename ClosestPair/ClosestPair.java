package closestpair;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**

* For a set of points in a coordinates system (10000 maximum), 
* ClosestPair class calculates the two closest points.

* @author: anonymous 
* @author: Marisa Afuera
*/

 public final class ClosestPair {
	/** array length. */
	private static int count = 0; // 
	/** array length. */
	private static int secondCount = 0;	
	/** Input data, maximum 10000. */
	private static Location[] array = new Location[10000];
	/** Minimum point coordinate. */
	private static Location point1 = null; 
	/** Minimum point coordinate. */
	private static Location point2 = null; 
	/** Minimum point length. */
	private static double minNum = Double.MAX_VALUE; 
	
	/**
	* Constructor.
	*/
	private ClosestPair() {
		
	}
	
	/**
	* Location class: Keep coordinates
	*/

	private static class Location { 
		/** coordinate x. */
		private double x = 0;
		/** coordinate y. */
		private double y = 0;
		
		/** Location function.
		 * @param xpar        (IN Parameter)  x coordinate <br/>
		 * @param ypar        (IN Parameter)  y coordinate <br/>
		 */
		
		Location(final double xpar, final double ypar) { //Save x, y coordinates
			this.x = xpar;
			this.y = ypar;
		}
	}

	/** xPartition function: arrange x-axis.
	 * @param a            (IN Parameter)  array of points <br/>
	 * @param first        (IN Parameter)  first point <br/>
	 * @param last         (IN Parameter)  last point <br/>
	 * @return pivot index
	 */
	
	public static int xPartition(
	        final Location[] a, final int first, final int last) {
		Location pivot = a[last]; // pivot
		int pIndex = last;
		int i = first - 1;
		Location temp; // Temporarily store value for position transformation
		for (int j = first; j <= last - 1; j++) {
			if (a[j].x <= pivot.x) { // Less than or less than pivot
				i++;
				temp = a[i]; // array[i] <-> array[j]
				a[i] = a[j];
				a[j] = temp;
			}
		}
		i++;
		temp = a[i]; // array[pivot] <-> array[i]
		a[i] = a[pIndex];
		a[pIndex] = temp;
		return i; // pivot index
	}
	
	/** yPartition function: arrange y-axis.
	 * @param a            (IN Parameter)  array of points <br/>
	 * @param first        (IN Parameter)  first point <br/>
	 * @param last         (IN Parameter)  last point <br/>
	 * @return pivot index
	 */
	
	public static int yPartition(
			final Location[] a, final int first, final int last) { 
		Location pivot = a[last]; // pivot
		int pIndex = last;
		int i = first - 1;
		Location temp; // Temporarily store value for position transformation
		for (int j = first; j <= last - 1; j++) {
			if (a[j].y <= pivot.y) { // Less than or less than pivot
				i++;
				temp = a[i]; // array[i] <-> array[j]
				a[i] = a[j];
				a[j] = temp;
			}
		}
		i++;
		temp = a[i]; // array[pivot] <-> array[i]
		a[i] = a[pIndex];
		a[pIndex] = temp;
		return i; // pivot index
	}
	
	/** xQuickSort function: //x-axis Quick Sorting.
	 * @param a            (IN Parameter)  array of points <br/>
	 * @param first        (IN Parameter)  first point <br/>
	 * @param last         (IN Parameter)  last point <br/>
	 */
	
	public static void xQuickSort(
			   final Location[] a, final int first, final int last) { 
		if (first < last) {
			int q = xPartition(a, first, last); // pivot
			xQuickSort(a, first, q - 1); // Left
			xQuickSort(a, q + 1, last); // Right
		}
	}

	/** yQuickSort function: //y-axis Quick Sorting.
	 * @param a            (IN Parameter)  array of points <br/>
	 * @param first        (IN Parameter)  first point <br/>
	 * @param last         (IN Parameter)  last point <br/>
	 */
	
	public static void yQuickSort(
			    final Location[] a, final int first, final int last) { 
		if (first < last) {
			int q = yPartition(a, first, last); // pivot
			yQuickSort(a, first, q - 1); // Left
			yQuickSort(a, q + 1, last); // Right
		}
	}

	/** closestPair function: find closest pair.
	 * @param a         (IN Parameter) array stored before divide <br/>
	 * @param indexNum  (IN Parameter) number coordinates divideArray <br/>
	 * @return minimum distance <br/>
	 */
	
	public static double closestPair(final Location[] a, final int indexNum) {
		Location[] divideArray = new Location[indexNum]; 
		System.arraycopy(a, 0, divideArray, 0, indexNum); // Copy previous array

		int totalNum = indexNum; // number of coordinates in the divideArray 
		int divideX = indexNum / 2; // Intermediate value for divide
		Location[] leftArray = new Location[divideX]; //divide - left array
		//divide-right array
		Location[] rightArray = new Location[totalNum - divideX]; 

		if (indexNum <= 3) { // If the number of coordinates is 3 or less
			return bruteForce(divideArray);
		}
		//divide-left array
		System.arraycopy(divideArray, 0, leftArray, 0, divideX);
		//divide-right array
		System.arraycopy(
			divideArray, divideX, rightArray, 0, totalNum - divideX); 

		double minLeftArea = 0; //Minimum length of left array
		double minRightArea = 0; //Minimum length of right array
		double minValue = 0; //Minimum lengt

		minLeftArea = closestPair(leftArray, divideX); // recursive closestPair
		minRightArea = closestPair(rightArray, totalNum - divideX);
		// window size (= minimum length)
		minValue = Math.min(minLeftArea, minRightArea); 

		// Create window.  Set the size for creating a window
		// and creating a new array for the coordinates in the window
		for (int i = 0; i < totalNum; i++) { 
			double xGap = Math.abs(divideArray[divideX].x - divideArray[i].x);
			if (xGap < minValue) {
				secondCount++; // size of the array
			} else {
				if (divideArray[i].x > divideArray[divideX].x) {
					break;
				}
			}
		}
		// new array for coordinates in window
		Location[] firstWindow = new Location[secondCount]; 
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
		yQuickSort(firstWindow, 0, secondCount - 1); // Sort by y coordinates
		/* Coordinates in Window */
		double length = 0;
		// size comparison within window
		for (int i = 0; i < secondCount - 1; i++) { 
			for (int j = (i + 1); j < secondCount; j++) {
				double xGap = Math.abs(firstWindow[i].x - firstWindow[j].x);
				double yGap = Math.abs(firstWindow[i].y - firstWindow[j].y);
				if (yGap < minValue) {
					length = Math.sqrt(Math.pow(xGap, 2) + Math.pow(yGap, 2));
					// If measured distance is less than current min distance
					if (length < minValue) { 
						// Change minimum distance to current distance
						minValue = length;
						// Conditional for registering final coordinate
						if (length < minNum) { 
							minNum = length;
							point1 = firstWindow[i];
							point2 = firstWindow[j];
						}
					}
				} 
				else {
					break;
				}	
			}
		}
		secondCount = 0;
		return minValue;
	}

	/** bruteForce function: When the number of coordinates is less than 3.
	 * @param arrayParam   (IN Parameter) array stored before divide <br/>
	 * @return  <br/>
	 */
	
	public static double bruteForce(final Location[] arrayParam) {  
		double minValue = Double.MAX_VALUE; // minimum distance
		double length = 0; 
		double xGap = 0; // Difference between x coordinates
		double yGap = 0; // Difference between y coordinates
		double result = 0;
		
        if (arrayParam.length == 2) {
        	// Difference between x coordinates
            xGap = (arrayParam[0].x - arrayParam[1].x);
            // Difference between y coordinates
		    yGap = (arrayParam[0].y - arrayParam[1].y);
		    // distance between coordinates
		    length = Math.sqrt(Math.pow(xGap, 2) + Math.pow(yGap, 2));
		    // Conditional statement for registering final coordinate
		    if (length < minNum) { 
	    	      minNum = length;
		          point1 = arrayParam[0];
			      point2 = arrayParam[1];
		      }	
		      result = length;
        }
        if (arrayParam.length == 3) { 
        	for (int i = 0; i < arrayParam.length - 1; i++) {
		    	  for (int j = (i + 1); j < arrayParam.length; j++) {
		    		  // Difference between x coordinates
			    	  xGap = (arrayParam[i].x - arrayParam[j].x); 
			    	  // Difference between y coordinates
				      yGap = (arrayParam[i].y - arrayParam[j].y); 
				      // distance between coordinates
				      length = 
				    	  Math.sqrt(Math.pow(xGap, 2) + Math.pow(yGap, 2)); 
			          // If measured distance is less than current min distance
				      if (length < minValue) { 
				    	  // Change minimum distance to current distance
				          minValue = length; 
					      if (length < minNum) { 
					          // Registering final coordinate
						      minNum = length;
						      point1 = arrayParam[i];
						      point2 = arrayParam[j];
					      }
				      }
			      }
		      }
		      result = minValue;
         
         }
         return result;
	}

	/** main function: execute class.
	 * @param args   (IN Parameter) <br/>
	 * @throws IOException If an input or output 
     *                     exception occurred
	 */
	
	public static void main(final String[] args) throws IOException {

		StringTokenizer token;

		BufferedReader in = new BufferedReader(new FileReader(
				"closestpair\\closest_data.txt"));
		//Input data consists of one x-coordinate and one y-coordinate
		String ch;
		System.out.println("Input data");
		try {
		    while ((ch = in.readLine()) != null) {
			    token = new StringTokenizer(ch, " "); 
			    // put in an array
			    array[count] = 	new Location(
			        Double.parseDouble(
			    	    token.nextToken()),
			            Double.parseDouble(token.nextToken())); 
			    count++; // the number of coordinates actually in the array
			    System.out.println(
			    	"x: " + array[count - 1].x + ", y: " 
			        + array[count - 1].y);
		    }
		}
		finally {
		    in.close();
		}

		xQuickSort(array, 0, count - 1); // Sorting by x value

		double result; // minimum distance
		result = closestPair(array, count); // ClosestPair start
		// minimum distance coordinates and distance output
		System.out.println("Output Data"); 
		System.out.println("(" + point1.x + ", " + point1.y + ")");
		System.out.println("(" + point2.x + ", " + point2.y + ")");
		System.out.println("Minimum Distance : " + result);

	}
}
