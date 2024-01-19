package yaro.algos.lib.sorting.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import yaro.algos.lib.sorting.Sortable;

public abstract class SortableIntegerTest {
    private static final Logger log = Logger.getLogger(SortableIntegerTest.class.getName());
    private static long maxRuntime = 0L;
    private Sortable<Integer> sortAlgo;

    public SortableIntegerTest(Sortable<Integer> sortableAlgorithm) {
        this.sortAlgo = sortableAlgorithm;
    }

    @AfterAll
    static void showSortingStats() {
        log.info(String.format("Longest runtime for this bubble sort is: %d seconds", maxRuntime));
    }

    @Test
    void bubbleSortSimpleTest() {
        Integer[] arrayDefault = { 10, 4, 5, 23, -45, 43, 100, 3, 3, 69, 3 };
        Integer[] arrayCustom = arrayDefault.clone();

        Arrays.sort(arrayDefault);
        assertFalse(Arrays.equals(arrayDefault, arrayCustom));

        sortAlgo.sortMePlease(arrayCustom);

        assertArrayEquals(arrayDefault, arrayCustom);
    }

    @Test
    void bubbleSortLargeArray() throws InterruptedException, ExecutionException {
        log.info("About to sort large arrays");
        int randomNumber = 0;
        final int arraySize = 100_000;
        final int min = 1;
        final int max = 1_000;
        Integer[] originalArray = new Integer[arraySize];

        for (int i = 0; i < arraySize; i++) {
            randomNumber = (int) ((Math.random() * (max - min)) + min);
            originalArray[i] = randomNumber;
        }

        Integer[] copiedArray = originalArray.clone();

        assertArrayEquals(originalArray, copiedArray);

        Arrays.sort(copiedArray);
        assertFalse(Arrays.equals(originalArray, copiedArray));

        Runnable sortTask = () -> {
            log.info(String.format("Running task on thread: %s", Thread.currentThread().getName()));
            sortAlgo.sortMePlease(originalArray);
        };

        var startTime = System.currentTimeMillis();
        CompletableFuture
                .runAsync(sortTask)
                .thenRunAsync(() -> {
                    log.info(String.format("Running task on thread: %s", Thread.currentThread().getName()));
                    var endTime = System.currentTimeMillis();
                    var customSortRunTime = (endTime - startTime) / 1000;
                    log.info(String.format("It took %d seconds for custom bubble sort to run", customSortRunTime));
                    if (maxRuntime < customSortRunTime)
                        maxRuntime = customSortRunTime;
                }).get(); // Blocking because we need that answer.

        assertArrayEquals(originalArray, copiedArray);
    }

    @Test
    void bubbleSortTinyLargeArray() {
        Integer[] arrayDefault = { 10, 2 };
        Integer[] arrayCustom = arrayDefault.clone();

        Arrays.sort(arrayDefault);
        assertFalse(Arrays.equals(arrayDefault, arrayCustom));

        sortAlgo.sortMePlease(arrayCustom);

        assertArrayEquals(arrayDefault, arrayCustom);
    }

    @Test
    void bubbleSortEmptyArrayTest() {
        Integer[] arrayDefault = {};
        Integer[] arrayCustom = arrayDefault.clone();

        Arrays.sort(arrayDefault);
        Arrays.equals(arrayDefault, arrayCustom);

        sortAlgo.sortMePlease(arrayCustom);

        assertArrayEquals(arrayDefault, arrayCustom);
    }

    @Test
    void bubbleSortNullArrayTest() {
        Integer[] array = null;

        sortAlgo.sortMePlease(array);

        assertEquals(array, null);
    }
}
