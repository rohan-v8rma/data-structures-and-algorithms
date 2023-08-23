// See notebook for complete explanation.

class Solution{
    /* 
    Time complexity: O(2 * (M + N))
    M + N for copying to merged array.
    Then (M + N) for bottom up creation of heap.
    
    Space complexity: O(M + N) for merged array.
    Heapify doesn't take stack space since it is iterative.
    */
    public int[] mergeHeaps(int[] a, int[] b, int n, int m) {
        // your code here
        int total = n + m;
        
        int[] merged = new int[n + m];
        
        int idx = 0;
        for(; idx < n; idx++) {
            merged[idx] = a[idx];
        }
        
        for(; idx < total; idx++) {
            merged[idx] = b[idx - n];
        }
        
        // Time complexity of bottom up approach: O(M + N)
        // Avoided leaf nodes by starting from (total / 2) - 1
        for(int heapifyIdx = (total / 2) - 1; heapifyIdx >= 0; heapifyIdx--) {
            iterativeHeapify(heapifyIdx, merged, total);
        }
        
        return merged;
    }
    
    // Stack space is O(1).
    static void iterativeHeapify(int idx, int[] arr, int length) {
        int swapIdx = idx;
        
        while(true) {
            int temp = idx * 2 + 1;
            
            if(
                temp < length
                &&
                arr[temp] > arr[swapIdx]
            ) {
                swapIdx = temp;
            }
            
            temp += 1;
            
            if(
                temp < length
                &&
                arr[temp] > arr[swapIdx]
            ) {
                swapIdx = temp;
            }
            
            if(swapIdx == idx) {
                break;
            }
            
            temp = arr[idx];
            arr[idx] = arr[swapIdx];
            arr[swapIdx] = temp;
            
            idx = swapIdx;
        }
    }
}