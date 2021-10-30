// You are given N points on a plane. Write a program which will find the sum of squares of distances between all pairs of points.

// Input
// The first line of input contains one integer number N (1 ≤ N ≤ 100 000) — the number of points. Each of the following N lines contain two integer numbers X and Y ( - 10 000 ≤ X, Y ≤ 10 000) — the coordinates of points. Two or more points may coincide.

// Output
// The only line of output should contain the required sum of squares of distances between all pairs of points.

// Examples
// input
// 4
// 1 1
// -1 -1
// 1 -1
// -1 1
// output
// 32

import java.util.*;
import java.lang.*;
import java.io.*;
public class Points
{
  public static void main(String[] args)
  {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();

    long[] x = new long[n];
    long[] y = new long[n];

    long ans = 0;
    for (int i = 0; i < n; i++)
    {
      x[i] = scn.nextInt();
      y[i] = scn.nextInt();
      ans = ans + (x[i] * x[i] * (n - 1l));
      ans = ans + (y[i] * y[i] * (n - 1l));
    }

    long[] suffixX = new long[n];
    long[] suffixY = new long[n];
    for (int i = n - 1; i >= 0; i--)
    {
      if (i < n - 1)
      {
        ans = ans - (2l * x[i] * suffixX[i + 1]);
        ans = ans - (2l * y[i] * suffixY[i + 1]);

        suffixX[i] = x[i] + suffixX[i + 1];
        suffixY[i] = y[i] + suffixY[i + 1];
      }
      else
      {
        suffixX[i] = x[i];
        suffixY[i] = y[i];
      }
    }
    System.out.println(ans);
  }
}
                                
