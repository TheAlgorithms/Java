import java.io.*;
import java.util.*;
public class sort
{
public static void main(String args[])
{
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();//enter size of array
int arr[]=new int[n];//create an array
for(int i=0;i<n;i++)
{
arr[i]=sc.nextInt(); //enter array elements
}
    int f=0;
do
{
    f=0;
   for (int j = 0; j < n-1; j++)
   {
      if (arr[j] > arr[j+1])
                {
                    // swap temp and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
          f=1;
                }
                }}while(f==1);
  for(int i=0;i<n;i++)   
    {
    System.out.println(arr[i]);//print the sorted array
    }
    }
    }
