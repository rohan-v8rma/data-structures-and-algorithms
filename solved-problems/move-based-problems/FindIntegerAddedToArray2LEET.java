// https://leetcode.com/problems/find-the-integer-added-to-array-ii/
// TODO: Make notes

class Solution {
    /*____ RECURSION DP, but the cache is NOT that useful. ____*/

    // int getMin(int j, int offset, int[] ni, int[] nj, int diff) {
    //     if(j == nj.length) return diff;
        
    //     // System.out.printf("%d, %d\n", j, offset);
        
    //     int min = Integer.MAX_VALUE;
        
    //     int currentDiff = nj[j] - ni[j + offset];
        
    //     if(dp[offset][j] < currentDiff) return dp[offset][j];
        
    //     if(
    //         diff == Integer.MAX_VALUE
    //         || diff == currentDiff
    //     ) {
    //         min = getMin(j + 1, offset, ni, nj, currentDiff);
    //     }
        
    //     // System.out.printf("%d, %d, %d\n", j, offset, min);
    //     for(int offsetVal = offset + 1; offsetVal <= 2; offsetVal++) {
    //         min = Math.min(getMin(j, offsetVal, ni, nj, diff), min);
    //     }
        
    //     // System.out.printf("%d, %d, %d\n", j, offset, min);
        
    //     // System.out.println(min);
        
    //     return dp[offset][j] = min;
    // }
    
    // int[][] dp;
    
    // public int minimumAddedInteger(int[] nums1, int[] nums2) {
    //     Arrays.sort(nums1);
    //     Arrays.sort(nums2);
        
    //     dp = new int[3][nums2.length];
        
    //     for(int[] dp1d: dp) Arrays.fill(dp1d, Integer.MAX_VALUE);
        
    //     return getMin(0, 0, nums1, nums2, Integer.MAX_VALUE);
    // }

    /* Abandoning paths with more than minimum difference (RECURSION) */

    // int getMin(int j, int offset, int[] ni, int[] nj, int diff) {
    //     if(j == nj.length) return diff;
        
        
    //     int min = Integer.MAX_VALUE;
        
    //     int currentDiff = nj[j] - ni[j + offset];
        
    //     if(confirmedDiff < currentDiff) return Integer.MAX_VALUE;
        
    //     if(
    //         diff == Integer.MAX_VALUE
    //         || diff == currentDiff
    //     ) {
    //         min = getMin(j + 1, offset, ni, nj, currentDiff);
    //     }
        
    //     for(int offsetVal = offset + 1; offsetVal <= 2; offsetVal++) {
    //         min = Math.min(getMin(j, offsetVal, ni, nj, diff), min);
    //     }
        
    //     return confirmedDiff = Math.min(min, confirmedDiff);
    // }
    
    // int confirmedDiff;
    
    // public int minimumAddedInteger(int[] nums1, int[] nums2) {
    //     Arrays.sort(nums1);
    //     Arrays.sort(nums2);
        
    //     confirmedDiff = Integer.MAX_VALUE;
        
    //     return getMin(0, 0, nums1, nums2, Integer.MAX_VALUE);
    // }

    /*____Pairwise taking the numbers to be excluded____*/
    
    // public int minimumAddedInteger(int[] nums1, int[] nums2) {
    //     Arrays.sort(nums1);
    //     Arrays.sort(nums2);

    //     int result = Integer.MAX_VALUE;

    //     for(int i = 0; i < nums1.length; i++) {
    //         for(int j = i + 1; j < nums1.length; j++) {
    //             int offset = 0;
    //             int diff = Integer.MAX_VALUE;
    //             for(int i1 = 0; i1 < nums1.length; i1++) {
    //                 if(i1 == i || i1 == j) {
    //                     offset++;
    //                     continue;
    //                 }
                    
    //                 int currentDiff = nums2[i1 - offset] - nums1[i1];
                    
    //                 if(diff == Integer.MAX_VALUE) {
    //                     diff = currentDiff;
    //                 }
    //                 else if(currentDiff != diff) {
    //                     diff = Integer.MAX_VALUE;
    //                     break;
    //                 }
    //             }

    //             result = Math.min(diff, result);
    //         }
    //     }

    //     return result;
    // }

    /*____OPTIMAL: 2 * N.log(N) + 3 * N____*/
    int validateDifference(int[] ni, int[] nj, int diff) {
        int skipped = 0;

        for(int j = 0; j < nj.length;) {
            if(nj[j] - ni[j + skipped] != diff) skipped++;
            else j++;

            if(skipped > 2) return Integer.MAX_VALUE;
        }
        
        return diff;
    }


    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        // We sort the arrays for easy checking of differences
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        // It is only possible to skip 2 elements, so lets do that.
        
        // We skip 0 elements from start, and evaluate the difference.
        int result = validateDifference(nums1, nums2, nums2[0] - nums1[0]);

        // We skip 1 element from start, and evaluate the difference.
        result = Math.min(result, validateDifference(nums1, nums2, nums2[0] - nums1[1]));

        // We skip 2 elements from start, and evaluate the difference.
        result = Math.min(result, validateDifference(nums1, nums2, nums2[0] - nums1[2]));
        
        // These are the only 3 possible values of differnece.

        return result;
    }


    
}