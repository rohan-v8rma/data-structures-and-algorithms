// https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock

class Solution {
    /*
    The logic is similar to that of products less than k.

    Some slight modifications have been made to optimize according to this 
    problem. Try to find them 
    */
    public long getDescentPeriods(int[] prices) {
        /* 
        We know that each individual price is a smooth descent period,
        so we initial the smoothDescents variable using the length of
        the prices array.

        In the algo, we keep the minimum window size as 2 since we have
        already counted all single element windows.
        */
        long smoothDescents = prices.length;
        
        int prevDayPrice = prices[0];
        
        int windowStart = 0;
        
        for(int windowEnd = 1; windowEnd < prices.length; windowEnd++) {    
            int todaysPrice = prices[windowEnd];
            if(prevDayPrice - todaysPrice == 1) {
                smoothDescents += windowEnd - windowStart;
            }
            else {
                windowStart = windowEnd;
            }
            prevDayPrice = todaysPrice;
        }

        return smoothDescents;
    }
}