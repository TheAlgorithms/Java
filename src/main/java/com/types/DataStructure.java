package src.main.java.com.types;

import java.util.Iterator;

/**
 * This interface is to define bacis functionality expected out of any implementation class
 * Since this is a data structure it should have the flexibility to contain any kind of object hence it has been made generic
 * Any implementation class need not to be thread safe or it could be depending on the implementation class how does it want to behave.
 * @param <T>
 */
public interface DataStructure<T> extends Iterator<T> {

    //Method to add element in the structure
    public boolean add(T t);

    //Method to remove the given object from structure
    public boolean remove(T o);

    //Method to get Iterator to parse on the given structure
    public Iterator<T> iterator();

    //Method to check if structure is empty
    public boolean isEmpty();

    //Method to get all the elements of data structure in array
    public Object[] toArray();

    //Method to get the size or number of elements in structure
    public int size();

}
