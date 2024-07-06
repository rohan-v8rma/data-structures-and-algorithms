// https://leetcode.com/problems/shortest-path-in-binary-matrix/
// TODO: Make notes

class Solution {
    boolean check(int r, int c, int mR, int mC) {
        return (r < mR && c < mC && r >= 0 && c >= 0);
    }

    /*
    ____REGULAR BFS____
    */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[] dx = new int[]{0, 0, 1, -1, 1, 1, -1, -1};
        int[] dy = new int[]{1, -1, 0, 0, -1, 1, -1, 1};

        int mR = grid.length;
        int mC = grid[0].length;

        if(grid[mR - 1][mC - 1] == 1 || grid[0][0] == 1) return -1;

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0, 0, 1});

        /*
        Note that since we're executing BFS, whenever we reach
        a cell, we would have reached it in the lowest possible
        distance.

        So, if we come upon a visited cell, there is no reason
        for exploring more possible paths to end, because they
        would anyways be longer. 
        */
        boolean[][] visited = new boolean[mR][mC];

        int dist = -1;

        while(!q.isEmpty()) {
            int[] node = q.poll();

            int r = node[0];
            int c = node[1];
            int d = node[2];

            if(
                !check(r, c, mR, mC)
                || visited[r][c]
                || grid[r][c] == 1
            ) continue;

            if(r == mR - 1 && c == mC - 1) {
                dist = d; 
                break;
            }

            visited[r][c] = true;

            for(int i = 0; i < 8; i++) {
                int newR = r + dx[i];
                int newC = c + dy[i];

                q.offer(new int[]{newR, newC, d + 1});
            }
        }

        return dist;
    }

    /*
    ____LEVEL WISE BFS____

    Avoids having to save an extra parameter in the array.
    */
    // public int shortestPathBinaryMatrix(int[][] grid) {
    //     int[] dx = new int[]{0, 0, 1, -1, 1, 1, -1, -1};
    //     int[] dy = new int[]{1, -1, 0, 0, -1, 1, -1, 1};

    //     int mR = grid.length;
    //     int mC = grid[0].length;

    //     if(grid[mR - 1][mC - 1] == 1 || grid[0][0] == 1) return -1;

    //     Queue<int[]> q = new LinkedList<>();

    //     q.offer(new int[]{0, 0});

    //     /*
    //     Note that since we're executing BFS, whenever we reach
    //     a cell, we would have reached it in the lowest possible
    //     distance.

    //     So, if we come upon a visited cell, there is no reason
    //     for exploring more possible paths to end, because they
    //     would anyways be longer. 
    //     */
    //     boolean[][] visited = new boolean[mR][mC];
    //     int level = 0;


    //     while(!q.isEmpty()) {
    //         int nodesInLevel = q.size();
    //         level++;

    //         while(nodesInLevel > 0) {
    //             int[] node = q.poll();
    //             nodesInLevel--;

    //             int r = node[0];
    //             int c = node[1];

    //             if(
    //                 !check(r, c, mR, mC)
    //                 || visited[r][c]
    //                 || grid[r][c] == 1
    //             ) continue;

    //             if(r == mR - 1 && c == mC - 1) {
    //                 return level;
    //             }

    //             visited[r][c] = true;

    //             for(int i = 0; i < 8; i++) {
    //                 int newR = r + dx[i];
    //                 int newC = c + dy[i];

    //                 q.offer(new int[]{newR, newC});
    //             }
    //         }
            
    //     }

    //     return -1;
    // }
}
