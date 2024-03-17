// https://www.codingninjas.com/studio/problems/minimum-path-sum_985349
// TODO: Make notes

import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minSumPath(int[][] grid) {
        // Modifying all the values in column 0
        for(int row = 1; row < grid.length; row++) {
            grid[row][0] += grid[row - 1][0];
        }

        // Modifying all the values in row 0
        for(int col = 1; col < grid[0].length; col++) {
            grid[0][col] += grid[0][col - 1];
        }

        for(int row = 1; row < grid.length; row++) {
            for(int col = 1; col < grid[0].length; col++) {
                grid[row][col] += Math.min(grid[row - 1][col], grid[row][col - 1]);
            }
        }

        return grid[grid.length - 1][grid[0].length - 1];
    }
}