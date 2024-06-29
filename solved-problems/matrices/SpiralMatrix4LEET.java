// https://leetcode.com/problems/spiral-matrix-iv/
// TODO: Make notes, if needed.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    int getNextElement(ListNode head) {
        if(head == null) return -1;

        return head.val;
    }

    ListNode moveAhead(ListNode head) {
        if(head == null) return null;

        return head.next;
    }

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int numOfIterations = Math.min(m, n);
        numOfIterations = (numOfIterations + numOfIterations % 2) / 2;

        int[][] matrix = new int[m][n];

        for(int iter = 0; iter < numOfIterations; iter++) {
            // Upper row
            int row1 = iter;
            for(int col = iter; col < n - iter; col++) {
                matrix[row1][col] = getNextElement(head);
                head = moveAhead(head);
            }
    
            // Right column
            int col1 = n - 1 - iter;
            for(int row = iter + 1; row < m - iter; row++) {
                matrix[row][col1] = getNextElement(head);
                head = moveAhead(head);
            }

            // Bottom row
            int row2 = m - 1 - iter;
            if(row1 != row2) {
                for(int col = n - 2 - iter; col >= iter; col--) {
                    matrix[row2][col] = getNextElement(head);
                    head = moveAhead(head);
                }
            }

            // Left column
            int col2 = iter;
            if(col1 != col2) {
                for(int row = m - 2 - iter; row >= iter + 1; row--) {
                    matrix[row][col2] = getNextElement(head);
                    head = moveAhead(head);
                }
            }
        }

        return matrix;
    }
}