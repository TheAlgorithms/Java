// This is a new dynamic programming problem. I have published a research paper
// about this problem under the guidance of Professor Rao Li (University of
// South Carolina, Aiken) along with my friend. The paper has been accepted by
// the Journal of Mathematics and Informatics.The problem is a variation of the
// standard longest common subsequence problem. It says that--> "Suppose there
// are two strings, X and Y. Now we need to find the longest string, which is a
// subsequence of X and a substring of Y."

// Link of the paper--> http://www.researchmathsci.org/JMIart/JMI-v25-8.pdf

import java.util.Scanner;

class LCSubseqSubstr {

 public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      System.out.print("Input the first string: ");

      String X = input.nextLine();
      System.out.print("Input the second string: ");

      String Y = input.nextLine();
      int m = X.length(), n = Y.length();

      int[][] W1 = new int[m + 1][n + 1];

      System.out.println("The longest string which is a subsequence of " + X +
                       " and a " + "substring of " + Y + " is " +
                       LCSS(X, Y, m, n, W1));
      System.out.println(
        "The length of the longest string which is a subsequence of " + X +" and a substring of " 
+ Y + " is " + LCSS(X, Y, m, n, W1).length());
  }

 public static String LCSS(String X, String Y, int m, int n, int[][] W) {
     int maxLength = 0;    // keeps the max length of LCSS
     int lastIndexOnY = n; // keeps the last index of LCSS in Y
     W = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
          W[i][j] = W[i - 1][j - 1] + 1;
        } else {
          W[i][j] = W[i - 1][j];
        }
        if (W[i][j] > maxLength) {
          maxLength = W[i][j];
          lastIndexOnY = j;
         }
       }
    }
    return Y.substring(lastIndexOnY - maxLength, lastIndexOnY);
  }
}
