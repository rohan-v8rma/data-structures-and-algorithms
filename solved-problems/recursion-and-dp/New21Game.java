// https://leetcode.com/problems/new-21-game/

class Solution {
    /*
    Recursive solution, got TLE, even after DP
    */
    // int[][] dp;

    // // ArrayList<Integer> arr;

    // int[] getPoints(int score, int n, int k, int maxPts) {
    //     if(score >= k) {
    //         if(score <= n) {
    //             return new int[]{1, 1};
    //         }

    //         return new int[]{0, 1};
    //     }

    //     if(dp[score][1] != 0) return dp[score];

    //     int[] returnVal = new int[2];

    //     for(int current = 1; current <= maxPts; current++) {
    //         // arr.add(current);
            
    //         int[] result = getPoints(score + current, n, k, maxPts);
            
    //         // arr.remove(arr.size() - 1);
    //         returnVal[0] += result[0];
    //         returnVal[1] += result[1];
    //     }

    //     return dp[score] = returnVal;
    // }

    // public double new21Game(int n, int k, int maxPts) {
    //     // arr = new ArrayList<>();
    //     dp = new int[k][2];

    //     int[] result = getPoints(0, n, k, maxPts);

    //     // return 1;
    //     return ((double)result[0]) / result[1];
    // }

    // double[] dp;

    // double getPoints(int score, int n, int k, int maxPts) {
    //     if(score >= k) {
    //         if(score <= n) {
    //             return 1;
    //         }

    //         return 0;
    //     }

    //     if(dp[score] != -1) return dp[score];

    //     double prob = 0;

    //     for(int current = 1; current <= maxPts; current++) {
    //         prob += getPoints(score + current, n, k, maxPts);
    //     }

    //     return dp[score] = prob / maxPts;
    // }

    // public double new21Game(int n, int k, int maxPts) {
    //     dp = new double[k];

    //     Arrays.fill(dp, -1);

    //     return getPoints(0, n, k, maxPts);
    // }

    /*
    ____ITERATIVE SOLUTION____

    Got TLE, since window sum calculation work is being
    repeated.
    */

    // public double new21Game(int n, int k, int maxPts) {
    //     double[] dp = new double[k + maxPts];

    //     for(int score = k - 1 + maxPts; score >= 0; score--) {
    //         if(score >= k) {
    //             dp[score] = (score <= n) ? 1 : 0;
    //         }
    //         else {
    //             double prob = 0;

    //             for(int current = 1; current <= maxPts; current++) {
    //                 prob += dp[score + current];
    //             }

    //             dp[score] = prob / maxPts;
    //         }
    //     }

    //     return dp[0];
    // }

    /*
    ____ITERATIVE SLIDING WINDOW SOLUTION____
    */

    public double new21Game(int n, int k, int maxPts) {
        double[] dp = new double[k + maxPts];

        double slidingWindowValue = 0;

        for(int score = k - 1 + maxPts; score >= k; score--) {
            /*
            This initial sliding window value is the count
            of how many scores achieved `>= k` are `<= n`

            This is divided by maxPts, which is the number
            of draws at `k - 1` points (which is the largest
            score at which draws are allowed. After that draws
            are stopped)
            */
            slidingWindowValue += (
                dp[score] 
                = (score <= n) ? 1 : 0
            );
        }

        for(int score = k - 1; score >= 0; score--) {
            double prob = slidingWindowValue;

            /* 
            Dividing by maxPts to get the actual probability
            to  represent the likelihood of actually reaching 
            this branch.
            
            NOTE that at this point, dp[score] represents what
            is the probabilty of satisfying the criteria, once
            we have achieved `score`
            */
            dp[score] = prob / maxPts;

            /*
            Shifting the sum window, since we need to now 
            calculate the prob for `score - 1`

            In this way, we can calculate, how likely is
            it that we satisfy the criteria, when we're at
            score `0`
            */
            slidingWindowValue += dp[score] - dp[score + maxPts];
        }

        return dp[0];
    }
}