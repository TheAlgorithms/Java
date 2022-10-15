package com.thealgorithms.maths;

import java.util.*;
public class Mean {
public static void main(String args[])
{ 
    Scanner sc =new Scanner(System.in);
    System.out.println("Enter n");
   int n =sc.nextInt();
     System.out.println("Enter array elements");
 int arr[]=new int[n];
for(int i=0;i<n;i++)
{
arr[i]=sc.nextInt();
}

int sum=0;
for(int i=0;i<n;i++)
{
sum+=arr[i];
}

float mean=sum/n;
System.out.println("Mean is "+mean);
    sc.close();


}
}