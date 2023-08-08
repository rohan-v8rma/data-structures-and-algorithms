// Problem link: https://leetcode.com/problems/product-of-array-except-self/description/

class Solution {
    /* 
    Simple yet non-working solution, 
    multiply all elements and individually divide elements one-by-one
    to get product except self for each index.

    BUT, THIS FAILS WHEN EVEN ONE ELEMENT IS 0
    */
    // public int[] productExceptSelf(int[] nums) {
        
    // }

    // Calculate prefix and suffix array.
    public int[] productExceptSelf(int[] nums) {

        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];

        int prefixProduct = 1;
        int suffixProduct = 1;

        int n = nums.length - 1;

        for(int index = 0; index <= n; index++) {
            prefix[index] = (prefixProduct *= nums[index]);
            suffix[n - index] = (suffixProduct *= nums[n - index]);
        }

        int[] prodExceptSelf = new int[nums.length];
        prodExceptSelf[0] = suffix[1];
        prodExceptSelf[n] = prefix[n - 1];
        for(int index = 1; index < n; index++) {
            prodExceptSelf[index] = prefix[index - 1] * suffix[index + 1];
        }

        return prodExceptSelf;
    }
}