public // Only non-negative numbers are present in the array according to the constraints.
public class Solution {
	
	static int pickAndNonPickTabulatedOptimized(
		int index, 
		ArrayList<Integer> nums
	) {
		if(nums.size() == 1) {
			return nums.get(0);
		}

		int currentSum = Math.max(nums.get(0), nums.get(1));
		int lastSum = nums.get(0);
		int secondToLastSum = -1;


		for(int tableIndex = 2; tableIndex < nums.size(); tableIndex++) {
			secondToLastSum = lastSum;
			lastSum = currentSum;
			currentSum = Math.max(
				nums.get(tableIndex) + secondToLastSum,
				lastSum
			);
		}

		return(currentSum); 
	}
	
	static int pickAndNonPickTabulated(
		int index, 
		ArrayList<Integer> nums
	) {
		if(nums.size() == 1) {
			return nums.get(0);
		}

		int[] tabulatedSums = new int[nums.size()];

		tabulatedSums[0] = nums.get(0);
		tabulatedSums[1] = Math.max(nums.get(0), nums.get(1));

		for(int tableIndex = 2; tableIndex < nums.size(); tableIndex++) {
			tabulatedSums[tableIndex] = Math.max(
				nums.get(tableIndex) + tabulatedSums[tableIndex - 2],
				tabulatedSums[tableIndex - 1]
			);
		}

		return(tabulatedSums[index]); 
	}

	static int pickAndNonPickMemoized(
		int index, 
		ArrayList<Integer> nums, 
		int[] memoizedSums
	) {
		if(memoizedSums[index] != -1) {
			return memoizedSums[index];
		}
		
		/* 
		Second element reached. Double jump not possible.
		So, we pick the greater of the last and the second-last element.

		In the scenario that negative elements were also present, we would
		also check if both of the last 2 elements are negative.
		If yes, return 0 (neither selected).
		*/
		if(index == 1) {
			return(
				memoizedSums[index] = 
				Math.max(nums.get(index), nums.get(index - 1))
			);
		}

		/* 
		First element reached. 
		So, only option is to pick in order to maximize sum
		since there are no negative elements.

		If there were negative elements in the mix, 
		we would first check, whether the number is greater than 0 or not,
		else return 0.
		*/
		if(index == 0) {
			return(
				memoizedSums[index] = 
				nums.get(index)
			);
		}

		// If current element gets picked, we have to increment index by 2.
		int pickSum = nums.get(index) + pickAndNonPickMemoized(index - 2, nums, memoizedSums);
		
		// If current element is NOT picked, we can increment by 1 because it is not adjancent.
		int nonPickSum = pickAndNonPickMemoized(index - 1, nums, memoizedSums);
		
		// Returning the maximum out of the two values.
		return(
			memoizedSums[index] = 
			Math.max(pickSum, nonPickSum)
		);
	}

	static int pickAndNonPickRecursive(int index, ArrayList<Integer> nums) {
		/* 
		Second element reached. Double jump not possible.
		So, we pick the greater of the last and the second-last element.

		In the scenario that negative elements were also present, we would
		also check if both of the last 2 elements are negative.
		If yes, return 0 (neither selected).
		*/
		if(index == 1) {
			return(Math.max(nums.get(index), nums.get(index - 1)));
		}

		/* 
		First element reached. 
		So, only option is to pick in order to maximize sum
		since there are no negative elements.

		If there were negative elements in the mix, 
		we would first check, whether the number is greater than 0 or not,
		else return 0.
		*/
		if(index == 0) {
			return(nums.get(index));
		}

		// If current element gets picked, we have to decrement index by 2.
		int pickSum = nums.get(index) + pickAndNonPickRecursive(index - 2, nums);
		
		// If current element is NOT picked, we can decrement by 1 because it is not adjancent.
		int nonPickSum = pickAndNonPickRecursive(index - 1, nums);
		
		// Returning the maximum out of the two values.
		return(Math.max(pickSum, nonPickSum));
	} 

	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		// Write your code here.

		
		// return pickAndNonPickRecursive(nums.size() - 1, nums);

		// int[] memoizedSums = new int[nums.size()];

		// for(int index = 0; index < nums.size(); index++) {
		// 	memoizedSums[index] = -1;
		// }

		// return pickAndNonPickMemoized(nums.size() - 1, nums, memoizedSums);

		// return pickAndNonPickTabulated(nums.size() - 1, nums);

		return pickAndNonPickTabulatedOptimized(nums.size() - 1, nums);
	}
} subsequence-non-adjacent-sum-NINJA {
    
}
