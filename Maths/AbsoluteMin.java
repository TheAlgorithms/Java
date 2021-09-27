package Maths;

import java.util.Arrays;

/**
 * description:
 *
 * <p>absMin([0, 5, 1, 11]) = 0, absMin([3 , -10, -2]) = -2
 */

public class AbsoluteMin {
  int [] theArray;
  public static int AbsMin(int[] tab){
    int Min= tab[0];
    for (int j = 1; j< tab.length;j++){

      if (Math.abs(Min)> Math.abs(tab[j])){
        Min=tab[j];
      }
    }
    return Min;
  }
  public static  void main(String[] args){
    int[] tab1={0,5,1,11};
    int[] tab2={3, -10,-2};
    System.out.println("min 1:"+ AbsMin(tab1));
    System.out.println("min 1:"+ AbsMin(tab2));
  }
}

