// Problem link: https://leetcode.com/problems/permutations-ii

class Solution {
    // This uses the loop-based recursion selection method.
    static void selectElementAtIndex(
        int index, 
        int[] nums, 
        boolean[] selectedIndices, 
        List<Integer> currPermutation,
        List<List<Integer>> permutations
    ) {
        if(index + 1 == nums.length) {
            permutations.add(new ArrayList<>(currPermutation));
            return;
        }
        
        // selection process for the index `index` of our permutation.
        int prevSelected = 100;
        for(int selectIdx = 0; selectIdx < nums.length; selectIdx++) {
            if(
                selectedIndices[selectIdx] == true
                ||
                prevSelected == nums[selectIdx]
            ) {
                continue;
            }

            prevSelected = nums[selectIdx];
            selectedIndices[selectIdx] = true;
            currPermutation.add(prevSelected);
            selectElementAtIndex(
                index + 1, 
                nums, 
                selectedIndices, 
                currPermutation, 
                permutations
            );
            // Backtracking steps.
            currPermutation.remove(index + 1);
            selectedIndices[selectIdx] = false;
        }
    }

    /*
    According to constraints, we can afford to sort the array.
    */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        boolean[] selectedIndices = new boolean[nums.length];

        Arrays.sort(nums);
        selectElementAtIndex(-1, nums, selectedIndices, new ArrayList<>(), permutations);

        return permutations;
    }
}