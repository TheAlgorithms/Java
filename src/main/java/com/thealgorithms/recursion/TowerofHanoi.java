package com.thealgorithms.recursion;
public class TowerofHanoi{
    public static void towerofHanoi(int n,String src,String helper,String dest){
      if(n==1){
        System.out.println("Tranfer disk"+n+"from"+src+"to"+dest);
        return;
      }
      //tranfer top n-1 from src to helper using dest as helper
      towerofHanoi(n-1, src,  dest,helper);


      //transfer nth from src to dest
      System.out.println("tranfer disk"+n+"from"+src+"to"+helper);

      //transfer n-1 from helper to dest using src as "helper"
      towerofHanoi(n-1, helper,src,dest);
    }

}