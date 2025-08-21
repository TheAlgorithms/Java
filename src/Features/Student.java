public class Student {
    
    private String id;
    private String name;
    private String email;
    private String department;
    private double gpa;

    // No-arg constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(String id, String name, String email, String department, double gpa) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.gpa = gpa;
    }

    // Copy constructor
    public Student(Student other) {
        if (other != null) {
            this.id = other.id;
            this.name = other.name;
            this.email = other.email;
            this.department = other.department;
            this.gpa = other.gpa;
        }
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public double getGpa() { return gpa; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setDepartment(String department) { this.department = department; }
    public void setGpa(double gpa) {
        if (gpa < 0.0) gpa = 0.0;
        if (gpa > 4.0) gpa = 4.0;
        this.gpa = gpa;
    }

    // Convenience builder-style methods (optional chaining)
    public Student withId(String id) { setId(id); return this; }
    public Student withName(String name) { setName(name); return this; }
    public Student withEmail(String email) { setEmail(email); return this; }
    public Student withDepartment(String department) { setDepartment(department); return this; }
    public Student withGpa(double gpa) { setGpa(gpa); return this; }

    @Override
    public String toString() {
        return "Student{id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", department='" + department + '\'' +
               ", gpa=" + gpa +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return (id != null && id.equals(s.id));
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
