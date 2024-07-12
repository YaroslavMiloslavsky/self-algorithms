package yaro.algos.lib.sorting.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import yaro.algos.lib.sorting.Sortable;
import yaro.algos.lib.sorting.factory.SortableFactory;

public abstract class SortableIntegerTest {
    private static final Logger log = Logger.getLogger(SortableIntegerTest.class.getName());
    private Sortable<Integer> sortAlgo;
    private String sortAlgorithmName = "Abstract sort";
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    protected final static SortableFactory<Integer> sortableFactory = new SortableFactory<>();

    protected SortableIntegerTest(Sortable<Integer> sortableAlgorithm, String name) {
        this.sortAlgorithmName = name;
        this.sortAlgo = sortableAlgorithm;
    }

    @Test
    void sortSimpleTest() {
        Integer[] arrayDefault = { 10, 4, 5, 23, -45, 43, 100, 3, 3, 69, 3 };
        Integer[] arrayCustom = arrayDefault.clone();

        Arrays.sort(arrayDefault);
        assertFalse(Arrays.equals(arrayDefault, arrayCustom));

        sortAlgo.sortMePlease(arrayCustom);

        assertArrayEquals(arrayDefault, arrayCustom);
    }

    @Test
    void sortLargeArray() throws InterruptedException, ExecutionException {
        log.info("About to sort large arrays using custom " + sortAlgorithmName);
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
        List<CompletableFuture<Void>> jobs = new ArrayList<>();

        jobs.add(CompletableFuture
                .runAsync(sortTask, executorService));

        log.info("Waiting while we get the sorting result ...");

        CompletableFuture.allOf(jobs.toArray(CompletableFuture[]::new))
                .thenRunAsync(() -> {
            log.info(String.format("Running task on thread: %s", Thread.currentThread().getName()));
            var endTime = System.currentTimeMillis();
            var customSortRunTime = (endTime - startTime) / 1000;
            log.info(String.format("It took %d seconds for custom %s to run", customSortRunTime,
                    sortAlgorithmName));
        }, executorService)
                .handle((result, exception) -> {
                    if (exception != null) {
                        log.warning("Could not sort: " + exception);
                        return null;
                    }
                    log.info("Thread running: " + Thread.currentThread());
                    log.info("No exception encountered");
                    return result;
                })
                .join();
        assertArrayEquals(originalArray, copiedArray);
    }

    @Test
    void sortTinyLargeArray() {
        Integer[] arrayDefault = { 10, 2 };
        Integer[] arrayCustom = arrayDefault.clone();

        Arrays.sort(arrayDefault);
        assertFalse(Arrays.equals(arrayDefault, arrayCustom));

        sortAlgo.sortMePlease(arrayCustom);

        assertArrayEquals(arrayDefault, arrayCustom);
    }

    @Test
    void sortEmptyArrayTest() {
        Integer[] arrayDefault = {};
        Integer[] arrayCustom = arrayDefault.clone();

        Arrays.sort(arrayDefault);
        Arrays.equals(arrayDefault, arrayCustom);

        sortAlgo.sortMePlease(arrayCustom);

        assertArrayEquals(arrayDefault, arrayCustom);
    }

    @Test
    void sortNullArrayTest() {
        Integer[] array = null;

        sortAlgo.sortMePlease(array);

        assertEquals(array, null);
    }
}
