// https://leetcode.com/problems/bitwise-ors-of-subarrays

class Solution {
    /*  
    This solution is O(32*N) in worst case, due to MONOTONIC INCREASING PROPERTY of OR results.
    This works for the constraint N <= 5 * 10^4.

    See notebook for Time complexity analysis

    It would be O(N^2) if applied to a XOR based question, since XOR results are NOT MONOTONIC INCREASING.
    */
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> allORs = new HashSet<>();

        /* 
        This is the set we OR the current element with, 
        so that we avoid yielding the ORs of non-contiguous sub-arrays.
        */
        Set<Integer> tillPreviousIndexORs = new HashSet<>();
        
        /*
        This is the set we put the ORed values with the current element in.

        Having 2 sets is necessary, so that we don't yield ORs of non-contiguous sub-arrays.
        */
        Set<Integer> currentIndexORs;

        for(int i = 0; i < arr.length; i++) {
            int currentElement = arr[i];
            currentIndexORs = new HashSet<>();
            currentIndexORs.add(currentElement);

            // Replaced by addAll
            // allORs.add(currentElement);

            for(int previousOR: tillPreviousIndexORs) {
                int OR = previousOR | currentElement;
                currentIndexORs.add(OR);

                // Replaced by addAll
                // allORs.add(OR);
            }   

            tillPreviousIndexORs = currentIndexORs;
            //? addAll is more efficient than adding in both sets at the same time.
            allORs.addAll(currentIndexORs);
        }

        return allORs.size();
    }
}