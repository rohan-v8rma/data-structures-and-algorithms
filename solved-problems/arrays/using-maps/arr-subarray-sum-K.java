// https://leetcode.com/problems/subarray-sum-equals-k/description/

class Solution {
    // Bruteforce would be O(n^2), where all sub-arrays are tested.

    /* 
    Optimal: Similar to number of sub-arrays with XOR k.
    We keep track of the sums seen in a hashmap.
    */
    public int subarraySum(int[] nums, int k) {
        // This is for keeping track of the sums we have seen till now
        Map<Integer, Integer> sumSeen = new HashMap<>();

        int count = 0;

        int currentSum = 0;

        /* 
        This is for the case when the entire sub-array uptil that point has the required sum K;
        i.e., the initial part of the sub-array that needs to be discarded, so that the remaining
        part yields us the required sum, is EMPTY.
        */
        sumSeen.put(currentSum, 1);

        for(int element: nums) {
            currentSum += element;
            if(sumSeen.containsKey(currentSum - k)) {
                // Adding the count of discardable sub-arrays, to get the required sum, to global count.
                count += sumSeen.get(currentSum - k);
            }

            sumSeen.put(currentSum, sumSeen.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}