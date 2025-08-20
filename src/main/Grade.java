public class Grade {
    private String courseName;
    private int marks;
    private String grade;
    private double gradePoint;

    // Constructor
    public Grade(String courseName, int marks) {
        this.courseName = courseName;
        this.marks = marks;
        setGradeAndPoint(marks); // auto-assign grade and GPA
    }

    // Getters
    public String getCourseName() {
        return courseName;
    }

    public int getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    // Setters
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setMarks(int marks) {
        this.marks = marks;
        setGradeAndPoint(marks); // update grade & GPA when marks change
    }

    // Private helper to assign grade & GPA based on KUET rules
    private void setGradeAndPoint(int marks) {
        if (marks >= 80) { grade = "A+"; gradePoint = 4.00; }
        else if (marks >= 75) { grade = "A"; gradePoint = 3.75; }
        else if (marks >= 70) { grade = "A-"; gradePoint = 3.50; }
        else if (marks >= 65) { grade = "B+"; gradePoint = 3.25; }
        else if (marks >= 60) { grade = "B"; gradePoint = 3.00; }
        else if (marks >= 55) { grade = "B-"; gradePoint = 2.75; }
        else if (marks >= 50) { grade = "C+"; gradePoint = 2.50; }
        else if (marks >= 45) { grade = "C"; gradePoint = 2.25; }
        else if (marks >= 40) { grade = "D"; gradePoint = 2.00; }
        else { grade = "F"; gradePoint = 0.00; }
    }

    // Print method
    public void printInfo() {
        System.out.println("Course: " + courseName +
                           " | Marks: " + marks +
                           " | Grade: " + grade +
                           " | GPA: " + gradePoint);
    }
}
// End of Grade class