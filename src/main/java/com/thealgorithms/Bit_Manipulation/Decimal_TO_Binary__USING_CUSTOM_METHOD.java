package com.thealgorithms.Bit_Manipulation;

public class Decimal_TO_Binary__USING_CUSTOM_METHOD {
    public static void toBinary(int decimal){    
        int binary[] = new int[40];    
        int index = 0;    
        while(decimal > 0){    
          binary[index++] = decimal%2;    
          decimal = decimal/2;    
        }    
        for(int i = index-1;i >= 0;i--){    
          System.out.print(binary[i]);    
        }    
   System.out.println();//new line  
   }    
   public static void main(String args[]){      
   System.out.println("Decimal of 10 is: ");  
   toBinary(10);    
   System.out.println("Decimal of 21 is: ");  
   toBinary(21);    
   System.out.println("Decimal of 31 is: ");    
   toBinary(31);  
   }
}

//Read more at https://www.javatpoint.com/java-decimal-to-binary