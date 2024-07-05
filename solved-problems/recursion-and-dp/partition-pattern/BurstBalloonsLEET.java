// https://leetcode.com/problems/burst-balloons/

class Solution {
    /*
    ____SELF DEVELOPED (NOT WORKING)____

    The recursive sub-problems are NOT independent, 
    so we are not able to get the answer using recursion.
    */
    // int getPrevElement(int idx, int startBalloon, int low, int[] nums) {
    //     if(idx == startBalloon) {
    //         if(low < 0) return 1;

    //         return nums[low];
    //     }
        
    //     return nums[idx - 1];
    // }

    // int getNextElement(int idx, int endBalloon, int high, int[] nums) {
    //     if(idx == endBalloon) {
    //         if(high >= nums.length) return 1;

    //         return nums[high];
    //     }

    //     return nums[idx + 1];
    // }

    // int getNumOfCoins(int startBalloon, int endBalloon, int low, int high, int[] nums) {
    //     int maxCoins = 0;

    //     for(
    //         int balloonToPop = startBalloon; 
    //         balloonToPop <= endBalloon; 
    //         balloonToPop++
    //     ) {
    //         maxCoins = Math.max(
    //             maxCoins,
    //             (
    //                 getPrevElement(balloonToPop, startBalloon, low, nums)
    //                 * nums[balloonToPop]
    //                 * getNextElement(balloonToPop, endBalloon, high, nums)
    //             )
    //             /*
    //             The problem is in the recursion happening here, we have to either 
    //             pop all the elements on the left first, or all on the right first.

    //             We are somewhere missing a maximum where it would have been ideal
    //             to delete some elements on the right, then some on the left, etc.
    //             */
    //             + Math.max(
    //               getNumOfCoins(startBalloon, balloonToPop - 1, low, balloonToPop + 1, nums)
    //               + getNumOfCoins(balloonToPop + 1, endBalloon, -1, high, nums),
    //               getNumOfCoins(balloonToPop + 1, endBalloon, balloonToPop - 1, high, nums)
    //               + getNumOfCoins(startBalloon, balloonToPop - 1, low, nums.length, nums)
    //             )
    //         );
    //     }

    //     return maxCoins;
    // }

    // public int maxCoins(int[] nums) {
    //     return getNumOfCoins(0, nums.length - 1, -1, nums.length, nums);
    // }

    /*
    ____STRIVER METHOD (RECURSIVE MEMOIZATION)____

    Goes in the opposite direction, one-by-one adding back balloons,
    and evaluating the cost of popping them.
    */
    // int getMaxCoins(int low, int high, int[] nums) {
    //     if(dp[low][high] != -1) return dp[low][high];

    //     int maxCoins = 0;

    //     for(int popLast = low + 1; popLast < high; popLast++) {
    //         maxCoins = Math.max(
    //             maxCoins,
    //             nums[low] * nums[popLast] * nums[high]
    //             + getMaxCoins(low, popLast, nums)
    //             + getMaxCoins(popLast, high, nums)
    //         );
    //     }

    //     return dp[low][high] = maxCoins;
    // }

    // int[][] dp;

    // public int maxCoins(int[] nums) {
    //     int[] newNums = new int[nums.length + 2];
    //     newNums[0] = 1;
    //     newNums[newNums.length - 1] = 1;

    //     dp = new int[newNums.length][newNums.length];

    //     for(int[] dp1d: dp) {
    //         Arrays.fill(dp1d, -1);
    //     }

    //     for(int i = 0; i < nums.length; i++) {
    //         newNums[i + 1] = nums[i];
    //     }

    //     return getMaxCoins(0, newNums.length - 1, newNums);
    // }

    /*
    ____STRIVER METHOD (ITERATIVE TABULATION)____

    Goes in the opposite direction, one-by-one adding back balloons,
    and evaluating the cost of popping them.
    */

    int[][] dp;

    public int maxCoins(int[] nums) {
        int[] newNums = new int[nums.length + 2];
        newNums[0] = 1;
        newNums[newNums.length - 1] = 1;

        dp = new int[newNums.length][newNums.length];

        for(int i = 0; i < nums.length; i++) {
            newNums[i + 1] = nums[i];
        }

        nums = newNums;

        for(int low = newNums.length - 1; low >= 0; low--) {
            for(int high = low; high < newNums.length; high++) {
                int maxCoins = 0;

                for(int popLast = low + 1; popLast < high; popLast++) {
                    maxCoins = Math.max(
                        maxCoins,
                        nums[low] * nums[popLast] * nums[high]
                        + dp[low][popLast]
                        + dp[popLast][high]
                    );
                }

                dp[low][high] = maxCoins;
            }
        }

        return dp[0][newNums.length - 1];
    }
}