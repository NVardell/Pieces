package com.bits.pieces.topics.leet;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

/**
 * HackerRank Practice Question ~ Kashable
 *
 * NAME - FizzBuzz
 * TOPICS -
 * COMPLEX -
 * URL -
 *
 * PROBLEM
 *      - Given number n, for each int 'i' in range from 1 to n inclusive, print one value per line as follows:
 *          • FizzBuzz - Multiple of both 3 & 5.
 *          • Fizz - Multiple of 3 (NOT 5).
 *          • Buzz - Multiple of 5 (NOT 3).
 *          • i - Multiple of neither 3 or 5.
 *
 * NOTES
 *      -
 *
 * CONSTRAINTS
 *      - 0 < n < 200,000
 *
 * EXAMPLE #1
 *      In ~ 15
 *      Out ~   1  -> 1
 *              2  -> 2
 *              3  -> Fizz
 *              4  -> 4
 *              5  -> Buzz
 *              6  -> Fizz
 *              8  -> 7
 *              8  -> 8
 *              9  -> Fizz
 *              10 -> Buzz
 *              11 -> 11
 *              12 -> Fizz
 *              13 -> 13
 *              14 -> 14
 *              15 -> FizzBuzz
 *      Note ~
 *          - The numbers 3, 6, 9, & 12 are multiples of 3(but not 5), so print Fizz on those lines.
 *          - The numbers 5 & 10 are multiples of 5 (but not 3), so print Buzz
 *          - The number 15 is a multiple of both 3 & 5, so print FizzBuzz
 *
 * @author NV
 * @since ${DATE}
 */
public class _0_HackerRank {

    @Test
    public void tests() {
        assertThat(solution(15), hasSize(15));
        assertThat(solution(15), hasItem("14"));
        assertThat(solution(15), contains("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"));
    }

    public List<String> solution(int x) {
        List<String> results = Lists.newArrayList();
        boolean flag3, flag5;

        for(int i=1; i<x+1; i++) {
            flag3 = i%3 == 0;
            flag5 = i%5 == 0;

            if(flag3 && flag5)
                results.add("FizzBuzz");
            else if(flag3)
                results.add("Fizz");
            else if(flag5)
                results.add("Buzz");
            else
                results.add(String.valueOf(i));
        }
        return results;
    }



    /**
     * Run if user wants to manually type number into stdin.
     * @param args User Input
     * @throws IOException Real Big Probs
     */
    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> results = Collections.emptyList();
        boolean flag3, flag5;

        for (int i = 1; i < n + 1; i++) {
            flag3 = i % 3 == 0;
            flag5 = i % 5 == 0;

            if (flag3 && flag5)
                results.add("FizzBuzz");
            else if (flag3)
                results.add("Fizz");
            else if (flag5)
                results.add("Buzz");
            else
                results.add(String.valueOf(i));
            bufferedReader.close();
        }
    }
}
