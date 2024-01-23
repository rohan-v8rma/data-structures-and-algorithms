// https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/

class Solution {
    public boolean check(int[] nums) {
        boolean dipSeen = false;

        int minimumNumBeforeDip = nums[0];

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] < nums[i - 1]) {
                // If there is a second dip, this array cannot be sorted through just rotation.
                if(dipSeen) {
                    return false;
                }

                dipSeen = true;
            }

            /* 
            If a dip has already been seen, we compare the current number 
            with the min. number before the dip. 
            
            If the current number is larger than it, .
            */
            if(dipSeen && nums[i] > minimumNumBeforeDip) {
                return false;
            }
            
            
            if(!dipSeen){
                minimumNumBeforeDip = Math.min(minimumNumBeforeDip, nums[i]);
            }
        }

        return true;
    }
}