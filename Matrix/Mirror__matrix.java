import java.util.*;
class Mirror__matrix
{
    public static void main(String[]args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Input the size of the matrix between 2 and 20");
        int m=sc.nextInt();
        if(m<2&&m>20)
            System.out.println("Invalid Input");
        else
            System.out.println("Input the matrix");
        int a[][]=new int[m][m];
        for(int x=0;x<m;x++)
        {
            for(int y=0;y<m;y++)
            {
                a[x][y]=sc.nextInt();
            }
        }
        System.out.println("The original matrix");
        for(int x=0;x<m;x++)
        {
            for(int y=0;y<m;y++)
            {
                System.out.print(a[x][y]+"\t");
            }
            System.out.println();
        }
        int b[][]=new int[m][m];
        for(int x=0;x<m;x++)
        {
            for(int y=0;y<m;y++)
            {
                b[x][y]=a[x][m-y-1];
            }
        }
        System.out.println("The mirror matrix is-");
        for(int x=0;x<m;x++)
        {
            for(int y=0;y<m;y++)
            {
                System.out.print(b[x][y]+"\t");
            }
            System.out.println();
        }
    }
}
        

