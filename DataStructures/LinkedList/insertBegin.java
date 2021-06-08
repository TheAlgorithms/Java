package LinkedList;

public class insertBegin {
    class node{
        int data;
        node next;
        node(int data){
            this.data=data;
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
            toAdd.next=null;
        }
       
    }
    Boolean isEmpty(){
        return head==null;
    }
      node head;
      void insertAtTheBeginning(int data){
          node toAdd=new node(data);
          toAdd.next=head;
          head=toAdd;
      }
      void print(){
          node temp=head;
          while(temp!=null){
              System.out.print(temp.data+" ");
              temp=temp.next;
          }
      }
      public static void main(String sp[]){
          insertBegin object=new insertBegin();
          object.add(12);
          object.add(13);
          object.add(14);
          object.print();
          System.out.println();
          object.insertAtTheBeginning(10);
          object.print();

      }
}
