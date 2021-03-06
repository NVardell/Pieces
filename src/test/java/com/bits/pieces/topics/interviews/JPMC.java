package com.bits.pieces.topics.interviews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class JPMC {

    private static int[] start = new int[2];

    /**
     * Main Program Driver
     */
    public static void main(String[] args) throws IOException {
        // For each line in buffer - Add to String list.
        //    - Use size of list to set Height Value
        //    - Use String Length to set Width Value
        List<String> lines = new ArrayList<String>() {{
            add("###...##..");
            add("....#....#");
            add("#..###.#.#");
            add("S#.#.#.#..");
            add("...#...E#.");
        }};
//        Queue<String> lines = new LinkedList<>();
//        lines.add("###...##..");
//        lines.add("....#....#");
//        lines.add("#..###.#.#");
//        lines.add("S#.#.#.#..");
//        lines.add("...#...E#.");

        List<String> lines_trap = new ArrayList<String>() {{ add("#S#.E"); }}; // Expected: "Trapped"
        List<String> lines_short = new ArrayList<String>() {{ add("#S..E"); }}; // Expected: "#S**E"
        List<String> lines_medium = new ArrayList<String>() {{
            add("#S.#E");  // Expected: "#S*#E"
            add("##...");  // Expected: "##***"
        }};



        char[][] grid = createGrid(lines);
        getNeighbors(grid);
    }


    /**
     * Iterate through each line of input.
     * @return List of input strings
     */
    private static char[][] createGrid(List<String> lines) throws IOException {

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
}
