// https://leetcode.com/problems/number-of-islands

class Solution {
    boolean check(int r, int c, int mR, int mC) {
        return (r >= 0 && c >= 0 && r < mR && c < mC);
    }

    /*
    ____DFS APPROACH____
    */

    void dfs(int r, int c, char[][] grid) {
        visited[r][c] = true;

        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};

        for(int i = 0; i < 4; i++) {
            int aR = r + dx[i];
            int aC = c + dy[i];

            if(check(aR, aC, mR, mC) && grid[aR][aC] == '1' && !visited[aR][aC]) {
                dfs(aR, aC, grid);
            }
        }
    }

    static boolean[][] visited;
    static int mR;
    static int mC;

    public int numIslands(char[][] grid) {
        mR = grid.length;
        mC = grid[0].length;

        visited = new boolean[mR][mC];

        int numOfIslands = 0;

        for(int r = 0; r < mR; r++) {
            for(int c = 0; c < mC; c++) {
                if(grid[r][c] == '1' && !visited[r][c]) {
                    numOfIslands++;
                    dfs(r, c, grid);
                }
            }
        }

        return numOfIslands;
    }

    /*
    ____BFS APPROACH____
    */

    // void bfs(int r, int c, char[][] grid) {
    //     visited[r][c] = true;

    //     int[] dx = new int[]{0, 0, 1, -1};
    //     int[] dy = new int[]{1, -1, 0, 0};

    //     /* 
    //     Using this space instead of recursion stack in DFS
    //     */
    //     Queue<int[]> q = new LinkedList<>();

    //     q.offer(new int[]{r, c});

    //     while(!q.isEmpty()) {
    //         int[] currentPt = q.poll();
    //         int curR = currentPt[0];
    //         int curC = currentPt[1];

    //         for(int i = 0; i < 4; i++) {
    //             int aR = curR + dx[i];
    //             int aC = curC + dy[i];

    //             if(check(aR, aC, mR, mC) && grid[aR][aC] == '1' && !visited[aR][aC]) {
    //                 q.add(new int[]{aR, aC});
    //                 visited[aR][aC] = true;
    //             }
    //         }
    //     }
    // }

    // static boolean[][] visited;
    // static int mR;
    // static int mC;

    // public int numIslands(char[][] grid) {
    //     mR = grid.length;
    //     mC = grid[0].length;

    //     visited = new boolean[mR][mC];

    //     int numOfIslands = 0;

    //     for(int r = 0; r < mR; r++) {
    //         for(int c = 0; c < mC; c++) {
    //             if(grid[r][c] == '1' && !visited[r][c]) {
    //                 numOfIslands++;
    //                 bfs(r, c, grid);
    //             }
    //         }
    //     }

    //     return numOfIslands;
    // }
}