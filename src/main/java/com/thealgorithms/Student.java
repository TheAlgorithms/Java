public class Student {
    private String name;
    private int roll;
    private String dept;
    private String batch;

    // DEFAULT CONSTRUCTOR
    public Student() {}

    // PARAMETERIZED CONSTRUCTOR
    public Student(String name, int roll, String dept, String batch) {
        this.name = name;
        this.roll = roll;
        this.dept = dept;
        this.batch = batch;
    }

    // COPY CONSTRUCTOR
    public Student(Student student) {
        this.name = student.getName();
        this.roll = student.getRoll();
        this.dept = student.getDept();
        this.batch = student.getBatch();
    }
    
    // GETTERS
    public String getName() { return name; }
    public int getRoll() { return roll; }
    public String getDept() { return dept; }
    public String getBatch() { return batch; }

    // SETTERS
    public void setName(String name) { this.name = name; }
    public void setRoll(int roll) { this.roll = roll; }
    public void setDept(String dept) { this.dept = dept; }
    public void setBatch(String batch) { this.batch = batch; }
}