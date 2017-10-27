//Oskar Enmalm 3/10/17
//This is Euclid's algorithm which is used to find the greatest common denominator

public class GCD{

        public static int gcd(int a, int b) {

        int r = a % b;
        while (r != 0) {
            b = r;
            r = b % r;
        }
        return b;
    }
	public static int gcd(int[] number) {
		  int result = number[0];
		  for(int i = 1; i < number.length; i++) 
		          result = gcd(result, number[i]);
		  
		  return result;
	}

	public static void main(String[] args) {
		int[] myIntArray = {4,16,32};
                System.out.println(gcd(myIntArray));
    }
}
