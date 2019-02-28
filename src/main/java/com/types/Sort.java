package src.main.java.com.types;

public interface Sort<T> {

    public <T extends Comparable<T>> T[] sort(T[] array);
}
