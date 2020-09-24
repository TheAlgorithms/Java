package com.maths;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Class for testing the Convolution of two signals using the Cooley-Tukey FFT algorithm.
 * You can also use Matlab for bigger signals and compare the results by modifying the code below.
 *
 * @author Ioannis Karavitsis
 * @version 1.0
 * */
class ConvolutionFFTTest
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
     * Test for convolutionFFT() function.
     * */
    @Test
    public void testConvolutionFFT()
    {
        ArrayList<FFT.Complex> x = new ArrayList<>();
        for (int i = 1; i <= 10; i++)            //x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }
            x.add(new FFT.Complex(i, 0));

        ArrayList<FFT.Complex> y = new ArrayList<>();
        for (int i = 0; i < 5; i++)              //y = { 0.2, 0.2, 0.2, 0.2, 0.2 }
            y.add(new FFT.Complex(0.2, 0));

        /* Matlab results */
        double[] real = { 0.2, 0.6, 1.2, 2, 3, 4, 5, 6, 7.000000000000001, 8, 6.800000000000001, 5.4, 3.8, 2 };

        /* Find the convolution of the two signals */
        ArrayList<FFT.Complex> convolved = ConvolutionFFT.convolutionFFT(x, y);

        /* Compare the results with those from Matlab (command: conv(x, y)).
        I rounded them to 1 decimal place, due to the different calculations and precision that Matlab and this Java program use. */
        for(int i = 0; i < convolved.size(); i++)
        {
            assertEquals(round(real[i], 1), round(convolved.get(i).getReal(), 1));
            assertEquals(0, round(convolved.get(i).getImaginary(),1));
        }

        /* Print the results (Optional) */
        /*for (FFT.Complex complex : convolved)
            System.out.println(complex.getReal() + " " + complex.getImaginary());*/
    }
}