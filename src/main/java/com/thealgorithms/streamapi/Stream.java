package com.thealgorithms.streamapi;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
    What is Collection Framework ..?
    - To Store or manipulate group of objects, we use Collection Framework.
    - Different types of Collections are List, Set, Map and Queue.

    What is Stream ..?
    - Stream is a pipeline of functions which allows you to perform operations such as filtering, mapping, and reducing on a collection of data.
*/


public class Stream{

    public static void main (String args []){

        //	Suppose we have a List of Data and we wanted to convert it into Set.
		List<Integer> arrayList = List.of(0, 1, 1, 2, 3, 5, 3, 2, 7, 4, 9, 8, 12, 19, 6, 11, 18) ;
		
		
		//	Here, we are expecting to filter all those elements from list which are even.
		List<Integer> evenList = evenElementList(arrayList) ;
		
		//	Here, we are expecting to convert each element to its square value.
		List<Integer> squareList = convertToSquare(arrayList) ;
		
		//	Here, we are expecting to receive first 5 elements from arrayList.
		List<Integer> first5element = getLimitedElements(arrayList, 5);
		
		//	Here, we are expecting to store the List elements into Set.
		Set<Integer> arraySet = convertListToSet(arrayList) ;

    }






    /*
	 	This Method return all the even elements.
	 	Take Argument as List.
	 	Return List of elements divisible by 2.
	*/
	public static List<Integer> evenElementList(List<Integer> arrayList){
		return arrayList.stream().filter((element)->{return element%2==0;}).collect(Collectors.toList()) ;
	}
	
	
	/*
	 	This Method return all the even elements.
	 	Take Argument as List.
	 	Return List of elements*element.
	 */
	public static List<Integer> convertToSquare(List<Integer> arrayList){
		return arrayList.stream().map((element)->{return element*element;}).collect(Collectors.toList()) ;
	}
	
	
	/*
	 	This Method return all the even elements.
	 	Take Argument as List and number of elements needed to be returned.
	 	Return List of n number of elements.
	 */
	public static List<Integer> getLimitedElements(List<Integer> arrayList, int size){
		return arrayList.stream().limit(size).collect(Collectors.toList()) ;
	}
	
	
	/*
	 	This Method convert List to Set.
	 	Take Argument as List.
	 	Return Set of elements.
	*/
	public static Set<Integer> convertListToSet (List<Integer> arrayList) {
		return arrayList.stream().map(eachElement -> eachElement).collect(Collectors.toSet()) ;
	}
}