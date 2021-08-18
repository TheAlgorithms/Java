/**
 * This program shows the use of lambda functions
 * instead of method overloading for calculating area of different shapes.
 * This is to be noted that in this scenario, method overloading will not 
 * be possible as the parameter list for the functions to calculate the
 * areas of a circle, square or surface area of a sphere is similar.
 * 
 * We can also see how method functionality can be sent by refernce to
 * another method with the help of lambda expressions and a functional
 * interface.
 * 
 * This code is contributed by AdityaMitra5102.
   */

package Misc;

@FunctionalInterface
interface Calculate
{
    public double area(double d);
}

public class Lambda
{
    void showArea(double x, Calculate shape)//Passing Lambda expression as parameter.
    {
        System.out.println(shape.area(x));
    }
    
    public static void main(String args[])
    {
        //Lambda Expressions
        Calculate circle=(double d)->{return Math.PI*d*d;};
        Calculate square=(double d)->{return d*d;};
        Calculate sphere=(double d)->{return 4*Math.PI*d*d;};
        
        Lambda ob=new Lambda();
        double x=5;//Radius for circle & sphere and side for square;
        System.out.print("Area of circle: ");
        ob.showArea(x,circle);
        System.out.print("Area of square: ");
        ob.showArea(x,square);
        System.out.print("Surface area of sphere: ");
        ob.showArea(x,sphere);
    }
}
