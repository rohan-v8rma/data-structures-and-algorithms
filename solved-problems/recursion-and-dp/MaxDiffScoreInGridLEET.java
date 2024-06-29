// https://leetcode.com/problems/maximum-difference-score-in-a-grid/

class Solution {
    // /*
    // ____TABULATION SOLUTION____
    // */
    // public int maxScore(List<List<Integer>> grid) {
    //     int maxRow = grid.size() - 1;
    //     int maxCol = grid.get(0).size() - 1;
        
    //     int[][] score = new int[maxRow + 1][maxCol + 1];

    //     int maxScore = Integer.MIN_VALUE;

    //     for(int row = maxRow; row >= 0; row--) {
    //         for(int col = maxCol; col >= 0; col--) {
    //             int currentVal = grid.get(row).get(col);
    //             int currentScore = Integer.MIN_VALUE;

    //             if(col + 1 <= maxCol) {
    //                 int colVal = grid.get(row).get(col + 1);

    //                 currentScore = Math.max(
    //                     currentScore,
    //                     score[row][col + 1] + (colVal - currentVal)
    //                 );
    //             }

    //             if(row + 1 <= maxRow) {
    //                 int rowVal = grid.get(row + 1).get(col);

    //                 currentScore = Math.max(
    //                     currentScore,
    //                     score[row + 1][col] + (rowVal - currentVal)
    //                 );
    //             }

    //             score[row][col] = Math.max(currentScore, 0);

    //             maxScore = Math.max(maxScore, currentScore);
    //         }
    //     }

    //     return maxScore;
    // }

    /*
    ____TABULATION SPACE OPTIMIZED SOLUTION____
    */
    public int maxScore(List<List<Integer>> grid) {
        int maxRow = grid.size() - 1;
        int maxCol = grid.get(0).size() - 1;
        
        int[][] score = new int[2][maxCol + 1];

        int maxScore = Integer.MIN_VALUE;

        for(int row = maxRow; row >= 0; row--) {
            for(int col = maxCol; col >= 0; col--) {
                int currentVal = grid.get(row).get(col);
                int currentScore = Integer.MIN_VALUE;

                if(col + 1 <= maxCol) {
                    int colVal = grid.get(row).get(col + 1);

                    currentScore = Math.max(
                        currentScore,
                        score[0][col + 1] + (colVal - currentVal)
                    );
                }

                if(row + 1 <= maxRow) {
                    int rowVal = grid.get(row + 1).get(col);

                    currentScore = Math.max(
                        currentScore,
                        score[1][col] + (rowVal - currentVal)
                    );
                }

                score[0][col] = Math.max(currentScore, 0);

                maxScore = Math.max(maxScore, currentScore);
            }

            score[1] = score[0];
            score[0] = new int[maxCol + 1];
        }

        return maxScore;
    }

    /*
    ____RECURSIVE MEMOIZATION____
    */
    // int recurse(int row, int col, List<List<Integer>> grid) {
    //     if(row > maxRow || col > maxCol) return 0;

    //     if(score[row][col] != -1) return score[row][col];

    //     int currentVal = grid.get(row).get(col);
    //     int currentScore = Integer.MIN_VALUE;

    //     if(col + 1 <= maxCol) {
    //         int colVal = grid.get(row).get(col + 1);

    //         currentScore = Math.max(
    //             currentScore,
    //             recurse(row, col + 1, grid) + (colVal - currentVal)
    //         );
    //     }

    //     if(row + 1 <= maxRow) {
    //         int rowVal = grid.get(row + 1).get(col);

    //         currentScore = Math.max(
    //             currentScore,
    //             recurse(row + 1, col, grid) + (rowVal - currentVal)
    //         );
    //     }
        
        
    //     maxScore = Math.max(maxScore, currentScore);

    //     return score[row][col] = Math.max(currentScore, 0);
    // }


    // int[][] score;
    // int maxScore;
    // int maxRow;
    // int maxCol;

    // public int maxScore(List<List<Integer>> grid) {
    //     maxRow = grid.size() - 1;
    //     maxCol = grid.get(0).size() - 1;

    //     score = new int[maxRow + 1][maxCol + 1];

    //     for(int[] score1d: score) Arrays.fill(score1d, -1);

    //     maxScore = Integer.MIN_VALUE;

    //     recurse(0, 0, grid);

    //     return maxScore;
    // }
}