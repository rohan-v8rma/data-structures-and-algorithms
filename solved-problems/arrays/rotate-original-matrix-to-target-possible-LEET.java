// https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/description/

class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {

        boolean[] flagArr = new boolean[4];
        Arrays.fill(flagArr, true);
        
        int maxIdx = mat.length - 1;

        for(int i = 0; i <= maxIdx; i++) {
            for(int j = 0; j <= maxIdx; j++) {
                // Checks whether 0 degree rotation works.
                if(mat[i][j] != target[i][j]) {
                    flagArr[0] = false;
                }

                // Checks whether 90 degree rotation works.
                if(mat[i][j] != target[j][maxIdx - i]) {
                    flagArr[1] = false;
                }

                // Checks whether 180 degree rotation works.
                if(mat[i][j] != target[maxIdx - i][maxIdx - j]) {
                    flagArr[2] = false;
                }

                // Checks whether 270 degree rotation works.
                if(mat[i][j] != target[maxIdx - j][i]) {
                    flagArr[3] = false;
                }

                // If none of them work, return false.
                if( !(flagArr[0] || flagArr[1] || flagArr[2] || flagArr[3]) ) {
                    return false;
                }


            }   
        }

        return true;
    }
}