import java.util.Scanner;

public class sparseMatrix
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("enter the size of matrix");
        int n=in.nextInt(); //size of the square matrix
        int a[][]=new int[n][n];
        System.out.println("enter elements: ");
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(i==j || j==i+1 || i==j+1)
                {
                    a[i][j]=in.nextInt();
                }
            }
        }
        System.out.println("print matrix: ");
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(i==j || j==i+1 || i==j+1)
                {
                    System.out.print(a[i][j]+" ");
                }
                else
                    System.out.print("*  ");
            }
            System.out.println("\n");
        }
    }
}