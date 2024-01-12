// https://leetcode.com/problems/continuous-subarray-sum

class Solution {
    // Bruteforce: O(N^2) would involve checking all possible sub-arrays using 2 for-loops

    // /* 
    // Better: O(N)
    
    // Similar to: 
    // - XOR of subarray = K
    // - Subarray sum = K

    // Makes use of HashMap to keep track of sums seen.
    // */
    // public boolean checkSubarraySum(int[] nums, int k) {
        
    //     // The value in this map stores the length of the sum that has been seen.
    //     Map<Integer, Integer> sumSeen = new HashMap<>();

    //     int currentSum = 0;
    //     int currentLength = 0;

    //     /* 
    //     This is for the case when the entire sub-array uptil that point has the required sum K;
    //     i.e., the initial part of the sub-array that needs to be discarded, so that the remaining
    //     part yields us the required sum, is EMPTY.
    //     */
    //     sumSeen.put(currentSum, currentLength);

    //     int multiple;

    //     for(int num: nums) {
    //         currentLength++;
    //         currentSum += num;
    //         multiple = 0;
            
    //         while(multiple <= currentSum) {
    //             if(
    //                 sumSeen.containsKey(currentSum - multiple)
    //                 &&
    //                 (currentLength - sumSeen.get(currentSum - multiple)) >= 2
    //             ) {
    //                 return true;
    //             }
    //             multiple += k;
    //         }

    //         /* 
    //         We use putIfAbsent, since if this particular remainder has been stored in an earlier
    //         iteration, it means that the length at that time would have been less.

    //         LESSER LENGTH is better since this length may have to be subtracted from
    //         current length, and that difference needs to be greater than 2.
    //         */
    //         sumSeen.putIfAbsent(currentSum, currentLength);
    //     }    

    //     return false;   
    // }

    /* 
    Optimal: O(N)

    Optimization over the better solution:
    - Stores the remainder (w.r.t k) of the sums seen, with the lengths of the sums as keys.
    - This reduces the need for the while-loop to check for multiples.
    */
    public boolean checkSubarraySum(int[] nums, int k) {
        
        // The value in this map stores the length of the sum that has been seen.
        Map<Integer, Integer> sumSeen = new HashMap<>();

        int currentSum = 0;
        int currentLength = 0;

        /* 
        This is for the case when the entire sub-array uptil that point has the required sum K;
        i.e., the initial part of the sub-array that needs to be discarded, so that the remaining
        part yields us the required sum, is EMPTY.
        */
        sumSeen.put(currentSum % k, currentLength);

        for(int num: nums) {
            currentLength++;
            currentSum += num;
            
            if(
                sumSeen.containsKey(currentSum % k)
                &&
                (currentLength - sumSeen.get(currentSum % k)) >= 2
            ) {
                return true;
            }

            /* 
            We use putIfAbsent, since if this particular remainder has been stored in an earlier
            iteration, it means that the length at that time would have been less.

            LESSER LENGTH is better since this length may have to be subtracted from
            current length, and that difference needs to be greater than 2.
            */
            sumSeen.putIfAbsent(currentSum % k, currentLength);
        }    

        return false;   
    }
}