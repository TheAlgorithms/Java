package DataStructures.Heaps;

/**
 * @author Nicolas Renard Exception to be thrown if the getElement method is used on an empty heap.
 */
@SuppressWarnings("serial")
public class EmptyHeapException extends Exception {

  public EmptyHeapException(String message) {
    super(message);
  }
}
