package src.main.java.com.types;

@FunctionalInterface
public interface Sort<T> {

    public <T extends Comparable<T>> T[] sort(T[] array);
}
