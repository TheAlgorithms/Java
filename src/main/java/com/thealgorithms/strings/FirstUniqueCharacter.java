// Given a string s, find the first non-repeating character in it and return its index. 
// If it does not exist, return -1.


package com.thealgorithms.strings;

public class FirstUniqueCharacter {
    public static int firstUniqChar(String s) {
        char [] arr = s.toCharArray();
        for(int i=0; i<arr.length;i++){
            int count = 0;
            for(int j=0; j<arr.length;j++){
                if(arr[i]==arr[j]){
                    count++;
                    if(count==2){
                        break;
                    }
                }
            }
            
            if(count==1){
                return i;
            }
        }
        
        return -1;
    }
}
