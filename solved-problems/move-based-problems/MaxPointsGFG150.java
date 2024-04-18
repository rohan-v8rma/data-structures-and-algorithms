// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-150/problems
// TODO: Make notes (In move based problems section)

class Solution {
    public long maxPoints(int n, int[][] arr) {
        long[][] lArr = new long[n][2];
        
        long[] add = new long[n];
        
        long max = Integer.MIN_VALUE;
        
        for(int i = 0; i < n; i++) {
            max = Math.max(arr[i][0] + add[i], max);
            
            int withoutCapIdx = i + 1;
            int nextCapIdx = i + arr[i][1] + 1;
            if(nextCapIdx < n) {
                add[nextCapIdx] = Math.max(add[nextCapIdx], add[i] + arr[i][0]);
            }
            
            if(withoutCapIdx < n) {
                add[withoutCapIdx] = Math.max(add[withoutCapIdx], add[i]);
            }
        }
        
        return max;
        
    }
}
