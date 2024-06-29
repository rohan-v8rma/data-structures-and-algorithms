// https://leetcode.com/problems/minimum-moves-to-pick-k-ones/
// TODO: Try train of thought 1 approach

class Solution {
    /*
    ____BRUTEFORCE____

    TC: O(N^2) or O(N * K) approx. Either of these will cause TLE.
    */

    // public long getMoveCt(int i, int[] nums, int k, int maxChanges) {
    //     long currentMoveCt = 0; 
    //     int chosen1s = nums[i];


    //     if(chosen1s == k) return currentMoveCt;

    //     if(i - 1 >= 0 && nums[i - 1] == 1) {
    //         currentMoveCt++;
    //         chosen1s++;
    //     }

    //     if(chosen1s == k) return currentMoveCt;

    //     if(i + 1 < nums.length && nums[i + 1] == 1) {
    //         currentMoveCt++;
    //         chosen1s++;
    //     }

    //     if(chosen1s == k) return currentMoveCt;

    //     // Changing adjacent index and swapping with the player's index.
    //     int conversionsMade = Math.min(maxChanges, k - chosen1s);
    //     chosen1s += conversionsMade;
    //     currentMoveCt += conversionsMade * 2;

    //     if(chosen1s == k) return currentMoveCt;

    //     int left = i - 2;
    //     int right = i + 2;

    //     for(int choice = 1; choice <= k - chosen1s; choice++) {
    //         while(left >= 0 && nums[left] != 1) left--;
    //         while(right < nums.length && nums[right] != 1) right++;

    //         if(left >= 0 && right < nums.length) {
    //             if(i - left < right - i) {
    //                 currentMoveCt += i - left;
    //                 left--;
    //             }
    //             else {
    //                 currentMoveCt += right - i;
    //                 right++;
    //             }
    //         }
    //         else if(left >= 0) {
    //             currentMoveCt += i - left;
    //             left--;
    //         }
    //         else {
    //             currentMoveCt += right - i;
    //             right++;
    //         }
    //     }
        
    //     return currentMoveCt;
    // }

    // public long minimumMoves(int[] nums, int k, int maxChanges) {
    //     long minMoves = getMoveCt(0, nums, k, maxChanges);

    //     for(int i = 0; i < nums.length; i++) {
    //         if(nums[i] == 1) {
    //             minMoves = Math.min(
    //                 minMoves, 
    //                 getMoveCt(i, nums, k, maxChanges)
    //             );
    //         }
            
    //     }

    //     return minMoves;
    // }

    /*
    OPTIMAL: N.log(N)
    */

    // Use this function to calculate both, the number of 1s and the sum of their indices.
    static long getRangeSum(int left, int right, long[] prefix) {
        left = Math.max(left, 0);
        right = Math.min(right, prefix.length - 1);

        if(left > right) return 0;

        left--;

        return (
            prefix[right] 
            - (left < 0 ? 0 : prefix[left])
        );
    }

    static int getRadius(int i, int req, int n) {
        int lbound = 0;
        int ubound = n - 1;

        int radius = n - 1;

        while(lbound <= ubound) {
            int currentRad = (lbound + ubound) / 2;

            int l = i - currentRad;
            int r = i + currentRad;

            int ctOf1s = (int)getRangeSum(l, r, prefix1Ct);

            if(ctOf1s >= req) {
                ubound = currentRad - 1;
                /*
                We can safely update radius everytime because we know everytime
                this if condition is triggered ctOf1s will be closer to the requirement.
                */
                radius = currentRad;
            }
            else {
                lbound = currentRad + 1;
            }
        }

        return radius;
    }

    public long getMoveCt(int i, int[] nums, int k, int maxChanges) {
        if(k == 1) return 0;

        int n = nums.length;

        int req = k;

        // 1 at current position
        k--;

        long cost = 0;

        // Taking left 1, if present and needed
        if(k > 0 && i - 1 >= 0 && nums[i - 1] == 1) {
            cost++;
            k--;
        }
        
        // Taking right 1, if present and needed
        if(k > 0 && i + 1 < n && nums[i + 1] == 1) {
            cost++;
            k--;
        }

        int conversion1s = 0;

        if(k > 0) {
            /* 
            1s obtained through conversion.
            cost holds how many 1s are directly adjacent to i
            */
            conversion1s = (int)Math.min(maxChanges, k);
            cost += 2 * conversion1s;
            k -= conversion1s;
        }

        if(k > 0) {
            /* 
            This means radius of 2 (2 indices from i) to (N - 1)
            This covers the edge case where we are at index 0 and
            the array is [1 0 0 0 0 1]
            */
            int radius = getRadius(i, req - conversion1s, n);

            int l = i - radius;
            int r = i + radius;

            cost += (
                getRangeSum(i + 2, r, prefixIndexSum)
                - getRangeSum(i + 2, r, prefix1Ct) * i
            );

            cost += (
                getRangeSum(l, i - 2, prefix1Ct) * i
                - getRangeSum(l, i - 2, prefixIndexSum)
            );

            // There is an extra 1, due to radius ends having 2 '1's
            if(getRangeSum(l, r, prefix1Ct) - 1 == req - conversion1s) {
                cost -= radius;
            }
        }

        return cost;
    }

    static long[] prefixIndexSum;
    static long[] prefix1Ct;

    public long minimumMoves(int[] nums, int k, int maxChanges) {
        int n = nums.length;

        prefixIndexSum = new long[n];
        prefix1Ct = new long[n];

        prefixIndexSum[0] = 0;
        prefix1Ct[0] = nums[0];

        for(int i = 1; i < n; i++) {
            prefix1Ct[i] = prefix1Ct[i - 1] + nums[i];
            prefixIndexSum[i] = prefixIndexSum[i - 1] + (nums[i] == 1 ? i : 0);
        }

        /* 
        When no 1s in array, changes are the only way of obtaining 1s.
        Each change and swap takes 2 operations.
        */
        long minMoves = maxChanges >= k ? 2 * k : Long.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            if(nums[i] == 1) {
                minMoves = Math.min(
                    minMoves, 
                    getMoveCt(i, nums, k, maxChanges)
                );
            }
            
        }

        return minMoves;
    }
}