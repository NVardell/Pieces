package com.bits.pieces.other;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void modTests(){
//        System.out.println(24%2880);
//        System.out.println(2880%24);
//        System.out.println(69120%24);
//        System.out.println(69120%27);
//        System.out.println(69120%39);
//        System.out.println(69120%42);
//        System.out.println(69120%48);
//        System.out.println(69120%6);
//        System.out.println("\n");
//        System.out.println(maxDivisor(24));
//        System.out.println(maxDivisor(7));
        int x = 3;
//        System.out.println(24%x);
//        System.out.println(27%x);
//        System.out.println(39%x);
//        System.out.println(42%x);
//        System.out.println(48%x);
//        System.out.println(93%x);
//        System.out.println(108%x);
//        System.out.println(126%x);
//        System.out.println(138%x);
//        System.out.println(189%x);
//        System.out.println(201%x);
//        System.out.println(288%x);
//        System.out.println(423%x);
//        System.out.println(516%x);
//        System.out.println(633%x);
//        System.out.println(708%x);
//        System.out.println(1377%x);
//        System.out.println(2130%x);
//        System.out.println(2880%x);
        x=2;
        System.out.println(12%x);
        System.out.println(20%x);
        System.out.println(24%x);
        System.out.println(44%x);
        System.out.println(48%x);
        System.out.println(60%x);
        System.out.println(64%x);
        System.out.println(96%x);
        System.out.println(116%x);
        System.out.println(316%x);
        System.out.println(404%x);
        System.out.println(476%x);
        System.out.println(948%x);
        System.out.println(1380%x);
        System.out.println(2512%x);
        System.out.println(3392%x);
    }

    int maxDivisor(int v){

        int divisor = 0;

        for(int i=2; i<8; i++)
            // {
            if(v%i == 0)
                divisor=i;
        // }
        if(divisor%3==0)
            return 3;
        if(divisor%2==0)
            return 2;
        return divisor;
    }
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
