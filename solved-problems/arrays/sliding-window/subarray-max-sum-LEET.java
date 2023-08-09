// Problem link: 

class Solution {
    /* 
    Sliding window using Kadane's algorithm
    WITHOUT ARRAY INDEX RETRIEVAL
    */
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;

        int sumTillNow = 0; 
        for(int num: nums) {
            /* 
            If the sum accumulated till now is negative, 
            no point including the elements encountered till now since they are driving
            the sum down
            */
            if(sumTillNow < 0) {
                sumTillNow = 0;
            }

            // Adding the current number to the sumTillNow
            sumTillNow += num;

            // We update maxSum, if the sumTillNow is greater
            if(sumTillNow > maxSum) {
                maxSum = sumTillNow;
            }
        }


        return maxSum;      
    }

    /* 
    Sliding window using Kadane's algorithm
    with ARRAY INDEX RETRIEVAL
    */
    public int maxSubArrayWithPrinting(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int subStartIndex = -1;
        int subEndIndex = -1;

        int tempStartIndex = -1;
        int sumTillNow = 0; 
        for(int index = 0; index < nums.length; index++) {
            /* 
            If the sum accumulated till now is negative, 
            no point including the elements encountered till now since they are driving
            the sum down
            */
            if(sumTillNow < 0) {
                sumTillNow = 0;
            }

            // This is the starting point of a window
            if(sumTillNow == 0) {
                tempStartIndex = index;
            }

            // Adding the current number to the sumTillNow
            sumTillNow += nums[index];

            /* 
            We update maxSum, if the sumTillNow is greater

            Also, we update the start and end index of the max sum subarray
            */
            if(sumTillNow > maxSum) {
                maxSum = sumTillNow;
                subStartIndex = tempStartIndex;
                subEndIndex = index;
            }
        }

        for(int index = subStartIndex; index <= subEndIndex; index++) {
            System.out.printf("%d, ", nums[index]);
        }
        System.out.println();

        return maxSum;   
    }
}

