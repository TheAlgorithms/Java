// Importing required Classes

import java.util.Enumeration; 
import java.util.Vector;

public class VectorDemo{
    public static void main(String[] args) {
        Vector<String> vc = new Vector<String>(); // Creating a new Vector(Dynamic Array)
        vc.addElement("A"); // Adding elements in the vector
        vc.addElement("O");
        vc.addElement("M");
        vc.addElement("F");
        vc.addElement("G");
        System.out.println(vc.size()); // Displaying the size of the vector
        System.out.println(vc.capacity()); // Displaying the capacity of the vector
        Enumeration<String> en = vc.elements(); // Creating an enumeration for traversing
        while(en.hasMoreElements()){            // the elements in the vector
            System.out.println(en.nextElement());
        }
    }
}