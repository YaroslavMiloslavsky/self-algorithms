package yaro.algos.lib.sorting.impl;

import yaro.algos.lib.sorting.constants.SortAlgorithm;

public final class BubbleSortImplIntegerTest extends SortableIntegerTest {

    public BubbleSortImplIntegerTest() {
        super(sortableFactory.getSortingAlgorithm(SortAlgorithm.BUBBLE), "Bubble Sort");
    }  
    
}