package com.thealgorithms.maths;

/**
 * https://en.wikipedia.org/wiki/Mean
 * <p>
 * by: Punit Patel
 */
public class Mean {

  /**
   * Arithmetic / Pythagorean Mean = (x₁ + x₂ + ... +  xₙ) / n
   */
  public static double arithmeticMean(double[] numbers) {
    double sum = 0;
    if (numbers.length == 0) {
      return sum;
    }
    for (double num : numbers) {
      sum += num;
    }

    return sum / numbers.length;
  }


  /**
   * Geometric Mean =  (x₁ · x₂ · ... · xₙ) ^ 1/n
   */
  public static double geometricMean(double[] numbers) {
    double product = 1;
    if (numbers.length == 0) {
      return product;
    }
    for (double num : numbers) {
      product *= num;
    }

    return Math.pow(product, 1.0 / numbers.length);
  }


  /**
   * Harmonic Mean = n / (1/x₁ + 1/x₂ + ... +  1/xₙ)
   */
  public static double harmonicMean(double[] numbers) {
    double sum = 0;
    if (numbers.length == 0) {
      return sum;
    }
    for (double num: numbers) {
      sum += 1/num;
    }

    return numbers.length / sum;
  }
}
