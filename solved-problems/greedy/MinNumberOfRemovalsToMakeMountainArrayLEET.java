// https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/description/
// TODO: Make notes

class Solution {
    /*
     * NON-WORKING SOLUTION
     */
    // int getLength(int index, int[] nums, int decHappened) {
    //     if(index == nums.length) return 0;

    //     if(dp[decHappened][index] != 0) {
    //         return dp[decHappened][index];
    //     }

    //     int l = 1;

    //     for(int i = index + 1; i < nums.length; i++) {
    //         int increaseArray = getLength(i, nums, 0);
    //         int decreaseArray = getLength(i, nums, 1);

    //         System.out.printf("%d: %d, %d, %d\n", index, i, increaseArray, decreaseArray);

    //         if(decHappened == 0) {
    //             /* 
    //             Since a decrease hasn't happened yet,
    //             either: 
    //             1. nums[index] < nums[i]
    //             OR 
    //             2. nums[index] > nums[i], THIS IS THE FIRST DECREASE

    //             But they can't be equal
    //             */
    //             if(nums[index] < nums[i]) {
    //                 l = Math.max(l, 1 + increaseArray);
    //                 l = Math.max(l, 1 + decreaseArray);
    //             }
    //         }
    //         else if(nums[index] > nums[i]) {
    //             l = Math.max(l, 1 + decreaseArray);
    //         }

    //         globalMax = Math.max(globalMax, increaseArray);
    //         // globalMax = Math.max(globalMax, decreaseArray);
    //     }

    //     globalMax = Math.max(globalMax, l);

    //     return dp[decHappened][index] = l;
    // }

    // static int[][] dp;
    // static int globalMax;
    /*
    This figures out the longest bitonic subsequence, it can be thought
    of as a mountain subsequence instead of a mountain array.
    */
    public static int longestBitonicSubsequence(int arr[]) {
        int n = arr.length;

        int[] longestDecreasingSubsequence = new int[n];
        /* 
        All decreasing subsequences atleast contain 1 element 
        (each index by itself)
        */
        Arrays.fill(longestDecreasingSubsequence, 1);

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    longestDecreasingSubsequence[i] = Math.max(
                        longestDecreasingSubsequence[i], 
                        longestDecreasingSubsequence[j] + 1
                    );
                }
            }
        }

        int[] longestIncreasingSubsequence = new int[n];
        Arrays.fill(longestIncreasingSubsequence, 1);

        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    longestIncreasingSubsequence[i] = Math.max(
                        longestIncreasingSubsequence[i], 
                        longestIncreasingSubsequence[j] + 1
                    );
                }
            }
        }

        int max = 1;

        for(int i = 0; i < n; i++) {
            if(
                /* 
                This is necessary, otherwise criteria of mountain array
                won't be satisfied.
                */
                longestIncreasingSubsequence[i] > 1 
                && longestDecreasingSubsequence[i] > 1
            ) {
                max = Math.max(
                    max, 
                    longestIncreasingSubsequence[i] 
                    + longestDecreasingSubsequence[i] 
                    - 1
                );
            }
        }
            
        return max;
    }
    

    public int minimumMountainRemovals(int[] nums) {
        return nums.length - longestBitonicSubsequence(nums);
    }
}