// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-146/problems

class Solution {
    /*_____RECURSIVE MEMOIZATION_____*/
    // static int recurse(int idx, int sum, int diff, int[] arr) {
    //     if(idx == arr.length) return sum;
        
    //     List<Integer> key = getKey(sum, idx, diff);
        
    //     if(dp.containsKey(key)) return dp.get(key);
        
    //     int result = 0;
        
    //     if(diff == Integer.MAX_VALUE) {
    //         // No previous element, so we can choose or NOT choose freely.
            
    //         // Choosing the current element.
    //         result = recurse(idx + 1, sum + arr[idx], arr[idx] - idx, arr);
            
            
    //         // Not choosing the current element.
    //         result = Math.max(result, recurse(idx + 1, sum, diff, arr));
    //     }
    //     else {
    //         if(diff == arr[idx] - idx) {
    //             // Choosing the current element only if the condition is met.
    //             result = recurse(idx + 1, sum + arr[idx], diff, arr);
    //         }
            
    //         // Not choosing the current element.
    //         result = Math.max(result, recurse(idx + 1, sum, diff, arr));
    //     }
        
    //     dp.put(key, result);
        
    //     return result;    
    // }
    
    // static List<Integer> getKey(int sum, int index, int diff) {
    //     return Arrays.asList(sum, index, diff);
    // }
    
    // static HashMap<List<Integer>, Integer> dp;
    
    
    // public static int maxScoreSubseq(int n, int[] arr) {
    //     dp = new HashMap<>();
        
    //     return recurse(0, 0, Integer.MAX_VALUE, arr);
    // 
    
    /*____O(N) solution____*/
    
    public static int maxScoreSubseq(int n, int[] arr) {
        HashMap<Integer, Integer> sums = new HashMap<>();
        
        int maxSum = Integer.MIN_VALUE;
        
        for(int i = 0; i < n; i++) {
            // Taking all individual elements as valid subsequences
            maxSum = Math.max(maxSum, arr[i]);
            
            if(arr[i] >= 0) {
                if(sums.containsKey(arr[i] - i)) {
                    /* 
                    The sum is already in hashmap, 
                    so we just add the current positive number to the sum,
                    */
                    int currentSum = sums.get(arr[i] - i) + arr[i];    
                    
                    maxSum = Math.max(maxSum, currentSum);
                    
                    sums.put(arr[i] - i, currentSum);
                }
                else {
                    /* 
                    If the sum is NOT present in hashmap, 
                    we assign the current positive number to be the sum
                    */
                    sums.put(arr[i] - i, arr[i]);    
                }    
            }
        }
        
        return maxSum;
    }
}
        
