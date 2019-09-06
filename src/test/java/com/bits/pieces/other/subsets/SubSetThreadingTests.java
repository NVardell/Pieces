package com.bits.pieces.other.subsets;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 7/20/2019
 */
@Slf4j
public class SubSetThreadingTests {


    @Test
    public void givenMultiThread_whenSyncMethod() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        SyncMethod summation = new SyncMethod();

        Instant start = Instant.now();
        IntStream.range(0, 100000).forEach(count -> service.submit(summation::syncCalculate));
        Instant finish = Instant.now();

        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        assertThat(summation.getSum(), is(100000));

        printElapsedTime(start, finish);

        ExecutorService svc = Executors.newFixedThreadPool(8);
        SyncMethod sum = new SyncMethod();

        start = Instant.now();
        IntStream.range(0, 100000).forEach(count -> svc.submit(sum::syncCalculate));
        finish = Instant.now();

        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        assertThat(summation.getSum(), is(100000));

        printElapsedTime(start, finish);
    }


    @Data
    private class SyncMethod {
        int sum = 0;
        private void calculate() { sum += 1; }
        private synchronized void syncCalculate() {
            sum += 1;
            sum -= 1;
            sum += 1;
            sum -= 1;
            sum += 1;
            log.info(".");
        }
    }

    private static void printElapsedTime(Instant start, Instant finish) {
        System.out.println("INSTANT - NANOS = " + ChronoUnit.NANOS.between(start, finish));
        System.out.println("INSTANT - MICROS = " + ChronoUnit.MICROS.between(start, finish));
        System.out.println("INSTANT - MILLI = " + ChronoUnit.MILLIS.between(start, finish));
    }






    @Test
    public void recursivePermutations(){
        int n = 94;  //   N = Number of elements
        int r = 8;  //   R = Ways to choose
//        List<int[]> combinations = generate1(n, r);
        List<int[]> combinations = new ArrayList<>();
        helper1(combinations, new int[r], 0, n-1, 0);
//        combinations.forEach(combo -> System.out.println(Arrays.toString(combo)));

        System.out.printf("Generated %d combination(s) of %d items from %d.", combinations.size(), r, n);
    }
    private void helper1(List<int[]> combinations, int[] data, int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            helper1(combinations, data, start + 1, end, index + 1);
            helper1(combinations, data, start + 1, end, index);
        }
    }
    private List<int[]> generate1(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        helper1(combinations, new int[r], 0, n-1, 0);
        return combinations;
    }




    @Test
    public void givenSetAndSelectionSize_whenCalculatedUsingIterativeAlgorithm_thenExpectedCount() {
        int n = 5;  //   N = Number of elements
        int r = 3;  //   R = Ways to choose

        List<int[]> selection = generateIterative(n, r);
        assertThat(selection.size(), is(10));
    }
    private List<int[]> generateIterative(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        int[] combination = new int[r];

        // initialize with lowest lexicographic combination
        for (int i = 0; i < r; i++) {
            combination[i] = i;
        }

        while (combination[r - 1] < n) {
            combinations.add(combination.clone());

            // generate next combination in lexicographic order
            int t = r - 1;
            while (t != 0 && combination[t] == n - r + t) {
                t--;
            }
            combination[t]++;
            for (int i = t + 1; i < r; i++) {
                combination[i] = combination[i - 1] + 1;
            }
        }

        return combinations;
    }
}
