package Maths;

import java.util.Arrays;

/**
 * description:
 *
 * <p>absMax([0, 5, 1, 11]) = 11, absMax([3 , -10, -2]) = -10
 */
public class AbsoluteMax {
  int [] theArray;
  public static int AbsMax(int[] tab){
    int Max= tab[0];
    for (int j = 1; j< tab.length;j++){

      if (Math.abs(Max)< Math.abs(tab[j])){
        Max=tab[j];
      }
    }
    return Max;
  }

  public static  void main(String[] args){
    int[] tab1={0,5,1,11};
    int[] tab2={3, -10,-2};
    System.out.println("max 1:"+ AbsMax(tab1));
    System.out.println("max 1:"+ AbsMax(tab2));
  }
}

