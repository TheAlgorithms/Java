package com.thealgorithms.misc;

import java.util.*;

/**
 * The array is divided into four sections: a[1..Lo-1] zeroes a[Lo..Mid-1] ones
 * a[Mid..Hi] unknown a[Hi+1..N] twos If array [mid] =0, then swap array [mid]
 * with array [low] and increment both pointers once. If array [mid] = 1, then
 * no swapping is required. Increment mid pointer once. If array [mid] = 2, then
 * we swap array [mid] with array [high] and decrement the high pointer once.
 * For more information on the Dutch national flag algorithm refer
 * https://en.wikipedia.org/wiki/Dutch_national_flag_problem
 */
public class Sort012D {

    public static void main(String args[]) {
        Scanner np = new Scanner(System.in);
        int n = np.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = np.nextInt();
        }
        int a1[]=sort012(a);
        System.out.println("The sorted elements from 'sort012' method are ");
        for(int i:a1){
            System.out.print(i+" ");
        }
        int a2[]=approach2(a);
        System.out.println("The sorted elements from 'approach2' method are ");
        for(int i:a2){
            System.out.print(i+" ");
        }
    }

    public static int [] sort012(int[] a) {
        int l = 0;
        int h = a.length - 1;
        int mid = 0;
        int temp;
        while (mid <= h) {
            switch (a[mid]) {
                case 0: {
                    temp = a[l];
                    a[l] = a[mid];
                    a[mid] = temp;
                    l++;
                    mid++;
                    break;
                }
                case 1:
                    mid++;
                    break;
                case 2: {
                    temp = a[mid];
                    a[mid] = a[h];
                    a[h] = temp;
                    h--;
                    break;
                }
            }
        }
        return a;
    }
    static int[] approach2(int a[]){
        int cnt0=0,cnt1=0,cnt2=0;
        for(int i:a){
            if(i==0)cnt0++;
            else if(i==1)cnt1++;
            else cnt2++;
        }
        int k=0;
        while(cnt0-->0)a[k++]=0;
        while(cnt1-->0)a[k++]=1;
        while(cnt2-->0)a[k++]=2;
        return a;
    }
}
