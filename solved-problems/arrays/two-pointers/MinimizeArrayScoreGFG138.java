// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-138/problems

class Solution {
    public static int minimizeArrayScore(int n, int[] arr) {
        // code here
        Arrays.sort(arr);
        
        int minScore = Integer.MIN_VALUE;
        
        int start = 0;
        int end = n - 1;
        
        while(start < end) {
            minScore = Math.max(minScore, arr[start] + arr[end]);
            start++;
            end--;
        }
        
        return minScore;
    }
}