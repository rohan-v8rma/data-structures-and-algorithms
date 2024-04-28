// https://leetcode.com/problems/median-of-two-sorted-arrays/

class Solution {
    /* 
    BRUTEFORCE

    TC: O(M + N) for merging the arrays and O(1) for getting median
    SC: O(M + N) for storing merged array
    */
    // public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    //     int[] combinedArr = new int[nums1.length + nums2.length];

    //     int onePtr = 0;
    //     int twoPtr = 0;

    //     while(onePtr < nums1.length && twoPtr < nums2.length) {
    //         if(nums1[onePtr] < nums2[twoPtr]) {
    //             combinedArr[twoPtr + onePtr] = nums1[onePtr++];
    //         }
    //         else {
    //             combinedArr[twoPtr + onePtr] = nums2[twoPtr++];
    //         }
    //     }

    //     while(onePtr < nums1.length) {
    //         lastElement = nums1[onePtr++];
    //     }

    //     while(twoPtr < nums2.length) {
    //         combinedArr[twoPtr + onePtr] = nums2[twoPtr++];
    //     }

    //     lastElement % 2 == 0) {
    //         return (
    //             combinedArr[(combinedArr.length / 2) - 1] 
    //             + 
    //             combinedArr[combinedArr.length / 2]
    //         ) / 2.0;
    //     }
     
    //     return combinedArr[combinedArr.length / 2];
        
    // }

    /* 
    BETTER

    TC: O((M + N) / 2) for finding the median
    */
    // public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    //     int combinedLen = nums1.length + nums2.length;

    //     int targetIndex = combinedLen / 2;

    //     int lastElement = 0;
    //     int secondLast = 0;

    //     int onePtr = 0;
    //     int twoPtr = 0;

    //     for(int index = 0; index <= targetIndex; index++) {
    //         secondLast = lastElement;
            
    //         if(onePtr < nums1.length && twoPtr < nums2.length) {
            
    //             secondLast = lastElement;

    //             if(nums1[onePtr] < nums2[twoPtr]) {
    //                 lastElement = nums1[onePtr++];
    //             }
    //             else {
    //                 lastElement = nums2[twoPtr++];
    //             }
    //         }
    //         else if(onePtr < nums1.length) {
    //             secondLast = lastElement;
    //             lastElement = nums1[onePtr++];
    //         }
    //         else {
    //             secondLast = lastElement;
    //             lastElement = nums2[twoPtr++];
    //         }            
    //     }

    //     if(combinedLen % 2 == 0) {
    //         return (
    //             secondLast
    //             + 
    //             lastElement
    //         ) / 2.0;
    //     }

    //     return lastElement;
    // }

    /* 
    BETTER 2 (Just uses linear search on intuition of OPTIMAL)

    TC: O(minimum(M, N))
    */
    // public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
    //     int combinedLen = nums1.length + nums2.length;

    //     int leftLen = combinedLen / 2;
        
    //     // Initially...
    //     int[] arr1 = nums1;
    //     int[] arr2 = nums2;

    //     // Keeping the shorter array as the one we control the pick variable from.
    //     if(nums2.length < nums1.length) {
    //         arr1 = nums2;
    //         arr2 = nums1;
    //     }
        
    //     /*
    //     Pick (combinedLen / 2) elements from second array, making up
    //     the entire left half of the combined array.

    //     This is possible since arr2 > ((arr2 + arr1)/2)
    //     */
    //     int elementsFrom2 = leftLen;

    //     // Pick no elements from first array, for the left half of combined array.
    //     int elementsFrom1 = 0;

    //     for(
    //         int changes = 0; 
    //         changes <= arr1.length; 
    //         changes++
    //     ) {
    //         int leftMax = Integer.MIN_VALUE;
    //         int rightMin = Integer.MAX_VALUE;

    //         boolean isValid = true;

    //         if(elementsFrom1 - 1 >= 0) {
    //             leftMax = arr1[elementsFrom1 - 1];
    //         }
             
