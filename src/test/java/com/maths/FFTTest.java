package com.maths;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing the FFT Cooley-Tukey algorithm.
 * You can also use Matlab for bigger signals and compare the results by modifying the code below.
 *
 * @author Ioannis Karavitsis
 * @version 1.0
 * */
class FFTTest
{
    /**
     * Function to round a number with double precision to n decimal places.
     *
     * More info:
     * https://www.baeldung.com/java-round-decimal-number
     *
     * @param num The number to be rounded.
     * @param places The number of decimal places up to which the number will be rounded.
     * @return The rounded number.
     * */
    private double round(double num, int places)
    {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(num));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Test function for fft() function.
     * */
    @Test
    public void testFFT()
    {
        ArrayList<FFT.Complex> x = new ArrayList<>();
        for (int i = 1; i <= 10; i++)            //x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }
            x.add(new FFT.Complex(i, 0));

        /* Matlab results */
        double[] real = { 55, -26.375866509656959, 12.071067811865476, -9.446748728072674, 5, -0.896397022434947, -2.071067811865476, 4.719012260164577, -5,  4.719012260164577, -2.071067811865476, -0.896397022434947, 5, -9.446748728072674, 12.071067811865476, -26.37586650965695 };
        double[] imaginary = { 0, -21.309863136978343, 2.585786437626905, 1.755766511785423, -6, 5.897902135516374, -5.414213562373095, 2.832272486752609, 0, -2.832272486752609, 5.414213562373095, -5.897902135516374, 6, -1.755766511785423, -2.585786437626905, 21.309863136978343 };

        FFT.fft(x,false);

        /* Print the results (Optional) */
        /*for(int i = 0; i < x.size(); i++)
            System.out.println(x.get(i).getReal() + " " + x.get(i).getImaginary());*/

        /* Compare the results (real and imaginary part) with those from Matlab (command: fft(x, 16)).
        I rounded them to 13 decimal places, due to the different calculations and precision that Matlab and this Java program use. */
        for(int i = 0; i < x.size(); i++)
        {
            assertEquals(round(real[i], 13), round(x.get(i).getReal(), 13));
            assertEquals(round(imaginary[i], 13), round(x.get(i).getImaginary(), 13));
        }

        /* Find the IFFT of the FFT of signal x */
        FFT.fft(x,true);

        /* Print the results (Optional) */
        /*for(int i = 0; i < x.size(); i++)
            System.out.println(x.get(i).getReal() + " " + x.get(i).getImaginary());*/

        /* Check if it's equal to signal x */
        for(int i = 0; i < 10; i++)         //The rest values are 0, so we don't have to check them.
        {
            assertEquals(i + 1, round(x.get(i).getReal(), 0));
            assertEquals(0, round(x.get(i).getImaginary(), 0));    //All imaginary parts must be 0.
        }
    }
}