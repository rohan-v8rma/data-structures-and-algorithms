class Solution {
    /*
    TC analysis in notebook

    2 boolean arrays used to indicate which rows and which columns need to be zero-ed.
    SC: O(m + n)
    */
    // public void setZeroes(int[][] matrix) {
    //     boolean[] rowsTo0 = new boolean[matrix.length];
    //     boolean[] colsTo0 = new boolean[matrix[0].length];

    //     for(int row = 0; row < matrix.length; row++) {
    //         for(int col = 0; col < matrix[0].length; col++) {
    //             if(matrix[row][col] == 0) {
    //                 rowsTo0[row] = true;
    //                 colsTo0[col] = true;
    //             } 
    //         }
    //     }

    //     for(int row = 0; row < matrix.length; row++) {
    //         for(int col = 0; col < matrix[0].length; col++) {
    //             if(colsTo0[col] || rowsTo0[row]) {
    //                 matrix[row][col] = 0;
    //             }       
    //         }
    //     }
    // }

    /*
    TC analysis in notebook

    2 Sets used to indicate which rows and which columns need to be zero-ed.
    
    Worst Case SC: O(m + n)

    But in average case, it is lesser because all rows and columns need not
    have be zero-ed for each test case.
    */
    // public void setZeroes(int[][] matrix) {
    //     Set<Integer> rowsTo0 = new HashSet<>();
    //     Set<Integer> colsTo0 = new HashSet<>();

    //     for(int row = 0; row < matrix.length; row++) {
    //         for(int col = 0; col < matrix[0].length; col++) {
    //             if(matrix[row][col] == 0) {
    //                 rowsTo0.add(row);
    //                 colsTo0.add(col);
    //             } 
    //         }
    //     }

    //     for(int row : rowsTo0) {
    //         for(int col = 0; col < matrix[0].length; col++) {
    //             matrix[row][col] = 0;
    //         }
    //     }

    //     for(int col : colsTo0) {
    //         for(int row = 0; row < matrix.length; row++) {
    //             matrix[row][col] = 0;
    //         }
    //     }
    // }

    /*
    OPTIMAL:

    TC: 2 * O(M * N)
    where 
    M is number of rows
    N is number of columns
    
    SC: O(1)
    */
    public void setZeroes(int[][] matrix) {

        // TC : O(N)
        boolean isrow0 = false;
        for(int column = 0; column < matrix[0].length; column++) {
            if(matrix[0][column] == 0) {
                isrow0 = true;
                break;
            }
        }

        // TC: O(M)
        boolean iscol0 = false;
        for(int row = 0; row < matrix.length; row++) {
            if(matrix[row][0] == 0) {
                iscol0 = true;
                break;
            }
        }

        // TC: O((M - 1) * (N - 1))
        for(int row = 1; row < matrix.length; row++) {
            for(int column = 1; column < matrix[0].length; column++) {
                if(matrix[row][column] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][column] = 0;
                }
            }
        }

        // TC: O((M - 1) * (N - 1))
        for(int row = 1; row < matrix.length; row++) {
            for(int column = 1; column < matrix[0].length; column++) {
                if(matrix[row][0] == 0 || matrix[0][column] == 0) {
                    matrix[row][column] = 0;
                }
            }
        }

        // TC: O(N)
        if(isrow0) {
            for(int column = 0; column < matrix[0].length; column++) {
                matrix[0][column] = 0;
            }
        }

        // TC: O(M)
        if(iscol0) {
            for(int row = 0; row < matrix.length; row++) {
                matrix[row][0] = 0;
            }
        }
    }
    
}