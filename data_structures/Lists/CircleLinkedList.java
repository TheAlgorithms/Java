public class CircleLinkedList<E>{
     private static class Node<E>{
         Node<E> next;
         E value;
         private Node(E value, Node<E> next){
              this.value = value;
              this.next = next;
         }
     }
     private int size; //For better O.O design this should be private allows for better black box design
     private Node<E> head; //this will point to dummy node;
     public CircleLinkedList(){ //constructer for class.. here we will make a dummy node for circly linked list implementation with reduced error catching as our list will never be empty;
         head = new Node<E>(null,head); //creation of the dummy node
         size = 0;
     }
     public int getSize(){ return size;} // getter for the size... needed because size is private.
     public void append(E value){ // for the sake of simplistiy this class will only contain the append function or addLast other add functions can be implemented however this is the basses of them all really.
         if(value == null){
         	throw new NullPointerException("Cannot add null element to the list"); // we do not want to add null elements to the list.
         }
         head.next = new Node<E>(value,head); //head.next points to the last element;
         size++;}
     public E remove(int pos){
     	if(pos>size || pos< 0){
     		throw new IndexOutOfBoundsException("position cannot be greater than size or negative"); //catching errors
     	}
     	Node<E> iterator = head.next; 
     	Node<E> before = head; //we need to keep track of the element before the element we want to remove we can see why bellow.
     	for(int i = 1; i<=pos; i++){
            iterator = iterator.next;
            before = before.next;   
     	}
     	E saved = iterator.value;
     	before.next = iterator.next; // assigning the next referance to the the element following the element we want to remove... the last element will be assigned to the head.
     	iterator.next = null; // scrubbing 
     	iterator.value = null; 
     	return saved;

     	}

     }

