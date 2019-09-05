package com.bits.pieces.other;

import lombok.Data;
import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
public class ThreadTests {

//    @Test
    public void givenMultiThread_whenNonSyncMethod() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        SyncMethod summation = new SyncMethod();
        IntStream.range(0, 5000).forEach(count -> service.submit(summation::calculate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        assertThat(summation.getSum(), is(5000));
    }

    @Test
    public void givenMultiThread_whenSyncMethod() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        SyncMethod summation = new SyncMethod();

        Instant start = Instant.now();
        IntStream.range(0, 500000).forEach(count -> service.submit(summation::syncCalculate));
        Instant finish = Instant.now();

        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        assertThat(summation.getSum(), is(5000));

        printElapsedTime(start, finish);
    }

    @Test
    public void givenMultiThread_whenSyncMethod_2() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);
        SyncMethod summation = new SyncMethod();

        Instant start = Instant.now();
        IntStream.range(0, 500000).forEach(count -> service.submit(summation::syncCalculate));
        Instant finish = Instant.now();

        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        assertThat(summation.getSum(), is(5000));

        printElapsedTime(start, finish);
    }

    @Data
    private class SyncMethod {
        int sum = 0;
        private void calculate() { sum += 1; }
        private synchronized void syncCalculate() {
            sum += 1;
            System.out.println("Thread's name: " + Thread.currentThread().getName());
        }
    }

    private static void printElapsedTime(Instant start, Instant finish) {
        System.out.println("INSTANT - NANOS = " + ChronoUnit.NANOS.between(start, finish));
        System.out.println("INSTANT - MICROS = " + ChronoUnit.MICROS.between(start, finish));
        System.out.println("INSTANT - MILLI = " + ChronoUnit.MILLIS.between(start, finish));
    }

}
