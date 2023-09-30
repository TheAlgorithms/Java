package com.thealgorithms.backtracking;
/*The problem consists of three rods and a number of disks of different sizes, which can be slid onto any rod. The puzzle starts with the disks in a neat stack in ascending order of size on one rod, the source rod, with the largest disk at the bottom and the smallest at the top. The goal is to move the entire stack to another rod, the target rod, subject to the following rules:

Only one disk can be moved at a time.
A disk can only be placed on top of a larger disk or an empty rod. */

public class TowerOfHanoi {
    //the three rods are src-source,helper,destination and n is number of disk
    public static void towerOfHanoi(int n,String src,String helper,String dest){
        if(n==1){//this is base condition
            System.out.println("transfer disk  "+n+"  from  "+src+"  to  "+dest);
            return ;
        }
//Move the top n-1 disks from the source rod to an auxiliary rod,
// using the target rod as the temporary rod
        towerOfHanoi(n-1, src, dest, helper);
        //Move the largest disk from the source rod to the target rod.
        System.out.println("transfer disk  "+n+"  from  "+src+"  to  "+dest);
        //Move the n-1 disks from the auxiliary rod to the target rod, 
        //using the source rod as the temporary rod.
        towerOfHanoi(n-1, helper, src, dest);
        
    }

    public static void main(String[] args) {
        int n=10;//number of disk, you can change it or make it user input using Scanner
        
        towerOfHanoi(n,"S", "H", "D");

        
    }
}
