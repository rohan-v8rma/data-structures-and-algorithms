// https://leetcode.com/problems/maximum-good-subarray-sum/

class Solution {
    // Code to get number of good subarrays
//     public long maximumSubarraySum(int[] nums, int k) {
//         HashMap<Integer, Integer> ctOfNumbersSeen = new HashMap<>();
        
//         int goodSubarrays = 0;
        
//         for(int num: nums) {
//             goodSubarrays += ctOfNumbersSeen.getOrDefault(num + k, 0);
//             goodSubarrays += ctOfNumbersSeen.getOrDefault(num - k, 0);
            
//             ctOfNumbersSeen.put(num, ctOfNumbersSeen.getOrDefault(num, 0) + 1);
//         }
        
//         return goodSubarrays;
//     }
    
//     static long[] prefix;
//     static long[] suffix;
    
//     // Takes O(1) time.
//     static long getRangeSum(int start, int end) {
//         // System.out.printf("%d, %d, %d\n", start, end, suffix[start] + prefix[end] - suffix[0]);
//         return (suffix[start] + prefix[end] - suffix[0]);
//     }
    
//     public long maximumSubarraySum(int[] nums, int k) {
//         HashMap<Integer, ArrayList<Integer>> positionOfNumsSeen = new HashMap<>();
//         HashSet<Integer> numbersSeen = new HashSet<>();
        
//         int n = nums.length;
//         prefix = new long[n];
//         suffix = new long[n];
        
//         prefix[0] = nums[0];
//         suffix[n - 1] = nums[n - 1];
        
//         for(int i = 1; i < n; i++) {
//             prefix[i] = prefix[i - 1] + (long)nums[i];
//             suffix[n - 1 - i] = suffix[n - i] + (long)nums[n - 1 - i];
//         }
        
//         // System.out.printf("%s, %s\n", Arrays.toString(prefix), Arrays.toString(suffix));
        
//         long maxSum = Long.MIN_VALUE;
        
//         for(int endIdx = 0; endIdx < nums.length; endIdx++) {
//             int num = nums[endIdx];
//             if(positionOfNumsSeen.containsKey(num + k)) {
//                 for(int startIdx: positionOfNumsSeen.get(num + k)) {
//                     maxSum = Math.max(getRangeSum(startIdx, endIdx), maxSum);
//                 }
//             }
            
//             if(positionOfNumsSeen.containsKey(num - k)) {
//                 for(int startIdx: positionOfNumsSeen.get(num - k)) {
//                     maxSum = Math.max(getRangeSum(startIdx, endIdx), maxSum);
//                 }
//             }
            
//             positionOfNumsSeen.putIfAbsent(num, new ArrayList<>());
//             positionOfNumsSeen.get(num).add(endIdx);
//         }
        
//         if(maxSum == Long.MIN_VALUE) {
//             return 0;
//         }
//         return maxSum;
//     }
    
    static long[] prefix;
    static long[] suffix;
    
    // Takes O(1) time.
    static long getRangeSum(int start, int end) {
        // System.out.printf("%d, %d, %d\n", start, end, suffix[start] + prefix[end] - suffix[0]);
        return (suffix[start] + prefix[end] - suffix[0]);
    }
    
    // O(N^2)
    public long maximumSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> positionOfNumsSeen = new HashMap<>();
        
        int n = nums.length;
        prefix = new long[n];
        suffix = new long[n];
        
        prefix[0] = nums[0];
        suffix[n - 1] = nums[n - 1];
        
        for(int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + (long)nums[i];
            suffix[n - 1 - i] = suffix[n - i] + (long)nums[n - 1 - i];
        }
        
        // System.out.printf("%s, %s\n", Arrays.toString(prefix), Arrays.toString(suffix));
        
        long maxSum = Long.MIN_VALUE;
        
        for(int endIdx = 0; endIdx < nums.length; endIdx++) {
            int num = nums[endIdx];
            if(positionOfNumsSeen.containsKey(num + k)) {
                maxSum = Math.max(getRangeSum(positionOfNumsSeen.get(num + k), endIdx), maxSum);
            }
            
            if(positionOfNumsSeen.containsKey(num - k)) {
                maxSum = Math.max(getRangeSum(positionOfNumsSeen.get(num - k), endIdx), maxSum);
            }
               
            if(positionOfNumsSeen.containsKey(num)) {
                // If the sum of the preceding subarray is negative, then we move the pointer ahead.
                if(getRangeSum(positionOfNumsSeen.get(num), endIdx - 1) < 0) {
                    positionOfNumsSeen.put(num, endIdx);
                }   
            }
            else {
                positionOfNumsSeen.put(num, endIdx);
            }
        }
        
        if(maxSum == Long.MIN_VALUE) {
            return 0;
        }
        return maxSum;
    }
}