    //         if(elementsFrom2 < arr2.length) {
    //             rightMin = arr2[elementsFrom2];
    //         }

    //         if(elementsFrom2 - 1 >= 0) {    
    //             leftMax = Math.max(arr2[elementsFrom2 - 1], leftMax);    
    //         } 

    //         if(elementsFrom1 < arr1.length) {
    //             rightMin = Math.min(arr1[elementsFrom1], rightMin);   
    //         }

    //         if(leftMax > rightMin) {        
    //             isValid = false;
    //         }

    //         if(isValid) {
    //             if(combinedLen % 2 == 0) {
    //                 return (leftMax + rightMin) / 2.0;
    //             }

    //             return rightMin;
    //         }

    //         // Adding an element from array 1 into the left half of the combined array
    //         elementsFrom1++;
    //         // Removing an element from array 2, taken in the left half of the combined array
    //         elementsFrom2--;

    //     }

    //     return 0;
    // }

    // OPTIMAL 1: Recursive Binary Search
    static int leftHalfLen;
    static int combinedLen;
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        combinedLen = nums1.length + nums2.length;

        leftHalfLen = combinedLen / 2;

        if(nums1.length < nums2.length) {
            /* 
            Since nums2 has more elements, we can afford to take 0 elements
            from nums1 because nums2 > ((nums1 + nums2) / 2)
            */
            return binSearch(0, nums1.length, nums1, nums2);
        }

        /* 
        Since nums1 has more elements, we can afford to take 0 elements
        from nums2 because nums1 > ((nums1 + nums2) / 2)
        */
        return binSearch(0, nums2.length, nums2, nums1);
    }

    public double binSearch(
        
        // Minimum possible no. of elements taken from arr1 for left half of combined array.
        int minPossibleFrom1, 
        
        // Maximum possible no. of elements taken from arr1 for left half of combined array.
        int maxPossibleFrom1, 
        
        int[] arr1, 
        int[] arr2
    ) {

        // Taking the middle value as per binary search methodology 
        int elementsFrom1 = (minPossibleFrom1 + maxPossibleFrom1) / 2;
        int elementsFrom2 = leftHalfLen - elementsFrom1;

        if(
            elementsFrom1 - 1 >= 0
            &&
            elementsFrom2 < arr2.length            
            &&

            /* 
            An element from arr1 which should not be in left half
            is actually there.
            */
            arr1[elementsFrom1 - 1] > arr2[elementsFrom2]
        ) {
            /* 
            maxPossibleFrom1 is now 1 less than the value
            we have tested and found to be excessive.
            */
            return binSearch(minPossibleFrom1, elementsFrom1 - 1, arr1, arr2);
        }
        else if(
            elementsFrom2 - 1 >= 0
            &&
            elementsFrom1 < arr1.length            
            &&
            
            /* 
            An element from arr2 which should not be in left half
            is actually there.
            */
            arr2[elementsFrom2 - 1] > arr1[elementsFrom1]
        ) {
            /* 
            minimumPossibleFrom1 is now 1 more than the value
            we have tested and found to be inadequate.
            */
            return binSearch(elementsFrom1 + 1, maxPossibleFrom1, arr1, arr2);
        }

        // Both cases passed so...
        // Reached the exact number of elements from arr1, so return the median
        int leftMax = Integer.MIN_VALUE;
        int rightMin = Integer.MAX_VALUE;

        if(elementsFrom1 - 1 >= 0) {
            leftMax = arr1[elementsFrom1 - 1];
        }
            
        if(elementsFrom2 < arr2.length) {
            rightMin = arr2[elementsFrom2];
        }

        if(elementsFrom2 - 1 >= 0) {    
            leftMax = Math.max(arr2[elementsFrom2 - 1], leftMax);    
        } 

        if(elementsFrom1 < arr1.length) {
            rightMin = Math.min(arr1[elementsFrom1], rightMin);   
        }

        if(combinedLen % 2 == 0) {
            return ((leftMax + rightMin) / 2.0);
        }

        return rightMin;
    }
}