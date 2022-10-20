//the reverseString function prints out the input string in reverse order, character by character
//and for the check for base case, we stop the function whenever we reach the first (index 0) character of the string, for which the length of the string is 1

import java.util.*;

public class ReverseString {
    public static void reverseString(String s, int length){
        System.out.print(s.charAt(length-1));
        if (length==1){
            System.out.println();
            return;
        }
        reverseString(s,length-1);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s= sc.next();
        reverseString(s, s.length());
    }
}
