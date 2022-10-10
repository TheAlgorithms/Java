public class JavaMathfunction  
{    
    public static void main(String[] args)     
    {    
        double x = 28;    
        double y = 4;    
          
        // return the maximum of two numbers  
        System.out.println("Maximum number of x and y is: " +Math.max(x, y));   
          
        // return the square root of y   
        System.out.println("Square root of y is: " + Math.sqrt(y));   
          
        //returns 28 power of 4 i.e. 28*28*28*28    
        System.out.println("Power of x and y is: " + Math.pow(x, y));      
  
        // return the logarithm of given value       
        System.out.println("Logarithm of x is: " + Math.log(x));   
        System.out.println("Logarithm of y is: " + Math.log(y));  
          
        // return the logarithm of given value when base is 10      
        System.out.println("log10 of x is: " + Math.log10(x));   
        System.out.println("log10 of y is: " + Math.log10(y));    
          
        // return the log of x + 1  
        System.out.println("log1p of x is: " +Math.log1p(x));    
  
        // return a power of 2    
        System.out.println("exp of a is: " +Math.exp(x));    
          
        // return (a power of 2)-1  
        System.out.println("expm1 of a is: " +Math.expm1(x));
         double a = 30;    
          
        // converting values to radian    
        double b = Math.toRadians(a);   
          
        // return the trigonometric sine of a      
        System.out.println("Sine value of a is: " +Math.sin(a));    
          
        // return the trigonometric cosine value of a  
        System.out.println("Cosine value of a is: " +Math.cos(a));  
          
        // return the trigonometric tangent value of a  
        System.out.println("Tangent value of a is: " +Math.tan(a));  
          
        // return the trigonometric arc sine of a      
        System.out.println("Sine value of a is: " +Math.asin(a));    
          
        // return the trigonometric arc cosine value of a  
        System.out.println("Cosine value of a is: " +Math.acos(a));  
          
        // return the trigonometric arc tangent value of a  
        System.out.println("Tangent value of a is: " +Math.atan(a));  
  
        // return the hyperbolic sine of a      
        System.out.println("Sine value of a is: " +Math.sinh(a));    
          
        // return the hyperbolic cosine value of a  
        System.out.println("Cosine value of a is: " +Math.cosh(a));  
          
        // return the hyperbolic tangent value of a  
        System.out.println("Tangent value of a is: " +Math.tanh(a));  
    }    
}  
