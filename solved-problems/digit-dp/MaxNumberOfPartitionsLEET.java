// https://leetcode.com/problems/maximize-the-number-of-partitions-after-operations/submissions

class Solution {
    /*_____BRUTEFORCE SOLUTION (26 * N^2): TLE_____*/

    // static int getNumOfPartitions(int[] charsAsInt, int k) {
    //     int partitions = 0;
        
    //     // Using a Bit-set.
    //     int charsPresent = 0;

    //     for(int c: charsAsInt) {
    //         if(((1 << c) & charsPresent) == 0) {
    //             if(Integer.bitCount(charsPresent) == k) {
    //                 charsPresent = 0;
    //                 partitions++;
    //             }

    //             charsPresent |= 1 << c;
    //         }
    //     }

    //     return partitions + 1;
    // }

    // public int maxPartitionsAfterOperations(String s, int k) {
    //     char[] charArr = s.toCharArray();
        
    //     int[] charsAsInt = new int[charArr.length];
        
    //     for(int i = 0; i < charArr.length; i++) {
    //         charsAsInt[i] = charArr[i] - 97;
    //     }

    //     int numOfPartitions = 0;

    //     for(int charIdx = 0; charIdx < charArr.length; charIdx++) {
    //         int originalLetter = charsAsInt[charIdx];
    //         for(int letter = 0; letter < 26; letter++) {
    //             charsAsInt[charIdx] = letter;
    //             numOfPartitions = Math.max(getNumOfPartitions(charsAsInt, k), numOfPartitions);
    //         }

    //         charsAsInt[charIdx] = originalLetter;
    //     }

    //     return numOfPartitions;
    // }

    /*______Recursion without DP: (N^2 * 12.5)______*/
    /*______Digit DP, Recursive Memoization: Less than N^2 due to common sub-problems.____*/

    static HashMap<Long, Integer> dpMap;

    static int getMaxPartitions(String s, int strIdx, int bitSetOfCharacters, int canChange, int k) {
        if(strIdx == s.length()) return 1;

        Long mapKey = ((long)(strIdx) << 27) + (bitSetOfCharacters << 1) + canChange;

        if(dpMap.containsKey(mapKey)) {
            return dpMap.get(mapKey);
        }

        int maxNumOfPartitions = 0;

        int currentChar = (s.charAt(strIdx) - 'a');
        int currentCharBit = (1 << currentChar);
        int newBitSetOfCharacters = bitSetOfCharacters | currentCharBit;

        if(Integer.bitCount(newBitSetOfCharacters) > k) {
            maxNumOfPartitions = Math.max(
                maxNumOfPartitions,
                1 + getMaxPartitions(s, strIdx + 1, currentCharBit, canChange, k)
            );
        }
        else {
            maxNumOfPartitions = Math.max(
                maxNumOfPartitions,
                getMaxPartitions(s, strIdx + 1, newBitSetOfCharacters, canChange, k)
            );
        }

        if(canChange == 1) {
            for(int c = 0; c < 26; c++) {
                if(c != currentChar) {
                    newBitSetOfCharacters = bitSetOfCharacters | (1 << c);
                    if(Integer.bitCount(newBitSetOfCharacters) > k) {
                        maxNumOfPartitions = Math.max(
                            maxNumOfPartitions,
                            1 + getMaxPartitions(s, strIdx + 1, 1 << c, 0, k)
                        );
                    }
                    else {
                        maxNumOfPartitions = Math.max(
                            maxNumOfPartitions,
                            getMaxPartitions(s, strIdx + 1, newBitSetOfCharacters, 0, k)
                        );
                    }
                }
            }
        }

        dpMap.put(mapKey, maxNumOfPartitions);   
        return maxNumOfPartitions;
    }

    public int maxPartitionsAfterOperations(String s, int k) {
        dpMap = new HashMap<Long, Integer>();

        return getMaxPartitions(s, 0, 0, 1, k);
    }
}