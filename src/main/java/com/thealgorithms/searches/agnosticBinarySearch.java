package com.thealgorithms.searches;
import java.util.*;
public class agnosticBinarySearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the size of an array");
        int n = sc.nextInt();
        int arr[]= new int[n];
        System.out.println("enter the "+n+" elements to store in an array");
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        System.out.println("enter key element to search in a array");
        int key=sc.nextInt();
        sc.close();
        int keyIndexPosition =agnosticbinarySearch(arr,key);
        System.out.println(keyIndexPosition);        
        
    }
    public static int agnosticbinarySearch(int arr[],int key){
        int start = 0;
        int end = arr.length-1;
        boolean arrDescending=arr[start]>arr[end];  //checking for Array is in ascending order or descending order.
        while(start<=end){
            int mid = end-start/2;
            if (arr[mid]==key){
                return mid;
            }
            if(arrDescending){       // boolean is true then our array is in descending order 
                if(key<arr[mid]){
                    start=mid+1;
                }
                else{
                    end=mid-1;
                }
            }
            else {         // otherwise our array is in ascending order 
                if(key>arr[mid]){
                   start=mid+1;
               }
               else{
                   end=mid-1;
               }
            }
        }
        return -1;
    }
}
