package yaro.algos.lib.sorting.factory;

import yaro.algos.lib.sorting.Sortable;
import yaro.algos.lib.sorting.constants.SortAlgorithm;
import yaro.algos.lib.sorting.impl.BubbleSortImpl;
import yaro.algos.lib.sorting.impl.MergeSortImpl;

public final class SortableFactory<T extends Comparable<T>> {

    public Sortable<T> getSortingAlgorithm(SortAlgorithm sortAlgorithm) {
        if (sortAlgorithm.equals(SortAlgorithm.BUBBLE))
            return new BubbleSortImpl<T>();
        else if (sortAlgorithm.equals(SortAlgorithm.MERGE))
            return new MergeSortImpl<T>();
        else return null;
    }
    
}
