/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linklist;

/**
 *
 * @author HP
 */
public class single {
    node head;
    single(){
        head=null;
    }
    void insertionstart(int d){
        node temp=new node(d);
        if (head==null){
            head=temp;
        }
        else{
            temp.next=head;
            head=temp;
        
        }
    
    }
    void atend(int d){
        node temp=new node(d);
        if(head==null){
            head=temp;
        
        }
        else{
            node tr=head;
            while(tr.next!=null){
                tr=tr.next;
            
            }
            tr.next=temp;
            
        
        }
    }
    void before(int key,int data){
        node temp=new node(data);
        if(head==null){
            head=temp;
        }
        else{
            node t1,t2;
            t1=head;
            t2=null;
            while(t1.d!=key){
                t2=t1;
                t1=t1.next;
            }
            temp.next=t1;
            t2.next=temp;
        }
    
    }
    void after(int key,int data){
        node temp=new node(data);
        if(head==null){
            head=temp;
        }
        else{
            node tr=head;
            while(tr.d!=key){
                tr=tr.next;
            
            }
            if(tr.next==null){
                System.out.println("key not exist");
            }
            else{
                temp.next=tr.next;
                tr.next=temp;
            
            }
        }
        }
    }
    void display(){
        node tr=head;
        while(tr.next!=null){
            System.out.print(tr.d+"->");
            tr=tr.next;
        
        }
        System.out.println("null");
    
    }

}
