// https://leetcode.com/problems/combination-sum/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Getting the length of the array
        // System.out.println(candidates.length);

        List<List<Integer>> combos = new ArrayList<>();
        
        addElementDuplicate(0, candidates, new ArrayList<Integer>(), combos, target);
        
            
        return combos;
    }

    /*
    In this method, we are picking a candidate only once. 
    
    None of the candidates are picked more than once. 
    
    This is the traditional approach for subsequence sum.
    */
    List<List<Integer>> addElementDistinct(int index, int[] candidates, ArrayList<Integer> currentArr, int targetSum) {
        List<List<Integer>> solutionArr = new ArrayList<>();

        if(targetSum == 0) {
            System.out.println(currentArr);
            // Cloning the array so that changes to the currentArr aren't reflected here.
            solutionArr.add((List)currentArr.clone());
            System.out.println(solutionArr);
            return solutionArr;
        }

        if( (targetSum < 0) || (index >= candidates.length) ) {
            return solutionArr;
        }
        
        currentArr.add(candidates[index]);
        targetSum -= candidates[index];
        solutionArr.addAll(addElementDistinct(index + 1, candidates, currentArr, targetSum));
        currentArr.remove(currentArr.size() - 1);
        targetSum += candidates[index];

        solutionArr.addAll(addElementDistinct(index + 1, candidates, currentArr, targetSum));


        return solutionArr;
    }

    /* 
    In this method, there are two branches of the recursion tree at every node, just like the earlier approach.
    
    However, unlike the earlier method, in the left branch, we choose to continue picking the current candidate, instead of moving on to the next candidate after picking the current candidate once.   
    
    In the right branch, we stop picking the current candidate, and move on once and for all, to the next candidate. 
    
    This allows us to pick a candidate more than once, with ease.
    */
    void addElementDuplicate(int index, int[] candidates, ArrayList<Integer> currentArr, List<List<Integer>> solutionArr, int targetSum) {
        if(targetSum == 0) {
            // Cloning the array so that changes to the currentArr aren't reflected here.
            solutionArr.add((List)currentArr.clone());
            return;
        }

        if( (targetSum < 0) || (index == candidates.length) ) {
            return;
        }
        
        currentArr.add(candidates[index]);
        // Continuing to pick the current candidate
        addElementDuplicate(index, candidates, currentArr, solutionArr, targetSum - candidates[index]);
        currentArr.remove(currentArr.size() - 1);

        // Moving on to the next candidate, for good.
        addElementDuplicate(index + 1, candidates, currentArr, solutionArr, targetSum);

        return;
    }
}