package com.thealgorithms.conversions;

import java.util.Scanner;
/**
 * Convets the temperatures from user to Celsius/Fahrenheit/Kelvin
 * 
 * @author Tharun S.M.
 */

public class TemperatureConversion {

    /*
     * Main method
     * 
     * @param args Command line arguments
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Get the type of temprature input
        System.out.println("Enter the temperature input type ");
        System.out.println("1 => Celsisu");
        System.out.println("2 => Fahrenheit");
        System.out.println("3 => Kelvin");
        int fromType = sc.nextInt();

        //get the actual temprature
        System.out.println("Enter the temperature :");
        String a = sc.next();
        float inTemp = Float.parseFloat(a);

        //get the type you want to convert your temperature to
        System.out.println("Type you want to conver your temperature to");
        System.out.println("1 => Celsisu");
        System.out.println("2 => Fahrenheit");
        System.out.println("3 => Kelvin");
        int toType = sc.nextInt();

        if(fromType == toType){
            System.out.println("Your Temperature is the same !!");
        }else if(fromType == 1 && toType == 2){
            System.out.println("Converted value: "+convertCelsiusToFahrenheit(inTemp)+" °F");
        }else if(fromType == 1 && toType == 3){
            System.out.println("Converted value: "+convertCelsiusToKelvin(inTemp)+" K");
        }
        
    }

    /**
     * This method converts a Celsius to Fahrenheit.
     *
     * @param param float paramter
     * @return double
     */
    private static double convertCelsiusToFahrenheit(float inTemp) {

        double result=0;
        result = (inTemp*1.8)+32;
        return result;
    }

    /**
     * This method converts a Celsius to Kelvin.
     *
     * @param param float paramter
     * @return double
     */
    private static double convertCelsiusToKelvin(float inTemp) {

        double result=0;
        result = inTemp+273.15;
        return result;
    }

    //Celcius conversion over farheinheit and kelvin yet to do


    
}
