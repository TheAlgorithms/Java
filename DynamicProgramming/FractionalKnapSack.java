/*
1. You are given a number n, representing the count of items.
2. You are given n numbers, representing the values of n items.
3. You are given n numbers, representing the weights of n items.
3. You are given a number "cap", which is the capacity of a bag you've.
4. You are required to calculate and print the maximum value that can be created in the bag without overflowing it's capacity.
Note1 -> Items can be added to the bag even partially. But you are not allowed to put same items again and again to the bag.
*/



import java.io.*;
import java.util.*;
 
public class Main {
 
    public static class Item implements Comparable<Item>{
        int value, wt;
        double ratio;
 
        public int compareTo(Item obj) {
            if (this.ratio == obj.ratio) {
                return 0;
            }
            if (this.ratio > obj.ratio) {
                return +1;
            }
            if (this.ratio < obj.ratio) {
                return -1;
            }
            return 0;
        }
    }
 

public static void insert(int arr[], Scanner scn) {
    for (int idx = 0; idx < arr.length; idx++) {
        arr[idx] = scn.nextInt();
    }
}
public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int values[] = new int[n];
    insert(values, scn);
    int wts[] = new int[n];
    insert(wts, scn);
    int maxCapacity = scn.nextInt();
 
    //ratio  findout
    Item arr[] = new Item[n];
 
    //assigning vales
    for (int idx = 0; idx < n; idx++) {
        Item temp = new Item();
        temp.value = values[idx];
        temp.wt = wts[idx];
        temp.ratio = (temp.value * 1.0) / temp.wt;
        arr[idx] = temp;
    }
    
    Arrays.sort(arr);
 
    double ans = 0;
    int i = n - 1;
    int currentCapacity = maxCapacity;
    while (i >= 0 && maxCapacity != 0) {
        if (arr[i].wt <= currentCapacity) {
            ans += arr[i].value;
            currentCapacity -= arr[i].wt;
        } else {
            ans += (arr[i].ratio * currentCapacity);
            currentCapacity = 0;
        }
        i--;
    }
 
    System.out.println(ans);
}
}
