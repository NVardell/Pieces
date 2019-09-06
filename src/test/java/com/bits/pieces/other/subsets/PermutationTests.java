package com.bits.pieces.other.subsets;

import org.h2.util.Permutations;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 9/5/2019
 */
public class PermutationTests {

    @Test
    public void testFactorialMethod() {
        List<String> items = Arrays.asList("A", "B", "C");
        long permutations = factorial(items.size());
        assertThat(permutations, is(6L));

        /////////////////////////////////////////////////////////////////
        System.out.println(items + " can be combined in " + permutations + " different ways:");
        LongStream.range(0, permutations).forEachOrdered(i -> {
            System.out.println(i + ": " + permutation(i, items));
        });
        /////////////////////////////////////////////////////////////////
        of("A", "B", "C")
                .map(s -> s.collect(toList()))
                .forEachOrdered(System.out::println);
        /////////////////////////////////////////////////////////////////
        System.out.println("\n\nAbout to run some runnable tasks!");
        Runnable setup = () -> System.out.println("setup connection");
        Runnable send = () -> System.out.println("send data");
        Runnable close = () -> System.out.println("close connection");
        of(setup, send, close)
                .flatMap(Function.identity())
                .forEachOrdered(task -> {
                    System.out.println("Running new Runnable Task.  Name = " + Thread.currentThread().getName());
                    task.run();
                });
        System.out.println("All done running them tasks!\n\n");
        /////////////////////////////////////////////////////////////////
        of(
                "Get up",
                "Brush teeths",
                "Eat breakfast"
//                "Get dressed",
//                "Find wallet",
//                "Find computer"
        )
                .map(s -> s.collect(toList()))
                .forEach(System.out::println);
        /////////////////////////////////////////////////////////////////
        of("A", "B", "C").parallel().forEach(p -> printThreadInfo(p));
        /////////////////////////////////////////////////////////////////
        System.out.println(
                of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
                        .findFirst()
                        .get()
                        .collect(toList())
        );
    }

    ///////////////////////////////  PARALLEL STUFF
    private static <T> void printThreadInfo(Stream<T> s) {
        System.out.println(Thread.currentThread().getName() + " handles " + s.collect(toList()));
    }
    ///////////////////////////////



    private static long factorial(int n) {
        if (n > 20 || n < 0) throw new IllegalArgumentException(n + " is out of range");
        return LongStream.rangeClosed(2, n).reduce(1, (a, b) -> a * b);
    }

    private static <T> List<T> permutation(long no, List<T> items) {
        return permutationHelper(no,
                new LinkedList<>(Objects.requireNonNull(items)),
                new ArrayList<>());
    }

    private static <T> List<T> permutationHelper(long no, LinkedList<T> in, List<T> out) {
        if (in.isEmpty()) return out;
        long subFactorial = factorial(in.size() - 1);
        out.add(in.remove((int) (no / subFactorial)));
        return permutationHelper((int) (no % subFactorial), in, out);
    }

    @SafeVarargs
    @SuppressWarnings("varargs") // Creating a List from an array is safe
    private static <T> Stream<Stream<T>> of(T... items) {
        List<T> itemList = Arrays.asList(items);
        return LongStream.range(0, factorial(items.length))
                .mapToObj(no -> permutation(no, itemList).stream());
    }
}
