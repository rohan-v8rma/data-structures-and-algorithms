//TODO: Use indices instead of house numbers to keep understanding simpler.

public class Solution {

	static int pickAndNonPickMemoized(
		int house, 
		int[] valueInHouse,
		int[] memoizedSums,
		boolean lastHousePicked
	) {
		int index = house - 1;

		// Houses are only numbered from 1 to N.
		if(house <= 0) {
			return 0;
		}

		// Reached the first house.
		if(house == 1) {
			/* 
			The last house is picked, 
			so picking the first house will result in us picking two adjacent houses
			*/
			if(lastHousePicked) {
				return 0;
			}

			/*
			The last house is NOT picked,
			so we can safely pick the first house.
			*/
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
			pickAndNonPickMemoized(house - 2, valueInHouse, memoizedSums, lastHousePicked)
			, 
			pickAndNonPickMemoized(house - 1, valueInHouse, memoizedSums, lastHousePicked)
			)
		);

	}

	static int memoizationSetup(
		int[] valueInHouse
	) {
		int[] memoizedSums = new int[valueInHouse.length];

		for(int index = 0; index < memoizedSums.length; index++) {
			memoizedSums[index] = -1;
		}

		int lastHouse = valueInHouse.length;

		// If current element gets picked, we have to decrement index by 2.
		int lastHousePickMax = 
			valueInHouse[lastHouse - 1] 
			+ 
			pickAndNonPickMemoized(
				lastHouse - 2, 
				valueInHouse, 
				memoizedSums,
				true
			);
			
		for(int index = 0; index < memoizedSums.length; index++) {
			memoizedSums[index] = -1;
		}

		// If current element is NOT picked, we can decrement by 1 because it is not adjacent.
		int lastHouseNonPickMax = 
			pickAndNonPickMemoized(
				lastHouse - 1, 
				valueInHouse,
				memoizedSums, 
				false
			);
			
		// Returning the maximum out of the two values.
		return(Math.max(
			lastHousePickMax, 
			lastHouseNonPickMax
			)
		);
	}

	public static long houseRobber(int[] valueInHouse) {
		// Write your code here.		

		return(
			memoizationSetup(valueInHouse)
		);
	}	
}