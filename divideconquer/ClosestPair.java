package divideconquer;

/**
 * For a set of points in a coordinates system (10000 maximum), ClosestPair class calculates the two
 * closest points.
 *
 * @author: anonymous
 * @author: Marisa Afuera
 */
public final class ClosestPair {

  /** Number of points */
  int numberPoints = 0;
  /** Input data, maximum 10000. */
  private Location[] array;
  /** Minimum point coordinate. */
  Location point1 = null;
  /** Minimum point coordinate. */
  Location point2 = null;
  /** Minimum point length. */
  private static double minNum = Double.MAX_VALUE;

  public static void setMinNum(double minNum) {
    ClosestPair.minNum = minNum;
  }

  public static void setSecondCount(int secondCount) {
    ClosestPair.secondCount = secondCount;
  }

  /** secondCount */
  private static int secondCount = 0;

  /** Constructor. */
  ClosestPair(int points) {
    numberPoints = points;
    array = new Location[numberPoints];
  }

  /** Location class is an auxiliary type to keep points coordinates. */
  public static class Location {

    double x = 0;
    double y = 0;

    /**
     * @param xpar (IN Parameter) x coordinate <br>
     * @param ypar (IN Parameter) y coordinate <br>
     */
    Location(final double xpar, final double ypar) { // Save x, y coordinates
      this.x = xpar;
      this.y = ypar;
    }
  }

  public Location[] createLocation(int numberValues) {
    return new Location[numberValues];
  }

  public Location buildLocation(double x, double y) {
    return new Location(x, y);
  }

  /**
   * xPartition function: arrange x-axis.
   *
   * @param a (IN Parameter) array of points <br>
   * @param first (IN Parameter) first point <br>
   * @param last (IN Parameter) last point <br>
   * @return pivot index
   */
  public int xPartition(final Location[] a, final int first, final int last) {

    Location pivot = a[last]; // pivot
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
    a[i] = a[last];
    a[last] = temp;
    return i; // pivot index
  }

  /**
   * yPartition function: arrange y-axis.
   *
   * @param a (IN Parameter) array of points <br>
   * @param first (IN Parameter) first point <br>
   * @param last (IN Parameter) last point <br>
   * @return pivot index
   */
  public int yPartition(final Location[] a, final int first, final int last) {

    Location pivot = a[last]; // pivot
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
    a[i] = a[last];
    a[last] = temp;
    return i; // pivot index
  }

  /**
   * xQuickSort function: //x-axis Quick Sorting.
   *
   * @param a (IN Parameter) array of points <br>
   * @param first (IN Parameter) first point <br>
   * @param last (IN Parameter) last point <br>
   */
  public void xQuickSort(final Location[] a, final int first, final int last) {

    if (first < last) {
      int q = xPartition(a, first, last); // pivot
      xQuickSort(a, first, q - 1); // Left
      xQuickSort(a, q + 1, last); // Right
    }
  }

  /**
   * yQuickSort function: //y-axis Quick Sorting.
   *
   * @param a (IN Parameter) array of points <br>
   * @param first (IN Parameter) first point <br>
   * @param last (IN Parameter) last point <br>
   */
  public void yQuickSort(final Location[] a, final int first, final int last) {

    if (first < last) {
      int q = yPartition(a, first, last); // pivot
      yQuickSort(a, first, q - 1); // Left
      yQuickSort(a, q + 1, last); // Right
    }
  }

