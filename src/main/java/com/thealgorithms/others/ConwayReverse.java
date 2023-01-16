package com.thealgorithms.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class ConwayReverse {


    private static final int MAX_ITERATION_ALLOWED = 5;
/*
* This class will generate the previous element from the conway sequence
* ex : the input will generate 1
*      1234 will generate 2444 because 2444 is pronounced one two three four
*      321845 will generate 22285555
*      if we loop 321845 will generate 22285555, 22885555555555, 22888888885555555555555555555555555
*      if we loop 11131221131211132221232112111312111213111213211231132132211211131221232112111312212221121123222110 will generate 1321132132211331121321231231121113112221121321133112132112211213322110, 31131122211311123113321112131221123113111231121123222110, 11131221131211132221232112111312111213322110, 132113213221133112132123222110, 31131122211311123113322110, 1113122113121113222110, 13211321322110, 311311222110, 1113122110, 132110, 3110, 1110, 10, 0
*
* */
    public static void main(String[]args) {
        Scanner scanner;
        String originalString;
        do {
            System.out.println("Enter a number with an even number of characters  : ");
            scanner = new Scanner(System.in);
            originalString = scanner.nextLine();
        }
        while (originalString.length()%2!=0 || originalString.length()<1);
        System.out.println(generateList(originalString));
    }


    protected static List<String> generateList(String s){
        if(s.length()%2!=0)
            throw new IllegalArgumentException("Invalid input " + s);
         List<String> numbers = new ArrayList<>();
        String s1 = s;
        int count = 0;
        do{
            count++;
            s1 = generatelastElement(s1);
            numbers.add(s1);
        }
        while (s1.length() > 1 && count < MAX_ITERATION_ALLOWED && s1.length()%2==0);
        return numbers;
    }

    protected static String generatelastElement(String originalString) {
        if(originalString.length()%2!=0)
            throw new IllegalArgumentException("Invalid input " + originalString);
        StringBuilder builder = new StringBuilder();
        Pattern.compile(".{1," + 2 + "}")
                .matcher(originalString)
                .results()
                .map(MatchResult::group).toList().forEach(s -> builder.append(String.valueOf(s.charAt(1)).repeat(Math.max(0, Integer.parseInt("" + s.charAt(0))))));
        return builder.toString();
    }
}
