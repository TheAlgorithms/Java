package DataStructures.HashMap.Hashing;

public class HashMap {
  private int hsize;
  private LinkedList[] buckets;

  public HashMap(int hsize) {
    buckets = new LinkedList[hsize];
    for (int i = 0; i < hsize; i++) {
      buckets[i] = new LinkedList();
      // Java requires explicit initialisaton of each object
    }
    this.hsize = hsize;
  }

  public int hashing(int key) {
    int hash = key % hsize;
    if (hash < 0) hash += hsize;
    return hash;
  }

  public void insertHash(int key) {
    int hash = hashing(key);
    buckets[hash].insert(key);
  }

  public void deleteHash(int key) {
    int hash = hashing(key);

    buckets[hash].delete(key);
  }

  public void displayHashtable() {
    for (int i = 0; i < hsize; i++) {
      System.out.printf("Bucket %d :", i);
      System.out.println(buckets[i].display());
    }
  }

  public static class LinkedList {
    private Node first;

    public LinkedList() {
      first = null;
    }

    public void insert(int key) {
      if (isEmpty()) {
        first = new Node(key);
        return;
      }

      Node temp = findEnd(first);
      temp.setNext(new Node(key));
    }

    private Node findEnd(Node n) {
      if (n.getNext() == null) {
        return n;
      } else {
        return findEnd(n.getNext());
      }
    }

    public Node findKey(int key) {
      if (!isEmpty()) {
        return findKey(first, key);
      } else {
        System.out.println("List is empty");
        return null;
      }
    }

    private Node findKey(Node n, int key) {
      if (n.getKey() == key) {
        return n;
      } else if (n.getNext() == null) {
        System.out.println("Key not found");
        return null;
      } else {
        return findKey(n.getNext(), key);
      }
    }

    public void delete(int key) {
      if (!isEmpty()) {
        if (first.getKey() == key) {
          first = null;
        } else {
          delete(first, key);
        }
      } else {
        System.out.println("List is empty");
      }
    }

    private void delete(Node n, int key) {
      if (n.getNext().getKey() == key) {
        if (n.getNext().getNext() == null) {
          n.setNext(null);
        } else {
          n.setNext(n.getNext().getNext());
        }
      }
    }

    public String display() {
      return display(first);
    }

    private String display(Node n) {
      if (n == null) {
        return "null";
      } else {
        return n.getKey() + "->" + display(n.getNext());
      }
    }

    public boolean isEmpty() {
      return first == null;
    }
  }

  public static class Node {
    private Node next;
    private int key;

    public Node(int key) {
      next = null;
      this.key = key;
    }

    public Node getNext() {
      return next;
    }

    public int getKey() {
      return key;
    }

    public void setNext(Node next) {
      this.next = next;
    }
  }
}
