package com.thealgorithms.maths;

import java.lang.Math.*;


/*
    @author Shubh Gajjar

*/


public class RadianToDegree {

    public static void main(String[] args) {

        assert radianToDegree(1) == 57.29578;
        
    }

      /**
     * Convert radian into degree 
     *
     * @param radian the radian
     * @return  degree 
     */


    public static float radianToDegree(double radian)
    {

        float degree = (float) ( (radian*180)/ Math.PI ) ;

        return degree;

    }
    
}
