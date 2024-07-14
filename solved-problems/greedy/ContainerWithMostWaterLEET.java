// https://leetcode.com/problems/container-with-most-water/
// TODO: Make notes

class Solution {
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;

        int maxWater = 0;
    
        while(l < r) {
            /* 
            Max Water is updated everytime the pointers move.

            We don't need to keep LEFT_MAX and RIGHT_MAX,
            because that max value would have already been 
            used to calculate water stored in a previous iteration
            AND it would have had more width, ensuring maximum water.
            */
            maxWater = Math.max(
                maxWater, 
                Math.min(height[l], height[r]) * (r - l)
            );

            /* 
            If left block is lower than right block,
            we move it ahead with the hope of find some block
            equal to or greater than right block,
            so that water is maximized.
            */
            if(height[l] <= height[r]) {
                l++;
            }
            else {
                r--;
            }
        }

        return maxWater;
    }
}