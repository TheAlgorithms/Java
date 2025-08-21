public class Student {
    private String name;
    private int age;
    private int roll;

    //constructors
    public Student(String name, int age, int rollNo){
        this.name = name;
        this.age = age;
        this.rollNo = roll;
    }
    //getters
    public String getName(){ 
        return name; 
    }
    public int getAge(){ 
        return age; 
    }
    public int getRollNo() { 
        return roll; 
    }
    //setters
    public void setName(String name) { 
        this.name = name; 
    }
    public void setAge(int age) { 
        this.age = age; 
    }
    public void setRollNo(int rollNo) { 
        this.rollNo = roll; 
    }
}

