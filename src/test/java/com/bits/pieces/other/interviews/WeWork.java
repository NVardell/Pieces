package com.bits.pieces.other.interviews;

/**
 * Given a grid of integer values:
 *      02165
 *      56874
 *      32698
 *      45683
 *      23487
 *
 * Write a function that takes in an x & y coordinate
 * and return the sum of all of its neighbors.
 *
 * NOTE : Only sum neighbor cells
 *
 * EXAMPLES
 *      0,0   ->   13
 *      1,1   ->   13
 *      2,2   ->   13
 *      3,3   ->   13
 *      4,4   ->   13
 *
 * @author Nate Vardell
 * @since 9/10/2019
 */
public class WeWork {

    private static final int[] changeInX = new int[] {-1, 0, 1};
    private static final int[] changeInY = new int[] {-1, 0, 1};

    private static int[][] grid = new int[][] {
            {0, 2, 1, 6, 5},
            {5, 6, 8, 7, 4},
            {3, 2, 6, 9, 8},
            {4, 5, 6, 8, 3},
            {2, 3, 4, 8, 7}
    };



    public static void main(String... args) {

    }

}
