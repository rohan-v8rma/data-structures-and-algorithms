// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-140 

class Solution {
    // /*
    // ______SELF DEVELOPED ALGO______
    
    // Time Complexity Calculation.
    
    // N^2 for checking each cell, whether it is a valid starting path.
    
    // Atmost, there are 25 cells containing coins.
    
    // Assuming case where 5 * 5 matrix is given, and all contain coins.
    
    // We will be able to start DFS for roughly half of the cells in the matrix,
    // due to WEAK CELL CONCEPT specified in the problem.
    
    // So we will start DFS on approx N^2/2 cells. 
    
    // Each call will visit all cells atleast once 
    // (due to all of them containing coins), maybe more.
    
    // Effective TC in worst case: (N^2/2) * 25.
    // */
    // static boolean[][] visited;
    
    // static int max;
    
    // static int dfs(int row, int col, int[][] grid) {
    //     if(
    //         row < 0 
    //         || row >= grid.length 
    //         || col < 0 
    //         || col >= grid[0].length 
    //         || visited[row][col]
    //     ) {
    //         return 0;
    //     }
        
    //     visited[row][col] = true;
        
    //     // Either going up.
    //     int coinCt = grid[row][col] + dfs(row - 1, col, grid);
    //     // Or going down.
    //     coinCt = Math.max(dfs(row + 1, col, grid) + grid[row][col], coinCt);
    //     // Or going left.
    //     coinCt = Math.max(dfs(row, col - 1, grid) + grid[row][col], coinCt);
    //     // Or going right.
    //     coinCt = Math.max(dfs(row, col + 1, grid) + grid[row][col], coinCt);
        
    //     max = Math.max(coinCt, max);
        
    //     visited[row][col] = false;
        
    //     return coinCt;
    // }
    
    
    // public static int maximumCoins(int N, int[][] grid) {
    //     visited = new boolean[grid.length][grid[0].length];
        
    //     max = 0;
        
    //     for(int startRow = 0; startRow < grid.length; startRow++) {
    //         for(int startCol = 0; startCol < grid[0].length; startCol++) {
    //             if(grid[startRow][startCol] == 0) {
    //                 visited[startRow][startCol] = true;
    //             }
    //         }
    //     }
        
    //     for(int startRow = 0; startRow < grid.length; startRow++) {
    //         for(int startCol = 0; startCol < grid[0].length; startCol++) {
    //             if((startRow + startCol) % 2 != 0) {
    //                 dfs(startRow, startCol, grid);
    //             }
    //         }
    //     }
        
    //     return max;
    // }
    
    /*
    ____SPACE OPTIMIZED APPROACH____
    */
    static int max;
    
    static int dfs(int row, int col, int[][] grid) {
        if(
            row < 0 
            || row >= grid.length 
            || col < 0 
            || col >= grid[0].length 
            || grid[row][col] == 0
        ) {
            return 0;
        }
        
        
        int coinCt = grid[row][col];
        
        // In effect, this is the same as marking the cell as visited.
        grid[row][col] = 0;
        
        // Exploring all 4 directions.
        int[] dRow = new int[]{0, 0, 1, -1};
        int[] dCol = new int[]{1, -1, 0, 0};
        
        int maxCoinsFrom1Direction = 0;
        
        for(int dir = 0; dir < 4; dir++) {
            maxCoinsFrom1Direction = Math.max(
                maxCoinsFrom1Direction,
                dfs(row + dRow[dir], col + dCol[dir], grid)
            );
        }
        
        // Making the cell unvisited again.
        grid[row][col] = coinCt;
        
        coinCt += maxCoinsFrom1Direction;
        
        max = Math.max(coinCt, max);
        
        return coinCt;
    }
    
    
    public static int maximumCoins(int N, int[][] grid) {
        max = 0;
        
        for(int startRow = 0; startRow < grid.length; startRow++) {
            for(int startCol = 0; startCol < grid[0].length; startCol++) {
                if((startRow + startCol) % 2 != 0) {
                    dfs(startRow, startCol, grid);
                }
            }
        }
        
        return max;
    }
}
        
