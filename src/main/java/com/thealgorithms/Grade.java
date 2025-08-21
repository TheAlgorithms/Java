package com.thealgorithms;

public class Grade {
     public String getLetterGrade(int score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else {
            return "C";
        }
    }
}
