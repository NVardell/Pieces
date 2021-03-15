package com.bits.pieces.practice.structures;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 */
public class T2 {

    private static int[] start = new int[2];
    int[][] directions={{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    /**
     * Main Program Driver
     */
    public static void main(String[] args) throws IOException {
        // For each line in buffer - Add to String list.
        //    - Use size of list to set Height Value
        //    - Use String Length to set Width Value

        // Test #1
        List<String> lines = new ArrayList<String>() {{ add("###...##.."); add("....#....#"); add("#..###.#.#"); add("S#.#.#.#.."); add("...#...E#.");  }}; List<String> lines_expected = new ArrayList<String>() {{  add("###***##.."); add("..**#**..#"); add("#.*###*#.#"); add("S#*#.#*#.."); add("***#..*E#.");  }};
        // Trap     List<String> lines = new ArrayList<String>() {{ add("#S#.E"); }}; List<String> lines_expected = new ArrayList<String>() {{ add("Trapped"); }};
        // Short    List<String> lines = new ArrayList<String>() {{ add("#S..E"); }}; List<String> lines_expected = new ArrayList<String>() {{ add("#S**E"); }};
        // Medium   List<String> lines = new ArrayList<String>() {{  add("#S.#E"); add("##..."); }}; List<String> lines_expected = new ArrayList<String>() {{  add("#S*#E"); add("##***"); }};


        printLines(lines);
        validateLines(lines, lines_expected);

        char[][] grid = createGrid(lines);
        getNeighbors(grid);
    }



    /**
     * Iterate through each line of input.
     * @return List of input strings
     */
    private static char[][] createGrid(List<String> lines) {

        int l = lines.size(); System.out.println("Length = " + l);
        int w = lines.get(0).length(); System.out.println("Width = " + w);
        char[][] grid = new char[l][w];

        int x = 0;
        for(String currentLine : lines) {
            for (int y = 0; y < w; y++) {
                char c = currentLine.charAt(y);
                grid[x][y] = c;

                if(c=='S') {
                    start[0] = x;
                    start[1] = y;
                }
            }
            x++;
        }

        printGrid(grid, l, w);
        return grid;
    }


    private static void getNeighbors(char[][] grid) {

    }



    private static void printGrid(char[][] grid, int l, int w) {
        for(int x=0; x<l; x++) {
            for (int y = 0; y < w; y++)
                System.out.print(grid[x][y]);
            System.out.println();
        }
    }

    private static void printLines(List<String> lines) {

    }

    private static void validateLines(List<String> lines, List<String> lines_expected) {
        assertThat(lines.size(), is(lines_expected.size()));

        for(String line : lines)
            assertThat(lines_expected, hasItem(line));
    }
    // ""
    // ""
    // ""
    // ""
    // ""
}
