package Others;

import java.util.Scanner;

public class RootPrecision {

  public static void main(String[] args) {
    // take input
    Scanner scn = new Scanner(System.in);

    // N is the input number
    int N = scn.nextInt();

    // P is precision value for eg - P is 3 in 2.564 and 5 in 3.80870.
    int P = scn.nextInt();
    System.out.println(squareRoot(N, P));

    scn.close();
  }

  public static double squareRoot(int N, int P) {
    // rv means return value
    double rv;

    double root = Math.pow(N, 0.5);

    // calculate precision to power of 10 and then multiply it with root value.
    int precision = (int) Math.pow(10, P);
    root = root * precision;
    /*typecast it into integer then divide by precision and again typecast into double
    so as to have decimal points upto P precision */

    rv = (int) root;
    return rv / precision;
  }
}
