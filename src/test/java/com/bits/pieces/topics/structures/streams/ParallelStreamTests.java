package com.bits.pieces.topics.structures.streams;

import org.junit.Test;

import java.util.OptionalInt;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/26/2019
 */
public class ParallelStreamTests {

    @Test
    public void streamReduceWithoutParallel() {
        OptionalInt reduced = IntStream.range(1, 4)
                .reduce((a, b) -> a + b);
        assertThat(reduced, is(6));

        int reducedTwoParams = IntStream.range(1, 4)
                .reduce(10, (a, b) -> a + b);
    }
}
