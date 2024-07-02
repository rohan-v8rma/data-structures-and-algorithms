class Solution {
    int recurse(int n) {
        int numOfPossibilities = 0;

        if(dp[n] != 0) return dp[n];

        for(int k = 0; k < n; k++) {
            numOfPossibilities += (
                // k nodes in 1 branch
                recurse(k)
                /* 
                n - k - 1 in another 
                (since k in other branch and 1 is root)
                */
                * recurse(n - k - 1)
            );
        }

        return dp[n] = numOfPossibilities;
    }

    int[] dp;

    public int numTrees(int n) {
        dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        return recurse(n);
    }
}