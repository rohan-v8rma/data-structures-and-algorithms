// https://leetcode.com/problems/partition-array-for-maximum-sum/

class Solution {
    /*
    ____RECURSIVE MEMOIZATION____

    TC: O(N * K)

    startIdx: which index the partition starts from.
    endIdx: which index the partition ends at.
    */
    // int getPartition(int[] arr, int k, int startIdx) {
    //     if(dp[startIdx] != Integer.MIN_VALUE) return dp[startIdx];

    //     int max = arr[startIdx];

    //     /* 
    //     Incrementing the end position of the partition one by one,
    //     because the best solution may be any of them.
    //     */
    //     for(
    //         int endIdx = startIdx; 
    //         endIdx < Math.min(arr.length, (k + startIdx)); 
    //         endIdx++
    //     ) {
    //         // Maximum element inside the current partition.
    //         max = Math.max(max, arr[endIdx]);

    //         /* 
    //         Seeing if the current partition is a part of 
    //         the best possible solution.
    //         */
    //         dp[startIdx] = Math.max(
    //             dp[startIdx],
    //             max * (endIdx - startIdx + 1) + getPartition(arr, k, endIdx + 1)
    //         );
    //     }

    //     return dp[startIdx];
    // }

    // int[] dp;

    // public int maxSumAfterPartitioning(int[] arr, int k) {
    //     dp = new int[arr.length + 1];
    //     Arrays.fill(dp, Integer.MIN_VALUE);
    //     dp[arr.length] = 0;

    //     return getPartition(arr, k, 0);
    // }


    /*
    ____ITERATIVE TABULATION____

    TC: O(N * K)

    startIdx: which index the partition starts from.
    endIdx: which index the partition ends at.
    */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] dp = new int[arr.length + 1];

        for(int startIdx = arr.length - 1; startIdx >= 0; startIdx--) {
            int max = arr[startIdx];

            /* 
            Incrementing the end position of the partition one by one,
            because the best solution may be any of them.
            */
            for(
                int endIdx = startIdx; 
                endIdx < Math.min(arr.length, (k + startIdx)); 
                endIdx++
            ) {
                // Maximum element inside the current partition.
                max = Math.max(max, arr[endIdx]);

                /* 
                Seeing if the current partition is a part of 
                the best possible solution.
                */
                dp[startIdx] = Math.max(
                    dp[startIdx],
                    max * (endIdx - startIdx + 1) + dp[endIdx + 1]
                );
            }
        }

        return dp[0];
    }
}