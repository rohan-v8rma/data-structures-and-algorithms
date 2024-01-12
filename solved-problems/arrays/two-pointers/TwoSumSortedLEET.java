// Problem link: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted

class Solution {
    
    //* Modification of UNSORTED two sum: O(N)
    public int[] twoSum1(int[] numbers, int target) {
        Map<Integer, Integer> numbersSeen = new HashMap<>();

        int[] solution = new int[2];

        for(int index = 0; index < numbers.length; index++) {
            int current = numbers[index];
            /* 
            If the second number part of the solution is greater than 
            the current number, we won't have seen it yet since the array
            is sorted, so we simply continue; instead of checking if the
            number has been seen or not, since we have surely not seen it.
            */
            if((target - current) > current) {
                numbersSeen.put(current, index);
                continue;
            }
            else if(numbersSeen.containsKey(target - current)) {
                solution[0] = numbersSeen.get(target - current) + 1;
                solution[1] = index + 1;
                break;
            }
            else {
                numbersSeen.put(current, index);
            }
        }

        return solution;
    }
    
    /* 
    * TWO POINTER approach
    
    This algorithm is O(N) as well, but it is a better O(N) than the above
    algorithm, since Set data structure (which has somewhat of an overhead
    associated with it as compared to not using it) is not used.
    */
    public int[] twoSum(int[] numbers, int target) {
        int firstIndex = 0;
        int secondIndex = numbers.length - 1;

        int[] solution = new int[2];

        while(true) {
            if(numbers[firstIndex] + numbers[secondIndex] == target) {
                solution[0] = firstIndex + 1;
                solution[1] = secondIndex + 1;
                break;
            }
            // Since sum is greater than target we move the second index to a smaller number
            else if(numbers[firstIndex] + numbers[secondIndex] > target) {
                secondIndex--;
            }
            // In the case the sum is smaller than target, we move the first index to a larger number
            else {
                firstIndex++;
            }
        }

        return solution;
    }
}