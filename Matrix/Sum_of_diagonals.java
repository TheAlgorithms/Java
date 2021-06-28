import java.util.*;
class Sum_of_Diagonals
{
    public static void main(String[]args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Input the number of rows and columns for the square matrix");
        int m=sc.nextInt();
        int a[][]=new int[m][m];
        for(int x=0;x<m;x++)
        {
            for(int y=0;y<m;y++)
            {
                a[x][y]=sc.nextInt();
            }
        }
        System.out.println("The matrix is-");
        for(int x=0;x<m;x++)
        {
            for(int y=0;y<m;y++)
            {
                System.out.print(a[x][y]+"\t");
            }
            System.out.println();
        }
        int sum1=0;int sum2=0;
        for(int x=0;x<m;x++)
        {
            for(int y=0;y<m;y++)
            {
                if(x==y)
                sum1=sum1+a[x][y];
                if(x+y==(m-1))
                sum2=sum2+a[x][y];
            }
        }
        System.out.println("Sum of left diagonal is-"+sum1);
        System.out.println("Sum of right diagonal is-"+sum2);
    }
}
      
 

         