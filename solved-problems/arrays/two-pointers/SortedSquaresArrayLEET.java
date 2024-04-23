// https://leetcode.com/problems/squares-of-a-sorted-array
// TODO: Make notes

class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;

        int smallestAbsElementIdx = 0;
        int val = Math.abs(nums[0]);

        for(int i = 1; i < n; i++) {
            if(Math.abs(nums[i]) < val) {
                smallestAbsElementIdx = i;
                val = Math.abs(nums[i]);
            }
        }

        int l = smallestAbsElementIdx;
        int r = l + 1;

        int[] sq = new int[n];
        int sqIdx = 0;

        while(l >= 0 && r < n) {
            int lVal = nums[l] * nums[l];
            int rVal = nums[r] * nums[r];
            if(lVal < rVal) {
                l--;
                sq[sqIdx++] = lVal;
            }
            else {
                r++;
                sq[sqIdx++] = rVal;
            }
        }

        while(l >= 0) {
            sq[sqIdx++] = nums[l] * nums[l--];
        }

        while(r < n) {
            sq[sqIdx++] = nums[r] * nums[r++];
        }
        

        return sq;
    }
}