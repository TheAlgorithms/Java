class Person
{
    private int id;
    private float salary;
    private Object someBigObject = new Object();
  
    public Person(int id, float salary)
    {
        this.id = id;
        this.salary = salary;
    }
  
    public float getSalary()
    {
        return salary;
    }
  
    @Override
    public String toString()
    {
        return "Person{" +
               "id=" + id +
               ", salary=" + salary +
               ", someBigObject=" + someBigObject +
               '}';
    }
}
  
public class Main
{
    public static void main(String[] args)
    {
        // Creating objects and their original
        // order (in tag array)
        int n = 5;
        Person persons[] = new Person[n];
        persons[0] = new Person(0, 233.5f);
        persons[1] = new Person(1, 23f);
        persons[2] = new Person(2, 13.98f);
        persons[3] = new Person(3, 143.2f);
        persons[4] = new Person(4, 3f);
        int tag[] = new int[n];
        for (int i = 0; i < n; i++)
            tag[i] = i;
  
        // Every Person object is tagged to
        // an element in the tag array.
        System.out.println("Given Person and Tag ");
        for (int i = 0; i < n; i++)
            System.out.println(persons[i] +
                               " : Tag: " + tag[i]);
  
        // Modifying tag array so that we can access
        // persons in sorted order.
        tagSort(persons, tag);
  
        System.out.println("New Tag Array after "+
                           "getting sorted as per Person[] ");
        for (int i=0; i<n; i++)
            System.out.println(tag[i]);
  
        // Accessing persons in sorted (by salary)
        // way using modified tag array.
        for (int i = 0; i < n; i++)
            System.out.println(persons[tag[i]]);
    }
  
    // Modifying tag array so that we can access
    // persons in sorted order of salary.
    public static void tagSort(Person persons[],
                               int tag[])
    {
        int n = persons.length;
        for (int i=0; i<n; i++)
        {
            for (int j=i+1; j<n; j++)
            {
                if (persons[tag[i]].getSalary() >
                        persons[tag[j]].getSalary())
                {
                    // Note we are not sorting the
                    // actual Persons array, but only
                    // the tag array
                    int temp = tag[i];
                    tag[i] = tag[j];
                    tag[j] = temp;
                }
            }
        }
    }
}
