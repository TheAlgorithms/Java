package Maths;

/**
 * https://en.wikipedia.org/wiki/Perfect_square
 */
public class PerfectSquare {
    public static void main(String[] args) {
        assert !isPerfectSquare(-1);
        assert !isPerfectSquare(3);
        assert !isPerfectSquare(5);
        assert isPerfectSquare(9);
        assert isPerfectSquare(100);
    }

    /**
     * Check if a number is perfect square number
     *
     * @param number the number to be checked
     * @return <tt>true</tt> if {@code number} is perfect square, otherwise <tt>false</tt>
     */


     /*
     Uses binary search for finding the perfect number

     */
    public static boolean isPerfectSquare(int number) {
        
        int value=0;
        int start=0,end=number;
        while(start<=end)
         {
             int mid=start+(end-start)/2;

             if(mid*mid==number)
                return true;
             
             if(mid*mid<number)
              {
                  value=mid;
                  start=mid+1;
              }
              else
                 end=mid-1;
         }



        return value * value == number;
    }
}