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
    Map<Character, Character> mapCharactersFrequency = new HashMap<Character, Character>();
    Set<Character> trackUinqueCharacter = new HashSet<Character>();
    
    for(int i=0; i<s.length(); i++){
        if(mapCharactersFrequency.containsKey(s.charAt(i))){
            if(t.charAt(i) != mapCharactersFreq.get(s.charAt(i))){
                return false;
            }
        }
        else{
            if(trackUinqueCharacter.contains(t.charAt(i))){
                return false;
            }
            
            mapCharactersFrequency.put(s.charAt(i), t.charAt(i));
        }
        trackUinqueCharacter.add(t.charAt(i));
    }
    
    // -------------------------------------------------------------

    
    return true;
  }
}
