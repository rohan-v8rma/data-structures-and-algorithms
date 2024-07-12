// https://leetcode.com/problems/132-pattern
// TODO: Make notes

class Solution {
    /*
    ____BRUTEFORCE____

    TC: O(N^3)
    */
    // public boolean find132pattern(int[] nums) {
    //     for(int i = 0; i < nums.length; i++) {
    //         for(int j = i + 1; j < nums.length; j++) {
    //             if(nums[i] < nums[j]) {
    //                 for(int k = j + 1; k < nums.length; k++) {
    //                     if(nums[i] < nums[k] && nums[k] < nums[j]) return true;
    //                 }
    //             }
    //         }
    //     }

    //     return false;
    // }

    /*
    ____BETTER____

    TC: O(N^2)
    */
    // public boolean find132pattern(int[] nums) {
    //     int min = Integer.MAX_VALUE;
    //     for(int j = 0; j < nums.length; j++) {
    //         if(min < nums[j]) {
    //             for(int k = j + 1; k < nums.length; k++) {
    //                 if(min <  nums[k] && nums[k] < nums[j]) return true;
    //             }
    //         }
            
    //         min = Math.min(min, nums[j]);
    //     }

    //     return false;
    // }

    /*
    ____OPTIMAL____

    Makes use of monotonically decreasing stack.

    let i: num1, j: num3, k: num2

    i < j < k
    num1 < num2 < num3


    If we continuously get smaller and smaller elements, 
    keep adding them into the stack.
    
    They can be used as candidates for num2, potentially,
    when we ultimately get a larger number

    Because, suppose the array is : 2, 4, 1, 3, 4

    As we traverse from the end, we are in search of a 
    num2, then a matching num3.

    We want to maximize num3, and have num2 as large as
    possible without exceeding num3.

    So, we store 4, then 3, then 1, in the stack.
    
    Now, upon getting another 4, we realize this is a 
    greater element that we've received as compared to what
    is on top of the stack.

    So, we have an opportunity to get a num3, num2 pair.
    But, upon removing 1 from the stack, we realize we have
    yet another candidate for num2, better than 1, since
    it is larger but still less than num3, opening up more
    possibilities for num1 which we may get ahead.

    So, we update num2 to 3. num3 is 4.

    Now upon traversing ahead to 2, we get a num1, less than
    num2. If we had kept num2 as 1, this would not have
    been detected as a valid 132 pattern.
    */
    public boolean find132pattern(int[] nums) {
        /*
        This stack contains the current num3, as well
        as all potential candidates for num3, 
        in monotonically increasing order.
        */
        ArrayDeque<Integer> num3Candidates = new ArrayDeque<>();

        int n = nums.length;

        int num2 = Integer.MIN_VALUE;

        num3Candidates.push(Integer.MAX_VALUE);

        for(int i = n - 1; i >= 0; i--) {
            /*
            If nums[i] is less than num2, it will
            definitely by less than num3 since that
            was validated below, at the time of setting
            the value of num2.
            */
            if(nums[i] < num2) return true;

            /*
            We found a number greater than the current num3
            candidate. So we can update our num3 and assign
            the previous (greatest possible) num3 candidate
            as our new num2.
            */
            if(nums[i] > num3Candidates.peek()) {
                int num3 = nums[i];

                /*
                Maximizing num2, without letting it exceed
                num3
                */
                while(num3 > num3Candidates.peek()) {
                    num2 = num3Candidates.pop();
                }

                // This prevents 2 equal values in the stack.
                if(num3 < num3Candidates.peek()) {
                    num3Candidates.push(num3);
                }
            }
            /*
            Since this is a smaller element, we keep it as
            potential num3 candidate.
            */
            else if(nums[i] < num3Candidates.peek()) {
                num3Candidates.push(nums[i]);
            }
        }

        return false;
    }
}