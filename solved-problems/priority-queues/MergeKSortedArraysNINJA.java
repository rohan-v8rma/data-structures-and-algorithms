// https://www.codingninjas.com/studio/problems/merge-k-sorted-arrays_975379

public class Solution {
	/* 
	BRUTEFORCE would be

	Add all elements to merged array and sort the array.

	TC: O(NK.log(NK))
	*/


	/*
	SELF DEVELOPED ALGO (OPTIMAL):

	Keep current element of each of the K arrays, in a priority queue.
	
	One-by-one, get min element from PQ 
	and 
	add next element of array whose element we just added to array.

	log(K) is TC of insertion and deletion, since there are K elements at max in PQ.
	This is done for each element.

	TC: O(NK.log(K))
	*/
	public static ArrayList<Integer> mergeKSortedArrays(ArrayList<ArrayList<Integer>> kArrays, int k) {
		// Write your code here.
		

		// for(int idx = 1; idx < kArrays.size(); idx++) {
		// 	mergedList.addAll(kArrays.get(idx));
		// }

		/* 
		Keeping elements in min-heap as indices, so that adding next element
		of the array we just got the minimum element from is straightforward.
		*/
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(
			(el1, el2) -> Integer.compare(
				kArrays.get(el1[0]).get(el1[1]),
				kArrays.get(el2[0]).get(el2[1])
			)
		);

		int totalSize = 0;

		// Adding the first element of each of the K arrays to the Priority Queue.
		for(int arrIdx = 0; arrIdx < kArrays.size(); arrIdx++) {
			minHeap.add(new int[]{arrIdx, 0});
			totalSize += kArrays.get(arrIdx).size();
		}

		// Creating a merged list of size = total number of elements.
		ArrayList<Integer> mergedList = new ArrayList<>(totalSize);


		int[] nextElementIdx;
		ArrayList<Integer> nextElementArray;

		while(!minHeap.isEmpty()) {
			nextElementIdx = minHeap.poll();
			
			/* 
			The first index of the value we polled from PQ, tells us the 
			index of the array we are going to get next minimum element from.
			*/
			nextElementArray = kArrays.get(nextElementIdx[0]);

			/*
			Adding the next minimum element to merged list.
			*/
			mergedList.add(nextElementArray.get(nextElementIdx[1]++));

			/*
			If the array we just got the next minimum element from has more
			elements, we add it back to the priority queue.
			*/
			if(nextElementIdx[1] < nextElementArray.size()) {
				minHeap.offer(nextElementIdx);
			}
		}

		return mergedList;
	}
}
