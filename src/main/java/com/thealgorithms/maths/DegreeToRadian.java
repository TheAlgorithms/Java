package com.thealgorithms.maths;

import java.lang.Math.*;


/*
    @author Shubh Gajjar

*/



public class DegreeToRadian {
    
    public static void main(String[] args) {


        assert degreeToRadian(100) == 1.7453293;
        
    }


     /**
     * Convert degree into radian 
     *
     * @param angle the degree
     * @return  radian 
     */

    public static float degreeToRadian (double angle)
    {

            float radian = (float) (angle*Math.PI)/180;

            return radian;


    }
    
}
