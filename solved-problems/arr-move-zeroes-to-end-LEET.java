class Solution {

    /* 
    Time complexity = O(N) + O(K)
    Space complexity = O(1)

    This is a two pointer approach, where 
    - `index` pointer is for keeping track of non-zero numbers.
    - second pointer is for iterating over elements of original array.
    */
    public void moveZeroes(int[] nums) {
        int index = 0;
    
        for(int element: nums) {
            if(element != 0) {
                nums[index++] = element;
            }
        }

        while(index < nums.length) {
            nums[index++] = 0;
        }

        // System.gc();
    }

    
}