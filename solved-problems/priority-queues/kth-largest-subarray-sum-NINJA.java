public class Solution {
	// For TC analysis: see notebook. SC: O(N + K)
	// public static int getKthLargest(ArrayList<Integer> arr, int k) {
	// 	ArrayList<Integer> sumsTillPrevIdx = new ArrayList<>();

	// 	PriorityQueue<Integer> minHeap = new PriorityQueue<>();

	// 	int currentSum = 0;

	// 	// Calculating all possible N(N + 1)/2 sums. SC: O(N)
	// 	for(int num: arr) {
	// 		for(int sumIdx = 0; sumIdx < sumsTillPrevIdx.size(); sumIdx++) {
				
	// 			currentSum = sumsTillPrevIdx.get(sumIdx) + num;
				
	// 			addToMinHeap(currentSum, minHeap, k);
				
	// 			sumsTillPrevIdx.set(sumIdx, currentSum);
	// 		}

	// 		addToMinHeap(num, minHeap, k);
			
	// 		sumsTillPrevIdx.add(num);
	// 	}

	// 	// Returning Kth largest sum.
	// 	return minHeap.poll();
	// }

    // static void addToMinHeap(int sum, PriorityQueue<Integer> minHeap, int k) {
    //     // Ensuring atmost K elements in heap.
    //     if(minHeap.size() < k) {
    //         minHeap.offer(sum);
    //     }
    //     else if(minHeap.peek() < sum) {
    //         minHeap.poll();
    //         minHeap.offer(sum);
    //     }
    // }
    
	// OPTIMAL. Space complexity = O(K)
	public static int getKthLargest(ArrayList<Integer> arr, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		int currentSum;

		// Calculating all possible N(N + 1)/2 sums. SC: O(1)
		for(int i = 0; i < arr.size(); i++) {
			currentSum = 0;
			for(int j = i; j < arr.size(); j++) {
				currentSum += arr.get(j);
				
				// Ensuring atmost K elements in heap.
                if(minHeap.size() < k) {
                    minHeap.offer(currentSum);
                }
                else if(minHeap.peek() < currentSum) {
                    minHeap.poll();
                    minHeap.offer(currentSum);
                }
			}
		}

		// Returning Kth largest sum.
		return minHeap.poll();
	}

}