package com.bits.pieces.topics.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * NAME - 253. Meeting Rooms II
 * TOPICS - Heap, Sort, Greedy
 * URL - https://leetcode.com/problems/meeting-rooms-ii/
 * - https://leetcode.com/problems/meeting-rooms-ii/submissions/
 * <p>
 * PROBLEM
 * Given an array of meeting time intervals where intervals[i] = [starti, endi]
 * Then return the minimum number of conference rooms required.
 * <p>
 * EXAMPLE #1
 * Input - intervals = [[0,30],[5,10],[15,20]]
 * Output - 2
 * <p>
 * EXAMPLE #2
 * Input - intervals = [[7,10],[2,4]]
 * Output - 1
 * <p>
 * NOTES
 * - 1 <= intervals.length <= 10^4 (10,000)
 * - 0 <= starti < endi <= 10^6 (1,000,000)
 *
 * @author NV
 * @since 5/17/2021
 */
public class T7 {

    private static final int[] expected = new int[] {77, 2, 1, 1, 4, 1, 2, 2, 2};
    private static final List<int[][]> inputs = new ArrayList<int[][]>(){{
        add(new int[][] {{64738, 614406}, {211748, 780229}, {208641, 307338}, {499908, 869489}, {218907, 889449}, {177201, 481150}, {123679, 384415}, {120440, 404695}, {191810, 491295}, {800783, 826206}, {165175, 221995}, {420412, 799259}, {484168, 617671}, {746410, 886281}, {765198, 792311}, {493853, 971285}, {194579, 313372}, {119757, 766274}, {101315, 917883}, {557309, 599256}, {167729, 723580}, {731216, 988021}, {225883, 752657}, {588461, 854166}, {231328, 285640}, {772811, 869625}, {892212, 973218}, {143535, 306402}, {336799, 998119}, {65892, 767719}, {380440, 518918}, {321447, 558462}, {54489, 234291}, {43934, 44986}, {11260, 968186}, {248987, 707178}, {355162, 798511}, {661962, 781083}, {149228, 412762}, {71084, 953153}, {44890, 655659}, {708781, 956341}, {251847, 707658}, {650743, 932826}, {561965, 814428}, {697026, 932724}, {583473, 919161}, {463638, 951519}, {769086, 785893}, {17912, 923570}, {423089, 653531}, {317269, 395886}, {412117, 701471}, {465312, 520002}, {168739, 770178}, {624091, 814316}, {143729, 249836}, {699196, 879379}, {585322, 989087}, {501009, 949840}, {424092, 580498}, {282845, 345477}, {453883, 926476}, {392148, 878695}, {471772, 771547}, {339375, 590100}, {110499, 619323}, {8713, 291093}, {268241, 283247}, {160815, 621452}, {168922, 810532}, {355051, 377247}, {10461, 488835}, {220598, 261326}, {403537, 438947}, {221492, 640708}, {114702, 926457}, {166567, 477230}, {856127, 882961}, {218411, 256327}, {184364, 909088}, {130802, 828793}, {312028, 811716}, {294638, 839683}, {269329, 343517}, {167968, 391811}, {25351, 369583}, {210660, 454598}, {166834, 576380}, {296440, 873280}, {660142, 822072}, {33441, 778393}, {456500, 955635}, {59220, 954158}, {306295, 429913}, {110402, 448322}, {44523, 88192}, {231386, 353197}, {120940, 902781}, {348758, 597599}, {329467, 664450}, {208411, 890114}, {230238, 516885}, {434113, 602358}, {349759, 419831}, {10689, 308144}, {94526, 180723}, {435280, 986655}, {611999, 690154}, {75208, 395348}, {403243, 489260}, {498884, 611075}, {487209, 863242}, {13900, 873774}, {656706, 782943}, {53478, 586952}, {226216, 723114}, {554799, 922759}, {467777, 689913}, {80630, 147482}, {277803, 506346}, {532240, 976029}, {206622, 761192}, {148277, 985819}, {10879, 807349}, {952227, 971268}, {172074, 919866}, {239230, 384499}, {607687, 984661}, {4405, 264532}, {41071, 437502}, {432603, 661142}, {144398, 907360}, {139605, 360037}, {943191, 997317}, {12894, 171584}, {382477, 800157}, {452077, 518175}, {208007, 398880}, {375250, 489928}, {384503, 726837}, {278181, 628759}, {114470, 635575}, {382297, 733713}, {156559, 874172}, {507016, 815520}, {164461, 532215}, {17332, 536971}, {418721, 911117}, {11497, 14032}});
        add(new int[][] {{0, 30}, {15, 20}, {5, 10}}); add(new int[][]{{7, 10}, {2, 4}}); add(new int[][]{{0, 5}, {6, 10}, {15, 20}, {25, 26}, {27, 30}}); add(new int[][]{{0, 30}, {5, 25}, {10, 20}, {15, 19}, {35, 40}}); add(new int[][]{{1, 5}, {5, 10}}); add(new int[][]{{1, 5}, {8, 9}, {8, 9}}); add(new int[][]{{9, 14}, {5, 6}, {3, 5}, {12, 19}}); add(new int[][]{{0, 30}, {5, 10}, {15, 20}, {10, 15}});
    }};

