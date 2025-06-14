/**
 * LambdaExpressionUtils.java
 * Unique Java utility functions using Lambda expressions.
 * Demonstrates Function, Predicate, Consumer, and Supplier.
 */

import java.util.Random;
import java.util.function.*;

public class LambdaExpressionUtils {

    public static void main(String[] args) {
        // Reverse a string
        Function<String, String> reverse = str -> new StringBuilder(str).reverse().toString();
        System.out.println("Reversed: " + reverse.apply("lambda"));

        // Check palindrome
        Predicate<String> isPalindrome = str -> str.equalsIgnoreCase(new StringBuilder(str).reverse().toString());
        System.out.println("Is Palindrome: " + isPalindrome.test("madam"));

        // Print message in all caps with exclamation
        Consumer<String> shout = s -> System.out.println(s.toUpperCase() + "!");
        shout.accept("functional interface");

        // Check if number is even
        Function<Integer, Boolean> isEven = n -> n % 2 == 0;
        System.out.println("Is 10 even? " + isEven.apply(10));

        // Get random greeting
        Supplier<String> randomGreeting = () -> {
            String[] greetings = {"Hello", "Hi", "Hey", "Hola"};
            return greetings[new Random().nextInt(greetings.length)];
        };
        System.out.println("Random Greeting: " + randomGreeting.get());
    }
    
}