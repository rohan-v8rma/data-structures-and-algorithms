// https://leetcode.com/problems/append-k-integers-with-minimal-sum/description/

class Solution {
    long series(int start, int n) {
        long end = start + n - 1;

        return ((start + end) * n) / 2;
    }

    public long minimalKSum(int[] nums, int k) {
        long sum = 0;

        Arrays.sort(nums);

        int elementsBeforeFirstInteger = Math.min(k, nums[0] - 1);

        k -= elementsBeforeFirstInteger;

        sum += series(1, elementsBeforeFirstInteger);

        for(int idx = 1; idx < nums.length; idx++) {
            if(k == 0) break;
            // Using a loop will cause TLE, since k is 10^8, so 10^8 iterations will run if k decremented one by one.
            // for(int num = nums[idx - 1] + 1; num < nums[idx]; num++) {
            //     k--;
            //     sum += num;

            //     if(k == 0) return sum;
            // }


            int numOfElementsToTake = Math.max(
                Math.min(k, nums[idx] - nums[idx - 1] - 1)
                , 0 // To prevent negative number of elements to be taken
            );

            k -= numOfElementsToTake;

            sum += series(nums[idx - 1] + 1, numOfElementsToTake);            
        }


        if(k > 0) {
            sum += series(nums[nums.length - 1] + 1, k);
        }

        return sum;
    }
}