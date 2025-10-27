package com.thealgorithms.puzzlesandgames;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RockPaperScissorsTest {

    @Test
    void testGetResultTie() {
        assertEquals("It's a tie!", RockPaperScissors.getResult("rock", "rock"));
    }

    @Test
    void testGetResultWin() {
        assertEquals("You win! :D ", RockPaperScissors.getResult("rock", "scissors"));
    }

    @Test
    void testGetResultLose() {
        assertEquals("You lose! :( ", RockPaperScissors.getResult("rock", "paper"));
    }

    @Test
    void testGetRandomChoiceIsValid() {
        String choice = RockPaperScissors.getRandomChoice();
        assertTrue(choice.equals("rock") || choice.equals("paper") || choice.equals("scissors"));
    }
}
