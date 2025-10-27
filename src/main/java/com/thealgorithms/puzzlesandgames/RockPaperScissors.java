package com.thealgorithms.puzzlesandgames;

import java.util.Random;
import java.util.Scanner;

/**
 * <a href="https://en.wikipedia.org/wiki/Rock_paper_scissors"></a>
 * RockPaperScissors (Jokenpô)
 * Simple terminal game where the user plays Rock, Paper, Scissors against the computer.
 * Rules:
 * - Rock beats Scissors
 * - Scissors beats Paper
 * - Paper beats Rock
 * Example:
 * User: rock
 * Computer: scissors
 * Result: You win!
 * Author: Lígia Alves (Hacktoberfest 2025)
 */
public class RockPaperScissors {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] options = {"rock", "paper", "scissors"};

        System.out.println("=== Rock Paper Scissors Game ===");
        System.out.println("Type 'rock', 'paper', or 'scissors'. Type 'exit' to quit.");

        while (true) {
            System.out.print("\nYour choice: ");
            String userChoice = scanner.nextLine().trim().toLowerCase();

            if (userChoice.equals("exit")) {
                System.out.println("Thanks for playing! ");
                break;
            }

            // Validate input
            if (!userChoice.equals("rock") && !userChoice.equals("paper") && !userChoice.equals("scissors")) {
                System.out.println("Invalid choice! Try again.");
                continue;
            }

            // Computer chooses randomly
            String computerChoice = options[random.nextInt(3)];
            System.out.println("Computer chose: " + computerChoice);

            // Determine result
            String result = getResult(userChoice, computerChoice);
            System.out.println(result);
        }

        scanner.close();
    }

    private static String getResult(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if (
                (userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                        (userChoice.equals("scissors") && computerChoice.equals("paper")) ||
                        (userChoice.equals("paper") && computerChoice.equals("rock"))
        ) {
            return "You win! :D ";
        } else {
            return "You lose! :( ";
        }
    }
}