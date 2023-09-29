package com.thealgorithms.maths;

public class SecondMinMax {

     /**
     * @brief Finds the Second minimum / maximum value from the array
     * @param int array
     * @exception IllegalArgumentException if input array is empty or length 1 also if all elements are same
     * @return the second minimum / maximum value from the input array
     * @author Bharath Sanjeevi ( https://github.com/BharathSanjeeviT )
     */

    public static int findSecondMin( int[] arr ) {
        int secMin = Integer.MAX_VALUE , min  = Integer.MAX_VALUE ;
        if ( arr.length == 0 )
            throw new IllegalArgumentException("Cant find Second Minimum for empty array");
        else if ( arr.length == 1 )
            throw new IllegalArgumentException("Cant find Second Minimum for single element");
        else
            for ( int num : arr )
                if ( num < min ) {
                    secMin = min ;
                    min = num ;
                } else if ( ( num < secMin ) && ( num != min ) )
                    secMin = num ;
        if ( secMin == Integer.MAX_VALUE )
            throw new IllegalArgumentException("Cant find Second Minimum in array full of same numbers") ;
        return secMin ;
    }

    public static int findSecondMax( int[] arr ) {
        int secMax = Integer.MIN_VALUE , max  = Integer.MIN_VALUE ;
        if ( arr.length == 0 )
            throw new IllegalArgumentException("Cant find Second Maximum for empty array");
        else if ( arr.length == 1 )
            throw new IllegalArgumentException("Cant find Second Maximum for single element");
        else
            for ( int num : arr )
                if ( num > max ) {
                    secMax = max ;
                    max = num ;
                } else if ( ( num > secMax ) && ( num != max ) )
                    secMax = num ;
        if ( secMax == Integer.MIN_VALUE )
            throw new IllegalArgumentException("Cant find Second Maximum in array full of same numbers") ;
        return secMax ;
    }

}
