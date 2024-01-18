package yaro.algos.lib.sorting.impl;

import yaro.algos.lib.sorting.Sortable;

public class MergeSortImpl<T extends Comparable<T>> implements Sortable<T> {

    @Override
    public void sortMePlease(T[] elements) {
        if (elements == null || elements.length <= 1)
            return;
        mergeSort(elements);
    }

    private void mergeSort(T[] elements) {

    }

    private void merge(T[] array, T[] leftSubArray, T[] rightSubArray) {
        
    }

}
