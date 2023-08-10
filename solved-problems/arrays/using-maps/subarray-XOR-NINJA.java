// https://www.codingninjas.com/studio/problems/subarrays-with-xor-k_6826258

import java.util.*;


public class Solution {
    
    // Bruteforce solution: O(n^2) 
    public static int subarraysWithSumK1(int []a, int b) {
        
        int count = 0;
        int xor;

        // The first for-loop determines the start of the set of sub-arrays
        for(int startI = 0; startI < a.length; startI++) {
            xor = 0;
            /*  
            Suppose startI = 0. 
            Then, the inner for-loop tests the xor values of all 
            sub-arrays that start from 0.

            It increases length of the subarray one-by-one
            */
            for(int endI = startI; endI < a.length; endI++) {
                xor ^= a[endI];
                if(xor == b) {
                    count++;
                }
            }
        }

        return count;
    }

    /* 
    Self Developed: TC is O(N^2)

    See TC analysis in notebook after OR subarray question.
    */
    public static int subarraysWithSumK2(int[]a, int b) {
        int count = 0;

        List<Integer> xorsTillCurrentIndex; 
        List<Integer> xorsTillPreviousIndex = new ArrayList<>();


        for(int element: a) {
            if(element == b) {
                count++;
            }

            xorsTillCurrentIndex = new ArrayList<>();
            for(int previousXor: xorsTillPreviousIndex) {
                int xor = previousXor ^ element;
                xorsTillCurrentIndex.add(xor);

                if(xor == b) {
                    count++;
                }
            }

            xorsTillCurrentIndex.add(element);
            xorsTillPreviousIndex = xorsTillCurrentIndex;
        }

        return count;
    }

    /* 
    Optimal: O(N) with O(N) space complexity due to HashMap.

    For complete mathematical logic why this works, see notebook.
    */
    public static int subarraysWithSumK(int[]a, int b) {
        int count = 0;

        Map<Integer, Integer> xorSeen = new HashMap<>();

        // When the subArray has no elements, the XOR is 0.
        int currentXor = 0;
        
        /* 
        This is for cases when the entire sub-array at a particular point 
        has the required XOR; 
        i.e., the starting part of the sub-array that needs to be a discarded
        is EMPTY.
        */
        xorSeen.put(0, 1);
        for(int element: a) {
            currentXor ^= element;
            if(xorSeen.containsKey(currentXor ^ b)) {
                // Adding the count of sub-arrays that when discarded, yield us the required XOR
                count += xorSeen.get(currentXor ^ b);
            }

            xorSeen.put(currentXor, xorSeen.getOrDefault(currentXor, 0) + 1);
        }

        return count;
    }
}