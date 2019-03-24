package dataStructures;

/**
 * Binary tree for general value type, without redundancy
 * @author RICARDO
 * @param <T> root data
 */
public class BinaryTree<T extends Comparable> {
    private final T data;
    private BinaryTree  right,  // the upper binary tree
                        left;   // the lower binary tree

    public BinaryTree(T data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return this.data.toString();
    }
    
    /**
     * inserts a new value in it's correspondant place
     * @param newDataValue value of the new banary tree to add on this tree
     */
    public void insert(T newDataValue){
        this.insert(new BinaryTree(newDataValue));
    }
    
    /**
     * inserts a new binary tree in it's correspondant place
     * @param newData new value to add on this tree 
     */
    public void insert(BinaryTree newData){
        
        int cpr = newData.data.compareTo(this.data); //new value comparission respect to actual value
        
        if (cpr < 0)
            if (this.left == null)
                this.setLeft(newData);
            else
                this.left.insert(newData);
        else if (cpr > 0)
            if (this.right == null)
                this.setRight(newData);
            else
                this.right.insert(newData);
        else
            System.out.println("Redundant value, not added");
    }

    /**
     * search and specific value on the tree
     * @param data Searched value 
     * @return Binary tree wich contains the value, null if it doesn't exist
     */
    public BinaryTree search(T data){
        int cpr = data.compareTo(this.data); //new value comparission respect to actual value
        
        if (cpr < 0) {
            if (this.left == null)
                return null;    //the value doesn't exist
            return this.left.search(data);
        }
        if (cpr > 0) {
            if (this.right == null)
                return null;    //the value doesn't exist
            return this.right.search(data);             
        }
        return this;
    }
    
    /**
     * Checks if the data value exist in the tree
     * @param data data to be searched
     * @return true if this tree contains the data value, false if not.
     */
    public boolean contains(T data){
        return !(this.search(data) == null);
    }
    
    /**
     * uses recursive black magic to print this tree in console
     * @param tabCounter prev tabs
     */
    private void print(int tabCounter){
        for (int i = 0; i < tabCounter; i++)
            System.out.print("\t");
        
        System.out.println(this);
        
        if (this.left!=null)
            this.left.print(tabCounter+1);  //it can't be ++ , pls don't change it
        if (this.right!=null)
            this.right.print(tabCounter+1);  //it can't be ++ , pls don't change it
    }
    
    /**
     * uses black magic to print this tree in console
     */
    public void print(){
        this.print(0);
    }
    
    //getters and setters
    public T getData() {
        return data;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }
}