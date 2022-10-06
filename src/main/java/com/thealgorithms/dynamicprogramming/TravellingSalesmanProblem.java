package com.thealgorithms.dynamicprogramming;

/*
The traveling salesman problems abide by a salesman and a set of cities. 
The salesman has to visit every one of the cities starting from a certain one (e.g., the hometown) and to return to the same city. 
The challenge of the problem is that the traveling salesman needs to minimize the total length of the trip.

Suppose the cities are x1 x2..... xn where cost cij denotes the cost of travelling from city xi to xj. 
The travelling salesperson problem is to find a route starting and ending at x1 that will take in all cities with the minimum cost.
*/


import java.util.Scanner;

public class TSP {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int c[][]=new int[10][10];
        int tour[]=new int[10];
        System.out.print("Enter No. of Cities: ");
        int n = in.nextInt();
        if(n==1){
            System.out.println("Path is not possible!");
            System.exit(0);
        }
        System.out.println("Enter the Cost Matrix:");
        for(int i=1;i<=n;i++)
            for(int j=1;j<=n;j++)
                c[i][j] = in.nextInt();        

        for(int i=1;i<=n;i++)
            tour[i]=i;       

        int cost = tspdp(c, tour, 1, n);
        System.out.print("The Optimal Tour is: ");
        for(int i=1;i<=n;i++)
            System.out.print(tour[i]+"->");
        System.out.println("1");        

        System.out.println("Minimum Cost: "+cost);
    }

    static int tspdp(int c[][], int tour[], int start, int n){
        int mintour[]=new int[10];
        int temp[]=new int[10];
        int mincost=999,ccost;
        if(start == n-1)
            return (c[tour[n-1]][tour[n]] + c[tour[n]][1]);

        for(int i=start+1; i<=n; i++) {

            for(int j=1; j<=n; j++)
                temp[j] = tour[j];
            
            temp[start+1] = tour[i];
            temp[i] = tour[start+1];            
            if((c[tour[start]][tour[i]]+(ccost=tspdp(c,temp,start+1,n)))<mincost){
                mincost = c[tour[start]][tour[i]] + ccost;              
                for(int k=1; k<=n; k++)
                    mintour[k] = temp[k];
            }
        }        

        for(int i=1; i<=n; i++)
            tour[i] = mintour[i];

        return mincost;       
    }
}
