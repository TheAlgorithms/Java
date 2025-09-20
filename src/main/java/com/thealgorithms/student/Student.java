
public class Student {
    public String name;
    private  int ID;
    public double cgpa;

    public Student() {
        this.name= "";
        this.ID=0;
        this.cgpa=0.0;
    }
    public Student(String name,int id,double cgpa){
        this.name = name;
        ID= id;
        this.cgpa=cgpa;
    }
    
}
