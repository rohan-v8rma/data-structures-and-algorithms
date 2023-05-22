public class Solution {
    // Used LinkedHashSet.
    public static int[] removeDuplicates1(
		ArrayList<Integer> arr,
		int n
	) {
		// Write your code here.

		/* 
		Using LinkedHashSet which has O(1) insertion time,
		and preserves insertion order.
		
		This way total time complexity is O(2N).

		O(N) - initial pass using for loop where we makes
		insertions into the LinkedHashSet.
		O(N) - setting the indices of the original array.
		*/

		Set<Integer> uniqueElements = new LinkedHashSet<>();

		int lastElement = arr.get(0);

		uniqueElements.add(lastElement);

		int distinctElements = 1;

		for(int index = 1; index < n; index++) {
			int currentElement = arr.get(index);
			
			if(currentElement > lastElement) {
				
				uniqueElements.add(currentElement);
				
				distinctElements++;
				lastElement = currentElement;
			}
		}


		int index = 0;
		for(Integer element: uniqueElements) {
			arr.set(index++, element);
		}

		while(index++ != n) {
			arr.remove(arr.size() - 1);
		}

		for(int element: arr) {
			System.out.printf("%d, ", element);
		}
		
		return arr;
	}



    /*
    Extreme cases:

    1. Medium case
    All elements are duplicate.
    So, no array writes take place in the first for-loop.

    But we have to remove elements in the second for-loop.

    Approx time complexity : O(2N)

    2. Best case
    No duplicate elements

    no array writes.

    Approx time complexity : O(2N)

    3. Worst case
    Couple of duplicate elements.

    Array writes take place in the first for-loop.

    We also have to remove elements in the second for loop.

    Approx time complexity : O(2N)
    */
	public static int[] removeDuplicates(
		ArrayList<Integer> arr,
		int n
	) {
		// Write your code here.

		int lastElement = (-1 * (int)Math.pow(10, 9)) - 1;

		int distinctIndex = 0;

		for(int index = 0; index < n; index++) {
			int currentElement = arr.get(index);
			
			if(currentElement > lastElement) {
                // Distinct index lags behind index, so we update the arr.
				if(distinctIndex < index) {
					arr.set(distinctIndex, currentElement);
				}
				distinctIndex++;
				lastElement = currentElement;
			}
		}

		for(int num = 1; num <= n - distinctIndex; num++) {
			arr.remove(n - num);
		}

		return arr;
	}
}