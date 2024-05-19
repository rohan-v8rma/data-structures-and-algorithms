// https://leetcode.com/contest/weekly-contest-398/problems/sum-of-digit-differences-of-all-pairs/
// OR https://leetcode.com/problems/sum-of-digit-differences-of-all-pairs

/*
* Effectively we are taking combinations of corresponding places in all numbers
? Which is why it is a permutation and combination problem, due to lack of a better category
*/

class Solution {
    public long sumDigitDifferences(int[] nums) {
        long ct = 0;
        
        int[][] placeCts = new int[10][10];
        
        // First counting the number of digits in all elements of nums
        int digits = 1;
        int copy = nums[0];
        while(copy >= 10) {
            copy /= 10;
            digits++;
        }
            
        // For each place, counting the number of elements with a particular digit
        for(int i = 0; i < digits; i++) {
            for(int j = 0; j < nums.length; j++) {
                placeCts[i][nums[j] % 10]++;
                nums[j] /= 10;    
            }
        }
        
        for(int i = 0; i < digits; i++) {
            int sum = nums.length;
            
            // For each digit, calculating the number of pairs with different digits
            for(int val = 0; val < 10 - 1; val++) {
                sum -= placeCts[i][val];
                ct += placeCts[i][val] * sum;
            }
        }
        
        // The above calculation gives the number of pairs with different digits
        return ct;
    }
}