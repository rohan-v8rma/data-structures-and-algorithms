// https://leetcode.com/contest/weekly-contest-398/problems/find-number-of-ways-to-reach-the-k-th-stair/
// OR https://leetcode.com/problems/find-number-of-ways-to-reach-the-k-th-stair

// TODO: Write the tabulation version of this problem

class Solution {
    int pow(int a, int b) {
        if(b == 0) return 1;
        
        int temp = pow(a, b / 2);
        temp *= temp;
        
        if(b % 2 != 0) temp *= a;
        
        return temp;
    }
    
    long getKey(long current, int target, int x) {
        target = (target << 1) + x;
        current = (current << 6) + target;
        
        return current;
    }
    
    int getWays(long current, int target, int jump, boolean decPossible) {        
        long key = getKey(current, jump, decPossible ? 1 : 0);
        if(dp.containsKey(key)) return dp.get(key);
        
        int result = 0;
        
        if(current == target) {
            result = 1;
        }
        
        if(!decPossible && current > target) return 0;
        
        if(decPossible) {
            result += getWays(current - 1, target, jump, false);
        }
        
        if(current <= target) {
            result += getWays(current + pow(2, jump), target, jump + 1, true);    
        }
        
        dp.put(key, result);
        return result;
    }
    
    HashMap<Long, Integer> dp;
    
    public int waysToReachStair(int k) {
        dp = new HashMap<>();
        return getWays(1, k, 0, true);
    }
}