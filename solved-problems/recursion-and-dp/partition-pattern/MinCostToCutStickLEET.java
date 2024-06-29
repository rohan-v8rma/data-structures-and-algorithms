// https://leetcode.com/problems/minimum-cost-to-cut-a-stick/

class Solution {
    /*
    ____SELF DEVELOPED ALGO (RECURSIVE MEMOIZATION)____   

    Note that cuts.length is at max 100.

    If we were to have a high level look, rodStart and rodEnd
    have 100 possible values which is the number of elements in `cuts`.

    Also, we are looping over `cuts` in each recursive call, which roughly 
    has 100 elements each time (although it will be less)

    So Time complexity : O(C ^ 3) == O(10 ^ 6)
    */
    // long getKey(int rodStart, int rodEnd) {
    //     return ((long)(rodStart) << 32) + rodEnd;
    // }

    // int recurse(int[] cuts, int startIdxCut, int endIdxCut, int rodStart, int rodEnd) {
    //     if(startIdxCut > endIdxCut) return 0;

    //     long key = getKey(rodStart, rodEnd);

    //     if(dp.containsKey(key)) return dp.get(key);

    //     int cost = Integer.MAX_VALUE;

    //     for(int i = startIdxCut; i <= endIdxCut; i++) {
    //         cost = Math.min(
    //             cost, 
    //             (rodEnd - rodStart)
    //             + recurse(cuts, startIdxCut, i - 1, rodStart, cuts[i])
    //             + recurse(cuts, i + 1, endIdxCut, cuts[i], rodEnd)
    //         );
    //     }

    //     dp.put(key, cost);

    //     return cost;
    // }

    // HashMap<Long, Integer> dp;

    // public int minCost(int n, int[] cuts) {
    //     Arrays.sort(cuts);

    //     dp = new HashMap<>();

    //     return recurse(cuts, 0, cuts.length - 1, 0, n);
    // }

    /*
    ____SELF DEVELOPED ALGO (TABULATION)____   

    Simply copied the body of the recursive function from above, 
    but had to make some changes to support the hashmap being used.

    UNIQUE APPROACH since hashmap used in TABULATION approach.
    */
    // long getKey(int rodStart, int rodEnd) {
    //     return ((long)(rodStart) << 32) + rodEnd;
    // }

    // int getRodEndPt(int[] cuts, int i, int n) {
    //     if(i < 0) return 0;
    //     if(i >= cuts.length) return n;

    //     return cuts[i];
    // }

    // HashMap<Long, Integer> dp;

    // public int minCost(int n, int[] cuts) {
    //     Arrays.sort(cuts);

    //     dp = new HashMap<>();

    //     for(int startIdxCut = cuts.length - 1; startIdxCut >= 0; startIdxCut--) {
    //         for(int endIdxCut = startIdxCut; endIdxCut < cuts.length; endIdxCut++) {
    //             // Copying the body of the recursive function
    //             int rodStart = getRodEndPt(cuts, startIdxCut - 1, n);
    //             int rodEnd = getRodEndPt(cuts, endIdxCut + 1, n);

    //             long key = getKey(startIdxCut, endIdxCut);

    //             int cost = Integer.MAX_VALUE;

    //             for(int i = startIdxCut; i <= endIdxCut; i++) {
    //                 cost = Math.min(
    //                     cost, 
    //                     (rodEnd - rodStart)
    //                     + dp.getOrDefault(getKey(startIdxCut, i - 1), 0)
    //                     + dp.getOrDefault(getKey(i + 1, endIdxCut), 0)
    //                 );
    //             }

    //             dp.put(key, cost);
    //         }
    //     }

    //     return dp.get(getKey(0, cuts.length - 1));
    // }

