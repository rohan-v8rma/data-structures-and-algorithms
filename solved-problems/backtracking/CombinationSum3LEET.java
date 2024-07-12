// https://leetcode.com/problems/combination-sum-iii
// TODO: Make notes

class Solution {
    List<List<Integer>> getCombinations(int reqSum, int low, int high, int k) {
        List<List<Integer>> results = new ArrayList<>();

        // Returning empty results in this case.
        if(low > high || reqSum < 0) return results;

        if(k == 1) {
            if(low <= reqSum && reqSum <= high) {
                results.add(new ArrayList<>(Arrays.asList(reqSum)));
            }
        }
        else {
            for(int currDigit = low; currDigit <= high; currDigit++) {
                List<List<Integer>> subResults = getCombinations(reqSum - currDigit, currDigit + 1, high, k - 1);

                for(List<Integer> subResult: subResults) {
                    /* 
                    The subresult has to have k - 1 elements for us to 
                    make a result that has k elements, by just adding 1 element
                    */
                    if(subResult.size() == k - 1) {
                        subResult.add(currDigit);
                        results.add(subResult);
                    }
                }
            }
        }
        
        return results;
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        return getCombinations(n, 1, 9, k);
    }
}