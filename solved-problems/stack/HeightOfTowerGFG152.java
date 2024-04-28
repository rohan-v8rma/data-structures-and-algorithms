// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-152/problems

class Solution {
    public int HeightTower(int n, int k, int[] arr) {
        int heightExceedCt = 0;
        
        int height = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        for(int i = 0; i < n; i++) {
            stack.push(arr[i]);
            height += arr[i];
            
            if(height > k) {
                heightExceedCt++;
                for(int p = 1; !stack.isEmpty() && p <= heightExceedCt; p++) {
                    height -= stack.pop();
                }
            }
        }
        
        return height;
    }
}
