// https://leetcode.com/problems/subarray-product-less-than-k

class Solution {
    /* 
    Bruteforce: O(N^2) would be to test all sub-arrays

    But that would fail since max value of N is 3 * 10^4,
    so no. of operations = 9 * 10^8
    */

    /*
    N and N(logN) are acceptable

    Not that no division can be performed in this question since division by 0 error can occur.
    */

    // Implementation that uses HashMap (see comparison with TreeMap in notebook)
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        /* 
        If k is 0 or 1, no sub-array has product less than that
        since an element of the array is at minimum = 1.
        */
        if(k <= 1) {
            return 0;
        }

        int count = 0;
 
        Map<Integer, Integer> productTillLastIndex = new HashMap<>();
        Map<Integer, Integer> productsInvolvingCurr;

        for(int num: nums) {

            /* 
            Since we have only positive numbers according to the constraints,
            there is no possibility of negative product sub-arrays.

            So, if num is bigger than k, there is no chance of it generating 
            subarrays that have product less than or equal to k.
            */
            if(num < k) {
                // 1 element array, starting at the current number.
                count++;
                productsInvolvingCurr = new HashMap<>();
                
                for(Map.Entry<Integer, Integer> prevProduct: 
                    productTillLastIndex.entrySet()
                ) {
                    int currentProduct = prevProduct.getKey() * num;
                    
                    if(currentProduct < k) {
                        int prevCount = prevProduct.getValue();
                        productsInvolvingCurr.put(currentProduct, prevCount);
                        count += prevCount;
                    }
                }

                productsInvolvingCurr.put(
                    num, 
                    productsInvolvingCurr.getOrDefault(num, 0) + 1
                );

                productTillLastIndex = productsInvolvingCurr;
            } 
            else {
                productTillLastIndex = new HashMap<>();
            }
        }
        return count;
    }


    // Implementation that uses TreeMap (see comparison with HashMap in notebook)
    public int numSubarrayProductLessThanK1(int[] nums, int k) {
        /* 
        If k is 0 or 1, no sub-array has product less than that
        since an element of the array is at minimum = 1.
        */
        if(k <= 1) {
            return 0;
        }

        int count = 0;
 
        Map<Integer, Integer> productTillLastIndex = new TreeMap<>();
        Map<Integer, Integer> productsInvolvingCurr;

        for(int num: nums) {

            /* 
            Since we have only positive numbers according to the constraints,
            there is no possibility of negative product sub-arrays.

            So, if num is bigger than k, there is no chance of it generating 
            subarrays that have product less than or equal to k.
            */
            if(num < k) {
                // 1 element array, starting at the current number.
                count++;
                productsInvolvingCurr = new TreeMap<>();
                
                for(Map.Entry<Integer, Integer> prevProduct: 
                    productTillLastIndex.entrySet()
                ) {
                    int currentProduct = prevProduct.getKey() * num;
                    
                    if(currentProduct < k) {
                        int prevCount = prevProduct.getValue();
                        productsInvolvingCurr.put(currentProduct, prevCount);
                        count += prevCount;
                    }
                    else {
                        break;
                    }
                }

                productsInvolvingCurr.put(
                    num, 
                    productsInvolvingCurr.getOrDefault(num, 0) + 1
                );

                productTillLastIndex = productsInvolvingCurr;
            } 
            else {
                productTillLastIndex = new TreeMap<>();
            }
        }

        return count;
    }

    // Optimal solution: TC analysis and explanation in notebook.
    public int numSubarrayProductLessThanK(int[] nums, int k) {

        /* 
        If k is 0 or 1, no sub-array has product less than that
        since an element of the array is at minimum = 1.
        */
        if(k <= 1) {
            return 0;
        }

        int validSubArrCount = 0;

        int windowStart = 0;
        int windowEnd = 0;


        int subArrayProduct = 1;
        /* 
        No new elements to add and make a valid window 
        (a valid window is one that has PRODUCT < K)
        */
        while(windowEnd < nums.length) {
            subArrayProduct *= nums[windowEnd];

            /* 
            It is possible that many numbers need to be removed 
            from the invalid sub-array, to make it valid again, 
            which is why this is being done using a while loop.
            */
            while(subArrayProduct >= k) {    
                // This division is possible since array doesn't have any 0s
                subArrayProduct /= nums[windowStart++];
            }

            /*
            This is based on the fact that if a particular sub-array
            of positive integers has product less than k, 
            portions of it will also satisfy that condition.

            See noteboook for more in-depth explanation.
            */
            validSubArrCount += windowEnd - windowStart + 1;
            /* 
            Incrementing windowEnd to increase window size once the 
            formula has been used to add to the valid sub-array count.
            */
            windowEnd++;
        }


        return validSubArrCount;
    }
}