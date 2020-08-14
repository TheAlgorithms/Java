package com.dataStructures;

public class DoublyLinkedList<T> {

    /**
     * Class to represent a node of the list
     * @param <T> Data type to be stored in list
     */
    private static class Node<T>{
        T data;
        Node<T> next;
        Node<T> previous;

        public Node(T data){
            this.data = data;
        }
    }

    /**
     * Reference to head of the list
     */
    private Node<T> head;

    /**
     * Number of elements in the list
     */
    private int size;

    /**
     * Default Constructor
     */
    public DoublyLinkedList(){
        this.head = null;
        size = 0;
    }

    /**
     * Insert an element at the starting of the list
     * @param data Element to be inserted
     */
    public void insertHead(T data){
        Node<T> node = new Node<>(data);
        if(head == null){
            node.next = null;
            node.previous = null;
            head = node;
        }else{
            head.previous = node;
            node.next = head;
            head = node;
        }
        ++size;
    }

    /**
     * Delete the first element of the list
     * @throws RuntimeException Throws runtime exception if delete attempt is made on an empty list
     */
    public void deleteHead(){
        if(head == null){
            throw new RuntimeException("Deleting from a empty list.");
        }
        if(size == 1){
            head = null;
            return;
        }
        this.head.next.previous = null;
        this.head = head.next;
        --size;
        
    }

    /**
     * Delete the first occurence of the matching element from the list
     * @param data Element to be deleted
     */
    public void delete(T data){
        Node<T> n = head;
        while(n != null && n.data != data){
            n = n.next;
        }
        if(n.data == data){
            n.previous.next = n.next;
            n.next.previous = n.previous;
            --size;
        }else{
            System.out.println("Element not found.");
        }
    }

    /**
     * Check whether list contains some elements
     * 
     */
    public boolean isEmpty(){
        return (head == null);
    }

    /**
     * 
     * @return The number of elements in the list
     */
    public int getSize(){
        return size;
    }

    /**
     *  Display current list
     */
    public void showList(){
        StringBuilder builder = new StringBuilder();
        Node<T> n = head;
        if(n == null){
            System.out.println("Empty List");
            return;
        }
        builder.append(n.data);
        n = n.next;
        while(n != null){
            builder.append("<-->").append(n.data);
            n = n.next;
        }
        System.out.println(builder.toString());
    }
    
}