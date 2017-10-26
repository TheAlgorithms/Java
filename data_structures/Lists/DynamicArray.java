import java.util.*;

public class DynamicArray<E> {
    private Object[] array;
    private int size;

    public DynamicArray(int initialCapacity) {
        this.array = new Object[initialCapacity];
        this.size = 0;
    }

    private void resize() {
        Object[] array = this.array;
        this.array = new Object[array.length * 2];
        System.arraycopy(array, 0, this.array, 0, array.length);
    }

    public void add(E e) {
        if (this.size == array.length)
            this.resize();

        this.array[this.size++] = e;
    }

    public void add(int index, E e) {
        if (this.size == array.length)
            this.resize();

        System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
        this.array[index] = e;
        this.size++;
    }

    public E get(int index) {
        //noinspection unchecked
        return (E) this.array[index];
    }

    public void set(int index, E e) {
        if (this.array[index] == null && e != null)
            ++this.size;
        else if (e == null && this.array[index] != null)
            --this.size;

        this.array[index] = e;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.array.length;
    }

    public int indexOf(E e) {
        for (int i = 0; i < this.size; i++)
            if (this.array[i].equals(e))
                return i;

        return this.size;
    }

    public static void main(String[] args) {
        DynamicArray<Integer> v = new DynamicArray<>(11);

        for (int i = 0; i < 100; i++)
            v.add(i);

        for (int i = 0; i < 100; i++)
            System.out.println(v.get(i));
    }
}
