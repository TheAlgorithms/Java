/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scopevalidator;

/**
 *
 * @author HP
 */
public class stack {
    char stk[];
    int top=-1;
    stack(String exp){
        stk=new char[exp.length()];
    
    }
    void push(char val){
        top++;
        stk[top]=val;
        
    }
    char pop(){
        char v=stk[top];
        top--;
        return v;
    
    }
    char peek(){
        char symbol=stk[top];
        return symbol;
    
    }
    boolean isempty(){
        if(top==-1){
            return true;
        
        }
        else{
            return false;
        }
    
    }
    void print(){
        for(int i=top;i<=0;i--){
            System.out.println("|"+stk[i]+"|");
        
        }
    }
}
