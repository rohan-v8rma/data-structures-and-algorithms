// https://leetcode.com/problems/find-the-maximum-sum-of-node-values/
// TODO: make notes

class Solution {
    // O(N.log(N))
    // public long maximumValueSum(int[] nums, int k, int[][] edges) {
    //     /* 
    //     We can XOR any 2 non-adjacent nodes, because
    //     if we XOR in pairs along the path between the 2 nodes,
    //     all nodes will be XORRed twice (getting the same value back)
    //     except the start and end nodes.
    //     */
    //     int n = edges.length + 1;

    //     long sum = 0;
        
    //     for(int i = 0; i < nums.length; i++) {
    //         // Calculating the initial sum.
    //         sum += nums[i];

    //         // Calculating the gain/loss for a particular number upon XORRing.
    //         nums[i] = (nums[i] ^ k) - nums[i];
    //     }

    //     /* 
    //     Sorting the amount of gain/loss occurred 
    //     in XORRing a particular number.
    //     */
    //     Arrays.sort(nums);
        
    //     /* 
    //     We XOR elements in pairs, starting from the 2 numbers that 
    //     give us the highest and second highest gain.

    //     We keep XORRing pairs until we see that XORRing the pairs
    //     results in decrease of sum.
    //     */
    //     for(int i = nums.length - 1; i > 0; i -= 2) {
    //         if(nums[i] + nums[i - 1] > 0) {
    //             sum += nums[i] + nums[i - 1];
    //         }
    //         else {
    //             break;
    //         }
    //     }


    //     return sum;
    // }

    // O(N)
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long sum = 0;

        /*
        This variable tracks: 
        - the worst positive change done, meaning the least gain we received
        - the best negative change NOT done, meaning the least loss we didn't receive

        So, at the end, if we XORRed odd number of elements
        we can minus the value of this variable from sum,
        to remove worst change done, or add the best change NOT done.
        */
        int worstDoneOrBestNotDone = Integer.MAX_VALUE;
        
        int xorredCount = 0;

        for(int num: nums) {
            int xorredNum = num ^ k;

            if(xorredNum > num) {
                sum += xorredNum;
                
                // 1 more element is XORRed.
                xorredCount++;

                worstDoneOrBestNotDone = Math.min(
                    worstDoneOrBestNotDone,
                    /* 
                    This way, when we subtract the variable value from sum, 
                    we are reversing the XORRing of this number,
                    making the number of XORRED elements even.
                    */
                    xorredNum - num 
                );
            }
            else {
                // NON xorred num added.
                sum += num;

                worstDoneOrBestNotDone = Math.min(
                    worstDoneOrBestNotDone,
                    /* 
                    Using the minus sign, when we subtract the variable value
                    from sum, we are essentially doing XORRing of this number
                    (by accepting the loss done by XORRing)
                    making the number of XORRED elements even.
                    */
                    -(xorredNum - num)
                );
            }
        }

        // We XORRED odd number of elements
        if(xorredCount % 2 != 0) {
            sum -= worstDoneOrBestNotDone;
        }

        return sum;
    }
    

}