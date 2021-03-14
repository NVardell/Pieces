package com.bits.pieces.practice.structures;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class Tester {

    private static int[] start = new int[2];
    private static int[] finish = new int[2];

    /**
     * Main Program Driver
     */
    public static void main(String[] args) throws IOException {
        getGrid();
    }


    /**
     * Iterate through each line of input.
     */
    private static void getGrid() throws IOException {
        // InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        // BufferedReader in = new BufferedReader(reader);

        // For each line in buffer - Add to String list.
        //    - Use size of list to set Height Value
        //    - Use String Length to set Width Value
        Queue<String> lines = new LinkedList<>();
        lines.add("###...##..");
        lines.add("....#....#");
        lines.add("#..###.#.#");
        lines.add("S#.#.#.#..");
        lines.add("...#...E#.");
//        List<String> lines = Arrays.asList(
//                "###...##..",
//                "....#....#",
//                "#..###.#.#",
//                "S#.#.#.#..",
//                "...#...E#.");

        int l = lines.size();
        System.out.println("Length = " + l);
        assert lines.peek() != null;
        int w = lines.peek().length();
        System.out.println("Width = " + w);
        char[][] grid = new char[l][w];

        int x = 0;
        while(lines.peek() != null) {
            String line = lines.poll();
            for (int y = 0; y < w; y++) {
                char c = line.charAt(y);
                grid[x][y] = c;

                if(c=='S') {
                    start[0]=x;start[1]=y;
                } else if(c=='E')

            }
            x++;
        }

        printGrid(grid, l, w);

    }

    private static void printGrid(char[][] grid, int l, int w) {
        for(int x=0; x<l; x++) {
            for (int y = 0; y < w; y++)
                System.out.print(grid[x][y]);
            System.out.println();
        }
    }
}
