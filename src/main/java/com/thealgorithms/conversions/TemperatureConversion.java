package com.thealgorithms.conversions;

import java.text.DecimalFormat;
import java.util.Scanner;
/**
 * Convets the temperatures from user to Celsius/Fahrenheit/Kelvin
 *
 * @author Tharun S.M.
 */

public class TemperatureConversion {
  /*
   * Object of DecimalFormat Class
   * to round the output to 2 decimal point value
   */
  private static final DecimalFormat rnd = new DecimalFormat("0.00");
  /*
   * Main method
   *
   * @param args Command line arguments
   */

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    // Get the type of temprature input
    System.out.println("Enter the temperature input type (use numbers)");
    System.out.println("1 => Celsius");
    System.out.println("2 => Fahrenheit");
    System.out.println("3 => Kelvin");
    int fromType = sc.nextInt();

    // get the actual temprature
    System.out.println("Enter the temperature :");
    String a = sc.next();
    float inTemp = Float.parseFloat(a);

    // get the type you want to convert your temperature to
    System.out.println(
        "Type you want to convert your temperature to (use numbers)");
    System.out.println("1 => Celsius");
    System.out.println("2 => Fahrenheit");
    System.out.println("3 => Kelvin");
    int toType = sc.nextInt();

    if (fromType == toType) {
      System.out.println("Your Temperature is the same !!");
    } else if (fromType == 1 && toType == 2) {
      System.out.println(
          "Converted value: " + rnd.format(convertCelsiusToFahrenheit(inTemp)) +
          " °F");
    } else if (fromType == 1 && toType == 3) {
      System.out.println("Converted value: " +
                         rnd.format(convertCelsiusToKelvin(inTemp)) + " K");
    } else if (fromType == 2 && toType == 1) {
      System.out.println(
          "Converted value: " + rnd.format(convertFahrenheitToCelsius(inTemp)) +
          " °C");
    } else if (fromType == 2 && toType == 3) {
      System.out.println("Converted value: " +
                         rnd.format(convertFahrenheitToKelvin(inTemp)) + " K");
    } else if (fromType == 3 && toType == 1) {
      System.out.println("Converted value: " +
                         rnd.format(convertKelvinToCelsius(inTemp)) + " °C");
    } else if (fromType == 3 && toType == 2) {
      System.out.println("Converted value: " +
                         rnd.format(convertKelvinToFahrenheit(inTemp)) + " °F");
    } else {
      System.out.println("Please check your input and output types");
    }
  }

  /**
   * This method converts a Kelvin to Celsius.
   *
   * @param param float paramter
   * @return double
   */
  private static double convertKelvinToCelsius(float inTemp) {

    double result = 0;
    result = inTemp - 273.15;
    return result;
  }

  /**
   * This method converts a Kelvin to Fahrenheit.
   *
   * @param param float paramter
   * @return double
   */
  private static double convertKelvinToFahrenheit(float inTemp) {

    double result = 0;
    result = (inTemp * 1.8) - 459.67;
    return result;
  }

  /**
   * This method converts a Fahrenheit to Kelvin.
   *
   * @param param float paramter
   * @return double
   */
  private static double convertFahrenheitToKelvin(float inTemp) {

    double result = 0;
    result = (inTemp + 459.67) / 1.8;
    return result;
  }

  /**
   * This method converts a Fahrenheit to Kelvin.
   *
   * @param param float paramter
   * @return double
   */
  private static double convertFahrenheitToCelsius(float inTemp) {

    double result = 0;
    result = (inTemp - 32) / 1.8;
    return result;
  }

  /**
   * This method converts a Celsius to Fahrenheit.
   *
   * @param param float paramter
   * @return double
   */
  private static double convertCelsiusToFahrenheit(float inTemp) {

    double result = 0;
    result = (inTemp * 1.8) + 32;
    return result;
  }

  /**
   * This method converts a Celsius to Kelvin.
   *
   * @param param float paramter
   * @return double
   */
  private static double convertCelsiusToKelvin(float inTemp) {

    double result = 0;
    result = inTemp + 273.15;
    return result;
  }

  /*
   * Reference URL
   * https://en.wikipedia.org/wiki/Conversion_of_scales_of_temperature This is
   * not the most effecient method for Temp conversion But its easy to
   * understand and straight forward
   */
}
