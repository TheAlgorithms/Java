package LinkedList;


public class implementationofLinkedList {
  class node{
      int data;
      node next;
      node(int data){
          this.data=data;
      }

  }
  node head;
  void add(int data){
     node toAdd=new node(data);
     if(isEmpty()){ 
         head=toAdd;
     }
     else{
         node temp=head;
         while(temp.next!=null){
             temp=temp.next;
         }
         temp.next=toAdd;
         toAdd.next=null;
     }
  }
  void print(){
      node temp=head;
      while(temp!=null){
          System.out.print(temp.data+" ");
          temp=temp.next;
      }
     // System.out.print(temp.data);
  }
  Boolean isEmpty(){
      return head==null;
  }
  public static void main(String s[]){
    //   int n;
      implementationofLinkedList obj=new implementationofLinkedList();
      obj.add(12);
      obj.add(13);
      obj.print();
//       Scanner sc=new Scanner(System.in);
//       n=sc.nextInt();
//    System.out.println("Enter the data to add"); 
//      for(int i=0 ; i<n ; i++){
//           obj.add(sc.nextInt());
//      }
//      obj.print();     
//      sc.close();
  }

}