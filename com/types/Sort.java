package com.types;

@FunctionalInterface
public interface Sort<T extends Comparable<T>> {

    T[] sort(T[] array);
}
