package main.java.com.example.demo;

public class Student {
    private String name;
    private int roll;
    private double gpa;

    public Student(){}

    public Student( int roll,String name, double gpa) {
        this.name = name;
        this.roll = roll;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoll() {
        return roll;
    }
    public void setRoll(int roll) {
        this.roll = roll;
    }
    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}

