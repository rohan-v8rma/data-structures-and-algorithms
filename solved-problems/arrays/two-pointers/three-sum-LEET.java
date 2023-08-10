// Problem link: https://leetcode.com/problems/3sum

import java.util.Arrays;

class Solution {
    /* 
    Bruteforce would be 3 for-loops

    But according to N = 3000, N^3 would exceed 10^8
    */
    // public List<List<Integer>> threeSum(int[] nums) {
        
    // }

    /* 
    * Better: O(N^2) * (3.log(3)))

    Number of operations ~ 1.2 * 10^7

    O(N^2) for 3 sum, and 3.log(3) for sorting the triplet before adding 
    it to the set.
    */
    public List<List<Integer>> threeSum1(int[] nums) {
        
        Set<List<Integer>> solTriplets = new HashSet<>();
        
        // After fixing one index, we are left with the two sum problem.
        for(int fixedIdx = 0; fixedIdx < nums.length; fixedIdx++) {
            /*
            We get the two sum solution on the array to the right of the 
            fixed index because if the solution included an index preceding
            the fixed index, it would have been included in a solution, 
            previously;  
            
            When the fixed index was a lesser value (equal to the index 
            preceding the fixed index that is a part of the solution).
            */
            int twoSumTarget = 0 - nums[fixedIdx];
            
            // This set contains the numbers seen 
            Set<Integer> numSeen = new HashSet<>();
            
            for(int twoSumIdx = fixedIdx + 1; twoSumIdx < nums.length; twoSumIdx++) {
                int currentNum = nums[twoSumIdx];

                if(numSeen.contains(twoSumTarget - currentNum)) {
                    List<Integer> solTriplet = new ArrayList<>();
                    solTriplet.add(nums[fixedIdx]);
                    solTriplet.add(twoSumTarget - currentNum);
                    solTriplet.add(currentNum);
                    
                    Collections.sort(solTriplet);
                    solTriplets.add(solTriplet);
                }
                
                numSeen.add(currentNum);
            }
        }

        return new ArrayList<>(solTriplets);
    }

    /*
    * OPTIMAL APPROACH
    
    The entire array is sorted initially: N.log(N)
    
    Then, 3rd element is fixed which is O(N) 
    AND the remaining 2 sum problem is solved, which is again O(N);
    for a total of: O(N^2)

    Number of operations = N^2 + N.log(N) ~ 9 * 10^6
    
    This is less than the better approach, hence is it is optimal.

    CONCLUSION IS THAT IT IS BETTER TO SORT THE ARRAY FIRST, RATHER
    THAN SORTING EACH TRIPLET INDIVIDUALLY.
    */

    /* 
    * OPTIMAL 1
    
    We use modification of unsorted two sum approach (which requires
    the numSeen set) for solving the sorted two sum problem we have below.
    */
    public List<List<Integer>> threeSum2(int[] nums) {
        
        // Sorting the array
        Arrays.sort(nums);

        List<List<Integer>> solTriplets = new ArrayList<>();
        
        int tripletLeft = Integer.MAX_VALUE;

        // After fixing one index, we are left with the two sum problem.
        for(int fixedIdx = 0; fixedIdx < nums.length; fixedIdx++) {
            
            if(nums[fixedIdx] == tripletLeft) {
                continue;
            }

            tripletLeft = nums[fixedIdx];

            /* 
            Since tripletLeft changed, tripletMid will also be reset since if one element 
            changes in the triplet, not possible to have a duplicate triplet.
            */
            int tripletMid = Integer.MAX_VALUE;

            /*
            We get the two sum solution on the array to the right of the 
            fixed index because if the solution included an index preceding
            the fixed index, it would have been included in a solution, 
            previously;  
            
            When the fixed index was a lesser value (equal to the index 
            preceding the fixed index that is a part of the solution).
            */
            int twoSumTarget = 0 - tripletLeft;
            
            // This set contains the numbers seen (This set is refreshed for every new two sum prob)
            Set<Integer> numSeen = new HashSet<>();
            if(fixedIdx < nums.length - 1) {
                numSeen.add(nums[fixedIdx + 1]);
            }
            
            for(int twoSumIdx = fixedIdx + 2; twoSumIdx < nums.length; twoSumIdx++) {
                
                int currentNum = nums[twoSumIdx];

                // Repetition of tripletMid will result in duplicate triplets, so continue;
                if(currentNum == tripletMid) {
                    continue;
                }

                if(numSeen.contains(twoSumTarget - currentNum)) {
                    tripletMid = nums[twoSumIdx];    
                    
                    // No need to sort triplets since we sorted the nums array at the beginning.
                    solTriplets.add(
                        new ArrayList<>(
                            Arrays.asList(
                                tripletLeft, 
                                twoSumTarget - currentNum, 
                                currentNum
                            )
                        )
                    );
                }
                
                numSeen.add(currentNum);
            }
        }

        return solTriplets;
    }

    /* 
    * OPTIMAL 2
    
    We use the 2 pointer sorted two sum approach
    for solving the sorted two sum problem we have below.

    This yields us a better O(N) than the O(N) of the modified two sum
    approach we used above, resulting in faster runtime.
    */
    public List<List<Integer>> threeSum(int[] nums) {
        
        // Sorting the array
        Arrays.sort(nums);

        List<List<Integer>> solTriplets = new ArrayList<>();
        
        int tripletLeft = Integer.MAX_VALUE;

        // After fixing one index, we are left with the two sum problem ON A SORTED ARRAY.
        for(int fixedIdx = 0; fixedIdx < nums.length; fixedIdx++) {
            
            if(nums[fixedIdx] == tripletLeft) {
                continue;
            }

            tripletLeft = nums[fixedIdx];

            int tripletMid = Integer.MIN_VALUE;
            int tripletRight = Integer.MAX_VALUE;

            int midIndex = fixedIdx + 1;
            int rightIndex = nums.length - 1;

            while(midIndex < rightIndex) {
                
                // Since the middle of the triplet is being repeated, we increment the midIndex.
                if(nums[midIndex] == tripletMid) {
                    midIndex++;
                    continue; // continuing so that
                }

                // Since the right of the triplet is being repeated, we increment the rightIndex.
                if(nums[rightIndex] == tripletRight) {
                    rightIndex--;
                    continue;
                }

                // Valid solution, so add it to the solution matrix
                if(nums[midIndex] + nums[rightIndex] == -1 * tripletLeft) {
                    // Incrementing and decrementing the indices.
                    tripletMid = nums[midIndex++];
                    tripletRight = nums[rightIndex--];
                    
                    // No need to sort triplets since we sorted the nums array at the beginning.
                    solTriplets.add(
                        new ArrayList<>(
                            Arrays.asList(
                                tripletLeft, 
                                tripletMid, 
                                tripletRight
                            )
                        )
                    );
                }
                // We need a bigger sum to reach target so midIndex needs to be increased.
                else if(nums[midIndex] + nums[rightIndex] < -1 * tripletLeft) {
                    midIndex++;
                }
                // We need a smaller sum to reach target so rightIndex needs to be decremented.
                else {
                    rightIndex--;
                }

            }
        }

        return solTriplets;
    }
}