    public static void main(String[] args) {
        for(int i=0; i<inputs.size(); i++) {
            System.out.println("TEST CASE #" + i+1); assertThat(solution(inputs.get(i)), is(expected[i]));
        }
    }

    private static int solution(int[][] intervals) {

        System.out.println("Intervals = " + Arrays.deepToString(intervals));
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        System.out.println("Sorted Intervals = " + Arrays.deepToString(intervals));

        int starti, endi;
        boolean intervalAdded;
        List<int[]> rooms = new ArrayList<int[]>();

        if (intervals.length < 1 || intervals.length > 10000)
            return 0;

        for (int[] interval : intervals) {

            starti = interval[0];
            endi = interval[1];
            intervalAdded = false;

            if (starti < 0 || starti > endi || endi > 1000000)
                return rooms.size();

            if (rooms.size() == 0) {
                rooms.add(new int[]{starti, endi});
                System.out.println("Rooms List Empty - Creating Room #1 \n\t Adding Initial Intervals [" + starti + ", " + endi + "]");
                System.out.println("\t\t\t\tADDED NEW ROOM #1\n\n\n");
            }
            else {
                System.out.println("\n\n====================================================================");
                for(int[] x : rooms) {
                    System.out.println(Arrays.toString(x));
                }
                rooms.sort(new Comparator<int[]>() {
                    public int compare(int[] a, int[] b) {
                        return (Integer) (a[a.length - 1]) - (b[b.length - 1]);
                    }
                });
                for(int[] x : rooms) {
                    System.out.println(Arrays.toString(x));
                }
                System.out.println("====================================================================\n\n");
                for (int j = 0; j < rooms.size(); j++) {
                    int[] roomIntervals = rooms.get(j);

                    System.out.println("Searching Slot #" + j + "'s " + roomIntervals.length +" INTERVALS = " + Arrays.toString(roomIntervals));
                    System.out.println("\t For new interval = [" + starti + ", " + endi + "]");

                    for (int x = 0; x < roomIntervals.length; x += 2) {

                        int roomStart = roomIntervals[x], roomEnd = roomIntervals[x + 1];

                        // New Meeting Interval Starts/Ends Before Current Meeting
                        if (starti >= roomEnd || endi <= roomStart) {
                            System.out.println("\t\t\tNEW TIME INTERVAL FITS CURRENT MEETING TIMES");

                            // Validate all intervals have been checked, and add new intervals.
                            if (roomIntervals.length - x == 2) {
                                // Create new int array to hold the additional times
                                int[] newIntervals = new int[roomIntervals.length + 2];
                                // Copy old interval list over to new list
                                System.arraycopy(roomIntervals, 0, newIntervals, 0, roomIntervals.length);
                                // Set the new meeting interval values in the last 2 indexes of the new array
                                newIntervals[roomIntervals.length] = starti;
                                newIntervals[roomIntervals.length + 1] = endi;
                                // Update the current room with the new time intervals array
                                rooms.set(j, newIntervals);
                                // Update flag
                                intervalAdded = true;

                                System.out.println("\t\t\t\t ADDING TO EXISTING ROOM [" + j + "] = " + Arrays.toString(newIntervals) + "\n\n\n");
                            }
                        } else
                            // Current meeting interval time has an overlap; no need to process remaining time intervals.
                            break;

                    }

                }

                // New meeting overlapped with existing times; adding a new room to the list.
                if (!intervalAdded) {
                    rooms.add(new int[]{starti, endi});
                    System.out.println("\t\t\t\tADDED NEW ROOM #" + rooms.size() + "\n\n\n");
                }
            }

        }
        System.out.println("\n\n\n\n\n\nFINISHED ===================================================");
        System.out.println("Final Rooms Array = ");
        rooms.forEach(room -> {
            System.out.println(Arrays.toString(room));
        });
        return rooms.size();
    }
}
