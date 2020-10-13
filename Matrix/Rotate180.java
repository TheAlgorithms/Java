import java.util.*;
class Rotate180
{
    public static void main(String[]args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Input the size of the array");
        int m=sc.nextInt();
        if(m>=2&&m<=20)
        {
            int a[][]=new int[m][m];
            System.out.println("Input the size of the array");
            for(int x=0;x<m;x++)
            {
                for(int y=0;y<m;y++)
                {
                    a[x][y]=sc.nextInt();
                }
            }
            System.out.println("The original matrix is-");
            for(int x=0;x<m;x++)
            {
                for(int y=0;y<m;y++)
                {
                    System.out.print(a[x][y]+"\t");
                }
                System.out.println();
            }
            int b[][]=new int[m][m];
            System.out.println("The rotated matrix is-");
            for(int x=0;x<m;x++)
            {
                for(int y=0;y<m;y++)
                {
                    b[x][y]=a[m-x-1][m-y-1];
                    System.out.print(b[x][y]+" \t");
                }
                System.out.println();
            }
        }
        else
            System.out.println("Size out of range");
    }
}

         