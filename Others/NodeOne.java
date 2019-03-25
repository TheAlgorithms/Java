public class NodeOne {
	 public Object anElement;
	 public NodeOne less;
	 public NodeOne greater;

    public NodeOne(Object theElement) {
	    this(theElement, null, null); //an empty node at the end will be by itself with no children, therefore the other 2 parameters are always null
	                                  //obviously the node can still be a child of other elements
    }

	 public NodeOne(Object currentElement, NodeOne lessSide, NodeOne greaterSide) {
	  anElement = currentElement;
	  this.less = lessSide;
	  this.greater = greaterSide;
	 }
}
