//NewCoder171
//FizzBuzz is a popular interview question where you output either Fizz or Buzz when an input value is divisible by 3 or 5 respectively and FizzBuzz if divisible by both 3 and 5
    
public class buzz {
	
	public static void fizzBuzz(int n){
		for (int i = 1; i < n; i++) {
		if( i % 3 == 0 && i % 5 == 0){
			System.out.println("FizzBuzz");
		} else if( i % 3 == 0){
			System.out.println("Fizz");
		} else if( i % 5 == 0){
			System.out.println("Buzz!");
		} else {
			System.out.println(i);
		}
	}
}