  /**
   * closestPair function: find closest pair.
   *
   * @param a (IN Parameter) array stored before divide <br>
   * @param indexNum (IN Parameter) number coordinates divideArray <br>
   * @return minimum distance <br>
   */
  public double closestPair(final Location[] a, final int indexNum) {

    Location[] divideArray = new Location[indexNum];
    System.arraycopy(a, 0, divideArray, 0, indexNum); // Copy previous array
    int divideX = indexNum / 2; // Intermediate value for divide
    Location[] leftArray = new Location[divideX]; // divide - left array
    // divide-right array
    Location[] rightArray = new Location[indexNum - divideX];
    if (indexNum <= 3) { // If the number of coordinates is 3 or less
      return bruteForce(divideArray);
    }
    // divide-left array
    System.arraycopy(divideArray, 0, leftArray, 0, divideX);
    // divide-right array
    System.arraycopy(divideArray, divideX, rightArray, 0, indexNum - divideX);

    double minLeftArea = 0; // Minimum length of left array
    double minRightArea = 0; // Minimum length of right array
    double minValue = 0; // Minimum lengt

    minLeftArea = closestPair(leftArray, divideX); // recursive closestPair
    minRightArea = closestPair(rightArray, indexNum - divideX);
    // window size (= minimum length)
    minValue = Math.min(minLeftArea, minRightArea);

    // Create window.  Set the size for creating a window
    // and creating a new array for the coordinates in the window
    for (int i = 0; i < indexNum; i++) {
      double xGap = Math.abs(divideArray[divideX].x - divideArray[i].x);
      if (xGap < minValue) {
        ClosestPair.setSecondCount(secondCount + 1); // size of the array
      } else {
        if (divideArray[i].x > divideArray[divideX].x) {
          break;
        }
      }
    }
    // new array for coordinates in window
    Location[] firstWindow = new Location[secondCount];
    int k = 0;
    for (int i = 0; i < indexNum; i++) {
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
              ClosestPair.setMinNum(length);
              point1 = firstWindow[i];
              point2 = firstWindow[j];
            }
          }
        } else {
          break;
        }
      }
    }
    ClosestPair.setSecondCount(0);
    return minValue;
  }

  /**
   * bruteForce function: When the number of coordinates is less than 3.
   *
   * @param arrayParam (IN Parameter) array stored before divide <br>
   * @return <br>
   */
  public double bruteForce(final Location[] arrayParam) {

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
        ClosestPair.setMinNum(length);
      }
      point1 = arrayParam[0];
      point2 = arrayParam[1];
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
          length = Math.sqrt(Math.pow(xGap, 2) + Math.pow(yGap, 2));
          // If measured distance is less than current min distance
          if (length < minValue) {
            // Change minimum distance to current distance
            minValue = length;
            if (length < minNum) {
              // Registering final coordinate
              ClosestPair.setMinNum(length);
              point1 = arrayParam[i];
              point2 = arrayParam[j];
            }
          }
        }
      }
      result = minValue;
    }
    return result; // If only one point returns 0.
  }

  /**
   * main function: execute class.
   *
   * @param args (IN Parameter) <br>
   * @throws IOException If an input or output exception occurred
   */
  public static void main(final String[] args) {

    // Input data consists of one x-coordinate and one y-coordinate

    ClosestPair cp = new ClosestPair(12);
    cp.array[0] = cp.buildLocation(2, 3);
    cp.array[1] = cp.buildLocation(2, 16);
    cp.array[2] = cp.buildLocation(3, 9);
    cp.array[3] = cp.buildLocation(6, 3);
    cp.array[4] = cp.buildLocation(7, 7);
    cp.array[5] = cp.buildLocation(19, 4);
    cp.array[6] = cp.buildLocation(10, 11);
    cp.array[7] = cp.buildLocation(15, 2);
    cp.array[8] = cp.buildLocation(15, 19);
    cp.array[9] = cp.buildLocation(16, 11);
    cp.array[10] = cp.buildLocation(17, 13);
    cp.array[11] = cp.buildLocation(9, 12);

    System.out.println("Input data");
    System.out.println("Number of points: " + cp.array.length);
    for (int i = 0; i < cp.array.length; i++) {
      System.out.println("x: " + cp.array[i].x + ", y: " + cp.array[i].y);
    }

    cp.xQuickSort(cp.array, 0, cp.array.length - 1); // Sorting by x value

    double result; // minimum distance

    result = cp.closestPair(cp.array, cp.array.length);
    // ClosestPair start
    // minimum distance coordinates and distance output
    System.out.println("Output Data");
    System.out.println("(" + cp.point1.x + ", " + cp.point1.y + ")");
    System.out.println("(" + cp.point2.x + ", " + cp.point2.y + ")");
    System.out.println("Minimum Distance : " + result);
  }
}
