import java.util.LinkedList; 
import java.util.Queue; 
  
public class stack  
{ 
    Queue<Integer> q = new LinkedList<Integer>(); 
      
    void push(int val) { 
        int size = q.size(); 
          
        q.add(val); 
           
        for (int i = 0; i < size; i++)  
        { 
            int x = q.remove(); 
            q.add(x); 
        } 
    } 
      
    int pop() { 
        if (q.isEmpty())  
        { 
            System.out.println("No elements"); 
            return -1; 
        } 
        int x = q.remove(); 
        return x; 
    } 
      
    int peek() { 
        if (q.isEmpty()) 
            return -1; 
        return q.peek(); 
    } 
      
    boolean isEmpty() { 
        return q.isEmpty(); 
    } 
  
    public static void main(String[] args)  { 
        stack s = new stack(); 
        s.push(10); 
        s.push(20); 
        System.out.println("Top element :" + s.peek()); 
        s.pop(); 
        s.push(30); 
        s.pop(); 
        System.out.println("Top element :" + s.peek()); 
    } 
} 
