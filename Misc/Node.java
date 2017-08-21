public class Node {
	 public Object anElement;
	 public Node less;
	 public Node greater;
	 
	 public Node(Object theElement) {
	    this(theElement, null, null); //an empty node at the end will be by itself with no children, therefore the other 2 parameters are always null
	                                  //obviously the node can still be a child of other elements
    }

	 public Node(Object currentElement, Node lessSide, Node greaterSide) {
	  anElement = currentElement;
	  this.less = lessSide;
	  this.greater = greaterSide;
	 }
}
