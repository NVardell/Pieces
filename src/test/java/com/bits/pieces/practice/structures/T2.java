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

    private static int width;
    private static int length;
    private static boolean[][] visited;
    private static int[] start = new int[2];
    private static final int[][] directions={{0, 1}, {0, -1}, {-1, 0}, {1, 0}};


    /**
     * Main Program Driver
     */
    public static void main(String[] args) throws IOException {
        // For each line in buffer - Add to String list.
        //    - Use size of list to set Height Value
        //    - Use String Length to set Width Value

        // Input = Output
        List<String> lines = new ArrayList<String>() {{ add("###...##.."); add("....#....#"); add("#..###.#.#"); add("S#.#.#.#.."); add("...#...E#.");  }}; List<String> lines_expected = new ArrayList<String>() {{ add("###...##.."); add("....#....#"); add("#..###.#.#"); add("S#.#.#.#.."); add("...#...E#.");  }};
        // Example  List<String> lines = new ArrayList<String>() {{ add("###...##.."); add("....#....#"); add("#..###.#.#"); add("S#.#.#.#.."); add("...#...E#.");  }}; List<String> lines_expected = new ArrayList<String>() {{  add("###***##.."); add("..**#**..#"); add("#.*###*#.#"); add("S#*#.#*#.."); add("***#..*E#.");  }};
        // Trap     List<String> lines = new ArrayList<String>() {{ add("#S#.E"); }}; List<String> lines_expected = new ArrayList<String>() {{ add("Trapped"); }};
        // Short    List<String> lines = new ArrayList<String>() {{ add("#S..E"); }}; List<String> lines_expected = new ArrayList<String>() {{ add("#S**E"); }};
        // Medium   List<String> lines = new ArrayList<String>() {{  add("#S.#E"); add("##..."); }}; List<String> lines_expected = new ArrayList<String>() {{  add("#S*#E"); add("##***"); }};


        findPath(createGrid(lines));


        validateLines(lines, lines_expected);

    }

    private static void findPath(char[][] grid) {
        visited = new boolean[grid.length][grid[0].length];
        visited[start[0]][start[1]] = true;
        System.out.println("Grid Length (↓) - " + grid.length); System.out.println("Grid Width  (→) - " + grid[0].length);

    }


    /**
     * Iterate through each line of input.
     * @return List of input strings
     */
    private static char[][] createGrid(List<String> lines) {

        length = lines.size(); System.out.println("Length = " + length);
        width = lines.get(0).length(); System.out.println("Width = " + width);
        char[][] grid = new char[length][width];

        int x = 0;
        for(String currentLine : lines) {
            for (int y = 0; y < width; y++) {
                char c = currentLine.charAt(y);
                grid[x][y] = c;

                if(c=='S') {
                    start[0] = x;
                    start[1] = y;
                }
            }
            x++;
        }

        printGrid(grid, length, width);
        return grid;
    }


    private static List<int[]> getNeighbors(int row, int col, char[][] grid) {
        List<int[]> neighbors = new ArrayList<>();

        for(int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if(newRow < 0
                    || newCol < 0
                    || newRow >= grid.length
                    || newCol >= grid[0].length
                    || grid[newRow][newCol] != 0 )
                continue;

            neighbors.add(new int[]{newRow, newCol});
            System.out.println(neighbors);
        }

        System.out.print("Returning Neighbors List - "); System.out.println(neighbors);
        return neighbors;
    }



    private static void printGrid(char[][] grid, int l, int w) {
        for(int x=0; x<l; x++) {
            for (int y = 0; y < w; y++)
                System.out.print(grid[x][y]);
            System.out.println();
        }
    }
    private static void printLines(List<String> lines) {
        for(String line : lines)
            System.out.println(line);
    }
    private static void validateLines(List<String> lines, List<String> lines_expected) {
        assertThat(lines.size(), is(lines_expected.size()));

        for(String line : lines)
            assertThat(lines_expected, hasItem(line));
    }

}
