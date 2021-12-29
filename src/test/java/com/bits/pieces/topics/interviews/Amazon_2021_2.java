package com.bits.pieces.topics.interviews;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 2021 ~ Q2
 *
 * @author Nate Vardell
 * @since 8/24/2019
 */
public class Amazon2 {
    private static int[] twoSum(int[] nums, int target) {
        return null;
    }

    @Test
    public void testTwoSum() {
        int[] output = twoSum((new int[]{3, 4, 2, 7, 1, 8, 11, 15}), 9);
        assertThat(output.length, is(2));
    }
}
