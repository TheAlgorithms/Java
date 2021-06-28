import java.util.*;

public class Karatsuba{
//This class contains only a static method to multiply.Parameters are the two numbers to be multiplied and the base(i.e. 10 for decimal numbers,2 for binary numbers, 8 for octal and so on). 
public static int multiply(int num1,int num2,int base){

//base case
        if(num1<base && num2<base)
        return num1*num2;


        String num1String=Integer.toString(num1);
        String num2String=Integer.toString(num2);

        float max_digits=(float) Math.max(num1String.length(),num2String.length());
        int half_of_max= (int) Math.ceil(max_digits/2);

        int num1_Low= (int) (num1%(Math.pow(base,half_of_max)));
        int num1_High= (int) (num1/(Math.pow(base,half_of_max)));

        int num2_Low= (int) (num2%(Math.pow(base,half_of_max)));
        int num2_High= (int) (num2/(Math.pow(base,half_of_max)));

        int a=multiply(num1_High,num2_High,base);
        int d=multiply(num1_Low,num2_Low,base);
        int e=multiply(num1_High+num1_Low,num2_High+num2_Low,base)-a-d;

        return (int) (a*(Math.pow(base,2*half_of_max)) + e*(Math.pow(base,half_of_max)) + d);
        }
        }


