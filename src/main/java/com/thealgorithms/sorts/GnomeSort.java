package com.thealgorithms.sorts;

/**
 * Implementation of gnome sort
 *
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @since 2018-04-10
 */
public class GnomeSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(final T[] array) {
        int i = 1;
        int j = 2;
        while (i < array.length) {
            if (SortUtils.less(array[i - 1], array[i])) {
                i = j++;
            } else {
                SortUtils.swap(array, i - 1, i);
                if (--i == 0) {
                    i = j++;
                }
            }
        }

        return array;
    }
}
