package com.bits.pieces.other;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    public void testSearchRange(){
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10}, 7)));
    }

    int low = -1;
    int high = -1;

    public int[] searchRange(int[] nums, int target) {
        int halfway = nums.length / 2;
        return new int[]{low, high};
    }

    @Test
    public void testInts(){
        int[] ia = new int[] {5,7,7,8,8,10};
        int half = ia.length / 2;
        System.out.println(ia.length);
        System.out.println(half);

        int[] ha = Arrays.copyOfRange(ia, 0, half);
        System.out.println(Arrays.toString(ha));
        int[] ha2 = Arrays.copyOfRange(ia, half, ia.length);
        System.out.println(Arrays.toString(ha2));
    }

    @Test
    public void testString(){
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
        List<Integer> indexes = new ArrayList(){{add(2);}};
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
        List<Integer> indexes = new ArrayList(){{add(2); add(4);}};
        indexes.add(6); indexes.add(17);
        indexes.add(632); indexes.add(18);
        indexes.add(12); indexes.add(19);
        indexes.add(234); indexes.add(20);
        indexes.add(61); indexes.add(21);
        indexes.add(63); indexes.add(22);
        indexes.add(12); indexes.add(23);
        indexes.add(11);
        indexes.add(14);
        indexes.add(15);
        indexes.add(16);
        System.out.println(indexes);
        int randomIndex = new Random().nextInt(indexes.size());
        System.out.println(randomIndex);
        randomIndex = new Random().nextInt(indexes.size());
        System.out.println(randomIndex);
        randomIndex = new Random().nextInt(indexes.size());
        System.out.println(randomIndex);
        randomIndex = new Random().nextInt(indexes.size());
        System.out.println(randomIndex);
        randomIndex = new Random().nextInt(indexes.size());
        System.out.println(randomIndex);
        randomIndex = new Random().nextInt(indexes.size());
        System.out.println(randomIndex);
        randomIndex = new Random().nextInt(indexes.size());
        System.out.println(randomIndex);
        randomIndex = new Random().nextInt(indexes.size());
        System.out.println(randomIndex);
        randomIndex = new Random().nextInt(indexes.size());
        System.out.println(randomIndex);
        randomIndex = new Random().nextInt(indexes.size());
        System.out.println(randomIndex);
        randomIndex = new Random().nextInt(indexes.size());
        System.out.println(randomIndex);
        randomIndex = new Random().nextInt(indexes.size());
        System.out.println(randomIndex);
    }

    @Test
    public void testRepeatingString(){
        String str = "helloslkhellodjladfjhello";
        String findStr = "hello";
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){

            lastIndex = str.indexOf(findStr,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
            }
        }
        System.out.println(count);
        System.out.println(StringUtils.countMatches(str, findStr));
    }
}
