package com.others;

/**
*An Abecedarian is a word where each letter is in alphabetical order
*
*/
public class Abecedarian {
    /**
    * This method checks if any given input string is Abecedarian or not.
    *
    * @param s String which we have to check is Abecedarian or not
    * @return boolean indicating true or false
    */

  public static boolean isAbecedarian(String s){
    int index=s.length()-1;
    s=s.toUpperCase();

    for (int i=0;i<index;i++){
        if (s.charAt(i)>s.charAt(i+1))
        return false;
    }
    return true;
  }

}
