
import java.util.Scanner;
import java.io.*;

import java.io.*;
public class mergesortString{
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        String[] names={"jia","charmi","honey","abbrahim"};
        
        
        int low=0,high=names.length-1;
        sort(names,low,high);
        printAll(names);
    }

    public static void sort(String[] names,int low,int high)
    {
        if(low<high)
        {
            int mid=(low+high)/2;
            sort(names,low,mid);
            sort(names,mid+1,high);
            merge(names,low,mid,high);
        }
    }


    public static void merge(String[] names,int low,int mid,int high)
    {
           int n1=mid-low+1;
           int n2=high-mid;

           String[] arr1=new String[n1];
           for(int i=0;i<n1;i++)
           {
                arr1[i]=names[low+i];
           }
           String[] arr2=new String[n2];
           for(int i=0;i<n2;i++)
           {
               arr2[i]=names[mid+1+i];

           }
           int i=0,j=0;
           int k=low;
           while(i<n1&&j<n2)
           {
                if( arr1[i].compareTo(arr2[j])<=0  )
                {
                    names[k++]=arr1[i];
                    i++;

                }
                else {              
                      names[k++]=arr2[j];
                      j++;
                }
           }
           if(i<n1)
           {
               names[k++]=arr1[i];
               i++;
           }
           if(j<n2)
           {
               names[k++]=arr2[j++];
           }
    }

    public static void printAll(String[] names)
    {
        for(int i=0;i<names.length;i++)
        System.out.println(names[i]);
    }


}