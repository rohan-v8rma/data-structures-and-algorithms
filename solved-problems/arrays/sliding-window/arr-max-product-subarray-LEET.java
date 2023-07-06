// Problem link: https://leetcode.com/problems/maximum-product-subarray
// See notes for proper algorithm explanation

class Solution {
    //* Self developed algorithm (Divide and Conquer approach)

    static int getSubArrayProduct(int[] nums, int startI, int endI) {
        if(endI - startI < 0) {
            return Integer.MIN_VALUE;
        }

        int product = 1;
        for(int index = startI; index <= endI; index++) {
            product *= nums[index];
        }

        return product;
    }

    static int findMaxEvenNegativeSegment(int[] nums, int startIndex, int endIndex, ArrayList<Integer> negativeIndices, int productTillNow) {
        int maxProduct = productTillNow;

        for(int negativeIndex: negativeIndices) {
            int productToLeft = getSubArrayProduct(nums, startIndex, negativeIndex - 1);
            int productToRight = getSubArrayProduct(nums, negativeIndex + 1, endIndex);
            if(productToLeft > maxProduct) {
                maxProduct = productToLeft;
            }

            if(productToRight > maxProduct) {
                maxProduct = productToRight;
            }
        }

        return maxProduct;
    }


    static int maxProduct(int[] nums, int startIndex, int endIndex) {
        if(endIndex - startIndex == 0) {
            return nums[startIndex];
        }
        else if(endIndex - startIndex < 0) {
            return Integer.MIN_VALUE;
        }

        int productTillNow = 1;
        /* 
        This flag is to see whether any elements have been encountered till now OR not.

        Useful in the case where 0 is the first element like : 0 -2 0

        See usage below.
        */
        boolean trueProduct = false;

        ArrayList<Integer> negativeIndicesTillNow = new ArrayList<>();
        
        
        for(int index = startIndex; index <= endIndex; index++) {            
            if(nums[index] == 0) {
                
                /* 
                If only the current 0 is considered as the sub-array.

                This is useful when the sub-arrays to the left and right have completely negative products
                */
                int maxProduct = 0;


                int maxProductFromRight = maxProduct(nums, index + 1, endIndex);
                int maxProductFromLeft = trueProduct 
                ? (
                    negativeIndicesTillNow.size() % 2 == 0 
                    ? productTillNow 
                    : findMaxEvenNegativeSegment(nums, startIndex, index - 1, negativeIndicesTillNow, productTillNow)
                )
                : Integer.MIN_VALUE;
                ;

                if(maxProduct < maxProductFromLeft) {
                    maxProduct = maxProductFromLeft;
                }

                if(maxProduct < maxProductFromRight) {
                    maxProduct = maxProductFromRight;
                }

                return maxProduct;
            }
            else {
                productTillNow *= nums[index];
                trueProduct = true;
                if(nums[index] < 0) {
                    // numOfNegatives++;
                    negativeIndicesTillNow.add(index);
                }
            }
        }

        return (
            negativeIndicesTillNow.size() % 2 == 0 
            ? productTillNow 
            : findMaxEvenNegativeSegment(nums, startIndex, endIndex, negativeIndicesTillNow, productTillNow)
        );
    }

    public int maxProduct(int[] nums) {
        //* Calling self developed alorithm
        // return maxProduct(nums, 0, nums.length - 1);

        //* Striver's algorithm - SLIDING WINDOW (basic) (Lowest runtime of 0ms) 
        // int maxProduct = Integer.MIN_VALUE;

        // // Calculating prefix products
        // int prefixProduct = 1;
        // for(int index = 0; index < nums.length; index++) {
        //     prefixProduct *= nums[index];
           
        //     // If a higher sub array product is reached, update maxProduct
        //     if(prefixProduct > maxProduct) {
        //         maxProduct = prefixProduct;
        //     }


        //    /* 
        //    Resetting the prefix product to 1, so that prefix product can be calculated from the next element onwards.
        //    In one sense, we are ending the previous window, and starting a new window
        //    */

        //     if(prefixProduct == 0) {
        //         prefixProduct = 1;
        //     }
        // }

        // // Calculating suffix products
        // int suffixProduct = 1;
        // for(int index = nums.length - 1; index >= 0; index--) {
        //     suffixProduct *= nums[index];
           
        //     // If a higher sub array product is reached, update maxProduct
        //     if(suffixProduct > maxProduct) {
        //         maxProduct = suffixProduct;
        //     }


        //    /* 
        //    Resetting the prefix product to 1, so that prefix product can be calculated from the next element onwards.
        //    In one sense, we are ending the previous window, and starting a new window
        //    */
        //     if(suffixProduct == 0) {
        //         suffixProduct = 1;
        //     }
        // }

        //* Striver's algorithm - SLIDING WINDOW (one loop only) (Runtime of 1ms)
        int maxProduct = Integer.MIN_VALUE;

        // Calculating prefix and suffix products in the same loop
        int prefixProduct = 1;
        int suffixProduct = 1;
        int n = nums.length;
        for(int index = 0; index < n; index++) {
            /* 
            Resetting the prefix product to 1, so that prefix product can be calculated from the next element onwards.
            In one sense, we are ending the previous window, and starting a new window
            */
            if(prefixProduct == 0) {
                prefixProduct = 1;
            }

            /* 
            Resetting the prefix product to 1, so that prefix product can be calculated from the next element onwards.
            In one sense, we are ending the previous window, and starting a new window
            */
            if(suffixProduct == 0) {
                suffixProduct = 1;
            }

            prefixProduct *= nums[index];
            
            // Calculating suffix from end
            suffixProduct *= nums[n - 1 - index];
            
            // If a higher sub array product is reached, update maxProduct
            if(prefixProduct > maxProduct) {
                maxProduct = prefixProduct;
            }
            if(suffixProduct > maxProduct) {    
                maxProduct = suffixProduct;
            }
            
        }

        return maxProduct;
    }
}