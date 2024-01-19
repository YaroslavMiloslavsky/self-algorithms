package yaro.algos.lib.sorting.impl;

import yaro.algos.lib.sorting.Sortable;

final public class MergeSortImplIntegerTest extends SortableIntegerTest {

    private static final Sortable<Integer> sortableAlgorithm = new MergeSortImpl<>();

    public MergeSortImplIntegerTest() {
        super(sortableAlgorithm, "Merge Sort");
    }
    
}
