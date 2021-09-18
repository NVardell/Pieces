package com.bits.pieces.topics.leet;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * NAME - Wiggle Sort 2
 * TOPICS - Array, Divide & Conquer, Sorting, Quick Select
 * COMPLEX - Medium
 * URL - https://leetcode.com/problems/wiggle-sort-ii/
 *
 * PROBLEM
 *      Given int array 'n', sort it from min to max.
 *      Ex.  n[0]  <  n[1]  >  n[2]  <  n[3].
 *
 * CONSTRAINTS
 *      - 1 <= nums.length <= 5 * 10^4
 *      - 0 <= nums[i] <= 5000
 *
 * EXAMPLE #1
 *      In ~ nums = [1,5,1,1,6,4]
 *      Out ~ [1,6,1,5,1,4]
 *      Note ~ [1,4,1,5,1,6] is also accepted.
 *
 * @author NV
 * @since ${DATE}
 */
public class _324_WiggleSort2 {

    @Test
    public void tests() {
        assertThat(solution((new int[] { 1, 2, 3, 4, 5 })), is(-1));
    }

    public int solution(int[] nums) {
        return -1;
    }
}
