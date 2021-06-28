// Java program to demonstrate working of Collections.sort() 
// to descending order.
import java.util.*;
public class CollSort{
public static void main(String[] args){
ArrayList<String> al = new ArrayList<String>();
        al.add("Homecoming"); 
        al.add("Friends"); 
        al.add("Dear"); 
        al.add("Is"); 
        al.add("Superb");
		
/* Collections.sort method is sorting the 
        elements of ArrayList in ascending order. */
/*For sorting in descending order use : Collections.sort(al, Collections.reverseOrder())*/
Collections.sort (al);
// Let us print the sorted list 
System.out.println("List after the use of" + 
                           " Collection.sort() :\n" + al); 
		}
		}