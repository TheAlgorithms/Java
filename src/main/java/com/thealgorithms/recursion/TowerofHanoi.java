package com.thealgorithms.recursion;
public class TowerofHanoi{
    public static void towerofHanoi(int n,String src,String helper,String dest){
      if(n==1){
        System.out.println("Tranfer disk"+n+"from"+src+"to"+dest);
        return;
      }
      towerofHanoi(n-1, src,  dest,helper);
      System.out.println("tranfer disk"+n+"from"+src+"to"+helper);
      towerofHanoi(n-1, helper,src,dest);
    }
    
}