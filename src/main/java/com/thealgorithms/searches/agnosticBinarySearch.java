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
        int k=sc.nextInt();
        sc.close();
        int m =agnosticbinarySearch(arr,k);
        System.out.println(m);        
        
    }
    public static int agnosticbinarySearch(int arr[],int k){
        int start = 0;
        int end = arr.length-1;
        boolean ac=arr[start]>arr[end];
        while(start<=end){
            int mid = end-start/2;
            if (arr[mid]==k){
                return mid;
            }
            if(ac){
            if(k<arr[mid]){
                start=mid+1;
            }
            else{
                end=mid-1;
            }}
            else {
                if(k>arr[mid]){
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
