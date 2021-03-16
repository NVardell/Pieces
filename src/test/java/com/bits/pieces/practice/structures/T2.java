package com.bits.pieces.practice.structures;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 */
public class T2 {

    private static int width;
    private static int length;
    private static final int[] start = new int[2];
    private static final int[] finish = new int[2];
    private static final int[][] directions={{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    private static List<String> lines;
    private static Map<String, int[]> parentNodes;


    /**
     * Main Program Driver
     */
    public static void main(String[] args) throws IOException {
        // For each line in buffer - Add to String list.
        //    - Use size of list to set Height Value
        //    - Use String Length to set Width Value

        // Input = Output
//        List<String> lines = new ArrayList<String>() {{ add("###...##.."); add("....#....#"); add("#..###.#.#"); add("S#.#.#.#.."); add("...#...E#.");  }}; List<String> lines_expected = new ArrayList<String>() {{ add("###...##.."); add("....#....#"); add("#..###.#.#"); add("S#.#.#.#.."); add("...#...E#.");  }};
        // Example  List<String> lines = new ArrayList<String>() {{ add("###...##.."); add("....#....#"); add("#..###.#.#"); add("S#.#.#.#.."); add("...#...E#.");  }}; List<String> lines_expected = new ArrayList<String>() {{  add("###***##.."); add("..**#**..#"); add("#.*###*#.#"); add("S#*#.#*#.."); add("***#..*E#.");  }};
        // Trap     List<String> lines = new ArrayList<String>() {{ add("#S#.E"); }}; List<String> lines_expected = new ArrayList<String>() {{ add("Trapped"); }};
        // Short
         lines = new ArrayList<String>() {{ add("#S..E"); }}; List<String> lines_expected = new ArrayList<String>() {{ add("#S**E"); }};
        // Medium   List<String> lines = new ArrayList<String>() {{  add("#S.#E"); add("##..."); }}; List<String> lines_expected = new ArrayList<String>() {{  add("#S*#E"); add("##***"); }};

        findPath(createGrid(lines));

        printParents(parentNodes);
        addPathToMap(parentNodes);

        validateLines(lines, lines_expected);

    }

    private static void addPathToMap(Map<String, int[]> parentNodes) {
        System.out.println("UPDATING MAP WITH SHORT PATH - Finish = " + Arrays.toString(finish));

        int[] temp = parentNodes.get(Arrays.toString(finish));

        while(temp != start){
            System.out.println("\tUPDATING MAP TEMP - " + Arrays.toString(temp));
            StringBuilder sb = new StringBuilder(lines.get(temp[0]));
            sb.setCharAt(temp[1], '*');
            lines.set(temp[0], String.valueOf(sb));
            System.out.println(lines);

            temp = parentNodes.get(Arrays.toString(temp));
        }

    }

    private static void printParents(Map<String, int[]> parentNodes) {
        System.out.println("\n\nParent Map - ");
        parentNodes.forEach((node,parent) -> {
            System.out.println("\t" + node + "-" + Arrays.toString(parent));
        });
    }


    private static void findPath(char[][] grid) {
        System.out.println("FINDING PATH");
        parentNodes = new HashMap<>();

        boolean[][] visited = new boolean[length][width];
        visited[start[0]][start[1]] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()) {
            int[] current = q.remove();

            if(grid[current[0]][current[1]] == 'E')
                break; // TODO - End has been reached - Exit & Update/Print Grid
            System.out.println("\tProcessing New Node In Queue - " + Arrays.toString(current));

            for(int[] neighbor : directions) {

                int[] newNeighbor = {current[0]+neighbor[0], current[1]+neighbor[1]};
                int newX = newNeighbor[0];
                int newY = newNeighbor[1];
                System.out.println("\t\tNew Neighbor [x][y] = [" + newX + "][" + newY + "]");


                if(newX >= 0 && newY >= 0
                        && newX < length && newY < width
                        && (grid[newX][newY] == '.' || grid[newX][newY] == 'E')) {

                    if (!visited[newX][newY]) {
                        System.out.println("\t\tAdding Neighbor to Queue - [" + newX + "][" + newY + "]");
                        q.add(new int[]{newX, newY});   // Add unvisited spot to Queue
                        System.out.println("\t\tAdding Neighbor to Parental Map - [" + Arrays.toString(newNeighbor) + "][" + Arrays.toString(current) + "]");
                        parentNodes.put(Arrays.toString(newNeighbor), current);
                        visited[newX][newY] = true;     // Mark new spot as visited
                    }
                }



            }
        }

        System.out.println("END OF FIND PATH FUNCTION ------------");
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
            for (int y=0; y<width; y++) {
                char c = currentLine.charAt(y);
                grid[x][y] = c;

                if(c=='S') {
                    start[0] = x;
                    start[1] = y;
                }
                if(c=='E') {
                    finish[0] = x;
                    finish[1] = y;
                }
            }
            x++;
        }

        printGrid(grid, length, width);
        return grid;
    }





    private static List<int[]> getNeighbors(int row, int col, char[][] grid) {
        List<int[]> neighbors = new ArrayList<>();

        System.out.println("Collecting Neighbors");
        for(int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if(newRow >= 0
                    && newCol >= 0
                    && newRow <= length
                    && newCol <= width
                    && grid[newRow][newCol] == '.' )
                neighbors.add(new int[]{newRow, newCol});

            System.out.println("\t" + neighbors);
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
        System.out.println(Arrays.toString(start));
        System.out.println(Arrays.toString(finish));
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
