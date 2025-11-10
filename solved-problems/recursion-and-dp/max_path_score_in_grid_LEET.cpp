// https://leetcode.com/problems/maximum-path-score-in-a-grid/

// TLE

// class Solution {
// public:
//     int dfs(int row, int col, vector<vector<int>>& grid, int k, int score, vector<vector<unordered_map<int, int>>>& dp) {
//         if(row >= grid.size() || col >= grid[0].size()) {
//             return -1;
//         }

//         k -= grid[row][col] > 0;

//         if(k < 0) {
//             return -1;
//         }

//         if(dp[row][col].find(k) == dp[row][col].end()) {

//             int additionalScore = grid[row][col];
    
//             if(row == grid.size() - 1 && col == grid[0].size() - 1) {
//                 dp[row][col][k] = additionalScore;
//             }
//             else {
//                 int forwardScore = max(
//                     dfs(row + 1, col, grid, k, score, dp),
//                     dfs(row, col + 1, grid, k, score, dp)
//                 );
    
//                 if(forwardScore == -1) {
//                     dp[row][col][k] = -1;
//                 }
//                 else {
//                     dp[row][col][k] = additionalScore + forwardScore;
//                 }
//             }
//         }

//         return dp[row][col][k];
//     }
    
//     int maxPathScore(vector<vector<int>>& grid, int k) {
//         vector<vector<unordered_map<int, int>>> dp = vector(grid.size(), vector(grid[0].size(), unordered_map<int, int>{}));
//         return dfs(0, 0, grid, k, 0, dp);
//     }
// };

class Solution {
public:
    int dfs(int row, int col, vector<vector<int>>& grid, int k, int score, vector<vector<vector<int>>>& dp) {
        if(row >= grid.size() || col >= grid[0].size()) {
            return -1;
        }

        k -= grid[row][col] > 0;

        if(k < 0) {
            return -1;
        }

        if(dp[row][col][k] == INT_MIN) {

            int additionalScore = grid[row][col];
    
            if(row == grid.size() - 1 && col == grid[0].size() - 1) {
                dp[row][col][k] = additionalScore;
            }
            else {
                int forwardScore = max(
                    dfs(row + 1, col, grid, k, score, dp),
                    dfs(row, col + 1, grid, k, score, dp)
                );
    
                if(forwardScore == -1) {
                    dp[row][col][k] = -1;
                }
                else {
                    dp[row][col][k] = additionalScore + forwardScore;
                }
            }
        }

        return dp[row][col][k];
    }
    
    int maxPathScore(vector<vector<int>>& grid, int k) {
        vector<vector<vector<int>>> dp = vector(grid.size(), vector(grid[0].size(), vector(k + 1, INT_MIN)));
        return dfs(0, 0, grid, k, 0, dp);
    }
};
