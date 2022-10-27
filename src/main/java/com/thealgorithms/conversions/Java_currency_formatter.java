import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();
        scanner.close();

        
        Locale u = Locale.US;
        Locale i = new Locale("en","IN","Rs.");
        Locale c = Locale.CHINA;
        Locale f = Locale.FRANCE;

        NumberFormat usa = NumberFormat.getCurrencyInstance(u);
        NumberFormat indi = NumberFormat.getCurrencyInstance(i);
        NumberFormat chin = NumberFormat.getCurrencyInstance(c);
        NumberFormat fra = NumberFormat.getCurrencyInstance(f);

        String us = usa.format(payment);
        String india = indi.format(payment);
        String china = chin.format(payment);
        String france = fra.format(payment); 
        
        System.out.println("US: " + us);
        System.out.println("India: " + india);
        System.out.println("China: " + china);
        System.out.println("France: " + france);
    }
}