    /*
    ____STRIVER APPROACH (RECURSIVE MEMOIZATION)____

    Striver's approach focuses on keeping the recursive parameters to a minimum
    and converting the full rod endpoints (0 and n) to cut points in the cuts
    array. 

    This simplifies the process of not having to use a hashmap to store rod
    cut points, and simply using cut array indices, which can be covered
    using regular arrays, since cut array max length is 100, wherease
    rod cut points range from 0 to 1000000.
    */
    // int recurse(int[] cuts, int startIdxCut, int endIdxCut) {
    //     if(startIdxCut > endIdxCut) return 0;

    //     /* 
    //     1. This is the DP caching step
    //     2. Note that by keeping the default value as 0, we again and again
    //     are executing some more constant time operations for rods of 1 length, 
    //     that cannot be cut, but that is still better than filling the entire
    //     matrix with -1.

    //     NOTE: We could've also changed the baseline to 1, storing all values
    //     with 1 added to them.
    //     */
    //     if(dp[startIdxCut][endIdxCut] != 0) return dp[startIdxCut][endIdxCut];

    //     int cost = Integer.MAX_VALUE;

    //     for(int i = startIdxCut; i <= endIdxCut; i++) {
    //         cost = Math.min(
    //             cost, 
    //             (cuts[endIdxCut + 1] - cuts[startIdxCut - 1])
    //             + recurse(cuts, startIdxCut, i - 1)
    //             + recurse(cuts, i + 1, endIdxCut)
    //         );
    //     }

    //     return dp[startIdxCut][endIdxCut] = cost;
    // }

    // int[][] dp;

    // public int minCost(int n, int[] cuts) {
    //     Arrays.sort(cuts);

    //     int[] newCuts = new int[cuts.length + 2];
        
    //     // Treating the endpoints of the actual rod as cuts as well, for simplicity
    //     newCuts[0] = 0;
    //     newCuts[newCuts.length - 1] = n;

    //     for(int i = 0; i < cuts.length; i++) {
    //         newCuts[i + 1] = cuts[i];
    //     }

    //     dp = new int[newCuts.length + 1][newCuts.length + 1];

    //     return recurse(newCuts, 1, newCuts.length - 2);
    // }

    /*
    ____STRIVER APPROACH (TABULATION)____

    Striver's approach focuses on keeping the recursive parameters to a minimum
    and converting the full rod endpoints (0 and n) to cut points in the cuts
    array. 

    This simplifies the process of not having to use a hashmap to store rod
    cut points, and simply using cut array indices, which can be covered
    using regular arrays, since cut array max length is 100, wherease
    rod cut points range from 0 to 1000000.
    */
    int[][] dp;

    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);

        int[] newCuts = new int[cuts.length + 2];
        
        // Treating the endpoints of the actual rod as cuts as well, for simplicity
        newCuts[0] = 0;
        newCuts[newCuts.length - 1] = n;

        for(int i = 0; i < cuts.length; i++) {
            newCuts[i + 1] = cuts[i];
        }

        cuts = newCuts;

        dp = new int[cuts.length + 1][cuts.length + 1];

        /* 
        Putting the recursive parameters as loop variables, 
        but moving in opposite direction, to effectively end up at the result 
        that we got at the topmost level in the recursion

        What is effectively happening is we are calculating the splitting cost
        of smaller portions of the rod first, then using those for the bigger
        rod costs.

        We are starting with the smallest possible rod at the end, and increasing
        it slowly.
        */
        for(int startIdxCut = cuts.length - 2; startIdxCut >= 1; startIdxCut--) {
            for(int endIdxCut = startIdxCut; endIdxCut <= cuts.length - 2; endIdxCut++) {
                // Copying the body of the recursive function
                
                int cost = Integer.MAX_VALUE;

                for(int i = startIdxCut; i <= endIdxCut; i++) {
                    cost = Math.min(
                        cost, 
                        (cuts[endIdxCut + 1] - cuts[startIdxCut - 1])
                        + dp[startIdxCut][i - 1]
                        + dp[i + 1][endIdxCut]
                    );
                }

                dp[startIdxCut][endIdxCut] = cost;   
            }
        }

        return dp[1][cuts.length - 2];
    }
}