// https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k/
// TODO: Make notes 
//? Special since this seemed like a regular graph problem, but it was a DP problem

class Solution {
    int m = 1000000007;
    int add(int a, int b, int c) {
        return (a + b) % c;
    }

    // int dfs(int r, int c, int sum, int k, int[][] grid) {
    //     int mR = grid.length; 
    //     int mC = grid[0].length;
    //     if(r >= mR || c >= mC) {
    //         return 0;
    //     }

    //     sum += grid[r][c];

    //     if(r == mR - 1 && c == mC - 1) {
    //         // System.out.println(sum);
    //         if(sum % k == 0) return 1;

    //         return 0;
    //     }

    //     return add(
    //         dfs(r + 1, c, sum, k, grid),
    //         dfs(r, c + 1, sum, k, grid),
    //         m
    //     );
    // }

    // public int numberOfPaths(int[][] grid, int k) {
    //     return dfs(0, 0, 0, k, grid);
    // }
    
    boolean check(int r, int c, int mR, int mC) {
        return (r < mR && c < mC && r >= 0 && c >= 0);
    }

    /*
    ____DP based solution____

    This works since the maximum remainder possible is 49, 
    which occurs when k is at maximum of 50, acc to constraints.

    So max ops is 50 * 5 * 10^4, 
    since grid has max of 5 * 10^4 elements acc to constraints
    */
    public int numberOfPaths(int[][] grid, int k) {
        int mR = grid.length; 
        int mC = grid[0].length;

        int[][][] rem = new int[mR][mC][k];

        /* 
        Initializing with a sub-path, that just contains 
        the bottom right cell, and so the remainder of that
        sub-path sum is just the value of the bottom right cell
        modded by k.
        */
        rem[mR - 1][mC - 1][grid[mR - 1][mC - 1] % k] = 1;

        int[] dr = new int[]{1, 0};
        int[] dc = new int[]{0, 1};

        for(int r = mR - 1; r >= 0; r--) {
            for(int c = mC - 1; c >= 0; c--) {
                /* 
                For every cell, a path can go either to the
                bottom or to the right.

                So, for paths ending at either the adjacent
                bottom or right cell, we calculate the new
                remainders for those paths, depending on what
                the remainders were before the current cell
                was added.
                */
                for(int i = 0; i < 2; i++) {
                    if(check(r + dr[i], c + dc[i], mR, mC)) {
                        for(int re = 0; re < k; re++) {
                            int newRe = add(re, grid[r][c], k);
                            rem[r][c][newRe] = add(rem[r][c][newRe], rem[r + dr[i]][c + dc[i]][re], m);
                        }
                    }
                }
            }
        }

        return rem[0][0][0];
    }
}