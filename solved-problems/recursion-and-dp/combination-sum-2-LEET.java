class Solution {
    /* 
    This is the bruteforce approach, where we even try all the duplicate combinations, and insert them into the set.

    Time Complexity: O( 2^n * ( K * log(H) ) )    

    - K is average length of combination associated with each of the 2^n possibilities. 
    (Some combinations won't be viable solutions so K = 0 for them)
    
    - log(H) is assuming worst case of collision in set, where H is the number of elements in the collision bucket, 
    and they are organized in a balanced BST.
    */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        /* 
        Sorting the candidates array because the subsequences which are solutions of the candidates array are in sorted order.

        This prevents solutions like [1, 7] and [7, 1].

        Time complexity : O(Nlog(N))
        */
        mergeSort(candidates, 0, candidates.length);    


        Set<List<Integer>> setOfArr = new HashSet<>();
        
        ArrayList<Integer> currentSolutionArr = new ArrayList<>();

        // Starting the process of selecting elements from index = 0.
        selectElement(0, currentSolutionArr, target, candidates, setOfArr);


        // Returning as a list.
        List<List<Integer>> solutionArr = new ArrayList<>(setOfArr);
        return solutionArr;
    }

    static void selectElement(int index, ArrayList<Integer> currentSolutionArr, int target, int[] candidates, Set<List<Integer>> setOfArr) {
        if(target == 0) {
            setOfArr.add((List)currentSolutionArr.clone());
            return;
        }
        else if(
            (target < 0)
            || 
            (index >= candidates.length)
        ) {
            return;
        }

        selectElement(index + 1, currentSolutionArr, target, candidates, setOfArr);
        currentSolutionArr.add(candidates[index]);
        selectElement(index + 1, currentSolutionArr, target - candidates[index], candidates, setOfArr);
        
        // Part of Backtracking 
        currentSolutionArr.remove(currentSolutionArr.size() - 1);

        return;
    }

    /* 
    This is the optimized approach, where at each level of the recursion tree, 
    we skip selecting the same elements in an effort to prevent duplicate subsequences.

    Meaning if we have to picking the starting element of a combination, 
    and we pick the 0th index which holds a `1`, we will skip 1st index if it also holds a `1`, 
    because that would lead to duplication combinations where the starting element is `1`.

    For finding the TIME COMPLEXITY:
    In the worst case, every element out of N is unique, so no duplicate combinations in 2^n. 

    Since a set is NOT needed in this approach, the extra log(H) factor for collisions is not applied. 
    Only the time required for copying the average count of K elements over all the combinations
    (some combinations aren't solutions so they contribute 0 to the average)
    to the solutionArr is multiplied

    Time Complexity: O( 2^n * K )    

    This is a better time complexity.
    */
    public List<List<Integer>> optimizedCombinationSum2(int[] candidates, int target) {
        /* 
        Sorting the candidates array because the subsequences 
        which are solutions of the candidates array are in sorted order.

        This prevents solutions like [1, 7] and [7, 1].

        Time complexity : O(Nlog(N))
        */
        mergeSort(candidates, 0, candidates.length);    

        List<List<Integer>> arrayOfArrays = new ArrayList<>();
        
        ArrayList<Integer> currentSolutionArr = new ArrayList<>();

        // Starting the process of selecting elements from index = 0.
        selectElement(-1, currentSolutionArr, target, candidates, arrayOfArrays);

        return arrayOfArrays;
    }

    static void selectElement(int index, ArrayList<Integer> currentSolutionArr, int target, int[] candidates, List<List<Integer>> arrayOfArrays) {
        if(index >= 0) {
            if(
                (index >= candidates.length)
                ||
                (target < 0)
            ) {
                return;
            }

            if( target == 0 ) {
                arrayOfArrays.add((List)currentSolutionArr.clone());
                return;
            }
        }

        int lastSelectedElement = Integer.MIN_VALUE;
        /* 
        The nextSelectIndex starts from (index + 1), for clarity in what is happening in the recursion calls.

        If we were pick the `index` element in this level, we would need to allow for `index + 1` element to be
        picked regardless of the value in `index`, 
        because in that scenario, `index + 1` would a be level lower in the recursion tree, not causing duplicacy.

        THIS IS DIFFERENT FROM TRADITIONAL APPROACH
        */
        for(int nextSelectIndex = index + 1; nextSelectIndex < candidates.length; nextSelectIndex++) {
            
            
            /* 
            OPTIMIZATION (3ms runtime converted to 1ms)
            Since the array is sorted in increasing order, 
            and the current element is greater than the target, 
            so no need to check further elements, 
            because they will definitely be greater than the target.
            */
            if(candidates[nextSelectIndex] > target) {
                break;
            }
            else if(lastSelectedElement == candidates[nextSelectIndex]) {
                continue;
            }
            else {
                lastSelectedElement = candidates[nextSelectIndex];
                currentSolutionArr.add(lastSelectedElement);
                /* 
                If a particular index is leading to picking issue, 
                then it is obvious that picking higher indices will also cause issues, 
                since candidate array is sorted in increasing order.

                So, we break the loop for checking as an optimization step.
                */
                selectElement(nextSelectIndex, currentSolutionArr, target - lastSelectedElement, candidates, arrayOfArrays);
                // Part of Backtracking procedure.
                currentSolutionArr.remove(currentSolutionArr.size() - 1);
            }
        }
    }

    // endIndex is exclusive
    static void mergeSort(int[] candidates, int startIndex, int endIndex) {
        if(endIndex - startIndex <= 1) {
            return;
        }

        int midIndex = (startIndex + endIndex) / 2;

        mergeSort(candidates, startIndex, midIndex);
        mergeSort(candidates, midIndex, endIndex);

        /* 
        The time complexity is due to the merge in place function, iterating over the sub-array for merging them.
        The mergeSort calls only take up space in the recursion stack.
        */
        mergeInPlace(candidates, startIndex, midIndex, endIndex);

        return;
    }

    static void mergeInPlace(int[] candidates, int startIndex, int midIndex, int endIndex) {
        int[] mergedArr = new int[endIndex - startIndex];
        int firstPointer = startIndex;
        int secondPointer = midIndex;
        int mergedPointer = 0;

        while((firstPointer < midIndex) && (secondPointer < endIndex)) {
            if(candidates[firstPointer] < candidates[secondPointer]) {
                mergedArr[mergedPointer++] = candidates[firstPointer++];
            }
            else {
                mergedArr[mergedPointer++] = candidates[secondPointer++];
            }
        }

        while(secondPointer < endIndex) {
            mergedArr[mergedPointer++] = candidates[secondPointer++];
        }
        while(firstPointer < midIndex) {            
            mergedArr[mergedPointer++] = candidates[firstPointer++];
        }

        mergedPointer = startIndex;
        for(int element: mergedArr) {
            candidates[mergedPointer++] = element;
        }
        return;
    }
}