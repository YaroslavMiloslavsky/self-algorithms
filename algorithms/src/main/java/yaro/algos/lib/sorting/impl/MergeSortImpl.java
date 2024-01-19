package yaro.algos.lib.sorting.impl;

import java.util.Arrays;
import yaro.algos.lib.sorting.Sortable;

public class MergeSortImpl<T extends Comparable<T>> implements Sortable<T> {

    @Override
    public void sortMePlease(T[] elements) {
        if (elements == null || elements.length <= 1)
            return;
        mergeSort(elements);
    }

    private void mergeSort(T[] elements) {
        int arrayLength = elements.length;
        if (arrayLength == 1)
            return;

        int middle = ((arrayLength & 1) == 0) ? (int) (arrayLength / 2) : (int) ((arrayLength + 1) / 2);
        T[] leftSideArray = Arrays.copyOfRange(elements, 0, middle);
        T[] rightSideArray = Arrays.copyOfRange(elements, middle, arrayLength);

        mergeSort(leftSideArray);
        mergeSort(rightSideArray);
        merge(elements, leftSideArray, rightSideArray);
    }

    private void merge(T[] array, T[] leftSubArray, T[] rightSubArray) {
        int l = 0, r = 0, llen = leftSubArray.length, rlen = rightSubArray.length;

        for (int i = 0; i < array.length; i++) {
            T element;
            if (l < llen && r < rlen) {
                if (leftSubArray[l].compareTo(rightSubArray[r]) <= 0) {
                    element = leftSubArray[l++];
                } else {
                    element = rightSubArray[r++];
                }
            } else if (l < llen) {
                element = leftSubArray[l++];
            } else {
                element = rightSubArray[r++];
            }
            array[i] = element;
        }
    }
}
