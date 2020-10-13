import java.util.*;
class Transpose
{
    public static void main(String[]args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Input the no. of rows in the array");
        int m=sc.nextInt();
        System.out.println("Input he no.of columns in the array");
        int n=sc.nextInt();
        int a[][]=new int[m][n];
        System.out.println("Enter the elements in the matrix");
        for(int x=0;x<m;x++)
        {
            for(int y=0;y<n;y++)
            {
                a[x][y]=sc.nextInt();
            }
        }
        System.out.println("The original matrix is-");
        for(int x=0;x<m;x++)
        {
            for(int y=0;y<n;y++)
            {
                System.out.print(a[x][y]+"\t");
            }
            System.out.println();
        }
        int b[][]=new int[n][m];
        System.out.println("The transposed matrix is");
        for(int x=0;x<n;x++)
        {
            for(int y=0;y<m;y++)
            {
                b[x][y]=a[y][x];
                System.out.print(b[x][y]+"\t");
            }
            System.out.println();
        }
    }
}