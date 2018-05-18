package algorithmsjava;
import java.lang.Math;
public class BST {
    int value;
    Boolean empty;
    BST leftChild,rightChild;
    public BST(){
        this.empty = true;
        this.leftChild = null;
        this.rightChild = null;
    }
    public BST(int value,BST leftChild,BST rightChild){
        this.empty = false;
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    };
    public BST(int value){
        this.empty = false;
        this.value = value;
        this.leftChild = new BST();
        this.rightChild = new BST();
    }
    void leaf(int val){
        empty = false;
        value = val;
        leftChild = new BST();
        rightChild = new BST();
    }
    void addValue(int val){
        if(empty){
            leaf(val);
        }
        else{
            if(val<=value) {
                leftChild.addValue(val);
            }else{
                rightChild.addValue(val);
            }
        }
    }
    boolean searchValue(int val){
        if(empty){
            return false;
        }else{
            if(val == value) {
                return true;
            }else if(val <= value){
                return leftChild.searchValue(val);
            }else return rightChild.searchValue(val);
        } 
    }
    int getMax(){
            if(rightChild != null){
                return rightChild.getMax();
            }else {
                return value;
            }
    }
    int extractMax(){
        if(rightChild.empty){
            int result = value;
            copyBST(leftChild);
            return result;
        }else{
            return rightChild.extractMax();
        }
    }
    void copyBST(BST tree){
        empty = tree.empty;
        value = tree.value;
        leftChild = tree.leftChild;
        rightChild = tree.rightChild;
    }
    void deleteValue(int val){  //à compléter
        if(!empty){
            if (val < value) leftChild.deleteValue(val);
	    else if (val>value) rightChild.deleteValue(val);
	    else if (leftChild.empty) copyBST(rightChild);
	    else if (rightChild.empty) copyBST(leftChild);
            else value=leftChild.extractMax();
	    
        }
    }
    int getHeight(){
        if(empty) return 0;
        else return (1 + java.lang.Math.max(leftChild.getHeight(),rightChild.getHeight()));
    }
    int getCount(){
        if(empty) return 0;
        else return(1 + leftChild.getCount() + rightChild.getCount());
    }
}

