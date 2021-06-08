package LinkedList;

public class reverseDoublyLinkedlist {
        class node{
          int data;
          node next;
          node prev;
          node(int data){
              this.data=data;

          }
        }
        node head;
        Boolean isEmpty(){
            return head==null;
        }    
     int length(){
           int count=1;
           if(isEmpty()){
               return 0;
           }
           node temp=head;
           while(temp.next!=null){
               temp=temp.next;
               count++;
           }
           return count;    

     }
     void reverse(){
         if(isEmpty()){
             return;
         }
         else
         {
            node temp=head;
            while(temp.next!=null){
                temp=temp.next;
            }
            node start=head;
           int i=length();
             
             for(int c=1 ; c<i ; c++,i--){
                node tempo=new node(1);
                  tempo.data=start.data;
                  start.data=temp.data;
                  temp.data=tempo.data;
                  start=start.next;
                  temp=temp.prev;

             }
         }
     }
     void add(int data){
         node toAdd=new node(data);
         if(isEmpty()){
         head=toAdd;
         }
         else
         {
             node temp=head;
             while(temp.next!=null){
                 temp=temp.next;
             }
             temp.next=toAdd;
             toAdd.prev=temp;
             toAdd.next=null;
         }
     }
     void print(){
   
         if(isEmpty()){
             System.err.println("Empty list");
         }
         else
         {
             node temp=head;
             while(temp!=null){
                 System.out.print(temp.data+" ");
                 temp=temp.next;
             }

         }
     }
     public static void main(String[] args) {
          reverseDoublyLinkedlist obj=new reverseDoublyLinkedlist();
          obj.add(12);
          obj.add(13);
          obj.add(14);
          obj.add(15);
          obj.print();
          System.out.println();
          obj.reverse();
          obj.print(); 

     }  
}
