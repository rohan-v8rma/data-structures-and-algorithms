// https://leetcode.com/contest/biweekly-contest-168/problems/count-ways-to-choose-coprime-integers-from-rows/

class Solution {
public:
    int gcd(int a, int b) {
        if(b > a) {
            return gcd(b, a);
        }

        if(b == 0) return a;

        return gcd(b, a % b);
    }

    int MODULO = pow(10, 9) + 7;

    int add(int a, int b) {
        return (a + b) % MODULO;
    }
    
    int ways(int idx, int gcdTillHere, vector<vector<int>>& mat, vector<vector<int>>& dp) {
        if(idx == mat.size()) {
            return gcdTillHere == 1;
        }

        if(dp[gcdTillHere][idx] != -1) {
            return dp[gcdTillHere][idx];
        }

        int total = 0;

        for(int i = 0; i < mat[idx].size(); i++) {
            total = add(
                total,
                ways(idx + 1, gcd(gcdTillHere, mat[idx][i]), mat, dp)
            );
        }

        dp[gcdTillHere][idx] = total;

        return total;
    }
    
    int countCoprime(vector<vector<int>>& mat) {
        int total = 0;

        vector<vector<int>> dp(151, vector<int>(mat.size(), -1));
        
        for(int i = 0; i < mat[0].size(); i++) {
            total = add(
                total,
                ways(1, mat[0][i], mat, dp)
            );            
        }

        return total;
    }
};©leetcode
