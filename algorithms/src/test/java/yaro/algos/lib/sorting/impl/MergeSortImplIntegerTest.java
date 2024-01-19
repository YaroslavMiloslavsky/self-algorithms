package yaro.algos.lib.sorting.impl;

import yaro.algos.lib.sorting.constants.SortAlgorithm;

public final class MergeSortImplIntegerTest extends SortableIntegerTest {

    public MergeSortImplIntegerTest() {
        super(sortableFactory.getSortingAlgorithm(SortAlgorithm.MERGE), "Merge Sort");
    }
    
}
