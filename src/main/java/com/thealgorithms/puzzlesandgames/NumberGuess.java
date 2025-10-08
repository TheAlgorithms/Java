/**
 * Simple Number Guessing Game.
 * The player guesses a random number between 1 and 100.
 * Reference: https://en.wikipedia.org/wiki/Guess_the_number
 */

package com.thealgorithms.puzzlesandgames;
import java.util.Scanner;

public class NumberGuess {
    public static void playGame() {
        Scanner sc = new Scanner(System.in);
        int number = (int) (Math.random() * 100) + 1;
        int guess = 0, tries = 0;

        System.out.println("🎯 Welcome to the Number Guessing Game!");
        System.out.println("I've picked a number between 1 and 100. Try to guess it!\n");

        while (true) {
            System.out.print("Enter your guess: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.next();
                continue;
            }

            guess = sc.nextInt();
            tries++;

            if (guess < 1 || guess > 100) {
                System.out.println("⚠️ Please guess a number between 1 and 100.");
                continue;
            }

            if (guess < number)
                System.out.println("Too low! 📉");
            else if (guess > number)
                System.out.println("Too high! 📈");
            else {
                System.out.println("🎉 Correct! The number was " + number + ".");
                System.out.println("You took " + tries + " tries.");
                break;
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        playGame();
        System.out.println("\nThanks for playing! 👋");
    }
}
