public class CircleLinkedList<E>{
    private static class Node<E>{
        Node<E> next;
        E value;
        private Node(E value, Node<E> next){
            this.value = value;
            this.next = next;
        }
    }

    //For better O.O design this should be private allows for better black box design
    private int size;
    //this will point to dummy node;
    private Node<E> head;
    private Node<E> tail;
    //constructer for class.. here we will make a dummy node for circly linked list implementation with reduced error catching as our list will never be empty;
    public CircleLinkedList(){
        head = new Node<>(null, head);
        tail = head;
    }
    // getter for the size... needed because size is private.
    public int getSize(){ return size;}
    // for the sake of simplistiy this class will only contain the append function or addLast other add functions can be implemented however this is the basses of them all really.
    public void append(E value){
        if(value == null){
            // we do not want to add null elements to the list.
            throw new NullPointerException("Cannot add null element to the list");
        }

        //add new node at the end of the list and update tail node to point to new node
        tail.next = new Node(value, head);
        tail = tail.next;
        size++;
    }

    public E remove(int pos){
        if(pos>=size || pos< 0){
            //catching errors
            throw new IndexOutOfBoundsException("position cannot be greater than size or negative");
        }
        Node<E> iterator = head.next;
        //we need to keep track of the element before the element we want to remove we can see why bellow.
        Node<E> before = head;
        for(int i = 1; i<=pos; i++){
            iterator = iterator.next;
            before = before.next;
        }
        E removedValue = iterator.value;
        // assigning the next reference to the the element following the element we want to remove... the last element will be assigned to the head.
        before.next = iterator.next;
        // scrubbing
        iterator.next = null;
        iterator.value = null;
        size--;

        return removedValue;
    }

}