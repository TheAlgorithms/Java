public class Employee {
    private String name;
    private int age;
    private int id;

    //constructors
    public Employee(String name, int age, int id){
        this.name = name;
        this.age = age;
        this.id = id;
    }
    //getters
    public String getName(){ 
        return name; 
    }
    public int getAge(){ 
        return age; 
    }
    public int getIDNo() { 
        return id; 
    }
    //setters
    public void setName(String name) { 
        this.name = name; 
    }
    public void setAge(int age) { 
        this.age = age; 
    }
    public void setIDNo(int id) { 
        this.id = id; 
    }
}

