// https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1

class Solution{
    // /*
    // ____RECURSIVE MEMOIZATION____
    // */
    // static int getNumOfOps(int firstMatrix, int lastMatrix, int arr[]) {
    //     if(firstMatrix >= lastMatrix) return 0;
        
    //     if(dp[firstMatrix][lastMatrix] != -1) return dp[firstMatrix][lastMatrix];
        
    //     int numOfOps = Integer.MAX_VALUE;
        
    //     /* 
    //     In the first case, there is only 1 matrix in the first partition.
    //     In the last case, there are all but 1 matrix in the first partition.
    //     */
    //     for(int endMatrix = firstMatrix; endMatrix < lastMatrix; endMatrix++) {
    //         numOfOps = Math.min(
    //             numOfOps,
    //             arr[firstMatrix - 1] * arr[endMatrix] * arr[lastMatrix]
    //             + getNumOfOps(firstMatrix, endMatrix, arr)
    //             + getNumOfOps(endMatrix + 1, lastMatrix, arr)
    //         );
    //     }
        
    //     return dp[firstMatrix][lastMatrix] = numOfOps == Integer.MAX_VALUE ? 0 : numOfOps;
    // }
    
    // static int[][] dp;
    
    // static int matrixMultiplication(int N, int arr[]) {
    //     dp = new int[N][N];
        
    //     for(int[] dp1d: dp) Arrays.fill(dp1d, -1);
        
    //     /*
    //     If there are N elements in arr, it means there are N - 1 matrices,
    //     where dimensions of ith matrix are arr[i - 1] * arr[i]
    //     */
    //     return getNumOfOps(1, N - 1, arr);
    // }
    
    /*
    ____ITERATIVE TABULATION____
    */
    
    static int[][] dp;
    
    static int matrixMultiplication(int N, int arr[]) {
        dp = new int[N][N];
        
        for(int[] dp1d: dp) Arrays.fill(dp1d, -1);
        
        /*
        If there are N elements in arr, it means there are N - 1 matrices,
        where dimensions of ith matrix are arr[i - 1] * arr[i]
        */
        
        // Navigating recursive parameters in opposite direction to get the final result at the end. 
        for(int firstMatrix = N - 1; firstMatrix >= 1; firstMatrix--) {
            for(int lastMatrix = firstMatrix; lastMatrix < N; lastMatrix++) {
                int numOfOps = Integer.MAX_VALUE;
                
                /* 
                In the first case, there is only 1 matrix in the first partition.
                In the last case, there are all but 1 matrix in the first partition.
                */
                for(int endMatrix = firstMatrix; endMatrix < lastMatrix; endMatrix++) {
                    numOfOps = Math.min(
                        numOfOps,
                        arr[firstMatrix - 1] * arr[endMatrix] * arr[lastMatrix]
                        + dp[firstMatrix][endMatrix]
                        + dp[endMatrix + 1][lastMatrix]
                    );
                }
                
                dp[firstMatrix][lastMatrix] = numOfOps == Integer.MAX_VALUE ? 0 : numOfOps;   
            }
        }
        
        return dp[1][N - 1];
    }
}