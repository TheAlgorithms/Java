package com.thealgorithms.strings;

import java.util.*;

/**
 * Isomorphic
 */
public class Isomorphic {

  public static boolean isIsomorphic(String s, String t) {
        
    //  Check if two strings have same length or not
    //  IF      not then return false
    //  ELSE    Check with further Logic
    // -------------------------------------------------------------
    if(s.length() != t.length()){
        return false;
    }
    // -------------------------------------------------------------

    // To mark the characters of string using MAP
    // character of first string as KEY and another as VALUE
    // now check occurence by keeping the track with SET data structure
    // -------------------------------------------------------------
    boolean check = true;
    Map<Character, Character> map = new HashMap<Character, Character>();
    Set<Character> set = new HashSet<Character>();
    
    for(int i=0; i<s.length(); i++){
        if(map.containsKey(s.charAt(i))){
            if(t.charAt(i) != map.get(s.charAt(i))){
                check = false;
                break;
            }
        }
        else{
            if(set.contains(t.charAt(i))){
                check = false;
                break;
            }
            
            map.put(s.charAt(i), t.charAt(i));
        }
        set.add(t.charAt(i));
    }
    
    // -------------------------------------------------------------

    
    return check;
}

  public static void main(String[] args) {

    String str1 = new String();
    String str2 = new String();

    // Taking input of strings from user
    // ---------------------------------------
    Scanner sc = new Scanner(System.in);
    str1 = sc.nextLine();
    str2 = sc.nextLine();
    // ---------------------------------------

    // Checking is two strings are isomorphic
    boolean ans = isIsomorphic(str1, str2);

    System.out.println(ans);
  }
}