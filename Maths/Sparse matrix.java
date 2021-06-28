
import java.util.Scanner;
 
public class sparse 
{
    public static void main(String args[])
    {
    	//Initilization of Matrix
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter numbers of row of the matrix");
        int m = sc.nextInt();
        System.out.println("Enter numbers of Coloum of the matrix");
        int n = sc.nextInt();
        double[][] mat = new double[m][n];
        int zeros = 0;
        System.out.println("Enter the elements of the matrix: ");
        //counting number of zeros in matrix
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<n; j++)
            {
                mat[i][j] = sc.nextDouble();
                if(mat[i][j] == 0)
                {
                    zeros++;
                }
            }
        }
        	//checking if formula satisfy or not 
        if(zeros > (m*n)/2)
        {
            System.out.println("The matrix is a sparse matrix");
        }
        else
        {
            System.out.println("The matrix is not a sparse matrix");
        }
 
        sc.close();
    }
}