// https://leetcode.com/problems/rotate-image/description/

class Solution {
    // SELF DEVELOPED ALGO.
    public void rotate(int[][] matrix) {
        
        int dimension = matrix.length - 1;

        int numOfLevels = (matrix.length + 1) / 2;

        for(int left = 0; left < numOfLevels; left++) {
            int right = dimension - left;

            for(int mover = left; mover < right; mover++) {
                int temp = matrix[left][mover];
                
                // top left <- bottom left
                matrix[left][mover] = matrix[dimension - mover][left];
                
                // bottom left <- bottom right
                matrix[dimension - mover][left] = matrix[right][dimension - mover];
                
                // bottom right <- top right
                matrix[right][dimension - mover] = matrix[mover][right];
                
                // top right <- top left
                matrix[mover][right] = temp;
            }
        }
    }
}