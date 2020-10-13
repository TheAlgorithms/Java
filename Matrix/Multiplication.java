import java.util.*;
class Multiplication
{
 public static void main(String[]args)
 {
     Scanner sc=new Scanner(System.in);
     System.out.println("Input the no. of rows and columns of matrix 1");
     int m1=sc.nextInt();
     int n1=sc.nextInt();
     System.out.println("Input the no.of rows and columns of matrix 2");
     int m2=sc.nextInt();
     int n2=sc.nextInt();
     int a[][]=new int[m1][n1];System.out.println("Input the elements in 1st matrix;");
     for(int x=0;x<m1;x++)
        {
            for(int y=0;y<n1;y++)
            {
                a[x][y]=sc.nextInt();
            }
        }
        int b[][]=new int[m2][n2];System.out.println("Input the elements in 2nd matrix;");
     for(int x=0;x<m2;x++)
        {
            for(int y=0;y<n2;y++)
            {
                b[x][y]=sc.nextInt();

            }
        }
        System.out.println("The first matrix is-");
         for(int x=0;x<m1;x++)
        {
            for(int y=0;y<n1;y++)
            {
                System.out.print(a[x][y]+" ");
            }
            System.out.println();
        }
        System.out.println("The second matrix is-");
        for(int x=0;x<m2;x++)
        {
            for(int y=0;y<n2;y++)
            {
                System.out.print(b[x][y]+" ");

            }
            System.out.println();
        }
        if(n1!=m2)
        System.out.println("Multiplication not possible");
        else
        {
            int res[][]=new int[m1][n2];
            for(int d=0;d<m1;d++)
            {
                int sum=0;
                for(int e=0;e<n2;e++)
                {
                    for(int x=0;x<m2;x++)
                    {
                    res[d][e]=res[d][e]+(a[d][x]*b[x][e]);
                }
            }
        }
        System.out.println("The resultant matrix is-");
        for(int i=0;i<m1;i++)
        {
            for(int j=0;j<n2;j++)
            {
                System.out.print(res[i][j]+" ");
            }
            System.out.println();
        }
    }
}
}
                    
        
     