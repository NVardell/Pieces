package com.bits.pieces.topics.subsets;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.paukov.combinatorics3.Generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 9/7/2019
 */
public class MyComboTest {

    private static final int n = 47;
    private static final int r = 8;
    private List<Integer> comboSums;
    private List<int[]> combinations;

    private static final int[] fullCardSet = new int[]{
            26000000, 26000001, 26000002, 26000003, 26000004, 26000005, 26000006, 26000007, 26000008, 26000009, 26000010, 26000011, 26000012, 26000013, 26000014, 26000015, 26000016, 26000017, 26000018, 26000019,
            26000020, 26000021, 26000022, 26000023, 26000024, 26000025, 26000026, 26000027, 26000028, 26000029, 26000030, 26000031, 26000032, 26000033, 26000034, 26000035, 26000036, 26000037, 26000038, 26000039,
            26000040, 26000041, 26000042, 26000043, 26000044, 26000045, 26000046, 26000047, 26000048, 26000049, 26000050, 26000051, 26000052, 26000053, 26000054, 26000055, 26000056, 26000057, 26000058, 26000059,
            26000060, 26000061, 26000062, 26000063, 27000000, 27000001, 27000002, 27000003, 27000004, 27000005, 27000006, 27000007, 27000008, 27000009, 27000010, 27000012, 28000000, 28000001, 28000002, 28000003,
            28000004, 28000005, 28000006, 28000007, 28000008, 28000009, 28000010, 28000011, 28000012, 28000013, 28000014, 28000015, 28000016, 28000017
    };

    private static final int[] halfCardSet = new int[]{
            26000000, 26000001, 26000002, 26000003, 26000004, 26000005, 26000006, 26000007, 26000008, 26000009, 26000010, 26000011, 26000012, 26000013, 26000014,
            26000015, 26000016, 26000017, 26000018, 26000019, 26000020, 26000021, 26000022, 26000023, 26000024, 26000025, 26000026, 26000027, 26000028, 26000029,
            26000030, 26000031, 26000032, 26000033, 26000034, 26000035, 26000036, 26000037, 26000038, 26000039, 26000040, 26000041, 26000042, 26000043, 26000044,
            26000045, 26000046
    };

    private Set<Integer> halfSet = ImmutableSet.of(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46);

    @Test
    public void apacheCommonsUtil() {
        Iterator<int[]> iterator = CombinatoricsUtils.combinationsIterator(n, r);
        int count = 0;
        while (iterator.hasNext()) {
            final int[] combination = iterator.next();
            count++;
        }
        System.out.println("Count = " + count);
    }


    @Test
    public void googleGuava(){  // FASTEST - HANDS DOWN
        Set<Set<Integer>> combinations = Sets.combinations(halfSet, r);
        System.out.println(combinations.size());
    }

    @Test
    public void combinatoricsLib(){
        System.out.println(Generator.combination(halfSet)
                .simple(r)
                .stream()
                .count());
    }


    @Test
    @Ignore
    public void iterativeApproach(){
        comboSums = new ArrayList<>();
        combinations = iterativeAlgorithm(n, r);
        combinations.forEach(combo -> System.out.println(Arrays.toString(combo)));
        comboSums.forEach(System.out::println);
    }
    private List<int[]> iterativeAlgorithm(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        int[] combination = new int[r];

        // initialize with lowest lexicographic combination
        for (int i = 0; i < r; i++) {
            combination[i] = i;
        }

        while (combination[r - 1] < n) {
            combinations.add(combination.clone());
            comboSums.add(combination[0] + combination[1] + combination[2]);

            // generate next combination in lexicographic order
            int t = r - 1;
            while (t != 0 && combination[t] == n - r + t) {
                t--;
            }
            combination[t]++;
            for (int i = t + 1; i < r; i++) {
                combination[i] = combination[i - 1] + 1;
            }
        }

        return combinations;
    }



    @Test
    @Ignore
    public void recursivePartitionOfElements_trackSelectedItems(){
        comboSums = new ArrayList<>();
        combinations = new ArrayList<>();
        recursivePartitioning(combinations, new int[r], 0, n - 1, 0);
        combinations.forEach(combo -> System.out.println(Arrays.toString(combo)));
        comboSums.forEach(System.out::println);
    }
    // This approach can work for large inputs so long as the number of elements to be selected is less than the maximum call stack depth.
    // If the number of elements to be chosen is also large, this method won't work.
    private void recursivePartitioning(List<int[]> combinations, int data[], int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
            comboSums.add(combination[0] + combination[1] + combination[2]);
        } else {
            int max = Math.min(end, end + 1 - data.length + index);
            for (int i = start; i <= max; i++) {
                data[index] = i;
                recursivePartitioning(combinations, data, i + 1, end, index + 1);
            }
        }
    }
}
