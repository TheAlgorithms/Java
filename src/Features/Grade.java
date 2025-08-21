public class Grade {

    // Simple numeric -> letter grade
    public String toLetter(double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be 0..100");
        }
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    // GPA points (typical 4.0 scale)
    public double toGpaPoints(double score) {
        String letter = toLetter(score);
        switch (letter) {
            case "A": return 4.0;
            case "B": return 3.0;
            case "C": return 2.0;
            case "D": return 1.0;
            default:  return 0.0;
        }
    }

    // Average of array
    public double average(double[] scores) {
        if (scores == null || scores.length == 0) {
            throw new IllegalArgumentException("Scores required");
        }
        double sum = 0;
        for (double s : scores) {
            if (s < 0 || s > 100) {
                throw new IllegalArgumentException("Score out of range: " + s);
            }
            sum += s;
        }
        return sum / scores.length;
    }

    // Average letter
    public String averageLetter(double[] scores) {
        return toLetter(average(scores));
    }
}