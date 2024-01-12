// Problem link: https://leetcode.com/problems/ones-and-zeroes

class Solution {
    // An attempt
    // public int findMaxForm(String[] strs, int m, int n) {
    //     Arrays.sort(strs, (str1, str2) -> Integer.compare(str1.length(), str2.length()));
        
    //     int ct = 0;
    //     for(String s: strs) {
    //         for(char c: s.toCharArray()) {
    //             if(c == '1') {
    //                 if(n == 0) {
    //                     return ct;
    //                 }
    //                 n--;
    //             }
    //             else if(c == '0') {
    //                 if(m == 0) {
    //                     return ct;
    //                 }
    //                 m--;
    //             }
    //         }
    //         ct++;
    //     }

    //     return ct;
    // }


    static int[] count1sAnd0s(String str) {
        int m = 0, n = 0;

        for(char c: str.toCharArray()) {
            if(c == '1') {
                n++;
            }
            else {
                m++;
            }
        }

        return new int[]{m, n};
    }

    /*
    Memoization solution

    See notebook for TC analysis and full explanation
    */
    // static int[][][] dpMatrix3D;
    // static int[][] mnValues;
    // int decideIthString(int remaining0s, int remaining1s, int strIdx) {
    //     /* 
    //     We exceeded the max possible 0's and 1's, 
    //     so we remove the previously selected string from the subset,
    //     by returning -1.
    //     */
    //     if(remaining0s < 0 || remaining1s < 0) {
    //         return -1;
    //     }
    //     /* 
    //     We processed all the strings, without exceeding the limits,
    //     so we return 0 to signify that no more strings can be included
    //     in the subset.
    //     */
    //     else if(strIdx == mnValues.length) {
    //         return 0;
    //     }
        
    //     if(dpMatrix3D[remaining0s][remaining1s][strIdx] != Integer.MIN_VALUE) {
    //         return dpMatrix3D[remaining0s][remaining1s][strIdx];
    //     }

    //     return (
    //         dpMatrix3D[remaining0s][remaining1s][strIdx] 
    //         = 
    //         Math.max(
    //             // TRYING to include the string at current strIdx
    //             1 + decideIthString(remaining0s - mnValues[strIdx][0], remaining1s - mnValues[strIdx][1], strIdx + 1),
    //             // Not including the string at current strIdx
    //             decideIthString(remaining0s, remaining1s, strIdx + 1)
    //         )
    //     );
    // }


    // public int findMaxForm(String[] strs, int quotaOf0s, int quotaOf1s) {
    //     mnValues = new int[strs.length][2];

    //     for(int strIdx = 0; strIdx < strs.length; strIdx++) {
    //         mnValues[strIdx] = count1sAnd0s(strs[strIdx]);
    //     }
        
    //     dpMatrix3D = new int[quotaOf0s + 1][quotaOf1s + 1][strs.length];
        

    //     for(int allocated0s = 0; allocated0s <= quotaOf0s; allocated0s++) {
    //         for(int allocated1s = 0; allocated1s <= quotaOf1s; allocated1s++) {
    //             for(int iS = 0; iS < strs.length; iS++) {
    //                 /* 
    //                 If m == 0 and n == 0, no string can be included,
    //                 which is why we leave those values as 0 (size = 0).
    //                 */
    //                 if(allocated0s != 0 || allocated1s != 0) {
    //                     dpMatrix3D[allocated0s][allocated1s][iS] = Integer.MIN_VALUE;
    //                 }
                    
    //             }
    //         }
    //     }

    //     return decideIthString(quotaOf0s, quotaOf1s, 0);
    // }

    /* 
    Tabulation solution

    Better because it uses 2D matrix only and saves a lot of copying operations.
    
    See notebook for comprehensive explanation.
    */
    static int[][] dpMatrix2D;

    public int findMaxForm(String[] strs, int quotaOf0s, int quotaOf1s) {
        int needed0s, needed1s;
        int[] mn;
        
        dpMatrix2D = new int[quotaOf0s + 1][quotaOf1s + 1];

        for(int strIdx = 0; strIdx < strs.length; strIdx++) {

            mn = count1sAnd0s(strs[strIdx]);
            needed0s = mn[0];
            needed1s = mn[1];

            /* 
            Running the loops in reverse direction so that needed values are not        
            overwritten.

            Since we used a 2D matrix, we don't have to perform copy operations
            like: dp[m][n][strIdx] = dp[m][n][strIdx - 1], because the value is
            already in place. 
            This saves operations makes the code faster.
            */
            for(
                int allocated0s = quotaOf0s; 
                allocated0s >= needed0s; 
                allocated0s--
            ) {
                for(
                    int allocated1s = quotaOf1s; 
                    allocated1s >= needed1s; 
                    allocated1s--
                ) {
                    dpMatrix2D[allocated0s][allocated1s]
                    =
                    Math.max(
                        /* 
                        If we don't include the current string, we use the previous value
                        of this cell, which was calculated in the previous iteration with
                        the previous string index.
                        */
                        dpMatrix2D[allocated0s][allocated1s],
                        /* 
                        Including the current string.
                        We re-use a cell value calculated in the previous iteration,
                        and this is why we are running the loops in decreasing direction.
                        */
                        1 + dpMatrix2D[allocated0s - needed0s][allocated1s - needed1s]
                    );
                }
            }
        }

        return dpMatrix2D[quotaOf0s][quotaOf1s];
    }
}