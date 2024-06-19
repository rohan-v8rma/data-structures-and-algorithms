// https://leetcode.com/problems/find-the-maximum-length-of-a-good-subsequence-ii/
// TODO: Try fixing the recursive solution

class Solution {
    /* 
    ____RECURSIVE SOLUTION____
    */

    // int recurse(int[] nums, int k, int prevI, int i, int curK, int len) {
    //     if(curK > k) return 0;
    //     if(i < 0) return len;

    //     if(prevI == -1) {
    //         return Math.max(
    //             recurse(nums, k, prevI, i - 1, curK, len),
    //             recurse(nums, k, i, i - 1, curK, 1)
    //         );
    //     }

    //     return Math.max(
    //         recurse(nums, k, i, i - 1, curK + (nums[i] == nums[prevI] ? 0 : 1), len + 1),
    //         recurse(nums, k, prevI, i - 1, curK, len)
    //     );
    // }

    // public int maximumLength(int[] nums, int k) {
    //     return recurse(nums, k, -1, nums.length - 1, 0, 0);
    // }

    /*
    ____BETTER RECURSIVE SOLUTION____

    This recursive solution has lesser number of parameters in the recursive
    call.
    */

    // int recurse(int[] nums, int k, int i, int curK) {
    //     if(curK < 0) return 0;
    //     if(i < 0) return 0;
        
    //     System.out.printf("i: %d\n", i);
        
    //     int result = 0;

    //     System.out.printf("i: %d, pre-result: %d\n", i, result);

    //     for(int prevI = i - 1; prevI >= 0; prevI--) {
    //         System.out.printf("i: %d, prevI: %d\n", i, prevI);
    //         if(nums[i] == nums[prevI]) {
    //             result = Math.max(
    //                 recurse(nums, k, prevI, curK),
    //                 result
    //             );
    //         }
    //         else {
    //             result = Math.max(
    //                 recurse(nums, k, prevI, curK - 1),
    //                 result
    //             );
    //         }
    //     }

    //     result = Math.max(result, recurse(nums, k, i - 1, curK) - 1);

    //     return result + 1;
    // }

    // public int maximumLength(int[] nums, int k) {
    //     return recurse(nums, k, nums.length - 1, k);
    // }

    /*
    ____MORE CONVENTIONAL RECURSIVE SOLUTION____
    */

    // int recurse(int[] nums, int k, int i, int curK) {
    //     if(curK < 0) return 0;
    //     if(i < 0) return 0;
        
    //     System.out.printf("i: %d\n", i);
        
    //     int result = 0;

    //     System.out.printf("i: %d, pre-result: %d\n", i, result);


    //     for(int prevI = i - 1; prevI >= 0; prevI--) {
    //         System.out.printf("i: %d, prevI: %d\n", i, prevI);
    //         if(nums[i] == nums[prevI]) {
    //             result = Math.max(
    //                 recurse(nums, k, prevI, curK),
    //                 result
    //             );
    //         }
    //         else {
    //             result = Math.max(
    //                 recurse(nums, k, prevI, curK - 1),
    //                 result
    //             );
    //         }
    //     }

    //     System.out.printf("i: %d, post-result: %d\n", i, result + 1);

    //     return result + 1;
    // }

    // public int maximumLength(int[] nums, int k) {
    //     int max = 0;
    //     for(int i = nums.length - 1; i >= 0; i--) {
    //         max = Math.max(
    //             recurse(nums, k, i, k),
    //             max
    //         );
    //     }
        
    //     return max;
    // }

    /*
    ____UNOPTIMIZED TABULATION____

    TC: O(k * n * n)
    */

    // public int maximumLength(int[] nums, int k) {
    //     int[][] dp = new int[nums.length][k + 1];

    //     int max = 1;

    //     for(int curK = 0; curK <= k; curK++) {
    //         for(int i = 0; i < nums.length; i++) {
    //             dp[i][curK] = 1;
    //             for(int p = i - 1; p >= 0; p--) {
    //                 if(nums[i] == nums[p]) {
    //                     dp[i][curK] = Math.max(
    //                         1 + dp[p][curK],
    //                         dp[i][curK]
    //                     );
    //                 }
    //                 else if(curK > 0) {
    //                     dp[i][curK] = Math.max(
    //                         1 + dp[p][curK - 1],
    //                         dp[i][curK]
    //                     );
    //                 }
    //             }

    //             max = Math.max(dp[i][curK], max);
    //         }
    //     }

    //     return max;
    // }

    /*
    ____OPTIMIZED TABULATION____

    TC: O(k * n)
    */

    public int maximumLength(int[] nums, int k) {
        int[][] dp = new int[nums.length][k + 1];

        int max = 1;

        HashMap<Integer, Integer> sameValueSubLen;

        for(int curK = 0; curK <= k; curK++) {
            int maxInPreviousRow = 0;

            sameValueSubLen = new HashMap<>();
            
            for(int i = 0; i < nums.length; i++) {
                /* 
                There is no change in number of different value pairs.
                
                We simply take the subsequence, where the last number is same
                as the current number
                */
                dp[i][curK] = 1 + sameValueSubLen.getOrDefault(nums[i], 0);

                /*
                We may be adding a new different value pair.

                (It is possible that we may be counting same values as a different
                value pair using this method, but that would be corrected due to
                the statement above.)
                */
                dp[i][curK] = Math.max(1 + maxInPreviousRow, dp[i][curK]);

                if(curK > 0) {
                    maxInPreviousRow = Math.max(
                        maxInPreviousRow,
                        dp[i][curK - 1]
                    );
                }

                sameValueSubLen.put(nums[i], dp[i][curK]);

                max = Math.max(dp[i][curK], max);
            }
        }

        return max;
    }
}