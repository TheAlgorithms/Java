package data_structures.doublylinkedlist;

/**
 * Created by Grzegorz on 3/29/2017.
 */
public class Link {
    public int value;
    public Link next; //This points to the link in front of the new link
    public Link previous; //This points to the link behind the new link

    public Link(int value) {
        this.value = value;
    }

    public void displayLink() {
        System.out.print(value + " ");
    }
}
