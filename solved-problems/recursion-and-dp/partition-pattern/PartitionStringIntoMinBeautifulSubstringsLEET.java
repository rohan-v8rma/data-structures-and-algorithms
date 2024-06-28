// https://leetcode.com/problems/partition-string-into-minimum-beautiful-substrings/

class Solution {
    // Function for checking whether a number is power of another. TC: O(31) at max.
    boolean isPower(long num) {
        long result = 1;

        while(result < num) {
            result *= 5;
        }

        return (num == result);
    }

    /*
    ____RECURSIVE MEMOIZATION____

    Partition pattern.
    */
    // int recurse(int index, char[] sArr) {
    //     if(index == sArr.length) return 0;

    //     /* 
    //     If the current index has a 0, we cannot partition the substring from
    //     `index` to end of the string s, so this will return a value of -1.
    //     */
    //     if(sArr[index] == '0') {
    //         return partitionCt[index] = -1;
    //     }

    //     int numPartitions = Integer.MAX_VALUE;

    //     long number = 0;

    //     for(int endIdx = index; endIdx < sArr.length; endIdx++) {
    //         number = (number << 1) + (sArr[endIdx] - '0');

    //         if(isPower(number)) {
    //             int partitionsAhead = recurse(endIdx + 1, sArr);

    //             if(partitionsAhead != -1) {
    //                 numPartitions = Math.min(
    //                     numPartitions,
    //                     1 + partitionsAhead
    //                 );
    //             }
    //         }
    //     }

    //     return partitionCt[index] = numPartitions == Integer.MAX_VALUE ? -1 : numPartitions;
    // }

    // int[] partitionCt;

    // public int minimumBeautifulSubstrings(String s) {
    //     partitionCt = new int[s.length() + 1];

    //     return recurse(0, s.toCharArrxay());
    // }

    /*
    _____ITERATIVE TABULATION_____
    */

    public int minimumBeautifulSubstrings(String s) {
        char[] sArr = s.toCharArray();

        int[] partitionCts = new int[sArr.length + 1];

        for(int startIdx = sArr.length - 1; startIdx >= 0; startIdx--) {
            long num = 0;
            int partitionCt = Integer.MAX_VALUE;
            
            if(sArr[startIdx] != '0') {
                for(int endIdx = startIdx; endIdx < sArr.length; endIdx++) {
                    num = (num << 1) + sArr[endIdx] - '0';
                    if(isPower(num) && partitionCts[endIdx + 1] != -1) {    
                        partitionCt = Math.min(
                            partitionCt,
                            1 + partitionCts[endIdx + 1]
                        );
                    }
                }
            }

            partitionCts[startIdx] = (partitionCt == Integer.MAX_VALUE) ? -1 : partitionCt;
        }

        return partitionCts[0];
    }
}