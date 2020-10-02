
import java.util.*;

class StrassenAlgo{

    private static int[][] Strassen(int[][] A, int[][] B) {
        int n = 3;
        int[][] res = new int[n][n];
        if (n == 1) {
            res[0][0] = A[0][0] * B[0][0];
        } else {
            int[][] a = new int[n / 2][n / 2];
            int[][] b = new int[n / 2][n / 2];
            int[][] c = new int[n / 2][n / 2];
            int[][] d = new int[n / 2][n / 2];

            int[][] e = new int[n / 2][n / 2];
            int[][] f = new int[n / 2][n / 2];
            int[][] g = new int[n / 2][n / 2];
            int[][] h = new int[n / 2][n / 2];

            divideArray(A, a, 0, 0);
            divideArray(A, b, 0, n / 2);
            divideArray(A, c, n / 2, 0);
            divideArray(A, d, n / 2, n / 2);

            divideArray(B, e, 0, 0);
            divideArray(B, f, 0, n / 2);
            divideArray(B, g, n / 2, 0);
            divideArray(B, h, n / 2, n / 2);

            int[][] p1 = Strassen(addMatrices(a, d), addMatrices(e, h));
            int[][] p2 = Strassen(addMatrices(c,d),e);
            int[][] p3 = Strassen(a, subMatrices(f, h));
            int[][] p4 = Strassen(d, subMatrices(g, e));
            int[][] p5 = Strassen(addMatrices(a,b), h);
            int[][] p6 = Strassen(subMatrices(c, a), addMatrices(e, f));
            int[][] p7 = Strassen(subMatrices(b, d), addMatrices(g, h));

            int[][] C11 = addMatrices(subMatrices(addMatrices(p1, p4), p5), p7);
            int[][] C12 = addMatrices(p3, p5);
            int[][] C21 = addMatrices(p2, p4);
            int[][] C22 = addMatrices(subMatrices(addMatrices(p1, p3), p2), p6);

            copySubArray(C11, res, 0, 0);
            copySubArray(C12, res, 0, n / 2);
            copySubArray(C21, res, n / 2, 0);
            copySubArray(C22, res, n / 2, n / 2);
        }
        return res;
    }

    public static int[][] addMatrices(int[][] a, int[][] b) {
        int n = a.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = a[i][j] + b[i][j];
            }
        }
        return res;
    }

    public static int[][] subMatrices(int[][] a, int[][] b) {
        int n = a.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = a[i][j] - b[i][j];
            }
        }
        return res;
    }

   public static void divideArray(int[][] P, int[][] C, int iB, int jB)
   {
       for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
           for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
               C[i1][j1] = P[i2][j2];
   }

   public static void copySubArray(int[][] C, int[][] P, int iB, int jB)
   {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
   }
   public static void main(String[] args){
     Scanner s = new Scanner(System.in);
     System.out.println("Enter a 3x3 matrix");
     int a[][] = new int[3][3];
     for(int i=0;i<3;i++)
       for(int j=0;j<3;j++)
           a[i][j] = s.nextInt();
     System.out.println("Enter a 3x3 matrix");
     int b[][] = new int[3][3];
     for(int i=0;i<3;i++)
       for(int j=0;j<3;j++)
          b[i][j] = s.nextInt();
     int[][] ans = Strassen(a,b);
     System.out.println("After matrix multipication");
     for(int i=0;i<3;i++){
       for(int j=0;j<3;j++){
           System.out.print(ans[i][j] + " ");
         }
         System.out.println("");
     }

  }
}
