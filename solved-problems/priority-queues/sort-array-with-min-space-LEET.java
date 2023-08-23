// https://leetcode.com/problems/sort-an-array

class Solution {
    static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    // Iterative heapify, uses O(1) space every time.
    static void iterativeHeapify(int idx, int[] arr, int arrLen) {
        int swapIdx, temp;
        while(true) {
            swapIdx = idx;
            
            temp = 2 * idx + 1;
            
            if(
                temp < arrLen
                &&
                arr[swapIdx] < arr[temp]
            ) {
                swapIdx = temp;
            }
            
            temp += 1;
            
            if(
                temp < arrLen
                && 
                arr[swapIdx] < arr[temp]
            ) {
                swapIdx = temp;
            }
            
            if(swapIdx == idx) {
                break;
            }

            // Perform the swap of the parent with the child.
            swap(arr, idx, swapIdx);

            idx = swapIdx;
        }
    }

    public int[] sortArray(int[] nums) {
        // perform using heapify for least TC   
        int numsLen = nums.length;

        /* 
        Starting index is the first internal node.
        We skip all leaf nodes since they don't need to be heapified.
        */
        for(int idx = (numsLen / 2) - 1; idx >= 0; idx--) {
            iterativeHeapify(idx, nums, numsLen);
        }

        

        for(int element = 1; element <= numsLen; element++) {
            /* 
            Swapping the largest element of heap with the last element 
            in heap
            */
            swap(nums, 0, numsLen - element);
            
            /*
            Executing iterative SIFT DOWN on the 0th index, to satisfy
            the max heap property again.
            */
            iterativeHeapify(0, nums, numsLen - element);
        }

        return nums;
    }
}