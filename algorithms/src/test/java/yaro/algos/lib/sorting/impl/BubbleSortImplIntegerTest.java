package yaro.algos.lib.sorting.impl;

import yaro.algos.lib.sorting.Sortable;

public final class BubbleSortImplIntegerTest extends SortableIntegerTest {

    private static final Sortable<Integer> sortableAlgorithm = new BubbleSortImpl<>();

    public BubbleSortImplIntegerTest() {
        super(sortableAlgorithm, "Bubble Sort");
    }  
    
}