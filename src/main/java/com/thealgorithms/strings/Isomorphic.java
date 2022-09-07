package com.thealgorithms.strings;
import java.util.*;

public class Isomorphic {
  public static boolean checkStrings(String s, String t) {
        
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
    Map<Character, Character> map = new HashMap<Character, Character>();
    Set<Character> set = new HashSet<Character>();
    
    for(int i=0; i<s.length(); i++){
        if(map.containsKey(s.charAt(i))){
            if(t.charAt(i) != map.get(s.charAt(i))){
                return false;
            }
        }
        else{
            if(set.contains(t.charAt(i))){
                return false;
            }
            
            map.put(s.charAt(i), t.charAt(i));
        }
        set.add(t.charAt(i));
    }
    
    // -------------------------------------------------------------

    
    return true;
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
    boolean ans = checkStrings(str1, str2);

    System.out.println(ans);
  }
}
