/** Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

 /** Program description - To sort the names alphabetically using comparators and collection class
  */
 package com.thealgorithms.strings;
import java.util.*;
public class String_Compare {
    public static void main(String args[])
    {
        // list is created of string type where names are initialized
        List<String> nm = new ArrayList<>();
        nm.add("Shah");
        nm.add("Chhotwant");
        nm.add("Ayush");
        nm.add("Mannu");
        nm.add("Sachin");
        nm.add("Siddhi");
        nm.add("Urus");
        nm.add("Mony");
        String_Compare3 kk=new String_Compare3();
        nm=kk.task(nm);
        System.out.println(nm);
        // task method is executed and the returned result is stored in the list and after that the list is displayed

        /**
         * OUTPUT :
         * Input - ["Shah","Chhotwant","Ayush","Mannu","Sachin","Siddhi","Urus","Mony"]
         * Output: [Ayush, Chhotwant, Mannu, Mony, Sachin, Shah, Siddhi, Urus]
         * 1st approach Time Complexity : O(n logn)
         * Auxiliary Space Complexity : O(n)
         * */
    }
    List<String> task(List<String> nm)
    {
        Collections.sort(nm , new Comparator<String>(){
            public int compare(String s1 , String s2)
            {
                return s1.compareTo(s2);
            }
        });
        return nm;

        // comparator method is used to sort the names in ascending order/alphabetically. To display in reverse order then line number 32 should be changed to s2.compareTo(s1)
    }
}