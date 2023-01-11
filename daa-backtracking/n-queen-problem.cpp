#include <iostream>
using namespace::std;

void visualizeSolution(int* queenColumnArr, int boardSize, int solutionNumber) {
    if(solutionNumber == 1) { // For spacing after the input line.
        printf("\n");
    }
    for(int queenRow = 0; queenRow < boardSize; queenRow++) {
        if(queenRow == 0) {
            printf("  ");
            for(int column = 0; column < boardSize; column++) {
                // Since limit of our function is theoretically 100 (0-99)
                printf(" %2d", column);
            }
            printf("\n");
        }

        // Since limit of our function is theoretically 100 (0-99)
        printf(" %2d ", queenRow);
        
        for(int queenColumn = 0; queenColumn < boardSize; queenColumn++) {
            if(queenColumnArr[queenRow] == queenColumn) {
                printf("Q  ");
            }
            else {
                printf("_  ");
            }
        }

        printf("\n");
    }

    for(int column = 0; column < boardSize + 1; column++) {
        printf("---");
    }
    printf("\n");
}

bool possibleToPlace(int queenRowNum, int potentialColumn, int boardSize, int* queenColumnArr) {

    /*
    //* 1st version of the for-loop (where are checking all the queens, even unplaced ones; which is unnecessary)
    for(int attackingQueenRow = 0; attackingQueenRow < boardSize; attackingQueenRow++) {

        //? No need to check for this since we haven't even placed this queen yet
        // if(attackingQueenRow == queenRowNum) { 
        //    continue; // A queen cannot attack itself!
        // }

        if(queenColumnArr[attackingQueenRow] != -1) { // Meaning the queen is placed
            // No need to check for row attack since each queen is being placed in different row.

            // Checking for column attack

            // Checking for principal diagonal attack

            // Checking for secondary diagonal attack
        }

    }
    */
    
    //* 2nd version of the for-loop
    // We know that only the queens of the previous rows have been placed till now, so we should check for attacks only from those queens.
    for(int attackingQueenRow = 0; attackingQueenRow < queenRowNum; attackingQueenRow++) { 
        
        //! We aren't checking for whether the queen is placed or NOT because we have only shortlisted the queens that have been placed.

        //? No need to check for row attack since each queen is being placed in different row.

        //? Checking for column attack
        if(queenColumnArr[attackingQueenRow] == potentialColumn) {
            return false;
        }

        //? Checking for principal diagonal attack
        if( (queenRowNum - attackingQueenRow) == (potentialColumn - queenColumnArr[attackingQueenRow]) ) {
            /*
            How this works:
            If suppose attacking queen is in row 1, column 2.

            And we wish to place the new queen of row 2, in column 3; which is attacked by the queen at (1, 2) along its PRINCIPAL DIAGONAL.

            So, 3-2 == 2-1; and false will be returned.
            */
            return false;
        }

        //? Checking for secondary diagonal attack
        if( (queenRowNum - attackingQueenRow) == (queenColumnArr[attackingQueenRow] - potentialColumn) ) {
            /*
            How this works:
            If suppose attacking queen is in row 2, column 3.

            And we wish to place the new queen of row 3, in column 2; which is attacked by the queen at (2, 3) along its SECONDARY DIAGONAL.

            So, (3-2) == (3-2); and false will be returned.
            */
            return false;
        }
    }

    // If control of the program was able to reach till here, it means the queen we are trying to place isn't attacked by any of the already placed queens.
    // So, we return `true` to signify that the queen can be placed here.
    return true; 
    
}

void placeQueen(int queenRowNum, int boardSize, int* queenColumnArr, int* solutionCt) {
    if(queenRowNum == boardSize) {
        // If all queens from 0 to (n-1) were successly placed, we know that the column array represents a solution to the N queen problem.
        
        ++*solutionCt;
        
        visualizeSolution(queenColumnArr, boardSize, *solutionCt);

        return; // Backtracking after finding a solution
    }

    for(int potentialColumn = 0; potentialColumn < boardSize; potentialColumn++) {
        if( possibleToPlace(queenRowNum, potentialColumn, boardSize, queenColumnArr) ) {
            

            // Placing the queen
            queenColumnArr[queenRowNum] = potentialColumn;
            
            // Recursive call for placing the queen in the next row.
            placeQueen(queenRowNum + 1, boardSize, queenColumnArr, solutionCt);

        }

        // If it isn't possible to place the current queen, we try to place the queen in the next column in the next iteration of this for-loop
    }

    // Unplacing the current queen before backtracking to avoid incorrect output of `possibleToPlace()` function; which would say that this queen (which in actuality is not placed) is attacking a certain cell, when in reality it can't attack any cell since it isn't placed.
    queenColumnArr[queenRowNum] = -1;
    return;
}

void nQueenProblem() {
    int n;
    cout << "Please enter a chessboard size less than 100 : ";
    cin >> n; 


    // A variable for keeping track of the number of solutions.
    int* solutionCt = new int;
    *solutionCt = 0;

    // The queen number represents the row in which it is placed.
    
    // The column number in which the queen is placed is stored in the array below
    int* queenColumnArray = new int[n];

    for(int queenNum = 0; queenNum < n; queenNum++) {
        queenColumnArray[queenNum] = -1; // Initializing unplaced queens with value -1.
    }

    placeQueen(0, n, queenColumnArray, solutionCt);
    if(*solutionCt == 0) {
        printf("There are NO possible solutions.\n");    
    }
    else {
        printf("There are %d possible solutions.\n", *solutionCt);
    }
}

int main() {
    nQueenProblem();

    return 0;
}