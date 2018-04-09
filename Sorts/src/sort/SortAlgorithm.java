package sort;

/**
 *  The common interface of most algorithms
 *
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 *
 **/
public interface SortAlgorithm {

    <T extends Comparable<T>> T[] sort(T[] unsorted);

}
