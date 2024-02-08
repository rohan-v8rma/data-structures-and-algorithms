// https://leetcode.com/problems/range-sum-query-immutable/

class NumArray {
    int[] prefixSum;
    int[] suffixSum;

    public NumArray(int[] nums) {
        int n = nums.length;

        prefixSum = new int[n];
        suffixSum = new int[n];

        prefixSum[0] = nums[0];
        suffixSum[n - 1] = nums[n - 1];

        for(int i = 1; i < n; i++) {
            prefixSum[i] = nums[i] + prefixSum[i - 1];
            suffixSum[n - 1 - i] = suffixSum[n - i] + nums[n - 1 - i];
        }
    }
    
    public int sumRange(int left, int right) {
        return prefixSum[right] + suffixSum[left] - suffixSum[0];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */