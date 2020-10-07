package Searches;

import java.util.*; 
class SublistSearch  
{  
  

static class Node  
{ 
    int data; 
    Node next; 
}; 
   
static boolean findList(Node first,Node second) 
{ 
    Node ptr1 = first, ptr2 = second; 
  

    if (first == null && second == null) 
        return true; 

    if (first == null || 
       (first != null && second == null)) 
        return false; 
  

    while (second != null) 
    { 

        ptr2 = second; 
  

        while (ptr1 != null) 
        { 
            
            if (ptr2 == null) 
                return false; 
  
            else if (ptr1.data == ptr2.data) 
            { 
                ptr1 = ptr1.next; 
                ptr2 = ptr2.next; 
            } 
  
            
            else break; 
        } 

        if (ptr1 == null) 
            return true; 

        ptr1 = first; 
  

        second = second.next; 
    } 
    return false; 
} 

static void printList(Node node) 
{ 
    while (node != null) 
    { 
        System.out.printf("%d ", node.data); 
        node = node.next; 
    } 
} 
  

static Node newNode(int key) 
{ 
    Node temp = new Node(); 
    temp.data= key; 
    temp.next = null; 
    return temp; 
} 

public static void main(String[] args)  
{ 

    Node a = newNode(1); 
    a.next = newNode(2); 
    a.next.next = newNode(3); 
    a.next.next.next = newNode(4); 
  
    Node b = newNode(1); 
    b.next = newNode(2); 
    b.next.next = newNode(1); 
    b.next.next.next = newNode(2); 
    b.next.next.next.next = newNode(3); 
    b.next.next.next.next.next = newNode(4); 
  
    if(findList(a, b) == true)  
        System.out.println("LIST FOUND"); 
    else
        System.out.println("LIST NOT FOUND"); 
} 
} 
  