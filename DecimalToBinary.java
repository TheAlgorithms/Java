package Conversions;

import java.util.Scanner;

/**
 * This class converts a Decimal number to a Binary number
 * 
 * @author Unknown
 *
 */
class DecimalToBinary {



	/**
     * Main Method
     * 
     * @param args Command Line Arguments
	 */
    public static void main(String args[]) throws Exception {
        final Scanner input;
        int n;
        input = new Scanner(System.in);
        
        System.out.printf("Conventional conversion.\n\tEnter the decimal number: ");
        n = input.nextInt();
        DecimalToBinary db = new DecimalToBinary();
        System.out.println("\tBinary number: " + db.conventionalConversion(n));
        
        System.out.printf("Bitwise conversion.\n\tEnter the decimal number: ");
        input.nextLine();
        n = input.nextInt();   
        System.out.println("\tBinary number: " +  db.bitwiseConversion(n));
        input.nextLine();
        input.close();
       
    }

    /**
     * This method converts a decimal number 
     * to a binary number using a conventional
     * algorithm.
     */
    public int conventionalConversion(int in) throws Exception{
    	if (in < 0){
    		throw new Exception("No números negativos");
    	}
        int d;
        int b = 0;
        int c = 0;
        while (in != 0) {
            d = in % 2;
            b = b + d * (int) Math.pow(10, c++);
            in /= 2;
        } //converting decimal to binary
        return b;
        
    }

    /**
     * This method converts a decimal number 
     * to a binary number using a bitwise
     * algorithm
     */
    public int bitwiseConversion(int in) throws Exception {
     	if (in < 0){
    		throw new Exception("No números negativos");
    	}
        int d;
        int b = 0;
        int c = 0;
        while (in != 0) {
            d = (in & 1);
            b += d * (int) Math.pow(10, c++);
            in >>= 1;
        }
        return b;

    }

}
