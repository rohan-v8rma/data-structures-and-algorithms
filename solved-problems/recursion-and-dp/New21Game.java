// TODO: Make notes

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
          slidingWindowValue += (dp[score] = (score <= n) ? 1 : 0);
      }

      for(int score = k - 1; score >= 0; score--) {
          double prob = slidingWindowValue;

          dp[score] = prob / maxPts;

          slidingWindowValue += dp[score] - dp[score + maxPts];
      }

      return dp[0];
  }
}