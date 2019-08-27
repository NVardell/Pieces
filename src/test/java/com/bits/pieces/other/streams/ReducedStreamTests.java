package com.bits.pieces.other.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
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
@Slf4j
public class ReducedStreamTests {

    @Test
    public void streamReduceWithoutParallel() {
        OptionalInt reduced = IntStream.range(1, 4)
                .reduce((a, b) -> a + b);
        assertThat(reduced.isPresent(), is(true));
        assertThat(reduced.getAsInt(), is(6));

        int reducedTwoParams = IntStream.range(1, 4)
                .reduce(10, (a, b) -> a + b);
        assertThat(reducedTwoParams, is(16));

        int reducedParallel = Arrays.asList(1, 2, 3).parallelStream()
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    log.info("combiner was called");
                    return a + b;
                });
        assertThat(reducedParallel, is(36));
    }
}
