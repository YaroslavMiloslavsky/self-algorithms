package yaro.algos.lib.sorting.impl;

import yaro.algos.lib.sorting.Sortable;

public final class MergeSortImplIntegerTest extends SortableIntegerTest {

    private static final Sortable<Integer> sortableAlgorithm = new MergeSortImpl<>();

    public MergeSortImplIntegerTest() {
        super(sortableAlgorithm, "Merge Sort");
    }
    
}
