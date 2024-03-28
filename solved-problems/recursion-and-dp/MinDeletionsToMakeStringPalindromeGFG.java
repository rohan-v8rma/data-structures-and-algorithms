// https://www.geeksforgeeks.org/problems/minimum-number-of-deletions4610/1

//{ Driver Code Starts
//Initial Template for Java
import java.io.*;
import java.util.*; 
class GFG{
    public static void main(String args[]) throws IOException { 
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        
        while(t-- > 0){
            int n = Integer.parseInt(read.readLine());
            String str = read.readLine().strip();
            Solution ob = new Solution();
            long ans = ob.minDeletions(str, n); 
            System.out.println(ans);
        }
    } 
} 
// } Driver Code Ends


//User function Template for Java
class Solution 
{ 
    /*_____Recursize Memoization Solution_____*/
    // int[][] dp;
    
    // int recurse(String str, int left, int right) {
    //     if(left >= right) return 0;
        
    //     if(dp[left][right] != -1) return dp[left][right];
        
    //     if(str.charAt(left) == str.charAt(right)) {
    //         // The characters are same, so we move ahead without deleting.
    //         return dp[left][right] = recurse(str, left + 1, right - 1);
    //     }
        
    //     return dp[left][right] = (
    //         1 
    //         + Math.min(
    //             // We delete the character at left pointer
    //             recurse(str, left + 1, right),
    //             // We delete the character at right pointer
    //             recurse(str, left, right - 1)
    //         )
    //     );
    // }
    
    // int minDeletions(String str, int n)
    // {
    //     dp = new int[n][n];
        
    //     for(int[] dpArray: dp) Arrays.fill(dpArray, -1);
        
    //     return recurse(str, 0, n - 1);
    // }
    
    /*_____Iterative Tabulation Solution_____*/
    // int minDeletions(String str, int n)
    // {
    //     int[][] dp = new int[n][n];
    
    //     /* 
    //     We need to build solution in BOTTOM UP manner,
    //     in order to keep the same recurrences as the RECURSIVE solution.
    //     */
        
    //     /*
    //     Building up from left at (n - 1), to left = 0.
    //     Building up from right at (0), to right at (n - 1)
    //     */
    //     for(int left = n - 1; left >= 0; left--) {
    //         for(int right = 0; right < n; right++) {
    //             if(left < right) {
    //                 if(str.charAt(left) == str.charAt(right)) {
    //                     /*
    //                     We can use [left + 1] index, because all left values 
    //                     above current `left` have been traverse, and their values calculated.
                        
    //                     We can use [right - 1] index, because all right values
    //                     below current `right` have been traversed, and their values calculated.
                        
    //                     Both of these facts are due to the direction the 2 for-loops move in.
    //                     */
    //                     dp[left][right] = dp[left + 1][right - 1];
    //                 }
    //                 else {
    //                     dp[left][right] = (
    //                         1
    //                         + Math.min(
    //                             dp[left + 1][right],
    //                             dp[left][right - 1]
    //                         )
    //                     );
    //                 }
    //             }   
    //         }
    //     }
        
    //     return dp[0][n - 1];
    // }
    
    /*_____Iterative Tabulation with SPACE OPTIMIZATION_____*/
    
    int minDeletions(String str, int n)
    {
        int[][] dp = new int[2][n];
    
        /* 
        We need to build solution in BOTTOM UP manner,
        in order to keep the same recurrences as the RECURSIVE solution.
        */
        
        /*
        Building up from left at (n - 1), to left = 0.
        Building up from right at (0), to right at (n - 1)
        */
        for(int left = n - 1; left >= 0; left--) {
            // Moving current values to previous position, for next iteration.
            dp[1] = dp[0];
            // Resetting current positions, for next iteration.
            dp[0] = new int[n];
            
            for(int right = 0; right < n; right++) {
                if(left < right) {
                    if(str.charAt(left) == str.charAt(right)) {
                        /*
                        We can use [left + 1] index, because all left values 
                        above current `left` have been traverse, and their values calculated.
                        
                        We can use [right - 1] index, because all right values
                        below current `right` have been traversed, and their values calculated.
                        
                        Both of these facts are due to the direction the 2 for-loops move in.
                        */
                        dp[0][right] = dp[1][right - 1];
                    }
                    else {
                        dp[0][right] = (
                            1
                            + Math.min(
                                dp[1][right],
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