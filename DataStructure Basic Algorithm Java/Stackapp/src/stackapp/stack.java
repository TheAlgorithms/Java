/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackapp;

/**
 *
 * @author HP
 */
public class stack {
    int top=-1;
    int stk[];
    int s;
    
    stack(int size){
        s=size;
        stk=new int[size];
    }
    
    void insert(int v){
        
        top++;
        stk[top]=v;
        
     }
    int pop(){
        int v=stk[top];
        top--;
        return v;
    
    }
    boolean isempty(){
        if (top==-1){
            return true;
        }
        else{
            return false;
        }
    }
    boolean isfull(){
        if(top==stk.length-1){
            return true;
        }
        else{
            return false;
        }
    }
    void print(){
        for(int i=s-1;i>=0;i--){
            System.out.println("|"+ stk[i]+"|");
        }
        
        
    }
}
