//Oskar Enmalm 3/10/17
//This is Euclid's algorithm which is used to find the greatest common denominator
//Overide function name gcd

public class GCD{

      public static int gcd(int num1, int num2) {
	
        int gcdValue = num1 % num2;
        while (gcdValue != 0) {
            num2 = gcdValue;
            gcdValue = num2 % gcdValue;
        }
        return num2;
    }
	public static int gcd(int[] number) {
		  int result = number[0];
		  for(int i = 1; i < number.length; i++) 
			  //call gcd function (input two value)
		          result = gcd(result, number[i]);
		  
		  return result;
	}

	public static void main(String[] args) {
		int[] myIntArray = {4,16,32};
		//call gcd function (input array)
                System.out.println(gcd(myIntArray));
    }
}
