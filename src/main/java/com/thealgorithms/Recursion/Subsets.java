package com.thealgorithms.Recursion;

// program to find power set of a string

import java.util.ArrayList;

public class Subsets {
    public static void main(String[] args) {
        String str = "abc";
        ArrayList<String> res = new ArrayList<>();
        subset("",str,res);
        System.out.println(res);
    }

    public static void subset(String p,String up,ArrayList<String> res){
        if(up.isEmpty()){
            res.add(p);
            return;
        }

        // Taking the character
        char ch = up.charAt(0);
        // Adding the character in the recursion
        subset(p+ch,up.substring(1),res);
        // Not adding the character in the recursion
        subset(p,up.substring(1),res);
    }
}
