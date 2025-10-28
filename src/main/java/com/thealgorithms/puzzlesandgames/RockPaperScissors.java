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
public final class RockPaperScissors {

    private RockPaperScissors() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String getResult(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if ((userChoice.equals("rock") && computerChoice.equals("scissors")) || (userChoice.equals("scissors") && computerChoice.equals("paper")) || (userChoice.equals("paper") && computerChoice.equals("rock"))) {
            return "You win! :D ";
        } else {
            return "You lose! :( ";
        }
    }

    public static String getRandomChoice() {
        String[] options = {"rock", "paper", "scissors"};
        Random random = new Random();
        return options[random.nextInt(options.length)];
    }
}
