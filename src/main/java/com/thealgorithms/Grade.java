public class Grade {
    public String getLetterGrade(int score) {
        if (score < 0 || score > 100) {
            return "Invalid score";
        } else if (score >= 80) {
            return "A";
        } else if (score >= 70) {
            return "B";
        } else if (score >= 50) {
            return "C";
        } else if (score >= 30) {
            return "D";
        } else {
            return "F";
        }
    }

    public double getGradePoint(int score) {
        if (score < 0 || score > 100) {
            return -1;
        } else if (score >= 80) {
            return 4.0;
        } else if (score >= 70) {
            return 3.0;
        } else if (score >= 50) {
            return 2.0;
        } else if (score >= 30) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    public boolean isPassing(int score) { return score >= 30; }

    public String getRemark(int score) {
        if (score < 0 || score > 100) {
            return "Invalid score";
        } else if (score >= 80) {
            return "Excellent";
        } else if (score >= 70) {
            return "Good";
        } else if (score >= 50) {
            return "Average";
        } else if (score >= 30) {
            return "Pass";
        } else {
            return "Fail";
        }
    }
}