public class person{
    private String name;
    private int age;

    public person(String name,int age)
    {
        this.name=name;
        this.age=age;
    }
    public String getname(){
        return name;
    }
    public int getage(){
        return age;
    }
    public void setname(String name)
    {
        this.name=name;
    }
    public void setage(int age)
    {
        this.age=age;
    }
}