// https://leetcode.com/problems/4sum

class Solution {
    /*
    In this 4 sum problem, we'll have to fix 2 indices and solve the remaining 2 sum problem.
    
    Since N = 200
    
    N^3 = 8 * 10^6 < 10^8
    */
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> quadruplets = new ArrayList<>();

        Arrays.sort(nums);
        int seed = 1000000007;
        int prev1 = seed, prev2 = seed, prev3 = seed, prev4 = seed;
        int idx1 = 0, idx2, idx3, idx4;
        for(idx1 = 0; idx1 < nums.length - 3; idx1++) {
            if(
                prev1 
                == 
                (prev1 = nums[idx1])
                ) {
                continue;
            }

            prev2 = seed;
            
            for(idx2 = idx1 + 1; idx2 < nums.length - 2; idx2++) {
                if(
                    prev2 
                    == 
                    (prev2 = nums[idx2])
                    ) {
                    continue;
                }

                idx3 = idx2 + 1;
                idx4 = nums.length - 1;
                
                prev3 = nums[idx3];
                prev4 = nums[idx4];

                while(idx3 < idx4) {
                    /* 
                    Converting prev1 to long so that all the remaining variable are upcasted
                    and overflow doesn't occur.

                    Only 1 variable needs to be converted since the expression is evaluated
                    from left to right.
                    */
                    long sum = ((long)prev1 + prev2 + prev3 + prev4);

                    if(sum < target) {
                        idx3++;
                        // Preventing duplicate values of the 3rd element
                        while(
                            (
                                prev3 
                                == 
                                (prev3 = nums[idx3])
                            )
                            &&
                            (idx3 < idx4)
                        ) {
                            idx3++;
                        }
                    }
                    else if(sum > target) {
                        idx4--;
                        // Preventing duplicate values of the 4th element
                        while(
                            (
                                prev4 
                                == 
                                (prev4 = nums[idx4])
                            )
                            &&
                            (idx3 < idx4)
                        ) {
                            idx4--;
                        }
                    }
                    else {
                        quadruplets.add(Arrays.asList(prev1, prev2, prev3, prev4));
                        idx3++;
                        idx4--;
                        // Preventing duplicate values of the 3rd element
                        while(
                            (
                                prev3 
                                == 
                                (prev3 = nums[idx3])
                            )
                            &&
                            (idx3 < idx4)
                        ) {
                            idx3++;
                        }
                        // Preventing duplicate values of the 4th element
                        while(
                            (
                                prev4 
                                == 
                                (prev4 = nums[idx4])
                            )
                            &&
                            (idx3 < idx4)
                        ) {
                            idx4--;
                        }
                    }
                }
            }
        }
        
        return quadruplets;
    }
}