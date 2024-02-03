// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-118/problems

class Solution {
    // O(N + 30) 
    static int findMin(int A[], int n) {
        int numOfOperations = 30;
        
        boolean isAnyEvenPresent = false;
        
        // This is 2^30. We could do Math.pow(2, numOfOp) but that would be slower.
        int evenVal = Integer.MAX_VALUE / 2 + 1;
        int oddCt = 0;
        
        for(int i = 0; i < n; i++) {
            if(A[i] % 2 != 0) {
                oddCt++;
            }
            else {
                while(A[i] % evenVal != 0) {
                    evenVal /= 2;
                    numOfOperations--;
                    isAnyEvenPresent = true;
                }
            }
        }
        
        if(oddCt % 2 == 0) {
            return 0;
        }
        
        // No evens present in the array, so the array sum cannot be made even using any operation.
        if(!isAnyEvenPresent) {
            return -1;
        }
        
        
        return numOfOperations;
        
    }
}