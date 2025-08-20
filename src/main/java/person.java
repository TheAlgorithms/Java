public class person{
    private string name;
    private int age;

    public person(String name,int age)
    {
        this.name=name;
        this.age=age;
    }
    public string getname(){
        return name;
    }
    public int getage(){
        return age;
    }
    public void setname(string name)
    {
        this.name=name;
    }
    public void setage(string age)
    {
        this.age=age;
    }
}