package Conversions;

/**
 + + * Converts any Octal Number to HexaDecimal 
 + + * 
 + + * @author Tanmay Joshi
 + + *
 + + *
 **/
import java.util.Scanner;

public class OctalToHexadecimal {
	
  /**
 + +	 * This method converts a Octal number to
 + +	 * a decimal number
 + +	 * 
 + +	 * @param The Octal Number
 + +	 * @return The Decimal number
 + +	 */ 
 public static int OctToDec(String s)
 {
	 int i =0;
	 for(int j =0;j<s.length();j++)
	 {
		 char num = s.charAt(j);
		 num-='0';
		 i*=8;
		 i+=num;
	 }
	 return i;
}

/**
 + +	 * This method converts a Decimal number to
 + +	 * a Hexadecimal number
 + +	 * 
 + +	 * @param The Decimal Number
 + +	 * @return The Hexadecimal number
 + +	 */ 
public static String DecimalToHex(int d) {
    String digits = "0123456789ABCDEF";
    if (d <= 0) 
		return "0";  
    String hex = "";
    while (d > 0) {
        int digit = d % 16;              
        hex = digits.charAt(digit) + hex;
        d = d / 16;
    }
    return hex;
}

           //Driver Program
public static void main ( String args[]) {
		
     Scanner input = new Scanner(System.in);
     System.out.print("Enter the Octal number: ");
     String oct  = input.next();                     //Take octal number as input from user in a string
     int decimal = OctToDec(oct);                    //Pass the octal number to function and get converted deciaml form
     String hex = DecimalToHex(decimal);             //Pass the decimla number to function and get converted Hex form of the number
     System.out.println("The Hexadecimal equivalant is: "+hex);
 }
}

