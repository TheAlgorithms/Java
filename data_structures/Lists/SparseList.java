import java.util.Iterator;

public class SparseList<V> implements Iterable<V> {
  private static class SparseListNode<V> extends SparseNode<V> {
    SparseListNode<V> nextNode;

    SparseListNode(int index, V val, SparseListNode<V> nextNode) {
      super(index, val);
      this.nextNode = nextNode;
    }

    SparseListNode<V> next() {
      return nextNode;
    }

    void setIndex(int index) {
      this.index = index;
    }

    void setNext(SparseListNode<V> newNode) {
      this.nextNode = newNode;
    }
  }


  SparseListNode<V> firstNode;
  int size, length;
  V zero;

  public SparseList(V zeroValue) {
    this.firstNode = null;
    this.zero = zeroValue;
  }

  public boolean empty() {
    return firstNode == null;
  }

  public int size() {
    return this.size;
  }

  public V get(int index) {
    if (empty() || index < 0 || index > size-1)
      throw new IndexOutOfBoundsException();

    SparseListNode<V> currentNode = firstNode;
    while (currentNode != null) {
      if (currentNode.index() > index) {
        return zero;
      } else if (currentNode.index() == index) {
        return currentNode.getValue();
      }
      currentNode = currentNode.next();
    }
    return zero;
  }

  public int indexOf(V x) {
    if (empty())
      return -1;
    SparseListNode<V> currentNode = firstNode;
    while (currentNode != null) {
      if (currentNode.getValue().equals(x)) {
        return currentNode.index();
      }
      currentNode = currentNode.next();
    }
    return -1;
  }

  public V remove(int index) {
    if (empty() || index < 0 || index > size-1)
      throw new IndexOutOfBoundsException();

    SparseListNode<V> previousNode = firstNode;
    SparseListNode<V> currentNode = firstNode;
    while (currentNode != null) {
      if (currentNode.index() > index) {
        return zero;
      } else if (currentNode.index() == index) {
        previousNode.setNext(currentNode.next());
        V val = currentNode.getValue();
        currentNode = null;
        return val;
      }
      previousNode = currentNode;
      currentNode = currentNode.next();
    }
    return zero;
  }

  public void add(int index, V element) {
    if (this.empty()) {
      this.firstNode = new SparseListNode<V>(index, element, null);
      this.length = index + 1;
      this.size = 1;
      return;
    }
    SparseListNode<V> currentNode = firstNode;
    if (index < currentNode.index()) {
      this.firstNode = new SparseListNode<V>(index, element, currentNode);
      this.size++;
      return;
    }

    while (currentNode.next() != null && index > currentNode.next().index())
        currentNode = currentNode.next();

    if (currentNode.next() == null) {
      SparseListNode<V> newNode = new SparseListNode<V>(index, element, null);
      currentNode.setNext(newNode);
      size += 1;
      length += 1;
    } else if (currentNode.next().index() == index) {
      currentNode.next().setValue(element);
    } else { // Insert new node after currentNode
      SparseListNode<V> newNode = new SparseListNode<V>(index, element, currentNode.next().next());
      currentNode.setNext(newNode);
      if (index >= size) {
        this.size++;
        if (index + 1 > this.length)
          this.length = index + 1;
      }
    }
  }

  public void add(V element) {
    add(this.length, element);
  }

  public Iterator<V> iterator() {
    return new SparseListIterator();
  }

  public String toString() {
    String result = "[";
    int i = 0;
    for (SparseListNode<V> currentNode = this.firstNode; currentNode != null;) {
      if (currentNode.index() == i) {
        result += currentNode.getValue().toString();
        currentNode = currentNode.next();
      } else {
        result += this.zero.toString();
      }

      if (currentNode != null) {
        result += ", ";
      }
      i++;
    }
    return result + "]";
  }

  private class SparseListIterator implements Iterator<V> {
    SparseListNode<V> next;

    SparseListIterator() {
      this.next = firstNode;
    }

    public V next() {
      V val = next.getValue();
      next = next.next();
      return val;
    }

    public boolean hasNext() {
      return next != null;
    }
  }

  public static void main(String args[]) {
		SparseList<Integer> myList = new SparseList<Integer>(0);
		myList.add(5, 23);
    System.out.println(myList);
		myList.add(0, 45);
    System.out.println(myList);
		myList.add(12);
    System.out.println(myList);
    myList.add(5, 15);
    System.out.println(myList);
    myList.add(25);
    myList.add(15, 22);
		System.out.println(myList);
  }
}

class SparseNode<T> {
  protected int index;
  T value;

  public SparseNode(int index, T value) {
    this.value = value;
    this.index = index;
  }

  public T getValue() {
    return this.value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public int index() {
    return this.index;
  }

  public String toString() {
    return '[' + Integer.toString(this.index) + "] = " + this.getValue().toString();
  }
}
