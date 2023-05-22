/*
Observe how the recurrence relation is similar to fibonacci series.

The results are also same as the fibonacci series.

The problem is actually analogous to fibonacci since the ways 
of getting to the current step is the sum of the ways of getting
to the last step (current step - 1) 
and the second to last step (current step - 2)

*/
public public class Solution {
	static int numOfWaysRecursive(long nStairs) {

		/* 
		Reached 0th step,
		so add 1 way to the number of possible ways
		*/
		if(nStairs == 0) {
			return 1;
		}

		/* 
		Reached 1st step.
		This step can be reached from 0th step by taking 1 step.
		*/
		if(nStairs == 1) {
			return 1;
		}

		return(
			numOfWaysRecursive(nStairs - 1) 
			+ 
			numOfWaysRecursive(nStairs - 2)
		) % 1000000007;
		
	}

	static int numOfWaysTabulatedOptimized(
		long nStairs
	) {
		/* 
		Reached 0th step,
		so add 1 way to the number of possible ways
		*/
		if(nStairs == 0L) {
			return 1;
		}

		/* 
		Reached 1st step.
		This step can be reached from 0th step by taking 1 step.
		*/
		if(nStairs == 1L) {
			return 1;
		}

		int currentVal = 1;
		int lastVal = 1;
		int secondToLastVal = -1;

		long loopVar = 2L;

		while(loopVar != nStairs + 1) {
			secondToLastVal = lastVal;
			lastVal = currentVal;

			// currentVal = (lastVal + secondToLastVal);
			currentVal = (lastVal + secondToLastVal) % 1000000007;

			loopVar++;
		}

		return currentVal;
	}

	static int numOfWaysMemoized(long nStairs, Map<Long, Integer> waysArray) {
		/* 
		Reached 0th step,
		so add 1 way to the number of possible ways
		*/
		if(nStairs == 0L) {
			return 1;
		}

		/* 
		Reached 1st step.
		This step can be reached from 0th step by taking 1 step.
		*/
		if(nStairs == 1L) {
			return 1;
		}

		// Checking if the map contains the key with nStairs as the 
		// if(waysArray.containsKey(nStairs)) {
		// 	return waysArray.get(nStairs);
		// }

		// int ways = (
		// 	numOfWaysMemoized(nStairs - 1, waysArray)
		// 	+
		// 	numOfWaysMemoized(nStairs - 2, waysArray)
		// );

		// waysArray.put(nStairs, ways);

		// return ways;

		return waysArray
		.computeIfAbsent(
			nStairs, 
			key ->
			numOfWaysMemoized(key - 1, waysArray)
			+
			numOfWaysMemoized(key - 2, waysArray)
		) % 1000000007;
	}

	public static int countDistinctWayToClimbStair(long nStairs) {
		// Write your code here.

		// return numOfWaysRecursive(nStairs);

		/* 
		We are using map for the waysArray instead of a regular array
		because the size of the array and ArrayList data types in java
		are constraint by the max value of the int data type.

		So, long cannot be used as indices in these data types. Even if
		we try to use long numbers, we will get error of implicit lossy
		conversion from long to int.
		*/
		// Map<Long, Integer> waysArray = new HashMap<>();

		// return numOfWaysMemoized(nStairs, waysArray);
		return numOfWaysTabulatedOptimized(nStairs);
	}
}
