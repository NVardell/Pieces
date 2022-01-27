package com.bits.pieces.topics;

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
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 7/20/2019
 */
@Slf4j
public class ThreadTests {

    @Test
    public void givenMultiThread_whenNonSyncMethod() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        SyncMethod summation = new SyncMethod();
        IntStream.range(0, 5000).forEach(count -> service.submit(summation::calculate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        assertThat(summation.getSum(), is(not(5000)));
    }

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
    }

    @Test
    public void givenMultiThread_whenSyncMethod_2() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(8);
        SyncMethod summation = new SyncMethod();

        Instant start = Instant.now();
        IntStream.range(0, 100000).forEach(count -> service.submit(summation::syncCalculate));
        Instant finish = Instant.now();

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
//            System.out.println("Thread's name: " + Thread.currentThread().getName());
        }
    }

    private static void printElapsedTime(Instant start, Instant finish) {
        System.out.println("INSTANT - NANOS = " + ChronoUnit.NANOS.between(start, finish));
        System.out.println("INSTANT - MICROS = " + ChronoUnit.MICROS.between(start, finish));
        System.out.println("INSTANT - MILLI = " + ChronoUnit.MILLIS.between(start, finish));
    }






    @Test
    public void recursivePermutations(){
//        ArrayList<ArrayList<Integer>> uniquePerms = permuteUnique(new int[]{1, 2, 3});
//        uniquePerms.forEach(System.out::println);

//
//
//
//      PARALLEL PERMS -
//
//        R = Ways to choose
//        N = Number of elements
        int n = 3;
        int r = 3;
        List<int[]> combinations = generate1(n, r);
        for (int[] combination : combinations) {
            System.out.println(Arrays.toString(combination));
        }

        System.out.printf("generated %d combinations of %d items from %d ", combinations.size(), r, n);
    }


    private void helper1(List<int[]> combinations, int data[], int start, int end, int index) {
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
}
