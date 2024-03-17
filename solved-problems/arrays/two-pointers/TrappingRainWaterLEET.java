// https://leetcode.com/problems/trapping-rain-water/

class Solution {
    /* 
    Ok solution: 
    TC: O(N)
    SC: O(2 * N)

    Check water trapped in each cell.
    */
    // public int trap(int[] height) {
    //     int water = 0;

    //     int N = height.length;

    //     int[] prefix = new int[N];
    //     int[] suffix = new int[N];

    //     int prefMax = 0;
    //     int suffMax = 0;
    //     for(int i = 0; i < N; i++) {
    //         prefix[i] = prefMax;
    //         suffix[N - i - 1] = suffMax;

    //         prefMax = Math.max(prefMax, height[i]);
    //         suffMax = Math.max(suffMax, height[N - i - 1]);
    //     }

    //     for(int hIdx = 1; hIdx < N - 1; hIdx++) {
    //         int h = height[hIdx];
    //         water += Math.max(
    //             // Gets the water trapped in present cell.
    //             Math.min(prefix[hIdx], suffix[hIdx]) - h,
    //             /* 
    //             This disallows negative water, when no boundary is present for 
    //             the current position, either on left or right or both
    //             */
    //             0 
    //         );
    //     }

    //     return water;
    // }

    /*
    OPTIMAL SOLUTION
    TC: O(N)
    SC: O(1)
    */
    public int trap(int[] height) {
        int water = 0;

        int N = height.length;

        int lIdx = 0;
        int rIdx = N - 1;

        int leftMaxVal = 0;
        int rightMaxVal = 0;

        while(lIdx <= rIdx) {
            /*
            If the height of the current left block is smaller/eq. to
            current right block, we will move left pointer ahead in an
            effort to find a left block taller than right block, so that
            some water can be stored at position of right block.
            */
            if(height[lIdx] <= height[rIdx]) {
                // Accomodating left position into left max.
                leftMaxVal = Math.max(leftMaxVal, height[lIdx]);

                /* 
                Case I. Water can be stored at current left block.

                We are sure that this is the maximum possible water
                that can be stored at this left block, because this
                leftMaxVal would have only been set if there was a 
                previous right block greater/eq. to that 
                previous left block.

                So, if there is a right block greater/eq. to leftMaxVal,
                then the leftMaxVal is the limiting block.

                Case II. leftMax is less than height of current left block,

                So, water won't be held at the current left block.
                */
                water += Math.max(leftMaxVal - height[lIdx], 0);


                lIdx++;
            }
            // Similar logic.
            else {
                rightMaxVal = Math.max(rightMaxVal, height[rIdx]);

                water += Math.max(rightMaxVal - height[rIdx], 0);

                rIdx--;
            }
        }

        return water;
    }
    
}