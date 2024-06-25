// https://practice.geeksforgeeks.org/contest/gfg-weekly-160-rated-contest/problems


class Solution {
    /*
    O(N ^ 2) solution, will lead to TLE.
    */
    // public static int maxGoodSubarrays(int n, int[] arr) {
    //     /* 
    //     dp[0] will give us the maximum number of good subarrays,
    //     that the entire array can be divided into.
    //     */
    //     int[] dp = new int[arr.length + 1];
        
    //     /*
    //     We start calculating values for the dp array, from the end.
    //     */
    //     for(int startIdx = arr.length - 2; startIdx >= 0; startIdx--) {
    //         int maxValue = Math.max(arr[startIdx], arr[startIdx + 1]);
            
    //         for(int endIdx = startIdx + 1; endIdx < arr.length; endIdx++) {
    //             maxValue = Math.max(maxValue, arr[endIdx]);
                
    //             // So, the sub-array from startIdx to endIdx is good.
    //             if(maxValue != arr[endIdx]) {
    //                 /* 
    //                 1. There are no possibilities for more subarrays,
    //                 to the right of [startIdx, endIdx]
    //                 */
    //                 if(endIdx == arr.length - 1) {
    //                     dp[startIdx] = Math.max(
    //                         dp[startIdx],
    //                         1
    //                     );
    //                 }
    //                 // It is possible to partition the right portion into good subarrays
    //                 else if(dp[endIdx + 1] > 0) {
    //                     dp[startIdx] = Math.max(
    //                         dp[startIdx],
    //                         1 + dp[endIdx + 1]
    //                     );
    //                 }
    //             }
    //         }
    //     }
        
    //     return dp[0];
    // }


    /*
    ____OPTIMAL____
    
    TC: O(N)
    */
    public static int maxGoodSubarrays(int n, int[] arr) {
        /*
        First, we try to find whether last element of the array
        is the maximum. 
        Only in that case, it is impossible to segment the array
        into good sub-arrays.
        
        If this is NOT true, atleast 1 partition, in which the entire
        array is contained, is possible.
        */
        
        int lastPartitionEnd = -1;
        
        int max = arr[arr.length - 1];
        for(int idx = arr.length - 2; idx >= 0; idx--) {
            // There is another element which is the maximum
            if(max < arr[idx]) {
                lastPartitionEnd = idx;
                break;
            }
        }
        
        if(lastPartitionEnd == -1) return 0;
        
        int numOfPartitions = 1;
        
        max = arr[0];
        
        for(int idx = 0; idx < lastPartitionEnd; idx++) {
            // So, the max occurred earlier, and this is a good subarray
            if(max > arr[idx]) {
                /*
                We can do this, since the last partition has atleast
                2 elements, so `idx + 1` will atmost refer to the
                2nd last element.
                */
                max = arr[idx + 1];
                numOfPartitions++;
            }
            else {
                // Found a new maximum.
                max = Math.max(max, arr[idx]);
            }
        }
        
        return numOfPartitions;
        
        /*
        Suppose test case is 6, 3, 2, 1, 5, 7, 8, 7
        
        last partition would be: 8, 7
        
        1st partition: 6, 3
        2nd partition: 2, 1
        
        when we move on to 5, then 7, we don't reach a decrease point
        before reaching `lastPartitionEnd`. Resultingly, these 2 elements
        will be added to the last partition: 5, 7, 8, 7
        */
    }
}
