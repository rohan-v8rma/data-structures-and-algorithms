// https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/

class Solution {
    /*_____RECURSIVE MEMOIZATION_____*/

    // int[][] dp;

    // int recurse(String s, int left, int right) {
    //     // We have already checked all characters, so no insertions possible
    //     if(left >= right) return 0;

    //     if(dp[left][right] != -1) return dp[left][right];

    //     if(s.charAt(left) == s.charAt(right)) {
    //         return dp[left][right] = recurse(s, left + 1, right - 1);
    //     }

    //     return dp[left][right] = (
    //         1 
    //         + Math.min(
    //             /* 
    //             Adding a character on the right, which corresponds to current left character.
                
    //             This is why we move left ahead, since it is matched with the added
    //             character on the right.
    //             */
    //             recurse(s, left + 1, right),
    //             /* 
    //             Adding a character on the left, which corresponds to current right character.
                
    //             This is why we move right ahead, since it is matched with the added
    //             character on the left.
    //             */
    //             recurse(s, left, right - 1)
    //         )
    //     );
    // }

    // public int minInsertions(String s) {
    //     dp = new int[s.length()][s.length()];

    //     for(int[] dpArr: dp) Arrays.fill(dpArr, -1);

    //     return recurse(s, 0, s.length() - 1);
    // }

    /*_____ITERATIVE TABULATION_____*/

    // public int minInsertions(String s) {
    //     // To keep the recurrences same, we will have to build the solution in BOTTOM UP manner.

    //     int n = s.length();

    //     int[][] dp = new int[n][n];

    //     for(int left = n - 1; left >= 0; left--) {
    //         for(int right = 0; right < n; right++) {
    //             if(left < right) {
    //                 if(s.charAt(left) == s.charAt(right)) {
    //                     dp[left][right] = dp[left + 1][right - 1];
    //                 }
    //                 else {
    //                     dp[left][right] = (
    //                         1 
    //                         + Math.min(
    //                             /* 
    //                             Adding a character on the right, which corresponds to current left character.
                                
    //                             This is why we move left ahead, since it is matched with the added
    //                             character on the right.
    //                             */
    //                             dp[left + 1][right],
    //                             /* 
    //                             Adding a character on the left, which corresponds to current right character.
                                
    //                             This is why we move right ahead, since it is matched with the added
    //                             character on the left.
    //                             */
    //                             dp[left][right - 1]
    //                         )
    //                     );   
    //                 }
    //             }
    //         }
    //     }

    //     return dp[0][n - 1];

    // }

    /*_____ITERATIVE TABULATION WITH SPACE OPTIMIZATION_____*/

    public int minInsertions(String s) {
        // To keep the recurrences same, we will have to build the solution in BOTTOM UP manner.

        int n = s.length();

        int[][] dp = new int[2][n];

        for(int left = n - 1; left >= 0; left--) {
            // Moving current values to previous position, for next iteration.
            dp[1] = dp[0];
            // Resetting current positions, for next iteration.
            dp[0] = new int[n];

            for(int right = 0; right < n; right++) {
                if(left < right) {
                    if(s.charAt(left) == s.charAt(right)) {
                        dp[0][right] = dp[1][right - 1];
                    }
                    else {
                        dp[0][right] = (
                            1 
                            + Math.min(
                                /* 
                                Adding a character on the right, which corresponds to current left character.
                                
                                This is why we move left ahead, since it is matched with the added
                                character on the right.
                                */
                                dp[1][right],
                                /* 
                                Adding a character on the left, which corresponds to current right character.
                                
                                This is why we move right ahead, since it is matched with the added
                                character on the left.
                                */
                                dp[0][right - 1]
                            )
                        );   
                    }
                }
            }
        }

        return dp[0][n - 1];

    }
}