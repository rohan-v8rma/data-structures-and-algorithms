//TODO: Use indices instead of house numbers to keep understanding simpler.

public class Solution {
	static int pickAndNonPickMemoized1(
		int house, 
		int[] valueInHouse,
		int[] memoizedSums
	) {
		int index = house - 1;

		// Houses are only numbered from 1 to N.
		if(house <= 0) {
			return 0;
		}

		// Reached the first house.
		if(house == 1) {
			return valueInHouse[index];
		}

		if(memoizedSums[index] != -1) {
			return memoizedSums[index];
		}

		return(memoizedSums[index] = 
			Math.max
			(
			valueInHouse[index] 
			+ 
			pickAndNonPickMemoized1(house - 2, valueInHouse, memoizedSums)
			, 
			pickAndNonPickMemoized1(house - 1, valueInHouse, memoizedSums)
			)
		);

	}

	public static long houseRobber(int[] valueInHouse) {
	    /* 
		This array is one shorter than the values array
		because we will exclude either the first or the last house.
		*/
		int[] memoizedSums = new int[valueInHouse.length - 1];

		for(int index = 0; index < valueInHouse.length - 1; index++) {
			memoizedSums[index] = -1;
		}

		int[] shortenedValues = Arrays.copyOfRange(valueInHouse, 0, valueInHouse.length - 1);
        
        /* 
		In this one, only guarantee is of last house NOT being selected.

		There is no guarantee of first house being selected.

		Because suppose inside the recursive call, if we select house 2,
		then house 1 cannot be selected because its adjacent.

		So, we also cover the edge cases where not including house 1 and last house
		gives us the best profit.
		*/
		int lastHouseNotSelected = 
			pickAndNonPickMemoized1(
				/*
				The length of the shortedValues is 
				valueInHouse.length - 1. 
				
				Meaning last index is valueInHouse.length - 2.

				The second last house is at valueInHouse.length - 2
				index because we removed the last house from the array.

				So it is the `valueInHouse.length - 1` house.
				*/
				// Selecting from the second last house onwards.
				valueInHouse.length - 1, 
				shortenedValues, 
				memoizedSums
			);

		shortenedValues = Arrays.copyOfRange(valueInHouse, 1, valueInHouse.length);

		for(int index = 0; index < valueInHouse.length - 1; index++) {
			memoizedSums[index] = -1;
		}

		int lastHouseSelected = 
			valueInHouse[valueInHouse.length - 1]
			+
			pickAndNonPickMemoized1(
				/*
				The length of the shortedValues is 
				valueInHouse.length - 1.

				The last house is at index `valueInHouse.length - 2`
				index. 

				So, third last house is at index `valueInHouse.length - 4`

				So it is the `valueInHouse.length - 3` house.
				*/
				valueInHouse.length - 3, 
				shortenedValues, 
				memoizedSums
			);

		return(
			Math.max(
				lastHouseNotSelected
				, 
				lastHouseSelected
			)
		);
	}	
}