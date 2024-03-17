// https://www.codingninjas.com/studio/problems/total-unique-paths_1081470

import java.util.* ;
import java.io.*; 
public class Solution {
	static int pathsTabulated(int m, int n) {
		int[][] tabMatrix = new int[m][n];

		for(int row = 0; row < m; row++) {
			tabMatrix[row][0] = 1;
		}

		for(int col = 0; col < n; col++) {
			tabMatrix[0][col] = 1;
		}

		for(int row = 1; row < m; row++) {
			for(int col = 1; col < n; col++) {
				tabMatrix[row][col] = tabMatrix[row][col - 1] + tabMatrix[row - 1][col];
			}
		}

		return tabMatrix[m - 1][n - 1];
	}

	public static int uniquePaths(int m, int n) {
		// Write your code here.

		return pathsTabulated(m, n);
	}
}