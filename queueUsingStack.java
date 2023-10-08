import java.io.*;
import java.util.*;
class MyQueue {
    Stack<Integer> st1,st2;
    public MyQueue() {
        this.st1 = new Stack<Integer>();
        this.st2 = new Stack<Integer>();
    }
    
    public void push(int x) {
        this.st1.push(x);
    }
    
    public int pop() {
        while (!st1.empty()){
            st2.push(st1.pop());
           // st1.pop();
        }
        int ans = st2.pop();
        while (!st2.empty()){
            st1.push(st2.pop());
        }
        return ans;
    }
    
    public int peek() {
        while (!st1.empty()){
            st2.push(st1.pop());
           // st1.pop();
        }
        int ans = st2.peek();
        while (!st2.empty()){
            st1.push(st2.pop());
        }
        return ans;
    }
    
    public boolean empty() {
        return st1.empty();
    }
    public static void main(String[] args){
        MyQueue obj = new MyQueue();
        obj.push(10);
        obj.push(20);
        int param_2 = obj.pop();
        int param_3 = obj.peek();
        boolean param_4 = obj.empty();
        System.out.println("Popped value: "+ param_2);
        System.out.println("top value: "+ param_3);
        System.out.println("Empty? "+ param_4);
    }
}
