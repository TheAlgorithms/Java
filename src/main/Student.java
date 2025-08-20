public class Student {
    private String name;
    private int roll;
    private String department;
    private int batch;
    private double cgpa;

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(String name, int roll, String department, int batch, double cgpa) {
        this.name = name;
        this.roll = roll;
        this.department = department;
        this.batch = batch;
        this.cgpa = cgpa;
    }

    // Getters
    public String getName() { return name; }
    public int getRoll() { return roll; }
    public String getDepartment() { return department; }
    public int getBatch() { return batch; }
    public double getCgpa() { return cgpa; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setRoll(int roll) { this.roll = roll; }
    public void setDepartment(String department) { this.department = department; }
    public void setBatch(int batch) { this.batch = batch; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    // Print method
    public void print() {
        System.out.println("Student Details:");
        System.out.println("Name: " + name);
        System.out.println("Roll: " + roll);
        System.out.println("Department: " + department);
        System.out.println("Batch: " + batch);
        System.out.println("CGPA: " + cgpa);
    }
}
// End of Student class