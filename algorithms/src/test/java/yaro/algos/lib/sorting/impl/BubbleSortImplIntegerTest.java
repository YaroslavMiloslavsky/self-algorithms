package yaro.algos.lib.sorting.impl;

import yaro.algos.lib.sorting.Sortable;

final class BubbleSortImplIntegerTest extends SortableIntegerTest {

    static final Sortable<Integer> sortableAlgorithm = new BubbleSortImpl<>();

    public BubbleSortImplIntegerTest() {
        super(sortableAlgorithm, "Bubble Sort");
    }  
    
}