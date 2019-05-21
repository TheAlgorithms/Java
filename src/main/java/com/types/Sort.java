package src.main.java.com.types;

@FunctionalInterface
public interface Sort<T> {

    <T extends Comparable<T>> T[] sort(T[] array);
}
