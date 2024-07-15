// https://leetcode.com/problems/find-in-mountain-array
// TODO: Make notes

/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
 class Solution {
    int findPeakIdx(MountainArray mArr) {
        int n = mArr.length();
        /* 
        We do this so that mid doesn't ever come 
        to the edges of the array, where peak idx 
        definitely doesn't lie
        */
        int low = 1;
        int high = n - 2;

        while(low <= high) {
            int mid = (low + high) / 2;

            int first = mArr.get(mid - 1);
            int second = mArr.get(mid);
            int third = mArr.get(mid + 1);

            if(first < second && second > third) {
                return mid;
            }
            else if(first < second && second < third) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        return 0;
    }

    int findTarget(int low, int high, MountainArray mArr, boolean inc, int t) {
        while(low <= high) {
            int mid = (low + high) / 2;
            int mEl = mArr.get(mid);
            if(mEl == t) return mid;
            
            if(inc) {
                if(mEl < t) {
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
            else {
                if(mEl > t) {
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    public int findInMountainArray(int target, MountainArray mArr) {
        int peakIdx = findPeakIdx(mArr);

        int n = mArr.length();

        int tIdx = Math.min(
            findTarget(0, peakIdx, mArr, true, target),
            findTarget(peakIdx, n - 1, mArr, false, target)
        );

        return tIdx == Integer.MAX_VALUE ? -1 : tIdx;
    }
}