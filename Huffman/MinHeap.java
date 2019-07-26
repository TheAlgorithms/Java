import java.util.ArrayList;
import java.util.List;

/**
 * A priority queue implemented by minimum heap
 *
 * @param <T> The type of the priority queue's element, and it must implement
 *            the {@code Comparable<T>} interface
 */
public class MinHeap<T extends Comparable<T>> {
  private List<T> element = new ArrayList<>();
  
  public MinHeap() {
    this.element.add(null);
  }
  
  public int elementNumber() {
    return this.element.size() - 1;
  }
  
  public boolean isEmpty() {
    return this.element.size() == 1;
  }
  
  public void Insert(T t) {
    this.element.add(t);
    this.PushUp();
  }
  
  public T deleteTop() {
    T result = null;
    if(! this.isEmpty()) {
      int size = this.elementNumber();
      
      result = this.element.get(1);
      this.swap(1, size);
      this.element.remove(size);
      
      this.PushDown();
    }
    return result;
  }
  
  public void swap(int i, int j) {
    T temp = this.element.get(i);
    this.element.set(i, this.element.get(j));
    this.element.set(j, temp);
  }
  
  public void PushUp() {
    int tail = this.elementNumber();
    int index = tail / 2;
    
    while(index >= 1) {
      if (index == tail / 2 &&tail % 2 == 0) {
        if (this.element.get(index).compareTo(this.element.get(tail)) > 0) {
          this.swap(index, tail);
          index /= index;
        } else {
          index = 0;
        }
      } else {
        if(this.element.get(index).compareTo(this.element.get(2 * index)) > 0
            && this.element.get(2 * index).compareTo(this.element.get(2 * index + 1)) == 0) {
          this.swap(index, 2 * index);
          index /= 2;
        } else if(this.element.get(index).compareTo(this.element.get(2 * index + 1)) > 0
            && this.element.get(2 * index + 1).compareTo(this.element.get(2 * index)) == 0) {
          this.swap(index, 2 * index + 1);
          index /= 2;
        } else {
          index = 0;
        }
      }
    }
  }
  
  public void PushDown() {
    int index = 1;
    int end = this.elementNumber() / 2;
    
    while (index <= end) {
      if (index == end && this.elementNumber() % 2 == 0) {
        if (this.element.get(index).compareTo(this.element.get(2*index)) > 0) {
          this.swap(index, 2*index);
        }
        index = end + 1;
      } else {
        if(this.element.get(index).compareTo(this.element.get(2 * index)) > 0
            && this.element.get(2 * index).compareTo(this.element.get(2 * index + 1)) == 0) {
          this.swap(index, 2 * index);
          index *= 2;
        } else if(this.element.get(index).compareTo(this.element.get(2 * index + 1)) > 0
            && this.element.get(2 * index + 1).compareTo(this.element.get(2 * index)) == 0) {
          this.swap(index, 2 * index + 1);
          index *= 2;
        } else {
          index = end + 1;
        }
      }
     
    }
  }
}
