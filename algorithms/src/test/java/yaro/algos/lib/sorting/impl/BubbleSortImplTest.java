package yaro.algos.lib.sorting.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import yaro.algos.lib.sorting.Sortable;

class BubbleSortImplTest {

    @Test
    void bubbleSortSimpleTest() {
        Integer[] arrayDefault = {10, 4, 5, 23, -45, 43, 100, 3, 3, 69, 3};
        Integer[] arrayCustom = arrayDefault.clone();

        Arrays.sort(arrayDefault);
        assertFalse(Arrays.equals(arrayDefault, arrayCustom));

        Sortable<Integer> librarySort = new BubbleSortImpl<>();
        librarySort.sortMePlease(arrayCustom);
        
        assertArrayEquals(arrayDefault, arrayCustom);
    }

    @Test
    void bubbleSortEmptyArrayTest() {
        Integer[] arrayDefault = {};
        Integer[] arrayCustom = arrayDefault.clone();

        Arrays.sort(arrayDefault);
        Arrays.equals(arrayDefault, arrayCustom);

        Sortable<Integer> librarySort = new BubbleSortImpl<>();
        librarySort.sortMePlease(arrayCustom);
        
        assertArrayEquals(arrayDefault, arrayCustom);
    }

    @Test
    void bubbleSortNullArrayTest() {
        Integer[] array = null;

        Sortable<Integer> librarySort = new BubbleSortImpl<>();
        librarySort.sortMePlease(array);
        
        assertEquals(array, null);
    }
    
}