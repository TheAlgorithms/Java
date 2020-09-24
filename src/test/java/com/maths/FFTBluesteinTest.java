package com.maths;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing the Bluestein's FFT algorithm.
 * You can also use Matlab for bigger signals and compare the results by modifying the code below.
 *
 * @author Ioannis Karavitsis
 * @version 1.0
 * */
class FFTBluesteinTest
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
     * Test function for fftBluestein() function.
     * */
    @Test
    public void testFFT()
    {
        ArrayList<FFT.Complex> x = new ArrayList<>();
        for (int i = 1; i <= 10; i++)            //x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }
            x.add(new FFT.Complex(i, 0));

        /* Matlab results */
        double[] real = { 55, -5, -5, -5, -5, -5, -5, -5, -5,  -5 };
        double[] imaginary = { 0, 15.388417685876266, 6.881909602355867, 3.632712640026803, 1.624598481164532, 0, -1.624598481164532, -3.632712640026803, -6.881909602355867, -15.388417685876266 };

        FFTBluestein.fftBluestein(x,false);

        /* Print the results (Optional) */
        /*for(int i = 0; i < x.size(); i++)
            System.out.println(x.get(i).getReal() + " " + x.get(i).getImaginary());*/

        /* Compare the results (real and imaginary part) with those from Matlab (command: fft(x)).
        I rounded them to 12 decimal places, due to the different calculations and precision that Matlab and this Java program use. */
        for(int i = 0; i < x.size(); i++)
        {
            assertEquals(round(real[i], 0), round(x.get(i).getReal(), 0));
            assertEquals(round(imaginary[i], 12), round(x.get(i).getImaginary(), 12));
        }

        /* Find the IFFT of the FFT of signal x */
        FFTBluestein.fftBluestein(x, true);

        /* Print the results (Optional) */
        /*for(int i = 0; i < x.size(); i++)
            System.out.println(x.get(i).getReal() + " " + x.get(i).getImaginary());*/

        /* Check if it's equal to signal x */
        for(int i = 0; i < 10; i++)
        {
            assertEquals(i + 1, round(x.get(i).getReal(), 0));
            assertEquals(0, round(x.get(i).getImaginary(), 0));    //All imaginary parts must be 0.
        }
    }
}