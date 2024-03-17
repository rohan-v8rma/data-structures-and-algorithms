// https://www.codingninjas.com/studio/problems/maze-obstacles_977241

import java.util.ArrayList;

public class Solution {
    static int pathsTabulated(
        int n, 
        int m, 
        ArrayList<ArrayList<Integer>> mat
    ) {
        int[][] tabMatrix = new int[n][m];
        
        // We can only move RIGHT or DOWN.

        int currentRow = 0;
        while(
            (currentRow < n) 
            && 
            (mat.get(currentRow).get(0) != -1)
        ) {
            tabMatrix[currentRow][0] = 1;

            currentRow++;
        }

        int currentCol = 0;
        while(
            (currentCol < m) 
            && 
            (mat.get(0).get(currentCol) != -1)
        ) {
            tabMatrix[0][currentCol] = 1;

            currentCol++;
        }

        for(int row = 1; row < n; row++) {
            for(int col = 1; col < m; col++) {
                if(mat.get(row).get(col) == -1) {
                    continue;
                }

                tabMatrix[row][col] = 
                (
                    tabMatrix[row - 1][col] 
                    + 
                    tabMatrix[row][col - 1]
                ) 
                % 
                (int)(Math.pow(10, 9) + 7);
            }
        }

        return tabMatrix[n - 1][m - 1] % (int)(Math.pow(10, 9) + 7);
    }

    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        // Write your code here.

        return pathsTabulated(n, m, mat);
    }

}
