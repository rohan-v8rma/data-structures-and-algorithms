// https://www.naukri.com/code360/problems/number-of-inversions_6840276

import java.util.*;

class Solution {
	// BRUTEFORCE (O(N ^ 2))
	// static int numberOfInversions(int[] A, int n) {
	// 	int invPairCt = 0;
		
	// 	for(int i = 0; i < n; i++) {
	// 		for(int j = i + 1; j < n; j++) {
	// 			if(A[j] < A[i]) invPairCt++;
	// 		}
	// 	}
		
	// 	return invPairCt;
	// }
	

	/*___Merge sort approach of counting number of inversions___*/

	static int numOfSwaps;

	static void merge(int[] A, int left, int right) {
		int mid = (left + right) / 2;

		int[] pieceArr = new int[right - left + 1];
		int piecePt = 0;

		int pt1 = left;
		int pt2 = mid + 1;

		while(pt1 <= mid && pt2 <= right) {
			if(A[pt2] < A[pt1]) {
				// System.out.printf("%d, %d, %d\n", A[pt1], A[pt2], mid - pt1  + 1);
				numOfSwaps += mid - pt1 + 1;
				pieceArr[piecePt++] = A[pt2++];
			}
			else pieceArr[piecePt++] = A[pt1++];
		}

		while(pt1 <= mid) pieceArr[piecePt++] = A[pt1++];
		while(pt2 <= right) pieceArr[piecePt++] = A[pt2++];

		for(int i = left; i <= right; i++) {
			A[i] = pieceArr[i - left];
		}
	}

	static void mergeSort(int[] A, int left, int right) {
		if(left >= right) return;

		int mid = (left + right) / 2;
		
		mergeSort(A, left, mid);
		mergeSort(A, mid + 1, right);


		merge(A, left, right);
	}

	static int numberOfInversions(int[] A, int n) {
		numOfSwaps = 0;

		mergeSort(A, 0, n - 1);

		// System.out.println(Arrays.toString(A));

		return numOfSwaps;
	}
}