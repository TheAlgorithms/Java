package com.thealgorithms.recursion;

public class TowerOfHanoi {
    public void solveHanoi(int n,char src,char dest,char aux)
    {
        if(n==1)
        {
            System.out.println("Move disk 1 from "+src+" to "+dest);
            return;
        }
        solveHanoi(n-1,src,aux,dest);
        System.out.println("Move disk "+n+" from "+src+" to "+dest);
        solveHanoi(n-1,aux,dest,src);
    }
}
