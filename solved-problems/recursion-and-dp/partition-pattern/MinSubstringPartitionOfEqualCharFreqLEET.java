// https://leetcode.com/problems/minimum-substring-partition-of-equal-character-frequency/

class Solution {
    /*
    ____RECURSIVE MEMOIZATION 1____

    (SELF DEVELOPED)

    TODO: Figure out time complexity of this solution

    Seems like O(N * 26), but each index probably takes
    more than O(26)
    */

    // boolean isBalancedPartition(int[] ct) {
    //     int min = Integer.MAX_VALUE;
    //     int max = Integer.MIN_VALUE;

    //     for(int i = 0; i < 26; i++) {
    //         if(ct[i] != 0) {
    //             min = Math.min(min, ct[i]);
    //             max = Math.max(max, ct[i]);
    //         }
    //     }

    //     // If all characters have the same count
    //     return min == max;
    // }

    // int getMinNumOfPartitions(char[] sArr, int i, int[] ct) {
    //     // Base case.
    //     if(i == sArr.length) {
    //         if(isBalancedPartition(ct)) {
    //             // This is the last and final partition in this string.
    //             return 1;
    //         }

    //         /* 
    //         This is the length of the string itself, 
    //         so it is anyways the max number of partitions
    //         there can be.
    //         */
    //         return 1000;
    //     }

    //     // Adding current character to current partition counts
    //     ct[sArr[i] - 'a']++;

    //     int result = getMinNumOfPartitions(sArr, i + 1, ct);

    //     /* 
    //     The current partition is a valid one, 
    //     so we can try and split the string again over here
    //     */
    //     if(isBalancedPartition(ct)) {
    //         if(dp[i + 1] == Integer.MAX_VALUE) {

    //             /* 
    //             This is the minimum number of partitions, that
    //             we can obtain in the latter part of the string,
    //             if we were to split here.
    //             */
    //             dp[i + 1] = getMinNumOfPartitions(sArr, i + 1, new int[26]);
    //         }
            
    //         // Creating a new partition, which is why we add 1.
    //         result = Math.min(
    //             result, 
    //             1 + dp[i + 1]
    //         );
    //     }

    //     // Removing the current character from the counts
    //     ct[sArr[i] - 'a']--;

    //     /*
    //     NOTE that we implement memoization, not at this level,
    //     but at the place where we split the string.

    //     Because of that we don't have to store the state of the
    //     partitions array as well.

    //     This is because whenever we split the string, the partitions
    //     array is initialized to 0s, which will remain the same.
    //     */
    //     return result;
    // }

    // int[] dp;

    // public int minimumSubstringsInPartition(String s) {
    //     dp = new int[s.length() + 1];

    //     Arrays.fill(dp, Integer.MAX_VALUE);

    //     return getMinNumOfPartitions(s.toCharArray(), 0, new int[26]);
    // }

    /*
    ____RECURSIVE MEMOIZATION 2____

    Using loop pattern.
    */

    boolean isBalancedPartition(int[] charCt) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < 26; i++) {
            if(charCt[i] != 0) {
                min = Math.min(min, charCt[i]);
                max = Math.max(max, charCt[i]);
            }
        }

        return min == max;
    }

    int getMinNumOfPartitions(char[] sArr, int i) {
        if(dp[i] != Integer.MAX_VALUE) return dp[i];

        int[] charCt = new int[26];

        for(int endIdx = i; endIdx < sArr.length; endIdx++) {
            charCt[sArr[endIdx] - 'a']++;

            if(isBalancedPartition(charCt)) {
                dp[i] = Math.min(
                    dp[i],
                    /*
                    Current partition + minimum number of
                    partitions starting from the character
                    after the end of the current partition.
                    */
                    1 + getMinNumOfPartitions(sArr, endIdx + 1)
                );
            }
        }

        return dp[i];
    }

    int[] dp;

    public int minimumSubstringsInPartition(String s) {
        dp = new int[s.length() + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        /*
        This is like asking how many partitions are in the substring from
        s.length + 1, to s.length, which doesn't make any sense.

        There can be no partitions in a string of negative length.
        */
        dp[s.length()] = 0;



        return getMinNumOfPartitions(s.toCharArray(), 0);
    }

    /*
    ____ITERATIVE TABULATION____

    TC: O(26 * N^2)
    */
    // boolean isBalancedPartition(int[] charCt) {
    //     int min = Integer.MAX_VALUE;
    //     int max = Integer.MIN_VALUE;

    //     for(int i = 0; i < 26; i++) {
    //         if(charCt[i] != 0) {
    //             min = Math.min(min, charCt[i]);
    //             max = Math.max(max, charCt[i]);
    //         }
    //     }

    //     return min == max;
    // }


    // public int minimumSubstringsInPartition(String s) {
    //     char[] sArr = s.toCharArray();

    //     int[] charCt = new int[26];

    //     int[] dp = new int[s.length() + 1];
    //     Arrays.fill(dp, Integer.MAX_VALUE);

    //     /*
    //     If suppose a substring is a valid partition in and of itself,
    //     then dp[startIdx] should be 1.

    //     For dp[startIdx] = 1 + dp[s.length()] to be 1, the second
    //     term has to be 0.
    //     */
    //     dp[s.length()] = 0;

    //     /* 
    //     We will be calculating the minimum number of partitions
    //     required, starting from the smallest substring at the end,
    //     incrementally adding characters to the substring we are 
    //     calculating the minimum number of partitions for.

    //     At the end of this, we will have the minimum number of 
    //     partitions for the entire string.
    //     */
    //     for(int startIdx = s.length() - 1; startIdx >= 0; startIdx--) {
    //         /*
    //         Initializing the charCt array, to see when we have valid
    //         partitions.
    //         */
    //         Arrays.fill(charCt, 0);
            
    //         for(int endIdx = startIdx; endIdx < s.length(); endIdx++) {
    //             charCt[sArr[endIdx] - 'a']++;

    //             if(isBalancedPartition(charCt)) {
    //                 dp[startIdx] = Math.min(
    //                     dp[startIdx],
    //                     /*
    //                     Current partition + minimum number of
    //                     partitions starting from the character
    //                     after the end of the current partition.
    //                     */
    //                     1 + dp[endIdx + 1]
    //                 );
    //             }
    //         }
    //     }

    //     return dp[0];
    // }
}