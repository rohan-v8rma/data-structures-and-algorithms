// https://leetcode.com/problems/game-of-life/

class Solution {
    public void gameOfLife(int[][] board) {
        
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                int aliveNeighbors = 0;

                if((row + 1) < board.length) {
                    
                    // BOTTOM NEIGHBOR
                    aliveNeighbors += board[row + 1][col] % 2;

                    if((col + 1) < board[0].length) {
                        // BOTTOM RIGHT NEIGHBOR
                        aliveNeighbors += board[row + 1][col + 1] % 2;
                    }

                    if((col - 1) >= 0) {
                        // BOTTOM LEFT NEIGHBOR
                        aliveNeighbors += board[row + 1][col - 1] % 2;
                    }
                }


                if((col + 1) < board[0].length) {
                    // RIGHT NEIGHBOR
                    aliveNeighbors += board[row][col + 1] % 2;
                }

                if((col - 1) >= 0) {
                    // LEFT NEIGHBOR
                    aliveNeighbors += board[row][col - 1] % 2;
                }

                if((row - 1) >= 0) {
                    // TOP NEIGHBOR                  
                    aliveNeighbors += board[row - 1][col] % 2;

                    if((col + 1) < board[0].length) {
                        // TOP RIGHT NEIGHBOR
                        aliveNeighbors += board[row - 1][col + 1] % 2;
                    }

                    if((col - 1) >= 0) {
                        // TOP LEFT NEIGHBOR
                        aliveNeighbors += board[row - 1][col - 1] % 2;
                    }
                }

                if(board[row][col] == 1) {
                    if(
                        aliveNeighbors < 2
                        ||
                        aliveNeighbors > 3
                    ) {
                        /*
                        3 means the cell is dead in next generation, but is currently alive

                        3 % 2 gives 1, indicating that the cell is alive
                        */
                        board[row][col] = 3;
                    }
                }        
                else if(aliveNeighbors == 3) {
                    /*
                    2 means the cell is alive in next generation, but is currently dead

                    2 % 2 gives 0, indicating that the cell is alive
                    */
                    board[row][col] = 2;
                }
            }    
        }

        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                if(board[row][col] == 3) {
                    board[row][col] = 0;
                }
                else if(board[row][col] == 2){
                    board[row][col] = 1;
                }
            }
        }
    }
}