/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palaindrom;

/**
 *
 * @author HP
 */
import java.util.Arrays;
public class stack {
    int top=-1;
    char stk[];
    char stk2[];
    int s;
    String latter;
    
    stack(String charac){
        s=charac.length();
        stk=new char[charac.length()];
        stk2=new char[charac.length()];
        latter=charac;
        
    }
    
    void insert(){
        for(int i=0;i<stk.length;i++){
            stk[i]=latter.charAt(i);
        }
        
     }
    void pop(){
        int j=0;
        for(int i=stk2.length-1;i>=0;i--){
            stk2[j]=stk[i];
            j++;
        }
        
    
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
        System.out.println("stk1////");
        for(int i=stk.length-1;i>=0;i--){
            System.out.println("|"+ stk[i]+"|");
        }
        System.out.println("stk2////");
        for(int i=stk2.length-1;i>=0;i--){
            System.out.println("|"+ stk2[i]+"|");
        }
        
        
    }
    void checking(){
        if(Arrays.equals(stk, stk2)){
            System.out.println("palaindrome");
        
        }
        else{
            System.out.println("not palaindrom");
        }
    
    }
}

