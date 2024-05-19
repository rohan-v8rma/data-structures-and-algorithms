// https://practice.geeksforgeeks.org/contest/gfg-weekly-155-rated-contest/problems

class Solution {
    public static int[] maxSum(int N, int L, int R, int[] arr) {
        int sum = 0;
        
        for(int el: arr) sum += el;
        
        int maxSum = Integer.MIN_VALUE;
        int kVal = 0;
        
        for(int k = L; k <= R; k++) {
            int subLen = 2 * k;
            
            for(int offset = 0; offset <= N - subLen; offset++) {
                int sumCopy = sum;
                
                for(int i = 0; i < k; i++) {
                    int newVal = Math.abs(arr[offset + i]) * ((i + 1) % 2 != 0 ? -1 : 1);
                    
                    if(arr[offset + i] != newVal) {
                        sumCopy += newVal * 2;
                    }
                }
                
                for(int i = 0; i < k; i++) {
                    int newVal = Math.abs(arr[offset + k + i]) * ((i + k + 1) % 2 == 0 ? -1 : 1);
                    
                    if(arr[offset + k + i] != newVal) {
                        sumCopy += newVal * 2;
                    }
                }
                
                
                
                if(sumCopy > maxSum) {
                    // System.out.printf("%d, %d, %d\n", k, offset, sumCopy);
                    maxSum = sumCopy;
                    kVal = k;
                }
            }
            
        }
        
        return new int[]{kVal, maxSum};
    }
}