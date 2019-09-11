package com.bits.pieces.other.interviews;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

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

    private static int[][] grid = new int[][] {
            {0, 2, 1, 6, 5},
            {5, 6, 8, 7, 4},
            {3, 2, 6, 9, 8},
            {4, 5, 6, 8, 3},
            {2, 3, 4, 8, 7}
    };

    private static final int[] changeInX = new int[] {-1, 0, 1};
    private static final int[] changeInY = new int[] {-1, 0, 1};

    private static int columns = grid[0].length;
    private static int rows = grid.length;

    @Test
    public void validateSumOfNeighbors() {
        assertThat(sumOfNeighbors(0, 0), is(13)); // 2 + 6 + 5 = 13
        assertThat(sumOfNeighbors(1, 1), is(27)); // 0 + 2 + 1 + 5 + 8 + 3 + 2 + 6 = 27
        assertThat(sumOfNeighbors(2, 2), is(51)); // 6 + 8 + 7 + 2 + 9 + 5 + 6 + 8 = 51
        assertThat(sumOfNeighbors(3, 3), is(51)); // 6 + 9 + 8 + 6 + 3 + 4 + 8 + 7 = 51
        assertThat(sumOfNeighbors(4, 4), is(19)); // 8 + 8 + 3 = 19
    }

    private int sumOfNeighbors(int x, int y) {
        int sum = 0;

        for(int changeX : changeInX) {
            for(int changeY : changeInX) {
                int newX = x + changeX;
                int newY = x + changeY;

                if(isOnMap(newX, newY)) {
                    sum += grid[newX][newY];
                }
            }
        }

        return sum - grid[x][y];
    }

    private boolean isOnMap(int x, int y) {
        return x >= 0 && y >= 0 && x < columns && y < rows;
    }
}
