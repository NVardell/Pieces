package com.bits.pieces.topics.temp;

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
public class T3 {

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
        // Example
        lines = new ArrayList<String>() {{ add("###...##.."); add("....#....#"); add("#..###.#.#"); add("S#.#.#.#.."); add("...#...E#.");  }}; List<String> lines_expected = new ArrayList<String>() {{  add("###***##.."); add("..**#**..#"); add("#.*###*#.#"); add("S#*#.#*#.."); add("***#..*E#.");  }};
        // Trap
        // lines = new ArrayList<String>() {{ add("#S#.E"); }}; List<String> lines_expected = new ArrayList<String>() {{ add("Trapped"); }};
        // Short
        // lines = new ArrayList<String>() {{ add("#S..E"); }}; List<String> lines_expected = new ArrayList<String>() {{ add("#S**E"); }};
        // Medium
        // lines = new ArrayList<String>() {{  add("#S.#E"); add("##..."); }}; List<String> lines_expected = new ArrayList<String>() {{  add("#S*#E"); add("##***"); }};

        findPath(createGrid(lines));

        printParents(parentNodes);
        addPathToMap(parentNodes);

        validateLines(lines, lines_expected);
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

        return grid;
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

    private static void addPathToMap(Map<String, int[]> parentNodes) {
        System.out.println("\n\nUPDATING MAP WITH SHORT PATH - Finish = " + Arrays.toString(finish));
        System.out.println("\tBEFORE"); printLines(lines);

        int[] temp = parentNodes.get(Arrays.toString(finish));

        while(temp != start){
            System.out.println("\tUPDATING MAP TEMP - " + Arrays.toString(temp));
            //--------------------------
            StringBuilder sb = new StringBuilder(lines.get(temp[0]));
            sb.setCharAt(temp[1], '*');
            lines.set(temp[0], String.valueOf(sb));
            //---------------------------------
            // String newLine = lines.get(temp[0]);
            // newLine = newLine.substring(0,temp[1]) + '*' + newLine.substring(temp[1]+1);
            // lines.set(temp[0], newLine);
            //------------------------------------------

            temp = parentNodes.get(Arrays.toString(temp));
        }
        System.out.println("\tAFTER"); printLines(lines);
    }

    private static void printLines(List<String> lines) {
        lines.forEach(System.out::println);
    }


    private static void printParents(Map<String, int[]> parentNodes) {
        System.out.println("\n\nParent Map - ");
        parentNodes.forEach((node,parent) -> {
            System.out.println("\t" + node + "-" + Arrays.toString(parent));
        });
    }
    private static void validateLines(List<String> lines, List<String> lines_expected) {
        System.out.println("\n\nASSERTING LINES");
        assertThat(lines.size(), is(lines_expected.size()));

        for(String line : lines)
            assertThat(lines_expected, hasItem(line));
    }

}
