package com.thealgorithms.conversions;

import java.util.Scanner;

/**
 * This class converts Decimal numbers to Octal Numbers
 */
public class TemperatureConversion {

    /**
     * Main Method
     *
     * @param args Command line Arguments
     */

    // enter in a decimal value to get Octal output
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Temperature Converter");

        while (true) {
            System.out.println("Enter the temperature value:");
            double value = scanner.nextDouble();

            System.out.println("Enter the scale of the input temperature (C/F/K):");
            String inputScale = scanner.next().toUpperCase();

            System.out.println("Enter the scale you want to convert to (C/F/K):");
            String outputScale = scanner.next().toUpperCase();

            double result = convertTemperature(value, inputScale, outputScale);
            System.out.println("Converted temperature: " + result + " " + outputScale);

            System.out.println("Do you want to convert another temperature? (yes/no)");
            String continueChoice = scanner.next().toLowerCase();
            if (!continueChoice.equals("yes")) {
                System.out.println("Exiting the program...");
                break;
            }
        }

        scanner.close();
    }

    public static double convertTemperature(double value, String inputScale, String outputScale) {
        double result = 0;

        if (inputScale.equals("C")) {
            if (outputScale.equals("F")) {
                result = (value * 9 / 5) + 32;
            } else if (outputScale.equals("K")) {
                result = value + 273.15;
            } else {
                result = value; // Same scale conversion
            }
        } else if (inputScale.equals("F")) {
            if (outputScale.equals("C")) {
                result = (value - 32) * 5 / 9;
            } else if (outputScale.equals("K")) {
                result = (value + 459.67) * 5 / 9;
            } else {
                result = value; // Same scale conversion
            }
        } else if (inputScale.equals("K")) {
            if (outputScale.equals("C")) {
                result = value - 273.15;
            } else if (outputScale.equals("F")) {
                result = (value * 9 / 5) - 459.67;
            } else {
                result = value; // Same scale conversion
            }
        }

        return result;
    }
}