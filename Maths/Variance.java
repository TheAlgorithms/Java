package Maths;

import java.util.Arrays;

public class Variance {

   public static void main(String[] args) {
      int[] testnums = {-5,15,10,4};
      assert getVariance(testnums) == 55.5;
      
      int[] numbers = {1,2,4,5,7,11};
      System.out.println("The variance of " + Arrays.toString(numbers) + 
                         " is = " + getVariance(numbers));
   }
   
   public static double getVariance(int[] numbers) {
      //Compute sum
      double sum = 0;
      for (int i = 0; i < numbers.length; i++) {
         sum += numbers[i];
      }
      //Compute mean
      double mean = sum / numbers.length;
      //Compute distances from each number to the mean
      double[] distances = new double[numbers.length];
      for (int i = 0; i < numbers.length; i++) {
         double distance = numbers[i] - mean;
         distances[i] += distance * distance;
      }
      //Compute sum of distances
      double distancesSum = 0;
      for (int i = 0; i < numbers.length; i++) {
         distancesSum += distances[i];
      }
      //Compute variance squared
      double varianceSquared = distancesSum / numbers.length;
      //Compute variance
      double variance = Math.sqrt(varianceSquared);
      variance = Math.round(variance * 100.0) / 100.0;
      return variance;
   }
}