public class Solution {
    // O(N^2)
    void rotateOnce(int[] nums) {
        int temp = nums[nums.length - 1];
        for(int index = nums.length -2; index >= 0; index--) {
            nums[index + 1] = nums[index]; 
        }

        nums[0] = temp;
        return;
    }

    public void rotateByK(int[] nums, int k) {
        // Checking if k is greater than the length of the array, and reducing in that case.
        k %= nums.length;
        for(int iteration = 1; iteration <= k; iteration++) {
            rotateOnce(nums);
        }
    }


    // O(N + K)
    public void rotateByK_Better(int[] nums, int k) {
        /* 
        Checking if k is greater than the length of the array, 
        and reducing unnecessary rotations in that case.
        */
        k %= nums.length;

        /* 
        Using a dynamically allocated fixed size array,
        instead of ArrayList to avoid complexity of resize operation
        */
        int[] endPortion = new int[k];

        // Temporary array for storing the end portion of the array.
        for(int index = 0; index < k; index++) {
            endPortion[index] = nums[nums.length - k + index];
        }

        // Filling the end portion of the array with rotated elements.
        for(int index = nums.length - 1; index >= k; index--) {
            nums[index] = nums[index - k];
        }

        // Writing the end portion of the original array to the front of the modified array.
        for(int index = 0; index < k; index++) {
            nums[index] = endPortion[index];
        }

        System.gc();
    }
    
    // O(2N) : Constant Space Complexity
    public void reverse(int startingIndex, int exclusiveEndIndex, int[] nums) {
        exclusiveEndIndex--;
        int temp;
        while(startingIndex < exclusiveEndIndex) {
            temp = nums[startingIndex];
            nums[startingIndex++] = nums[exclusiveEndIndex];
            nums[exclusiveEndIndex--] = temp;
        }
    }

    public void rotateLeft(int[] nums, int k) {
        k %= nums.length;

        // Reversing the order of the first k elements.
        reverse(0, k, nums);
        // Reversing the order of the remaining elements.
        reverse(k, nums.length, nums);
        // Reversing the entire array at the end.
        reverse(0, nums.length, nums);
    }

    // Right rotation
    public void rotateRight(int[] nums, int k) {
        k %= nums.length;

        // Reversing the order of the last k elements.
        reverse(nums.length - k, nums.length, nums);
        // Reversing the order of the remaining elements.
        reverse(0, nums.length - k, nums);
        // Reversing the entire array at the end.
        reverse(0, nums.length, nums);

        System.gc();
    }
}
