/**
 * @param <T>
 */
public class LinkedList<T> implements List<T>{

    private Node<T> head;  
    private int size = 0;

    public LinkedList(){
        this.head = new Node<>();
    }

    @Override
    public boolean add(T value) {
        try{
            Node<T> aux = this.head; 
            while ( aux.getNext() != null ){ 
                aux = aux.getNext();
            }
            aux.setNext( new Node<>( value ) ); 
            size = size + 1;
        }catch ( Exception e ){
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(int index) throws IndexOutOfBoundsException {
        if( index < 0 || index > size() ) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> aux = this.head;
        try{
            for (int i = 0; i < index && aux.getNext() != null; i++) { 
                aux = aux.getNext();
            }
   
            aux.setNext( aux.getNext().getNext() );
            size = size - 1;
        }catch ( Exception e ){
            return false;
        }
        return true;
    }

    /**     
     * @param value
     * @return
     */
    public boolean remove(T value) {
        Node<T> aux = this.head; 
        if (null != value) {
            while (!value.equals(aux.getNext().getValue())) { 
                aux = aux.getNext();
            }           
            aux.setNext(aux.getNext().getNext());
            size = size - 1;
        }else{
            return false;
        }
        return true;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
      
        if( index < 0 || index > size() ) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> aux = this.head; 
        for (int i = 0; i <= index && aux.getNext() != null; i++){  
            aux = aux.getNext();
        }

        return aux.getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder("");
        string.append("LinkedList={");

        Node<T> aux = this.head; 
        while ( aux.getNext() != null ) {
            aux = aux.getNext();
            string.append( aux.toString() ).append(",");
        }
        string.replace(string.length()-1, string.length(), "}");

        return string.toString();
    }

    /**     
     * @param <E>
     */
    class Node<E> {

        private E value;
        private Node<E> next;

        public Node( E value ){
            this.value = value;
        }

        public Node() {

        }

        public E getValue() {
            return value;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        @Override
        public String toString(){
            return this.getValue().toString();
        }
    }
}



/** 
 * @author Nawodya
 * @param <T>
 */

interface List<T> {

    /**
     * @param value
     * @return
     */
    boolean add( T value );

    /**
     * @param index
     * @return 
     * @throws IndexOutOfBoundsException
     */
    boolean remove( int index ) throws IndexOutOfBoundsException;

    /**
     * @param index
     * @return
     * @throws IndexOutOfBoundsException 
     */
    T get( int index ) throws IndexOutOfBoundsException;

    /**
     * @return
     */
    int size();
}