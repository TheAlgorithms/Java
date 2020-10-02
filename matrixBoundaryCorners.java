/**
 * Write a description of class boundary_diagonals here.
 * java program to give user 2 choices (1)sum of diagonal elements of a matrix (2)sum of corner elements
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class boundary_diagonals
{
    public static void main()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter no. of rows of square matrix");
        int n=sc.nextInt();
        int a[][]=new int[n][n];
        System.out.println("enter elements");
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                a[i][j]=sc.nextInt();
            }
        }
        System.out.println("original array is");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(a[i][j]+"\t");
            }
            System.out.println();
        }
        
        System.out.println("1. sum of diagonal elements");
        System.out.println("2. sum of corner elements");
        System.out.println("enter choice 1/2");
        int ch=sc.nextInt();
        int s=0;
        switch(ch)
        {
            case 1:
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(i==j||(i+j)==(n-1))
                    s+=a[i][j];
                }
            }
            System.out.println("sum of diagonal elements is "+s);
            break;
            
            case 2:
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if((i==0&&j==0)||(i==0&&j==n-1)||(i==n-1&&j==0)||(i==n-1&&j==n-1))
                    s+=a[i][j];
                }
            }
            System.out.println("sum of corner elements is "+s);
            
            default:System.out.println("INVALID CHOICE");
        }
    }
}
