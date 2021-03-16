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
public class T4 {

    private static int width, length;
    private static int[] start, finish;
    private static char[][] grid;
    private static final int[][] DIRECTIONS ={{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    private static Map<String, int[]> parentNodes;


    public static void main(String[] args) throws IOException {
        // Example Test Case
        List<String> lines = new ArrayList<String>() {{ add("###...##.."); add("....#....#"); add("#..###.#.#"); add("S#.#.#.#.."); add("...#...E#.");  }}; List<String> lines_expected = new ArrayList<String>() {{  add("###***##.."); add("..**#**..#"); add("#.*###*#.#"); add("S#*#.#*#.."); add("***#..*E#."); }};
        runner(lines, lines_expected);

        // Trap Test Case
        lines = new ArrayList<String>() {{ add("#S#.E"); }}; lines_expected = new ArrayList<String>() {{ add("Trapped"); }};
        runner(lines, lines_expected);

        // Short Test Case
        lines = new ArrayList<String>() {{ add("#S..E"); }}; lines_expected = new ArrayList<String>() {{ add("#S**E"); }};
        runner(lines, lines_expected);

        // Medium Test Case
        lines = new ArrayList<String>() {{  add("#S.#E"); add("##..."); }};  lines_expected = new ArrayList<String>() {{  add("#S*#E"); add("##***"); }};
        runner(lines, lines_expected);

    }

    private static void runner(List<String> lines, List<String> lines_expected) {
        if(findPath(createGrid(lines))) {
            addPathToMap(parentNodes);
            validateLines(lines, lines_expected);
        } else
            System.out.println("Trapped\n\n");
    }


    private static char[][] createGrid(List<String> lines) {

        length = lines.size();
        width = lines.get(0).length();
        grid = new char[length][width];

        int x = 0;
        for(String currentLine : lines) {
            for (int y=0; y<width; y++) {
                char c = currentLine.charAt(y);
                grid[x][y] = c;

                if(c=='S')
                    start = new int[] {x, y};
                if(c=='E')
                    finish = new int[] {x, y};
            }
            x++;
        }

        return grid;
    }

    private static boolean findPath(char[][] grid) {
        parentNodes = new HashMap<>();

        boolean[][] visited = new boolean[length][width];
        visited[start[0]][start[1]] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()) {
            int[] current = q.remove();

            if(grid[current[0]][current[1]] == 'E')
                return true;

            for(int[] neighbor : DIRECTIONS) {

                int newX = current[0]+neighbor[0];
                int newY = current[1]+neighbor[1];

                if(newX >= 0 && newY >= 0
                        && newX < length && newY < width
                        && (grid[newX][newY] == '.' || grid[newX][newY] == 'E')) {

                    if (!visited[newX][newY]) {
                        q.add(new int[]{newX, newY});   // Add unvisited spot to Queue
                        parentNodes.put(Arrays.toString(new int[]{newX,newY}), current);
                        visited[newX][newY] = true;     // Mark new spot as visited
                    }
                }
            }
        }

        return false;
    }

    private static void addPathToMap(Map<String, int[]> parentNodes) {

        int[] temp = parentNodes.get(Arrays.toString(finish));

        while(temp != start){
            grid[temp[0]][temp[1]] = '*';
            temp = parentNodes.get(Arrays.toString(temp));
        }
    }


    private static void validateLines(List<String> lines, List<String> lines_expected) {
        System.out.println("ASSERTING LINES\n\n");
        assertThat(lines.size(), is(lines_expected.size()));

        for(int x=0; x<length; x++) {
            assertThat(lines_expected, hasItem(String.valueOf(grid[x])));
        }
    }

}
