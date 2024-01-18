package yaro.algos.lib.sorting;

public interface Sortable<T extends Comparable<T>> {
    
    void sortMePlease(T[] elements);
}

