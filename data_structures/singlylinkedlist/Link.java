package data_structures.singlylinkedlist;

public class Link {
    public int value;
    public Link next; //This is what the link will point to

    public Link(int value) {
        this.value = value;
    }

    public void displayLink() {
        System.out.print(value + " ");
    }
}
