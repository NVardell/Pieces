package com.bits.pieces.topics.temp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 6/30/2019
 */
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
@Slf4j
public class Temp {


    @Test
    public void testSearchRange() {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 7)));
    }

    int low = -1;
    int high = -1;

    public int[] searchRange(int[] nums, int target) {
        int halfway = nums.length / 2;
        return new int[]{low, high};
    }

    @Test
    public void testInts() {
        int[] ia = new int[]{5, 7, 7, 8, 8, 10};
        int half = ia.length / 2;
        System.out.println(ia.length);
        System.out.println(half);

        int[] ha = Arrays.copyOfRange(ia, 0, half);
        System.out.println(Arrays.toString(ha));
        int[] ha2 = Arrays.copyOfRange(ia, half, ia.length);
        System.out.println(Arrays.toString(ha2));
    }

    @Test
    public void testString() {
        String temp = "asdf-adsf-";
        System.out.println(temp);
        temp = temp.trim();
        System.out.println(temp);
    }

    @Test
    public void testAdd() {
        List<Integer> indexes = Arrays.asList(0);
        indexes.add(2);
        System.out.println(indexes);
    }

    @Test
    public void testAdd_2() {
        List<Integer> indexes = new ArrayList() {{
            add(2);
        }};
        indexes.add(2);
        System.out.println(indexes);
    }

    @Test
    public void testAdd_3() {
        List<Integer> indexes = Collections.emptyList();
        indexes.add(2);
        System.out.println(indexes);
    }

    @Test
    public void random() {
        List<Integer> indexes = Arrays.asList(2, 4, 6, 17, 632, 18, 12, 19, 234, 21, 61, 21, 63, 22, 12, 23, 11, 14, 15, 16);

        IntStream.range(0, indexes.size()).forEach( x ->
                System.out.println(indexes.get(new Random().nextInt(indexes.size())))
        );
    }

    @Test
    public void testRepeatingString() {
        String str = "helloslkhellodjladfjhello";
        String findStr = "hello";
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

            lastIndex = str.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }
        System.out.println(count);
        System.out.println(StringUtils.countMatches(str, findStr));
    }
}
