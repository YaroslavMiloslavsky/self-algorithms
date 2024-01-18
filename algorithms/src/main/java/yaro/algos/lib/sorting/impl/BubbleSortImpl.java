package yaro.algos.lib.sorting.impl;

import yaro.algos.lib.sorting.Sortable;

public final class BubbleSortImpl<T extends Comparable<T>> implements Sortable<T> {

    @Override
    public void sortMePlease(T[] elements) {
        if (elements == null || elements.length <= 1) return;

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j + 1 < elements.length - i /* Optimization */; j++) {
                if (elements[j].compareTo(elements[j + 1]) > 0) {
                    sortInPlace(elements, j);
                }
            }
        }
    }

    private void sortInPlace(T[] elements, int j) {
        T temp = elements[j];
        elements[j] = elements[j + 1];
        elements[j + 1] = temp;
    }

   

}
